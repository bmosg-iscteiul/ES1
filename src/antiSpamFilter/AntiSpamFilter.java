package antiSpamFilter;

import antiSpamFilter.gui.GUI;

import javax.swing.*;

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
