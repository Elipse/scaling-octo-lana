/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.croer.javalog.mvc.LogVSettings;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author elialva
 */
public class Util {

    public static void activateFunctionKey(JDialog dialog, AbstractAction formAction, String keyStroke) {
        JRootPane rootPane = dialog.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), keyStroke);
        formAction.putValue(Action.NAME, keyStroke);
        rootPane.getActionMap().put(keyStroke, formAction);
    }
    
    public static void activateFunctionKey(JFrame frame, AbstractAction formAction, String keyStroke) {
        JRootPane rootPane = frame.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), keyStroke);
        formAction.putValue(Action.NAME, keyStroke);
        rootPane.getActionMap().put(keyStroke, formAction);
    }
}
