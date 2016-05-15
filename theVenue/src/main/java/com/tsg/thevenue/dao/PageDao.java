/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Page;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface PageDao {
    
    
    public Page createPage(Page page); /* @return page with the page id assigned*/
    
    public Page previewPage(Page page);
    
    public void deletePage(int pageId);
    
    public void updatePage(Page page);
    
    public List<Page> getAllPages();
    
    public List<Page> getChildPages(int pageId);
    
    public Page getPageById(int pageId);
    
    public Page getPageByTitle(String title);
    
    public Page getPageByURLTitle(String urlTitle);
}
