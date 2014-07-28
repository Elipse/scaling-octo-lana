/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog;

import javax.swing.SwingWorker;

/**
 *
 * @author elialva
 */
public class NewClass {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
           Tipo t = new Tipo();
           t.execute();
        }
        Thread.sleep(200000);
    }

   static class Tipo extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            System.out.println(" " + Thread.currentThread() + "-:yo:-" + this);
            Thread.sleep(2000);
            return null;
        }
    }
}
