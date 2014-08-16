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
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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

    //No es final "alguien" puede hacerlo apuntar a otro lado
    //No es unmodifiable "alguien" puede eliminar o cambiar elementos
    //No es sync "alguien" puede causar inconsistencias - IndexOutBounds
    //
    //Es una API pública? resuélvelo
    //La funcionalidad contempla esos casos? resuélvelo
    //NO??? relájateeeeee
    private volatile List<JpaRepository> repositoriesList;
    private final int delay;
    //
    private boolean begin;
    private final PausableScheduledThreadPoolExecutor executor;
    private final ExtractTask extracTask;
    private final WriteLogTask writeLogTask;
    private volatile PropertyChangeSupport pcs;
    private volatile Thread writer;
    //
    private volatile boolean logGenerated;
    private volatile String pathLogFile;
    private volatile int maxSize;
    //
    private volatile FireProperty fireCount;

    LogModel(List<JpaRepository> repositoriesList) {
        this(repositoriesList, 2);
    }

    LogModel(List<JpaRepository> repositoriesList, int delay) {
        this.repositoriesList = repositoriesList;
        this.delay = delay;
        //
        begin = true;
        //
        executor = new PausableScheduledThreadPoolExecutor(1);
        //Producer & Consumer Configuration
        LinkedBlockingDeque<Map> mapList = new LinkedBlockingDeque<>();
        extracTask = new ExtractTask(mapList);
        writeLogTask = new WriteLogTask(mapList);
        fireCount = new FireProperty();
        //
        pcs = new PropertyChangeSupport(this);  //eureka!! Si tu lo cambias y yo lo utilizo  = volatile
    }

    public void on() {  //Depende que initialize se haya disparado
        if (begin) {
            begin = false;
            executor.scheduleWithFixedDelay(extracTask, 1, delay, TimeUnit.MILLISECONDS);
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

    public void quit() {
        executor.shutdown();
        //
        Thread moribund = writer;
        writer = null;
        moribund.interrupt();
    }

    public void setLogGenerated(boolean logGenerated) {
        this.logGenerated = logGenerated;
    }

    public void setLogPath(String pathLogFile) {
        this.pathLogFile = pathLogFile;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize * 1024;
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

    private class ExtractTask implements Runnable {

        private final LinkedBlockingDeque mapList;

        ExtractTask(LinkedBlockingDeque<Map> mapList) {
            this.mapList = mapList;
        }

        @Override
        public void run() {
            try {
                Map<String, Integer> statics = LogMProcess.processLogs(LogModel.this.repositoriesList);
                mapList.add(statics);

                Integer count = 0;
                for (Map.Entry<String, Integer> entry : statics.entrySet()) {
                    count += entry.getValue();
                }

                fireCount.setCount(count);
                EventQueue.invokeAndWait(fireCount);  //Se genera en cada llamado. GC lo colecta sin problemas.
            } catch (InterruptedException | InvocationTargetException ex) {
                Logger.getLogger(LogModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class FireProperty implements Runnable {

        //volatile podría no ser necesario?!
        //Usar un setter para tener solo un objeto FireProperty
        private volatile Integer count = 0;

        @Override
        public void run() {
            LogModel.this.pcs.firePropertyChange("logCount", null, count);
        }

        /**
         * @param count the count to set
         */
        public void setCount(Integer count) {
            this.count = count;
        }
    }

    private class WriteLogTask implements Runnable {

        private final LinkedBlockingDeque<Map> mapList;

        WriteLogTask(LinkedBlockingDeque<Map> mapList) {
            this.mapList = mapList;
        }

        @Override
        public void run() {
            Thread thisThread = Thread.currentThread();
            while (LogModel.this.writer == thisThread) {
                try {
                    Map map = mapList.take();
                    try {
                        if (LogModel.this.logGenerated) {
                            //Create log
                            File file = new File(LogModel.this.pathLogFile);
                            FileUtils.writeLines(file, Arrays.asList(">" + new Date()), true);
                            FileUtils.writeLines(file, map.entrySet(), true);
                            if (file.length() > LogModel.this.maxSize) {
                                File tmp = new File(file.getAbsolutePath() + "." + System.currentTimeMillis());
                                FileUtils.moveFile(file, tmp);
                            }
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
