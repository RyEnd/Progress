/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_USER
            = "insert into User (Username, Password, Email, Enabled) value (?,?,?,1)";

    private static final String SQL_DELETE_USER
            = "delete from User where Username = ?";

    private static final String SQL_UPDATE_USER
            = "update User set Username = ?, Password = ?, Email = ? where UserId = ?";

    private static final String SQL_SELECT_USER_BY_ID
            = "select * from User where UserId = ?";

    private static final String SQL_SELECT_ALL_USERS
            = "select * from User";

    private static final String SQL_SELECT_USER_BY_POST_ID
            = "select * u.UserId, u.Username, u.Password, u.Email"
            + " from User u"
            + " join post p on p.PostId = u.UserId"
            + " where u.UserId = ?";
    
    private static final String SQL_INSERT_AUTHORITY
            = "insert into Authority (Username, Authority) values (?,?)";
    
    private static final String SQL_DELETE_AUTHORITIES
            = "delete from Authority where Username = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {

        jdbcTemplate.update(SQL_INSERT_USER,
                user.getUserName(),
                user.getPassword(),
                user.getEmail());
        user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, user.getUserName(), authority);
        }
        
        return user;
    }

    @Override
    public void deleteUser(String username) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getUserId());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public User getUserById(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new UserDaoImpl.UserMapper(), userId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserByPostId(int userId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_POST_ID,
                new UserMapper(),
                userId);
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User u = new User();
            u.setUserName(rs.getString("Username"));
            u.setPassword(rs.getString("Password"));
            u.setEmail(rs.getString("Email"));
            u.setUserId(rs.getInt("UserId"));
            return u;
        }
    }

}
