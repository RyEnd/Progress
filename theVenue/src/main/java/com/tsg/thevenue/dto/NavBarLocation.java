/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dto;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class NavBarLocation {
    private int navBarLocationId;
    private Integer pageFk;
    
    private Page page;

    public int getNavBarLocationId() {
        return navBarLocationId;
    }

    public void setNavBarLocationId(int navBarLocationId) {
        this.navBarLocationId = navBarLocationId;
    }

    public Integer getPageFk() {
        return pageFk;
    }

    public void setPageFk(Integer pageFK) {
        this.pageFk = pageFK;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.navBarLocationId;
        hash = 47 * hash + Objects.hashCode(this.pageFk);
        hash = 47 * hash + Objects.hashCode(this.page);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NavBarLocation other = (NavBarLocation) obj;
        if (this.navBarLocationId != other.navBarLocationId) {
            return false;
        }
        if (!Objects.equals(this.pageFk, other.pageFk)) {
            return false;
        }
        if (!Objects.equals(this.page, other.page)) {
            return false;
        }
        return true;
    }
    
    
    
}
