/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class StatusDaoImpl implements StatusDao{
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_GET_STATUS_BY_ID
            ="Select * from Status where statusId =? ";

    @Override
    public Status getStatusById(int statusId) {
       return jdbcTemplate.queryForObject(SQL_GET_STATUS_BY_ID, new StatusMapper(),statusId );
    }
    
    private final class StatusMapper implements RowMapper<Status>{

        @Override
        public Status mapRow(ResultSet rs, int i) throws SQLException {
            Status s = new Status();
            s.setStatusId(rs.getInt("StatusId"));
            s.setStatus(rs.getString("StatusName"));
            return s;
        }
        
    }
    
    
}
