/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Author;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AuthorDao {
    
    public Author getAuthorById(int authorId);
    public List<Author> getAllAuthor();
}
