/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import java.beans.PropertyChangeEvent;
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

    LogController() {
        System.out.println("Brotosaurio");
        model = null;
        view = null;
    }

    LogController(LogModel model) {
        System.out.println("Wopert " + this);

        this.model = model;
        this.view = new LogView(this, this.model);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Mocos " + Thread.currentThread());
                view.setVisible(true);
            }
        });
    }

    void toggleLogGenerated() {
        model.setLogGenerated(!model.isLogGenerated());
    }

    public void start() {
        model.on();
        view.enableStopView();
    }

    public void stop() {
        model.off();
        view.enableStartView();
    }

    public void quit() {
        model.quit();
        view.quit();
    }

    private void init() {
        System.out.println("Inoto " + view + " " + model);
        this.model.addPropertyChangeListener(view);
        this.view.setModel(model);
        this.view.setController(LogController.this);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Moquis " + Thread.currentThread());
                view.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(user_dir + "\\target\\classes\\springXMLConfig.xml");
        LogModel model = appContext.getBean(LogModel.class);
        LogView view = appContext.getBean(LogView.class);
//        LogController logController = new LogController(model);
        LogController logController = appContext.getBean(LogController.class);
        System.out.println("PCS T# " + Thread.currentThread());
        logController.start();
    }

}
