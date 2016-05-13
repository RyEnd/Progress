/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.UserDao;
import com.tsg.thevenue.dto.User;
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
public class UserController {

    private final UserDao dao;

    @Inject
    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/manageusers", method = RequestMethod.GET)
    public String displayManageUserPage() {
        return "manageusers";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User add(@RequestBody User user) {
        return dao.addUser(user);
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int userId) {
        dao.deleteUser(userId);
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int userId, @RequestBody User user) {
        user.setUserId(userId);
        dao.updateUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("id") int userId) {
        return dao.getUserById(userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @RequestMapping(value = "/user/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByPostId(int userId) {
        return dao.getUserByPostId(userId);
    }

}
