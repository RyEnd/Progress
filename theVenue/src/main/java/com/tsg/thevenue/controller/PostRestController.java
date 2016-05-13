/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.CategoryDao;
import com.tsg.thevenue.dao.PostDao;
import com.tsg.thevenue.dto.Post;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
public class PostRestController {
    private final PostDao dao;
    private final CategoryDao daoCat;
    
    @Inject
    public PostRestController(PostDao dao, CategoryDao daoCat) {
        this.dao = dao;
        this.daoCat = daoCat;
    }

    @RequestMapping(value = "/post/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Post get(@PathVariable("id") int id) {
        return dao.getPostById(id);
    }
    
    
    @RequestMapping(value="/post", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)  
    @ResponseBody
    public Post create(@Valid @RequestBody Post post){
       return dao.createPost(post);
    }
    
    @RequestMapping(value="/post/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        dao.deletePost(id);
    }
    
    @RequestMapping(value="/post/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody Post post)
    {
        post.setPostId(id);
        dao.updatePost(post);
    }
    
    @RequestMapping(value="/posts", method=RequestMethod.GET)
    @ResponseBody
    public List<Post> getAll(){
        return dao.getAllPosts();
    }
    
    @RequestMapping(value="/post/publish", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publishPost(@RequestBody int id){
        dao.publishPost(id);
    }
    
    @RequestMapping(value="/post/archive", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archivePost(@RequestBody int id){
        dao.archivePost(id);
    }
    
    @RequestMapping(value = "/postsByTag/{tagId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostByTagId(@PathVariable int tagId){
        return dao.getPostsByTagId(tagId);
    }
    
    @RequestMapping(value = "/postsByTagName/{tagName}", method= RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostByTagName(@PathVariable String tagName){
        return dao.getPostByTagName(tagName);
    }
    
    @RequestMapping(value = "/postsByCategory/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostByCategoryId(@PathVariable int categoryId){
        return dao.getPostByCategoryId(categoryId);
    }
    
    @RequestMapping(value = "/postsByCategoryName/{categoryName}", method= RequestMethod.GET)
    @ResponseBody
    public List<Post> getPostByCategoryName(@PathVariable String categoryName){
        return dao.getPostByCategoryName(categoryName);
    }
    
}
