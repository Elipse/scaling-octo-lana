/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author elialva
 */
public class Configuration {

    private static CompositeConfiguration CONFIGURATION;

    @Autowired(required = true)
    private Configuration(CompositeConfiguration configuration) {
        CONFIGURATION = configuration;
    }

    /**
     * Get the value of CONFIGURATION
     *
     * @return the value of CONFIGURATION
     */
    public static CompositeConfiguration getCONFIGURATION() {
        return CONFIGURATION;
    }
}
