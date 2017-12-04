package antiSpamFilter;

import antiSpamFilter.gui.GUI;
import antiSpamFilter.utils.Rule;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class that starts and loads the antiSpam program
 *
 * @author Carlos Rafael Fernandes
 * @author André Sousa
 * @author Bruno Gama
 * @author Rui farinha
 */

public class AntiSpamFilter {

    // <editor-fold defaultstate="collapsed" desc="Singleton Code">
    private static AntiSpamFilter instance;

    public static AntiSpamFilter getInstance() {
        if (instance == null)
            instance = new AntiSpamFilter();
        return instance;
    }// </editor-fold>

    private GUI gui;
    private static final int INDEPENDENT_RUNS = 5;

    /**
     * Class Constructor
     */

    private AntiSpamFilter() {
        initGUI();
        showGUI();
        loadRules();
        System.out.println("Program Started");
    }

    /**
     * GUI Functions
     */

    private void initGUI() {
        gui = new GUI();
    }

    private void showGUI() {
        //<editor-fold defaultstate="collapsed" desc="Swing look and feel setting code">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //Show GUI (Thread Safe)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
                SwingUtilities.updateComponentTreeUI(gui);
            }
        });
    }

    /**
     * GUI getter
     * @return gui
     */
    
    
    public GUI getGUI() {
        return gui;
    }

    /**
     * IO
     * <p>
     * Rules
     * load rules from files ham and spam and
     * counts how many rules there are in that file
     */

    public int countRules() {
        int count = 0;
        try {
            BufferedReader rulesReader = new BufferedReader(new FileReader(gui.getRulesPath()));
            while (rulesReader.readLine() != null) {
                count++;
            }
            rulesReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void loadRules() {
        ArrayList<Rule> manual_rules = new ArrayList<>();
        ArrayList<Rule> auto_rules = new ArrayList<>();
        try {
            BufferedReader rulesReader = new BufferedReader(new FileReader(gui.getRulesPath()));
            for (int i = 0; i < countRules(); i++) {
                String[] text = rulesReader.readLine().split("\t");
                if (text.length != 1) {
                    manual_rules.add(new Rule(text[0], Double.parseDouble(text[1])));
                    auto_rules.add(new Rule(text[0], Double.parseDouble(text[1])));
                } else {
                    manual_rules.add(new Rule(text[0], 0));
                    auto_rules.add(new Rule(text[0], 0));
                }
            }
            rulesReader.close();
            gui.setManualRules(manual_rules);
            gui.setAutoRules(auto_rules);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks by the number of rules existing if they are weight distributed
     */

    private double evaluateRuleWeight(String[] ruleList, ArrayList<Rule> weightedRules) {
        double val = 0.0;
        for (int i = 1; i < ruleList.length; i++) {
            String rule = ruleList[i];
            for (Rule r : weightedRules) {
                if (r.getRule().equals(rule)) {
                    val += r.getWeight();
                    break;
                }
            }
        }
        return val;
    }

    /**
     * Evaluates the loaded ham rules and gives them a distributed weight
     */

    public int evaluateHam(ArrayList<Rule> weightedRules) {
        int fp = 0;
        try {
            BufferedReader hamList = new BufferedReader(new FileReader(gui.getHamsPath()));
            String current_line;
            while ((current_line = hamList.readLine()) != null) {
                String[] split_line = current_line.split("\t");
                double current_value = evaluateRuleWeight(split_line, weightedRules);
                if (current_value >= 5)
                    fp++;
            }
            hamList.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fp;
    }

    /**
     * Evaluates the loaded spam rules and gives them a distributed weight
     */

    public int evaluateSpam(ArrayList<Rule> weightedRules) {
        int fn = 0;
        try {
            BufferedReader spamList = new BufferedReader(new FileReader(gui.getSpamPath()));
            String current_line;
            while ((current_line = spamList.readLine()) != null) {
                String[] split_line = current_line.split("\t");
                double current_value = evaluateRuleWeight(split_line, weightedRules);
                if (current_value < 5)
                    fn++;
            }
            spamList.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fn;
    }

    /**
     * Checks if the Weights are well distributed
     */

    private double[] checkSolutions() {
        double[] bestSolutions = new double[gui.getAutoRules().size()];
        int bestValues = -1;
        try {
            BufferedReader bestHV = new BufferedReader(new FileReader("experimentBaseDirectory\\AntiSpamStudy\\data\\NSGAII\\AntiSpamFilterProblem\\BEST_HV_FUN.tsv"));
            double best_fp = -1, best_fn = -1;
            String line;
            int i = 0;
            while ((line = bestHV.readLine()) != null) {
                double fp = Double.parseDouble(line.split(" ")[0]), fn = Double.parseDouble(line.split(" ")[1]);
                if (bestValues == -1) {
                    bestValues = 0;
                    best_fp = fp;
                    best_fn = fn;
                } else if (fp < best_fp && fn > best_fn) {
                    bestValues = i;
                    best_fp = fp;
                    best_fn = fn;
                }
                i++;
            }
            bestHV.close();
            BufferedReader bestWeights = new BufferedReader(new FileReader("experimentBaseDirectory\\AntiSpamStudy\\data\\NSGAII\\AntiSpamFilterProblem\\BEST_HV_VAR.tsv"));
            int l = 0;
            line = bestWeights.readLine();
            while (l != bestValues) {
                line = bestWeights.readLine();
                l++;
            }
            String[] weights = line.split(" ");
            for (int j = 0; j < bestSolutions.length; j++) {
                bestSolutions[j] = Double.parseDouble(weights[j]);
            }
            bestWeights.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bestSolutions;
    }

    public void saveResults(boolean isAuto) {
        try {
            BufferedWriter rulesFile = new BufferedWriter(new FileWriter(gui.getRulesPath(), false));
            ArrayList<Rule> rules;
            if (isAuto)
                rules = gui.getAutoRules();
            else
                rules = gui.getManualRules();
            for (Rule r : rules) {
                rulesFile.write(r.getRule() + "\t" + r.getWeight() + "\n");
                rulesFile.flush();
            }
            rulesFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Running Modes
     * <p>
     * Manual & Automatic
     */

    public void runManual() {
        gui.setFP(evaluateHam(gui.getManualRules()));
        gui.setFN(evaluateSpam(gui.getManualRules()));
    }

    public void runAuto() {
        AntiSpamFilterAutomaticConfiguration.runAutomatic(gui.getIndependentRuns());
        double[] bestWeights = checkSolutions();
        ArrayList<Rule> autoRules = gui.getAutoRules();
        for (int i = 0; i < bestWeights.length; i++) {
            autoRules.get(i).setWeight(bestWeights[i]);
        }
        gui.setAutoRules(autoRules);
        gui.setFP(evaluateHam(gui.getAutoRules()));
        gui.setFN(evaluateSpam(gui.getAutoRules()));
    }

    /**
     * Main Function calls AntiSpamFilter Instance
     */

    public static void main(String[] args) {
        AntiSpamFilter.getInstance();
    }

}
