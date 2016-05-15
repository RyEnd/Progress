/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Category;
import java.util.List;

/**
 *
 * @author apprentice
 */

public interface CategoryDao {    
    
    public Category createCategory(Category category);

    public void deleteCategory(int categoryId);

    public void updateCategory(Category category);

    public Category getCategoryById(int id);
    
    public Category getCategoryByName(String category);

    public List<Category> getCategoriesByPostId(int PostId);

    public List<Category> getAllCategories();
}