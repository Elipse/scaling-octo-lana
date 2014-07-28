/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author elialva
 */
public class LogController {

    private final LogModel model;
    private final LogView view;

    LogController(LogModel model) {
        this.model = model;
        this.view = new LogView(this, this.model);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.setVisible(true);
            }
        });
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
    
    public void turnOnLights() {
        System.out.println("Chupachona");
    }

    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(user_dir + "\\target\\classes\\springXMLConfig.xml");
        LogModel model = appContext.getBean(LogModel.class);
//        LogView view = appContext.getBean(LogView.class);
        
        LogController logController = new LogController(model);
        logController.start();
    }
}
