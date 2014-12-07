/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import org.apache.commons.configuration.CompositeConfiguration;
import util.Configuration;
import util.JFileChooserXeam;
import util.Util;

/**
 *
 * @author elialva
 */
public class LogVSettings extends javax.swing.JDialog {

    private JFileChooserXeam fileChooser;
    private final CompositeConfiguration CONFIGURATION = Configuration.getCONFIGURATION();
    private String oldLogPath;
    private boolean oldIsSelected;
    private int oldFileSize;

    /**
     * Creates new form LogVSettings
     *
     */
    public LogVSettings() {
        super((Frame) null, true);
        initComponents();
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //TODO Una factory & un mapa "Fn" vs "Command"
        Util.activateFunctionKey(LogVSettings.this, new FormAction("ESCAPE"));
        Util.activateFunctionKey(LogVSettings.this, new FormAction("F2"));
        Util.activateFunctionKey(LogVSettings.this, new FormAction("F3"));
        Util.activateFunctionKey(LogVSettings.this, new FormAction("F9"));
        Util.activateFunctionKey(LogVSettings.this, new FormAction("K"));
        //
        String iconDir = CONFIGURATION.getString("icon.dir");
        jButton2.setIcon(new ImageIcon(iconDir + CONFIGURATION.getString("icon.fileChooser")));
        jButton3.setIcon(new ImageIcon(iconDir + CONFIGURATION.getString("icon.switch")));
        //
        jCheckBox1.setSelected(oldIsSelected = CONFIGURATION.getBoolean("logGenerated"));
        //
        jSpinner1.setValue(oldFileSize = CONFIGURATION.getInt("logMaxSize"));
        //
        jTextArea1.setText(oldLogPath = CONFIGURATION.getString("logPath"));
        //
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) jSpinner1.getEditor();
        editor.getTextField().grabFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSpinner1.setValue(20);

        jLabel1.setText("Max size for log file (KB):");

        jScrollPane1.setBorder(null);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("F2");
        jButton2.setFocusable(false);
        jButton2.setMaximumSize(new java.awt.Dimension(32, 64));
        jButton2.setMinimumSize(new java.awt.Dimension(32, 64));
        jButton2.setPreferredSize(new java.awt.Dimension(32, 64));

        jButton3.setText("F9");
        jButton3.setFocusable(false);
        jButton3.setMaximumSize(new java.awt.Dimension(32, 64));
        jButton3.setMinimumSize(new java.awt.Dimension(32, 64));
        jButton3.setPreferredSize(new java.awt.Dimension(32, 64));

        jCheckBox1.setText("Generates log");
        jCheckBox1.setFocusable(false);

        jLabel2.setText("Log file path");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private class FormAction extends AbstractAction {

        FormAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String functionKey = (String) getValue(Action.NAME);
            switch (functionKey) {
                case "ESCAPE":
                    jCheckBox1.setSelected(oldIsSelected);
                    jTextArea1.setText(oldLogPath);
                    jSpinner1.setValue(oldFileSize);
                    dispatchEvent(new WindowEvent(LogVSettings.this, WindowEvent.WINDOW_CLOSING));
                    break;
                case "F2":
                    fileChooser = new JFileChooserXeam();
                    fileChooser.setSelectedFile(new File(jTextArea1.getText()));
                    int returnVal = fileChooser.showOpenDialog(LogVSettings.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        jTextArea1.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                    break;
                case "F3":
                    LogVSettings.this.firePropertyChange("isLogGenerated", oldIsSelected, jCheckBox1.isSelected());
                    try {
                        jSpinner1.commitEdit();
                    } catch (ParseException ex) {
                        Logger.getLogger(LogVSettings.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    LogVSettings.this.firePropertyChange("maxSize", oldFileSize, jSpinner1.getValue());
                    LogVSettings.this.firePropertyChange("logPath", oldLogPath, jTextArea1.getText());
                    oldIsSelected = jCheckBox1.isSelected();
                    oldFileSize = (int) jSpinner1.getValue();
                    oldLogPath = jTextArea1.getText();
                    dispatchEvent(new WindowEvent(LogVSettings.this, WindowEvent.WINDOW_CLOSING));
                    break;
                case "F9":
                    jCheckBox1.setSelected(!jCheckBox1.isSelected());
                    break;
                case "K":
                    System.out.println("What a...!!!");
                    break;
                default:
                    System.out.println("ttt " + getValue(Action.NAME));
                    throw new AssertionError();
            }
        }
    }
}
