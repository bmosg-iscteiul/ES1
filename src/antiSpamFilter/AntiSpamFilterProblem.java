package antiSpamFilter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import antiSpamFilter.gui.GUI;
import antiSpamFilter.utils.Rule;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

    public AntiSpamFilterProblem() {
        // 10 variables (anti-spam filter rules) by default
        this(335);
    }

    public AntiSpamFilterProblem(Integer numberOfVariables) {
        setNumberOfVariables(numberOfVariables);
        setNumberOfObjectives(2);
        setName("AntiSpamFilterProblem");

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

        for (int i = 0; i < getNumberOfVariables(); i++) {
            lowerLimit.add(-5.0);
            upperLimit.add(5.0);
        }

        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
    }

    public void evaluate(DoubleSolution solution) {
        double aux, xi, xj;
        double[] fx = new double[getNumberOfObjectives()];
        double[] x = new double[getNumberOfVariables()];
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            x[i] = solution.getVariableValue(i);
        }
        ArrayList<Rule> weightedRules = new ArrayList<>();
        try {
            BufferedReader rulesList = new BufferedReader(new FileReader(AntiSpamFilter.getInstance().getGUI().getRulesPath()));
            String line;
            for(int i = 0; i < x.length; i++) {
                line = rulesList.readLine();
				String[] split_rule = line.split("\t");
				weightedRules.add(new Rule(split_rule[0], x[i]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        fx[0] = AntiSpamFilter.getInstance().evaluateHam(weightedRules);
        fx[1] = AntiSpamFilter.getInstance().evaluateSpam(weightedRules);


        solution.setObjective(0, fx[0]);
        solution.setObjective(1, fx[1]);
    }
}
