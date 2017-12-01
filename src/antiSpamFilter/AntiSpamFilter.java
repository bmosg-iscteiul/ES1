package antiSpamFilter;

import antiSpamFilter.gui.GUI;
import antiSpamFilter.utils.Rule;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AntiSpamFilter {

    // <editor-fold defaultstate="collapsed" desc="Singleton Code">
    private static AntiSpamFilter instance;
    public static AntiSpamFilter getInstance(){
        if(instance==null)
            instance = new AntiSpamFilter();
        return instance;
    }// </editor-fold>

    private GUI gui;

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

    /*--------------------------------------------------- Run Modes --------------------------------------------------*/

    public void runManual() {
        //TODO
    }

    public void runAuto() {
        //TODO
    }



    /*----------------------------------------------------- Main -----------------------------------------------------*/

    public static void main(String[] args){
        AntiSpamFilter.getInstance();
    }


}
