package com.tsg.thevenue.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class EndpointController {
    
   

    @RequestMapping(value = "/home1", method = RequestMethod.GET)
    public String showHome() {
        return "play";
    }

//    @RequestMapping(value = "/control", method = RequestMethod.GET)
//    public String showAdminControl() {
//        return "controlAdmin";
//    }
//
//    @RequestMapping(value = "/preview", method = RequestMethod.GET)
//    public String showPreview() {
//        return "preview";
//    }
}
