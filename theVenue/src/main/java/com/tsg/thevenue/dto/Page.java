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
public class Page {
    private int pageId;
    private String title;
    private String heading;
    private String body;
    private Integer parentPageFk;
    private String urlTitle;
    private String navName;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getParentPageFk() {
        return parentPageFk;
    }

    public void setParentPageFk(Integer parentPageFk) {
        this.parentPageFk = parentPageFk;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.pageId;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.heading);
        hash = 79 * hash + Objects.hashCode(this.body);
        hash = 79 * hash + Objects.hashCode(this.parentPageFk);
        hash = 79 * hash + Objects.hashCode(this.urlTitle);
        hash = 79 * hash + Objects.hashCode(this.navName);
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
        final Page other = (Page) obj;
        if (this.pageId != other.pageId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.heading, other.heading)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.urlTitle, other.urlTitle)) {
            return false;
        }
        if (!Objects.equals(this.navName, other.navName)) {
            return false;
        }
        if (!Objects.equals(this.parentPageFk, other.parentPageFk)) {
            return false;
        }
        return true;
    }

   
    

    
}
