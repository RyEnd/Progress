/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.controller;

import com.tsg.thevenue.dao.UserDao;
import com.tsg.thevenue.dto.User;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private UserDao dao;
    private PasswordEncoder encoder;

    @Inject
    public UserController(UserDao dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/manageusers", method = RequestMethod.GET)
    public String displayManageUserPage() {
        return "manageusers";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User add(@RequestBody User user) {
        String hashedPw = encoder.encode(user.getPassword());
        user.setPassword(hashedPw);
        return dao.addUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int userId) {
        dao.deleteUser(userId);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int userId, @RequestBody User user) {
        user.setUserId(userId);
        String hashedPw = encoder.encode(user.getPassword());
        user.setPassword(hashedPw);
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

//    @RequestMapping(value = "/user/{postId}", method = RequestMethod.GET)
//    @ResponseBody
//    public User getUserByPostId(int userId) {
//        return dao.getUserByPostId(userId);
//    }
    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = dao.getAllUsers();
        model.put("users", users);
        return "displayUserList";
    }

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User u = new User();
        u.setUsername(req.getParameter("username"));
        String hashedPw = encoder.encode(req.getParameter("password"));
        u.setPassword(hashedPw);
        u.setEmail(req.getParameter("email"));
        u.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            u.addAuthority("ROLE_ADMIN");
        }

        dao.addUser(u);

        return "redirect:displayUserList";
    }

//    @RequestMapping(value="/deleteUser", method=RequestMethod.GET)
//    public String deleteUser(@RequestParam("username") String username, Map<String, Object> model){
//        dao.deleteUser(username);
//        return "redirect:displayUserList";
//    }
}
