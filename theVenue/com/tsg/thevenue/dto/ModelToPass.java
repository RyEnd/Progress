/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dto;

import java.util.List;

/**
 *
 * @author apprentice
 */
public class ModelToPass {
    private List<Page> page;
    private List<NavBarLocation> navBarLocation;

    public List<Page> getPage() {
        return page;
    }

    public void setPage(List<Page> page) {
        this.page = page;
    }

    public List<NavBarLocation> getNavBarLocation() {
        return navBarLocation;
    }

    public void setNavBarLocation(List<NavBarLocation> navBarLocation) {
        this.navBarLocation = navBarLocation;
    }
    
    
}
