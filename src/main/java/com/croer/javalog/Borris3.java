/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog;

import com.croer.db.search.repositories.ProductoLogRepository;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author elialva
 */
public class Borris3 {

    @Value("${ameno2}")
    private String pathFile;

    @Value("#{systemProperties['java.version']}")
    private String javaVersionMap;

    @Value("${java.version}")
    private String javaVersionMap2;

    @Value("${user.dir}")
    private String classpath;

    @Value("${testo}")
    private int nomas;
    
//    @Value("#{myMeche['mercedes.auto']}")
    @Value("#{myMeche.elipse}")
    private String metis;
    
//    @Value("${emails}")
    @Resource(name="myMeche.emails")
    private Map emilios;
    
    @Value("#{myCarmen.carmen}")
    private String buvts;
    
    @Resource(name="model.repositories")
    private List p;
    
    @Autowired
    private Properties databaseConfig;

    public String getPathFile() {
        return pathFile + ":::" + buvts;
    }
    
    
    public Map misEmilios() {
        return emilios;
    }

    public String getVer() {
        return p + "--" + ++nomas + ":::" + javaVersionMap + ":::" + javaVersionMap2 + ":::" + classpath + "::k:" + metis;
    }

    Properties getPropertos() {
        return databaseConfig;
    }
}
