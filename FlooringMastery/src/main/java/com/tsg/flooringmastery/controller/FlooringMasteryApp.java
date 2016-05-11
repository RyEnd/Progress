package com.tsg.flooringmastery.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class FlooringMasteryApp {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        
        controller.run();
    }
}
