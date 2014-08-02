/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.javalog.mvc;

import org.apache.commons.configuration.CompositeConfiguration;

/**
 *
 * @author elialva
 */
public class ConfigurationHard {
    private static CompositeConfiguration CONFIGURATION;
    
    public static CompositeConfiguration getCompositeConfiguration() {
        return CONFIGURATION;
    }
}
