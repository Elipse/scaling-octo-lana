/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.apache.commons.configuration.CompositeConfiguration;
import util.Configuration;
import util.Util;

/**
 *
 * @author elialva
 */
class LogView extends javax.swing.JFrame implements PropertyChangeListener {

    private final CompositeConfiguration CONFIGURATION = Configuration.getCONFIGURATION();
    //
    private LogModel model;
    private LogVSettings logVSettings;

    public void setModel(LogModel model) {
        if (this.model != null) {
            return;
        }
        this.model = model;
    }
    private LogController controller;

    public void setController(LogController controller) {
        this.controller = controller;
    }
    //

    LogView() {
        nimbus();
        initComponents();
        //
        Util.activateFunctionKey(LogView.this, new LogView.FormAction(), "ESCAPE");
        Util.activateFunctionKey(LogView.this, new LogView.FormAction(), "F2");
        Util.activateFunctionKey(LogView.this, new LogView.FormAction(), "F3");
        Util.activateFunctionKey(LogView.this, new LogView.FormAction(), "F9");
        //
        String iconDir = CONFIGURATION.getString("icon.dir");
        System.out.println("Nomep " + iconDir + CONFIGURATION.getString("icon.activate"));
        jButton1.setIcon(new ImageIcon(iconDir + CONFIGURATION.getString("icon.settings")));
        jToggleButton1.setIcon(new ImageIcon(iconDir + CONFIGURATION.getString("icon.deactivate")));
        jToggleButton1.setSelectedIcon(new ImageIcon(iconDir + CONFIGURATION.getString("icon.activate")));
        //
        this.model = null;
        this.controller = null;
        //
        logVSettings = new LogVSettings();
        logVSettings.addPropertyChangeListener(LogView.this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "logCount":
                //Process statics map
                Integer count = (Integer) evt.getNewValue();
                if (count == null) {
                    jLabel5.setText("Conteo estadístico inexistente.");
                    jToggleButton1.setEnabled(false);
                    return;
                }
                //Update labels' text
                jLabel2.setText(count + "");
                jLabel4.setText(Integer.valueOf(jLabel4.getText()) + count + "");
                break;
            case "isLogGenerated":
                controller.toggleLog((boolean) evt.getNewValue());
                break;
            case "maxSize":
                controller.setMaxSize((int) evt.getNewValue());
                break;
            case "logPath":
                controller.setLogPath((String) evt.getNewValue());
                break;
            default:
                break;
        }
    }

    void quit() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }

    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    private void nimbus() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }// </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Procesamiento Bitácoras");

        jLabel1.setText("Parcial Registros Procesados:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("0");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Total Registros Procesados:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("0");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("                      ");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jToolBar1.setRollover(true);

        jButton1.setText("F2");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jToggleButton1.setText("F9");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jToggleButton1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    void showSettings() {
        logVSettings.setLocationRelativeTo(null);
        logVSettings.setVisible(true);
    }

    private class FormAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            String functionKey = (String) getValue(Action.NAME);
            switch (functionKey) {
                case "ESCAPE":
                    break;
                case "F2":
                    controller.showSettings();
                    break;
                case "F3":
                    controller.quit();
                    break;
                case "F9":
                    jToggleButton1.doClick();
                    controller.toggleProcess();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
