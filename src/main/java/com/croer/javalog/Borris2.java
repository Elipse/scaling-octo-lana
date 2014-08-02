/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog;

import com.croer.db.search.repositories.AlineacionRepository;
import java.util.Iterator;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author elialva
 */
public class Borris2 {

    public static void main(String[] args) {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("C:\\Users\\IBM_ADMIN\\Documents\\@Projects_Eli\\201309 Finder&Getter\\_NBP\\digitalcatalog\\JavaLog\\src\\main\\resources\\springXMLConfig.xml");
        System.out.println("JVAdap " + context.getBean("jpaVendorAdapter"));
        PropertyPlaceholderConfigurer bean2 = context.getBean("propertyConfigurer", PropertyPlaceholderConfigurer.class);

        Borris3 bean = context.getBean(Borris3.class);
        System.out.println("Yep " + bean.getPathFile() + ":" + bean.getVer());
        System.out.println("Emilios " + bean.misEmilios());
        System.out.println("Ops " + bean.getPropertos());
        PropertiesConfiguration bean1 = context.getBean("buildProperties", PropertiesConfiguration.class);
        System.out.println("Porks " + bean1.getString("yahel"));
        CompositeConfiguration bean3 = context.getBean(CompositeConfiguration.class);
        System.out.println("Multiple " + bean3.getString("maxy"));
        Borris4 bean4 = context.getBean(Borris4.class);
        System.out.println("Beno4 " + bean4);
        //AlineacionRepository bean = context.getBean("alineacionRepository", AlineacionRepository.class);
        //System.out.println("Beno " + bean);
    }
}
