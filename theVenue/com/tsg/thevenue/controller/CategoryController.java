/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.CategoryDao;
import com.tsg.thevenue.dto.Category;
import java.util.List;
import javax.inject.Inject;
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
public class CategoryController {
    private final CategoryDao dao;
    
    @Inject
    public CategoryController(CategoryDao dao){
        this.dao = dao;
    }
//    ***********************Tested and Works- Pankaj********************
    @RequestMapping(value="/category", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category create(@RequestBody Category c){
       return dao.createCategory(c);
    }
    
    @RequestMapping(value="/categories", method=RequestMethod.GET)
    @ResponseBody
    public List<Category> getAll(){
        return dao.getAllCategories();
    }
    
//    ***************************************************************
    
    
    @RequestMapping(value = "/category/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Category get(@PathVariable("id") int id) {
        return dao.getCategoryById(id);
    }
    
    
    
    @RequestMapping(value="/category/{name}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        dao.deleteCategory(id);
    }
    
    @RequestMapping(value="/category/{name}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody Category category)
    {
        category.setCategoryId(id);
        dao.updateCategory(category);
    }
    
    
}
