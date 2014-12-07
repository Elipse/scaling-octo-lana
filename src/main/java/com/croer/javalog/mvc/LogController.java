/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author elialva
 */
public class LogController {

    @Autowired
    private final LogModel model;
    @Autowired
    private final LogView view;
    private boolean started;

    LogController() {
        model = null;
        view = null;
        System.out.println("Inoto0 " + view + " " + model);
    }
    
    LogController(LogModel model, LogView view) {
        this.model = model;
        this.view = view;
        System.out.println("XtenEamx " + view + " " + model);
        this.model.addPropertyChangeListener(view);
//        this.view.setModel(model);
        this.view.setController(LogController.this);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Moquis " + Thread.currentThread());
                LogController.this.view.setLocationRelativeTo(null);
                LogController.this.view.setVisible(true);
            }
        });
    }

    public void toggleProcess() {
        started = !started;
        if (started) {
            stop();
        } else {
            start();
        }
    }

    public void start() {
        model.on();
    }

    public void stop() {
        model.off();
    }

    public void quit() {
        model.quit();
        view.quit();
    }

    private void init() {
        System.out.println("Inoto " + view + " " + model);
        this.model.addPropertyChangeListener(view);
//        this.view.setModel(model);
        this.view.setController(LogController.this);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Moquis " + Thread.currentThread());
                view.setLocationRelativeTo(null);
                view.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(user_dir + "\\target\\classes\\springXMLConfig.xml");
        LogController logController = appContext.getBean(LogController.class);
        logController.start();

    }

    void showSettings() {
        view.showSettings();
    }

    void toggleLog(boolean flag) {
        model.setLogGenerated(flag);
    }

    void setMaxSize(int maxSize) {
        model.setMaxSize(maxSize);
    }

    void setLogPath(String logPath) {
        model.setLogPath(logPath);
    }

}
