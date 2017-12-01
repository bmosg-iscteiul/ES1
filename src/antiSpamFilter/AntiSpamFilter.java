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

public class AntiSpamFilter {

    // <editor-fold defaultstate="collapsed" desc="Singleton Code">
    private static AntiSpamFilter instance;
    public static AntiSpamFilter getInstance(){
        if(instance==null)
            instance = new AntiSpamFilter();
        return instance;
    }// </editor-fold>

    private GUI gui;
    private static final int INDEPENDENT_RUNS = 5 ;


    private AntiSpamFilter(){
        initGUI();
        showGUI();
        loadRules();
        System.out.println("Program Started");
    }

    /*------------------------------------------------ GUI Functions -------------------------------------------------*/

    private void initGUI() {
        gui = new GUI();
    }

    private void showGUI(){
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

    /*--------------------------------------------------- Getters ----------------------------------------------------*/

    public ArrayList<Rule> getRules(){
        if(gui.getMode()==GUI.Manual)
            return gui.getManualRules();
        if(gui.getMode()==GUI.Auto)
            return gui.getAutoRules();
        return null;
    }

    public GUI getGUI() {
        return gui;
    }

    /*------------------------------------------------------ IO ------------------------------------------------------*/

    public int countRules() {
        int count =0;
        try {
            BufferedReader rulesReader = new BufferedReader(new FileReader(gui.getRulesPath()));
            while (rulesReader.readLine()!=null){
                count++;
            }
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
            for (int i = 0; i <countRules(); i++) {
                String[] text = rulesReader.readLine().split("\t");
                if(text.length!=1) {
                    manual_rules.add(new Rule(text[0], Double.parseDouble(text[1])));
                    auto_rules.add(new Rule(text[0], Double.parseDouble(text[1])));
                }
                else {
                    manual_rules.add(new Rule(text[0], 0));
                    auto_rules.add(new Rule(text[0], 0));
                }
            }
            gui.setManualRules(manual_rules);
            gui.setAutoRules(auto_rules);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*--------------------------------------------------- Evaluate ---------------------------------------------------*/
    private double evaluateRuleWeight(String[] ruleList, ArrayList<Rule> weightedRules) {
        double val = 0.0;
        for(int i = 1; i < ruleList.length; i++) {
            String rule = ruleList[i];
            for(Rule r : weightedRules) {
                if(r.getRule().equals(rule)) {
                    val += r.getWeight();
                    break;
                }
            }
        }
        return val;
    }

    public int evaluateHam(ArrayList<Rule> weightedRules) {
        int fp = 0;
        try {
            BufferedReader hamList = new BufferedReader(new FileReader(gui.getHamsPath()));
            String current_line;
            while((current_line = hamList.readLine()) != null) {
                String[] split_line = current_line.split("\t");
                double current_value = evaluateRuleWeight(split_line, weightedRules);
                if(current_value >= 5)
                    fp++;
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return fp;
    }

    public int evaluateSpam(ArrayList<Rule> weightedRules) {
        int fn = 0;
        try {
            BufferedReader hamList = new BufferedReader(new FileReader(gui.getSpamPath()));
            String current_line;
            while((current_line = hamList.readLine()) != null) {
                String[] split_line = current_line.split("\t");
                double current_value = evaluateRuleWeight(split_line, weightedRules);
                if(current_value < 5)
                    fn++;
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return fn;
    }


    /*--------------------------------------------------- Run Modes --------------------------------------------------*/

    public void runManual() {
        gui.setFP(evaluateHam(gui.getManualRules()));
        gui.setFN(evaluateSpam(gui.getManualRules()));
    }

    public void runAuto() {
        AntiSpamFilterAutomaticConfiguration.runAutomatic();
    }




    /*----------------------------------------------------- Main -----------------------------------------------------*/

    public static void main(String[] args){
        AntiSpamFilter.getInstance();
    }



    static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(
            List<ExperimentProblem<DoubleSolution>> problemList) {
        List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();

        for (int i = 0; i < problemList.size(); i++) {
            Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
                    problemList.get(i).getProblem(),
                    new SBXCrossover(1.0, 5),
                    new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
                    .setMaxEvaluations(25000)
                    .setPopulationSize(100)
                    .build();
            algorithms.add(new ExperimentAlgorithm<>(algorithm, "NSGAII", problemList.get(i).getTag()));
        }

        return algorithms;
    }

}
