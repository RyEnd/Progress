/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class TagDaoImpl implements TagDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_TAG
            = "insert into Tag (TagName) values (?)";
    private static final String SQL_DELETE_TAG
            = "delete from Tag where Tagid = ?";
    private static final String SQL_UPDATE_TAG
            = "update Tag set TagName = ? where TagId = ?";
    private static final String SQL_SELECT_TAG
            = "select * from Tag where TagId = ?";
    private static final String SQL_SELECT_TAGS_BY_POST_ID
            = "select t.TagId, t.TagName"
            + " from Tag t"
            + " join PostTag pt on t.tagId = pt.TagFk"
            + " where pt.PostFk = ?";
    private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag where TagName = ? order by TagId asc ";
    private static final String SQL_SELECT_ALL_TAG_NAMES
            = "SELECT TagName FROM Tag LEFT OUTER JOIN PostTag ON Tag.TagId = PostTag.TagFk";
    private static final String SQL_SELECT_TAG_BY_TAGNAME
            = " Select * from Tag where TagName =?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag tag) {
        Tag duplicateTag = getTagByTagName(tag.getTagName());
        if (duplicateTag == null) {
            jdbcTemplate.update(SQL_INSERT_TAG,
                    tag.getTagName());
            tag.setTagId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
            return tag;
        } else {
            return duplicateTag;
        }

    }

    @Override
    public void deleteTag(int tagId) {
        jdbcTemplate.update(SQL_DELETE_TAG, tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG,
                tag.getTagName(),
                tag.getTagId());
    }

    @Override
    public Tag getTagById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG, new TagDaoImpl.TagMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tList= jdbcTemplate.query(SQL_SELECT_ALL_TAGS, new TagDaoImpl.TagMapper());
        return(tList.size()==0 ? null : tList);
    }
    
    @Override
    public String[] getAllTagNamesForTagCloud(){
        List<String> sList = jdbcTemplate.query(SQL_SELECT_ALL_TAG_NAMES, new TagCloudMapper());
        
        String[] tArray = sList.toArray(new String[sList.size()]);
        
        return tArray;
    }

    @Override
    public List<Tag> getTagsByPostId(int postId) {

        try {
            List<Tag> tList = jdbcTemplate.query(SQL_SELECT_TAGS_BY_POST_ID, new TagMapper(), postId);
            
            return  (tList.size()==0 ? null :( new ArrayList(tList)));
        } catch (DataAccessException dataAccessException) {
            return null;

        }
    }

    @Override
    public Tag getTagByTagName(String tagName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG_BY_TAGNAME, new TagMapper(), tagName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag t = new Tag();
            t.setTagName(rs.getString("TagName"));
            t.setTagId(rs.getInt("TagId"));
            return t;
        }
    }
    
    private static final class TagCloudMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int i) throws SQLException {
            String s = (rs.getString("TagName"));
            return s;
        }
        
    }

}
