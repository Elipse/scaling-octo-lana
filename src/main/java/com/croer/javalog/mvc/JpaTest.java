/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.javalog.mvc;

import com.croer.db.search.entities.Ortograma;
import com.croer.db.search.repositories.OrtogramaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author elialva
 */
public class JpaTest {

    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(user_dir + "\\target\\classes\\springXMLConfig.xml");
        OrtogramaRepository ortoRepo = appContext.getBean(OrtogramaRepository.class);
        List<String> l = new ArrayList();
        l.add("nutria");
        for (String string : l) {
            Ortograma findOne = new Ortograma(string);
//            findOne.setAlineacionList(new Vector<Alineacion>());
            Ortograma ortis = ortoRepo.getOne(string);
            System.out.println("--" + findOne.getAlineacionList());
        }
    }

}
