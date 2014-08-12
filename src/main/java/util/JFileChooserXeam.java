/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileView;

/**
 *
 * @author elialva
 */
public class JFileChooserXeam extends JFileChooser {

    @Override
    protected JDialog createDialog(Component parent) throws HeadlessException {
        JDialog d = super.createDialog(parent);
        d.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "save&exit");
        d.getRootPane().getActionMap().put("save&exit", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                JButton b = getUI().getDefaultButton(JFileChooserXeam.this);
                b.doClick();
//                System.out.println("Gepop EAM " + action);
//                JFileChooserXeam.this.approveSelection();
            }
        });
        return d;
    }

}
