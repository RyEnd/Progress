/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class AuthorDaoImpl implements AuthorDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_GET_AUTHOR_BY_ID
            ="select * from Author where AuthorId =?"; 
    
    private static final String SQL_GET_ALL_AUTHORS
            ="SELECT * from Author";

    @Override
    public Author getAuthorById(int authorId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_AUTHOR_BY_ID, new AuthorMapper(), authorId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthor() {
        return jdbcTemplate.query(SQL_GET_ALL_AUTHORS, new AuthorMapper());
    }
    
    private static final class AuthorMapper implements RowMapper<Author>{

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            Author author = new Author();
            author.setAuthorId(rs.getInt("AuthorId"));
            author.setAuthorName(rs.getString("AuthorName"));
            return author;
        }
        
    }
    
    
    
}
