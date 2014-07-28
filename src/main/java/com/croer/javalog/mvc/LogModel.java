/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import util.PausableScheduledThreadPoolExecutor;

/**
 *
 * @author elialva
 */
class LogModel implements PropertyChangeListener {

    private boolean begin;
    private final PausableScheduledThreadPoolExecutor executor;
    private final ExtractTask extracTask;
    private final WriteLogTask writeLogTask;
    private final PropertyChangeSupport pcs;
    //
    private volatile Thread writer;

    LogModel(List<JpaRepository> repositoriesList, String pathLogFile, int maxSize) {
        begin = true;
        //
        executor = new PausableScheduledThreadPoolExecutor(1);
        //Producer & Consumer Configuration
        LinkedBlockingDeque<Map> mapList = new LinkedBlockingDeque<>();
        repositoriesList = Collections.unmodifiableList(repositoriesList);
        extracTask = new ExtractTask(mapList, repositoriesList);
        writeLogTask = new WriteLogTask(mapList, new File(pathLogFile), maxSize);
        //
        pcs = new PropertyChangeSupport(this);
    }

    public void on() {  //Depende que initialize se haya disparado

        if (begin) {
            begin = false;
            executor.scheduleWithFixedDelay(extracTask, 1, 1, TimeUnit.MILLISECONDS);
            //
            writer = new Thread(writeLogTask);
            writer.setDaemon(true);
            writer.start();
            return;
        }
        executor.resume();
    }

    public void off() { //Depende que on se haya disparado
        executor.pause();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.pcs.firePropertyChange(evt);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void quit() {
        executor.shutdown();
        //
        Thread moribund = writer;
        writer = null;
        moribund.interrupt();
    }

    private class ExtractTask implements Runnable {

        private final LinkedBlockingDeque mapList;
        private final List<JpaRepository> repositoriesList;

        ExtractTask(LinkedBlockingDeque<Map> mapList, List<JpaRepository> repositoriesList) {
            this.mapList = mapList;
            this.repositoriesList = repositoriesList;
        }

        @Override
        public void run() {
            try {
                Map<String, Integer> statics = LogMProcess.processLogs(repositoriesList);

                mapList.add(statics);

                Integer count = 0;
                for (Map.Entry<String, Integer> entry : statics.entrySet()) {
                    count += entry.getValue();
                }

                EventQueue.invokeLater(new FireProperty(count));  //Se genera en cada llamado. GC lo colecta sin problemas.
            } catch (Exception ex) {
                Logger.getLogger(LogModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class FireProperty implements Runnable {

        //volatile podría no ser necesario?!
        private volatile Integer count;

        FireProperty(Integer count) {
            this.count = count;
        }

        @Override
        public void run() {
            LogModel.this.pcs.firePropertyChange("logCount", null, count);
        }
    }

    private class WriteLogTask implements Runnable {

        private final LinkedBlockingDeque<Map> mapList;
        private final File file;  //Se asume que solo un thread usa WriteLogTask
        private final int maxSize;

        WriteLogTask(LinkedBlockingDeque<Map> mapList, File file, int maxSize) {
            this.mapList = mapList;
            this.file = file;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            Thread thisThread = Thread.currentThread();
            while (writer == thisThread) {
                try {
                    Map map = mapList.take();
                    try {
                        //Create log
                        FileUtils.writeLines(file, Arrays.asList(">" + new Date()), true);
                        FileUtils.writeLines(file, map.entrySet(), true);
                        if (file.length() > maxSize) {
                            File tmp = new File(file.getAbsolutePath() + "." + System.currentTimeMillis());
                            FileUtils.moveFile(file, tmp);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(LogView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
