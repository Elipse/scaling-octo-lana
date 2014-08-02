/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.javalog.mvc;

import org.springframework.beans.factory.annotation.Autowired;

public final class StaticHub {
    private static String theStaticInstance;

    /**
     * Note this initialization method is private! No nasty public setInstance method.
     */
    @Autowired(required = true)
    private StaticHub(String instance) {
        theStaticInstance = instance;
    }

    /**
     * My public API, making the Spring-created instance of MyInterface statically accessible
     * @return 
     */
    public static String getInstance() {
        return theStaticInstance;
    }
}