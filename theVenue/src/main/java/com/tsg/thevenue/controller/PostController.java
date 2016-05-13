/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.PostDao;
import com.tsg.thevenue.dao.TagDao;
import com.tsg.thevenue.dto.Post;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class PostController {
   private final PostDao postDao;
   private final TagDao tagDao;
   
    @Inject
    public PostController(PostDao postDao, TagDao tagDao) {
        this.postDao = postDao;
        this.tagDao = tagDao;
    }
    
    @RequestMapping(value="/posts/{postTitle}", method=RequestMethod.GET)
    public String viewSingleBlog(String postTitle, Model model){
        Post p = postDao.getPostByTitle(postTitle);
        model.addAttribute("post", p);
        return "postDisplay";
    }
    
    @RequestMapping(value="/tagCloud", method=RequestMethod.GET)
    @ResponseBody
    public String[] getTagsForTagCloud(){
        return tagDao.getAllTagNamesForTagCloud();
    }
    
    @RequestMapping(value="/getEmployeePosts", method=RequestMethod.GET)
    @ResponseBody
    public List<Post> getEmployeePosts(){
        return postDao.getEmployeePosts();
    }
}
