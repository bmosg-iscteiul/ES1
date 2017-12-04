/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antiSpamFilter.gui;

import antiSpamFilter.AntiSpamFilter;
import antiSpamFilter.utils.Rule;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * This is our take on a simple Graphical User Interface
 * This layout was designed and adjusted at the start of the project via group meetings
 *
 * @author Carlos Rafael Fernandes
 *
 */
public class GUI extends javax.swing.JFrame {

    /**
     * @
     */
    public GUI() {
        initComponents();

        initConsole();

        setLocationRelativeTo(null);
        setResizable(false);

//        HamPath.setText(System.getProperty("user.dir")+"\\AntiSpamConfigurationForProfessionalMailbox\\ham.log");
//        SpamPath.setText(System.getProperty("user.dir")+"\\AntiSpamConfigurationForProfessionalMailbox\\spam.log");
        RulesPath.setText(System.getProperty("user.dir")+"\\AntiSpamConfigurationForProfessionalMailbox\\rules.cf");
        OutputPath.setText(System.getProperty("user.dir")+"\\experimentBaseDirectory");
        TableManual.getTableHeader().setReorderingAllowed(false);
        TableAuto.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModeRadioGroup = new javax.swing.ButtonGroup();
        Start = new javax.swing.JToggleButton();
        ManualRadio = new javax.swing.JRadioButton();
        AutoRadio = new javax.swing.JRadioButton();
        TabbedPane = new javax.swing.JTabbedPane();
        ManualMode = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableManual = new javax.swing.JTable();
        ManualSave = new javax.swing.JButton();
        GenerateRandom = new javax.swing.JButton();
        AutoMode = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableAuto = new javax.swing.JTable();
        AutoSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        IndependentRuns = new javax.swing.JTextField();
        ConsolePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Console = new javax.swing.JTextArea();
        PathPanel = new javax.swing.JPanel();
        InputPanel = new javax.swing.JPanel();
        ChangeSpamPath = new javax.swing.JButton();
        ChangeHamPath = new javax.swing.JButton();
        ChangeRulesPath = new javax.swing.JButton();
        RulesPath = new javax.swing.JTextField();
        HamPath = new javax.swing.JTextField();
        SpamPath = new javax.swing.JTextField();
        SpamJLabel = new javax.swing.JLabel();
        HamJLabel = new javax.swing.JLabel();
        RulesJLabel = new javax.swing.JLabel();
        OutputPanel = new javax.swing.JPanel();
        OutputPath = new javax.swing.JTextField();
        ChangeOutputPath = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        FP = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        FN = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anti Spam Filter");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        ModeRadioGroup.add(ManualRadio);
        ManualRadio.setText("Manual");

        ModeRadioGroup.add(AutoRadio);
        AutoRadio.setText("Auto");

        TableManual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rules", "Weight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableManual.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(TableManual);

        ManualSave.setText("Save Config");
        ManualSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManualSaveActionPerformed(evt);
            }
        });

        GenerateRandom.setText("Generate Ramdom Configuration");
        GenerateRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateRandomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ManualModeLayout = new javax.swing.GroupLayout(ManualMode);
        ManualMode.setLayout(ManualModeLayout);
        ManualModeLayout.setHorizontalGroup(
            ManualModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManualModeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ManualModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManualModeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(GenerateRandom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ManualSave)))
                .addContainerGap())
        );
        ManualModeLayout.setVerticalGroup(
            ManualModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManualModeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ManualSave)
                    .addComponent(GenerateRandom))
                .addContainerGap())
        );

        TabbedPane.addTab("Manual Mode", ManualMode);

        TableAuto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rules", "Weight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableAuto.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane4.setViewportView(TableAuto);

        AutoSave.setText("Save Config");
        AutoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutoSaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Independent Runs:");

        IndependentRuns.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IndependentRuns.setText("5");

        javax.swing.GroupLayout AutoModeLayout = new javax.swing.GroupLayout(AutoMode);
        AutoMode.setLayout(AutoModeLayout);
        AutoModeLayout.setHorizontalGroup(
            AutoModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoModeLayout.createSequentialGroup()
                .addContainerGap(551, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IndependentRuns, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AutoSave)
                .addContainerGap())
            .addGroup(AutoModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AutoModeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4)
                    .addContainerGap()))
        );
        AutoModeLayout.setVerticalGroup(
            AutoModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AutoModeLayout.createSequentialGroup()
                .addContainerGap(454, Short.MAX_VALUE)
                .addGroup(AutoModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AutoSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(IndependentRuns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(AutoModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AutoModeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addGap(40, 40, 40)))
        );

        TabbedPane.addTab("Auto Mode", AutoMode);

        Console.setEditable(false);
        Console.setColumns(20);
        Console.setRows(5);
        jScrollPane1.setViewportView(Console);

        javax.swing.GroupLayout ConsolePanelLayout = new javax.swing.GroupLayout(ConsolePanel);
        ConsolePanel.setLayout(ConsolePanelLayout);
        ConsolePanelLayout.setHorizontalGroup(
            ConsolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsolePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        ConsolePanelLayout.setVerticalGroup(
            ConsolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConsolePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabbedPane.addTab("Console", ConsolePanel);

        InputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Files"));

        ChangeSpamPath.setText("...");
        ChangeSpamPath.setMaximumSize(new java.awt.Dimension(45, 20));
        ChangeSpamPath.setMinimumSize(new java.awt.Dimension(45, 20));
        ChangeSpamPath.setPreferredSize(new java.awt.Dimension(45, 20));
        ChangeSpamPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeSpamPathActionPerformed(evt);
            }
        });

        ChangeHamPath.setText("...");
        ChangeHamPath.setMaximumSize(new java.awt.Dimension(45, 20));
        ChangeHamPath.setMinimumSize(new java.awt.Dimension(45, 20));
        ChangeHamPath.setPreferredSize(new java.awt.Dimension(45, 20));
        ChangeHamPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeHamPathActionPerformed(evt);
            }
        });

        ChangeRulesPath.setText("...");
        ChangeRulesPath.setMaximumSize(new java.awt.Dimension(45, 20));
        ChangeRulesPath.setMinimumSize(new java.awt.Dimension(45, 20));
        ChangeRulesPath.setPreferredSize(new java.awt.Dimension(45, 20));
        ChangeRulesPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeRulesPathActionPerformed(evt);
            }
        });

        RulesPath.setEditable(false);

        HamPath.setEditable(false);

        SpamPath.setEditable(false);

        SpamJLabel.setText("spam.log");

        HamJLabel.setText("ham.log");

        RulesJLabel.setText("rules.cf");

        javax.swing.GroupLayout InputPanelLayout = new javax.swing.GroupLayout(InputPanel);
        InputPanel.setLayout(InputPanelLayout);
        InputPanelLayout.setHorizontalGroup(
            InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RulesJLabel)
                    .addComponent(SpamJLabel)
                    .addComponent(HamJLabel))
                .addGap(18, 18, 18)
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RulesPath, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                    .addComponent(HamPath)
                    .addComponent(SpamPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChangeRulesPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeHamPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeSpamPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InputPanelLayout.setVerticalGroup(
            InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RulesJLabel)
                    .addComponent(RulesPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeRulesPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HamJLabel)
                    .addComponent(HamPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeHamPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpamJLabel)
                    .addComponent(SpamPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeSpamPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OutputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Files"));

        OutputPath.setEditable(false);

        ChangeOutputPath.setText("...");
        ChangeOutputPath.setMaximumSize(new java.awt.Dimension(45, 20));
        ChangeOutputPath.setMinimumSize(new java.awt.Dimension(45, 20));
        ChangeOutputPath.setPreferredSize(new java.awt.Dimension(45, 20));
        ChangeOutputPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeOutputPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OutputPanelLayout = new javax.swing.GroupLayout(OutputPanel);
        OutputPanel.setLayout(OutputPanelLayout);
        OutputPanelLayout.setHorizontalGroup(
            OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OutputPanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(OutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ChangeOutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(388, 388, 388))
        );
        OutputPanelLayout.setVerticalGroup(
            OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OutputPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeOutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout PathPanelLayout = new javax.swing.GroupLayout(PathPanel);
        PathPanel.setLayout(PathPanelLayout);
        PathPanelLayout.setHorizontalGroup(
            PathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 775, Short.MAX_VALUE)
            .addComponent(OutputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 775, Short.MAX_VALUE)
        );
        PathPanelLayout.setVerticalGroup(
            PathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PathPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OutputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Path Config", PathPanel);

        TabbedPane.setSelectedIndex(3);

        jLabel2.setText("FN:");

        FP.setText("0");

        jLabel5.setText("FP");

        FN.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ManualRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AutoRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FP)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FN)
                        .addGap(18, 18, 18)
                        .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Start)
                    .addComponent(ManualRadio)
                    .addComponent(AutoRadio)
                    .addComponent(FN)
                    .addComponent(jLabel2)
                    .addComponent(FP)
                    .addComponent(jLabel5))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChangeRulesPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeRulesPathActionPerformed
        JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CF Files", "cf");
        path_chooser.setFileFilter(filter);
        path_chooser.setDialogTitle("Choose Rules File");
        int returnVal = path_chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            RulesPath.setText(path_chooser.getSelectedFile().getPath());
            AntiSpamFilter.getInstance().loadRules();
        }
    }//GEN-LAST:event_ChangeRulesPathActionPerformed

    private void ChangeHamPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeHamPathActionPerformed
        JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("LOG File", "log");
        path_chooser.setFileFilter(filter);
        path_chooser.setDialogTitle("Choose File");
        int returnVal = path_chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            HamPath.setText(path_chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_ChangeHamPathActionPerformed

    private void ChangeSpamPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeSpamPathActionPerformed
        JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("LOG File", "log");
        path_chooser.setFileFilter(filter);
        path_chooser.setDialogTitle("Choose File");
        int returnVal = path_chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            SpamPath.setText(path_chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_ChangeSpamPathActionPerformed

    private void ChangeOutputPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeOutputPathActionPerformed
        JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
        path_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        path_chooser.setDialogTitle("Choose Output Dir");
        int returnVal = path_chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            OutputPath.setText(path_chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_ChangeOutputPathActionPerformed

    private void GenerateRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateRandomActionPerformed
        ArrayList<Rule> auto_rules = new ArrayList<>();
        for(int i=0; i<TableManual.getModel().getRowCount(); i++){
            TableManual.getModel().setValueAt(String.valueOf((Math.random()*10)-5) ,i ,1);
        }
    }//GEN-LAST:event_GenerateRandomActionPerformed

    private void AutoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutoSaveActionPerformed
        AntiSpamFilter.getInstance().saveResults(true);
    }//GEN-LAST:event_AutoSaveActionPerformed

    private void ManualSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManualSaveActionPerformed
        AntiSpamFilter.getInstance().saveResults(false);
    }//GEN-LAST:event_ManualSaveActionPerformed

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        if(ManualRadio.isSelected() || AutoRadio.isSelected()) {
            if (AutoRadio.isSelected()) {
                TabbedPane.setSelectedIndex(2);
                System.out.println("Running NSGAII Algorithm");
                repaint();
                EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        AntiSpamFilter.getInstance().runAuto();
                        EventQueue.invokeLater(new Runnable(){
                            @Override
                            public void run() {
                                System.out.println("NSGAII Algorithm Complete");
                                TabbedPane.setSelectedIndex(1);
                                repaint();
                            }
                        });
                    }
                });
            }
            else {
                TabbedPane.setSelectedIndex(0);
                repaint();
                EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        AntiSpamFilter.getInstance().runManual();
                    }
                });

            }
        }
        else{
            int VIBRATION_LENGTH = 10;
            int VIBRATION_VELOCITY = 2;
            try {
                Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
                if (runnable != null) runnable.run();
                int originalX = this.getLocationOnScreen().x;
                int originalY = this.getLocationOnScreen().y;
                for(int i = 0; i < VIBRATION_LENGTH; i++) {
                    Thread.sleep(10);
                    this.setLocation(originalX + VIBRATION_VELOCITY, originalY);
                    Thread.sleep(10);
                    this.setLocation(originalX - VIBRATION_VELOCITY, originalY);
                    Thread.sleep(10);
                    this.setLocation(originalX, originalY);
                }
            }
            catch (Exception err) {
                err.printStackTrace();
            }
        }
    }//GEN-LAST:event_StartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AutoMode;
    private javax.swing.JRadioButton AutoRadio;
    private javax.swing.JButton AutoSave;
    private javax.swing.JButton ChangeHamPath;
    private javax.swing.JButton ChangeOutputPath;
    private javax.swing.JButton ChangeRulesPath;
    private javax.swing.JButton ChangeSpamPath;
    private javax.swing.JTextArea Console;
    private javax.swing.JPanel ConsolePanel;
    private javax.swing.JLabel FN;
    private javax.swing.JLabel FP;
    private javax.swing.JButton GenerateRandom;
    private javax.swing.JLabel HamJLabel;
    private javax.swing.JTextField HamPath;
    private javax.swing.JTextField IndependentRuns;
    private javax.swing.JPanel InputPanel;
    private javax.swing.JPanel ManualMode;
    private javax.swing.JRadioButton ManualRadio;
    private javax.swing.JButton ManualSave;
    private javax.swing.ButtonGroup ModeRadioGroup;
    private javax.swing.JPanel OutputPanel;
    private javax.swing.JTextField OutputPath;
    private javax.swing.JPanel PathPanel;
    private javax.swing.JLabel RulesJLabel;
    private javax.swing.JTextField RulesPath;
    private javax.swing.JLabel SpamJLabel;
    private javax.swing.JTextField SpamPath;
    private javax.swing.JToggleButton Start;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JTable TableAuto;
    private javax.swing.JTable TableManual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

    public static final int Manual =1;
    public static final int Auto =2;

    public int getMode(){
        if(AutoRadio.isSelected())
            return 2;
        if(ManualRadio.isSelected())
            return 1;
        return 0;
    }

    public String getRulesPath(){
        return RulesPath.getText();
    }

    public String getHamsPath(){
        return HamPath.getText();
    }

    public String getSpamPath(){
        return SpamPath.getText();
    }

    public int getIndependentRuns(){
        return Integer.parseInt(IndependentRuns.getText());
    }

    public void setFP(int i){
        FP.setText(i+"");
    }

    public void setFN(int i){
        FN.setText(i+"");
    }

    public void setManualRules(ArrayList<Rule> rules){
        String rules_string [][] = new String [rules.size()][2];
        for(int i=0; i<rules.size(); i++) {
            rules_string [i][0] = rules.get(i).getRule();
            rules_string [i][1] = String.valueOf(rules.get(i).getWeight());
        }
        TableManual.setModel(new javax.swing.table.DefaultTableModel(rules_string,new String [] {"Rules", "Weight"}));
    }

    public void setAutoRules(ArrayList<Rule> rules){
        String rules_string [][] = new String [rules.size()][2];
        for(int i=0; i<rules.size(); i++) {
            rules_string [i][0] = rules.get(i).getRule();
            rules_string [i][1] = String.valueOf(rules.get(i).getWeight());
        }
        TableAuto.setModel(new javax.swing.table.DefaultTableModel(rules_string,new String [] {"Rules", "Weight"}));
    }

    public ArrayList<Rule> getManualRules(){
        ArrayList<Rule> auto_rules = new ArrayList<>();
        for(int i=0; i<TableManual.getModel().getRowCount(); i++){
            String rule = (String)TableManual.getModel().getValueAt(i,0);
            double weight = Double.parseDouble((String)TableManual.getModel().getValueAt(i,1));
            auto_rules.add(new Rule(rule,weight));
        }
        return auto_rules;
    }

    public ArrayList<Rule> getAutoRules(){
        ArrayList<Rule> auto_rules = new ArrayList<>();
        for(int i=0; i<TableAuto.getModel().getRowCount(); i++){
            String rule = (String)TableAuto.getModel().getValueAt(i,0);
            double weight = Double.parseDouble((String)TableAuto.getModel().getValueAt(i,1));
            auto_rules.add(new Rule(rule,weight));
        }
        return auto_rules;
    }


    private void initConsole() {
        PrintStream console = new PrintStream(new OutputStream() {
            public void write(int arg0) throws IOException {
                char a = (char) arg0;
                printSimple(a + "");
            }
        });

        System.setOut(console);
        System.setErr(console);
    }

    public void printSimple(String s) {
        Console.append(s);
        Console.setCaretPosition(Console.getDocument().getLength());
    }

}
