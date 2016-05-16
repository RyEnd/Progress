/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface UserDao {

    public User addUser(User user);

    public void deleteUser(String username);

    public void updateUser(User user);

    public List<User> getAllUsers();

    public User getUserById (int userId);
    
    public User getUserByPostId (int userId);

}
