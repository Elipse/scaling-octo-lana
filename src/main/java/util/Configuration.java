/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author elialva
 */
public class Configuration implements ApplicationContextAware {

    private static CompositeConfiguration CONFIGURATION;
    private static ApplicationContext CONTEXT;

    @Autowired(required = true)
    private Configuration(CompositeConfiguration configuration) {
        CONFIGURATION = configuration;
    }

    public static CompositeConfiguration getCONFIGURATION() {
        if (CONFIGURATION == null) {
            List lc = new ArrayList();
            try {
                PropertiesConfiguration pc1 = new PropertiesConfiguration("entities.properties");
                PropertiesConfiguration pc2 = new PropertiesConfiguration("mvc.properties");
                lc.add(pc1);
                lc.add(pc2);
            } catch (ConfigurationException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
            CONFIGURATION = new CompositeConfiguration(lc);
        }
        return CONFIGURATION;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (CONTEXT != null) {
            return;
        }
        CONTEXT = context;
    }

    public static ApplicationContext getApplicationContext() {
        if (CONTEXT == null) {
            FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(System.getProperty("user.dir") + "\\target\\classes\\springXMLConfig.xml");
            CONTEXT = appContext;
        }
        return CONTEXT;
    }
}
