package com.lucasilva.dryve.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogUtils {

    public void logRequisicao(String verb, String route, String retorno, String className, 
    		String status) {
        Logger logger = LoggerFactory.getLogger(className);
        System.out.println("");
        logger.info("Dryve-System: Verb: " + verb + " | route: " + route + " | "
        		+ "Retorno: " + retorno + " - " + status + ".");
    }
    
    public void logInfo(String service,  String method, String status, String className) {
        Logger logger = LoggerFactory.getLogger(className);
        System.out.println("");
        logger.info("Dryve-System: service: " + service + " | method: " + method + " | "
        		+ "Status: " + status);
    }
}