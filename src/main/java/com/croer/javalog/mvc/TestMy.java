/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.javalog.mvc;

import com.croer.db.search.repos.Tetos;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author elialva
 */
public class TestMy {
    
    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(user_dir + "\\target\\classes\\springXMLConfig_1.xml");
        Tetos bean = appContext.getBean(Tetos.class);
        bean.refresh(null);
        
    }
}
