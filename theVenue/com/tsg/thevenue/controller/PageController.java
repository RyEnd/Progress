/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.CategoryDao;
import com.tsg.thevenue.dao.NavBarLocationDao;
import com.tsg.thevenue.dao.PageDao;
import com.tsg.thevenue.dao.PostDao;
import com.tsg.thevenue.dto.Category;
import com.tsg.thevenue.dto.NavBarLocation;
import com.tsg.thevenue.dto.Page;
import com.tsg.thevenue.dto.Post;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class PageController {

    private final PageDao pageDao;
    private final NavBarLocationDao navBarLocDao;
    private final PostDao postDao;
    private final CategoryDao catDao;

    @Inject
    public PageController(PageDao pageDao, NavBarLocationDao navBarLocDao, PostDao postDao, CategoryDao catDao) {
        this.pageDao = pageDao;
        this.navBarLocDao = navBarLocDao;
        this.postDao = postDao;
        this.catDao = catDao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String DisplayHomePage(HttpServletResponse response) {
        
        NavBarLocation navBarHome = navBarLocDao.getHomeSlot();

        if (navBarHome.getPageFk() == 0) {
            return "redirect:blog/active";
        } else {
            String url = navBarHome.getPage().getUrlTitle();
            return "redirect:displayPage/" + url;
        }
    }

    @RequestMapping(value = "/displayPage/{urlTitle}", method = RequestMethod.GET)
    public String displayPage(@PathVariable("urlTitle") String urlTitle, Model model) {
        
        navBarMenu(model);

        Page page = pageDao.getPageByURLTitle(urlTitle);
        List<Page> childPages = pageDao.getChildPages(page.getPageId());
        
        model.addAttribute("childPages", childPages);
        model.addAttribute("page", page);
        
        return "pageDisplay";
    }
    
    @RequestMapping(value="/blog/post/{urlTitle}", method=RequestMethod.GET)
    public String displayPost(@PathVariable String urlTitle, Model model){
        navBarMenu(model);
        Post p = postDao.getPostByUrlTitle(urlTitle);
        model.addAttribute("post", p);
        return "postDisplay";
    }

    @RequestMapping(value = "/blog/active", method = RequestMethod.GET)
    public String viewActiveBlog(Model model) {
        navBarMenu(model);
        categoryMenu(model);
        List<Post> pList = postDao.getAllActivePosts();
        
        model.addAttribute("pList", pList);
        return "blog";
    }
    
    @RequestMapping(value = "/blog/tag/{tagName}", method=RequestMethod.GET)
    public String viewBlogByTag(@PathVariable String tagName, Model model){
        navBarMenu(model);
        categoryMenu(model);
        List<Post> pList = postDao.getPostByTagName(tagName);
        model.addAttribute("pList", pList);
        return "blog";
    }
    
    @RequestMapping(value = "/blog/category/{categoryName}", method=RequestMethod.GET)
    public String viewBlogByCategory(@PathVariable String categoryName, Model model){
        navBarMenu(model);
        categoryMenu(model);
        List<Post> pList = postDao.getPostByCategoryName(categoryName);
        model.addAttribute("pList", pList);
        return "blog";
    }
    
    @RequestMapping(value = "/blog/activeDateDesc", method=RequestMethod.GET)
    public String viewActiveBlogDateDesc(Model model) {
        navBarMenu(model);
        categoryMenu(model);
        List<Post> pList = postDao.getAllActivePostsDateDsc();
        model.addAttribute("pList", pList);
        return "blog";
    }
    
    @RequestMapping(value = "/preview/{page}", method = RequestMethod.GET)
    public String showPagePreview(@PathVariable Page page, Model model) {
        Page p = pageDao.previewPage(page);
        model.addAttribute("page", p);
        return "previewPage";
    }
    
    @RequestMapping(value = "/control", method = RequestMethod.GET)
    public String showAdminControl() {
        return "controlAdmin";
    }
    
    @RequestMapping(value = "/previewSaved/{pageToPreviewId}", method = RequestMethod.GET)
    public String previewPage(@PathVariable int pageToPreviewId, Model model) {
        Page page = pageDao.getPageById(pageToPreviewId);
        model.addAttribute("page", page);
        return "previewPage";
    }
    
    /**
     * This method creates the nav bar menu model for all the pages
     *
     * @param model
     */
    public void navBarMenu(Model model) {
        List<NavBarLocation> navBarModel = navBarLocDao.getFilledSlots();
        model.addAttribute("navBar", navBarModel);
    }
    
    /**
     * This method creates the category menu model for all the blog pages
     *
     * @param model
     */
    public void categoryMenu(Model model) {
        List<Category> categoryModel = catDao.getAllCategories();
        model.addAttribute("cList", categoryModel);
    }
}
