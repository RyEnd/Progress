/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.NavBarLocationDao;
import com.tsg.thevenue.dao.PageDao;
import com.tsg.thevenue.dto.ModelToPass;
import com.tsg.thevenue.dto.NavBarLocation;
import com.tsg.thevenue.dto.Page;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class PageRestController {

    private final PageDao dao;
    private final NavBarLocationDao navDao;

    @Inject
    public PageRestController(PageDao dao, NavBarLocationDao navDao) {
        this.dao = dao;
        this.navDao = navDao;
    }    
    
//    @RequestMapping(value = "/staticPage", method =RequestMethod.GET )
//    public String displayStaticPage(HttpServletRequest req,Model model){
//        int pageId = Integer.parseInt(req.getParameter("pageId"));
//        Page page = dao.getPageById(pageId);
//        model.addAttribute("page",page);
//        return "viewStaticPage";
//    }

    //what is this?
    @RequestMapping(value = "/getStaticPageById", method = RequestMethod.GET)
    public String displayStaticPage(HttpServletRequest req, Model model) {
        int pageId = Integer.parseInt(req.getParameter("pageId"));
        Page page = dao.getPageById(pageId);
        model.addAttribute("page", page);
        return "viewStaticPage";
    }

    @RequestMapping(value = "/staticPage/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaticPage(@PathVariable int id){
        dao.deletePage(id);
    }
    
    @RequestMapping(value="/staticPage/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStaticPage(@PathVariable int id, @RequestBody Page page){
        page.setPageId(id);
        dao.updatePage(page);
    }
    
    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Page getStaticPageById(@PathVariable("id") int id) {
        return dao.getPageById(id);
    }
    
    @RequestMapping(value = "/staticPage",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Page createStaticPages(@RequestBody Page page){
        return dao.createPage(page);
    }
    
    @RequestMapping(value = "/staticPages", method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllStaticPages() {
        return dao.getAllPages();
    }

    @RequestMapping(value = "/staticPagesAndNavBarLocations", method = RequestMethod.GET)
    @ResponseBody
    public ModelToPass getAllStaticPagesAndNavBarLocations() {
        List<Page> listPages = navDao.getUnassignedPages();
        List<NavBarLocation> navBarLocationList = navDao.getFilledSlots();
        ModelToPass modelToPass = new ModelToPass();
        modelToPass.setNavBarLocation(navBarLocationList);
        modelToPass.setPage(listPages);
        return modelToPass;
    }

    @RequestMapping(value = "/navBarLocation/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearNavBarLocation(@PathVariable int id){
            navDao.clearSlot(id);
    }

    @RequestMapping(value = "/NavBarLocations", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAllNavBarLocation(@RequestBody ArrayList<NavBarLocation> navBarLocationList) { 
      
        for (NavBarLocation navBarLocation : navBarLocationList) {
            navBarLocation.setPage(navDao.getPageByNvbl(navBarLocation));
        }
        navDao.setAllSlots(navBarLocationList);
    }
    
    @RequestMapping(value = "/AssignNavBarLocation", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignNavBarLocation(@RequestBody Integer pageFkToNextSlot) { 
      
        navDao.setPageToNVBLId(pageFkToNextSlot);
    }
    
    @RequestMapping(value = "/UnassignNavBarLocation", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unassignNavBarLocation(@RequestBody Integer id){
        navDao.clearSlot(id);
    }
}