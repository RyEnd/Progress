/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_CATEGORY
            = "insert into Category (CategoryName) values (?)";
    private static final String SQL_DELETE_CATEGORY
            = "delete from Category where CategoryId = ?";
    private static final String SQL_UPDATE_CATEGORY
            = "update Category set CategoryName = ? where CategoryId = ?";
    private static final String SQL_SELECT_CATEGORY
            = "select * from Category where CategoryId = ?";
    private static final String SQL_SELECT_CATEGORIES_BY_POST_ID
            = "select c.CategoryId, c.CategoryName"
            + " from Category c"
            + " join PostCategory pc on c.CategoryId = pc.CategoryFk"
            + " where pc.PostFk = ?";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Category order by CategoryId asc";
    private static final String SQL_SELECT_CATEGORY_BY_NAME
            ="select * from Category where CategoryName = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category createCategory(Category category) {
        Category duplicateCategory = getCategoryByName(category.getCategoryName());
        if (duplicateCategory==null) {
            jdbcTemplate.update(SQL_INSERT_CATEGORY,
                category.getCategoryName());
        category.setCategoryId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return category;
        }else{
            return duplicateCategory;
        }
        
    }

    @Override
    public void deleteCategory(int categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                category.getCategoryName(),
                category.getCategoryId());
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY, new CategoryMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public List<Category> getCategoriesByPostId(int postId) {
        List<Category> cList = jdbcTemplate.query(SQL_SELECT_CATEGORIES_BY_POST_ID, new CategoryMapper(), postId);
        return ( cList == null || cList.size() == 0 ? null : cList );
    }

    @Override
    public Category getCategoryByName(String category) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_BY_NAME, new CategoryMapper(), category);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category c = new Category();
            c.setCategoryName(rs.getString("CategoryName"));
            c.setCategoryId(rs.getInt("CategoryId"));
            return c;
        }
    }
}
