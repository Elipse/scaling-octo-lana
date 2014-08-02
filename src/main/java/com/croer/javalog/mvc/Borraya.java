/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.javalog.mvc;

import javax.swing.JTable;
import org.apache.commons.configuration.CompositeConfiguration;

/**
 *
 * @author elialva
 */
public class Borraya {
    
    private final CompositeConfiguration cc = new CompositeConfiguration();
    
    public static void main(String[] args) {
        Borraya b = new Borraya();
        String string = b.cc.getString("hello");
        JTable t = null;
        t.setModel(null);
        System.out.println("Mixop " + string);
    }
    
}
