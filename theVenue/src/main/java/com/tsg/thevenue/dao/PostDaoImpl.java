/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.thevenue.dao;

import com.tsg.thevenue.dto.Category;
import com.tsg.thevenue.dto.Post;
import com.tsg.thevenue.dto.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class PostDaoImpl implements PostDao {

    //add statements
    private static final String SQL_INSERT_POST
            = "INSERT INTO Post (Title, UrlTitle, AuthorFk, Body, PublishDate, StatusFk, StartDate, EndDate) value (?,?,?,?,?,?,?,?)";

    private static final String SQL_INSERT_POSTSTAGS
            = "INSERT INTO PostTag (PostFk, TagFk) values(?,?)";
    private static final String SQL_INSERT_POSTCATEGORIES
            = "INSERT INTO PostCategory (PostFk, CategoryFk) values(?,?)";
//    private static final String SQL_INSERT_TAGS =
//            "INSERT INTO Tags (name) values (?)";
//    private static final String SQL_INSERT_AUTHOR =
//            "INSERT INTO Authors (name) values (?)";
    //update statements
    private static final String SQL_UPDATE_POST
            = "UPDATE Post SET Title = ?, AuthorFk = ?, Body = ?, StartDate = ?,EndDate=?, StatusFk = ? WHERE PostId = ?";
    //delete statements
    private static final String SQL_DELETE_POST
            = "DELETE FROM Post WHERE PostId = ?";
    private static final String SQL_DELETE_POSTSTAGS
            = "DELETE FROM PostTag WHERE PostFk = ?";
    private static final String SQL_DELETE_POSTSCATEGORIES
            = "DELETE FROM PostCategory WHERE PostFk = ?";
    //get statements
    private static final String SQL_SELECT_ALL_POSTS
            = "SELECT * FROM Post ORDER BY PublishDate ASC";
    private static final String SQL_SELECT_ALL_ACTIVE_POSTS
            = "SELECT * FROM Post WHERE StatusFk = 1 ORDER BY PublishDate DESC";
    private static final String SQL_SELECT_EMPLOYEE_POSTS
            = "SELECT * FROM Post WHERE AuthorFk = 2 AND StatusFk = 3 OR StatusFk = 4";
    private static final String SQL_SELECT_ALL_ACTIVE_POSTS_ASC
            = "SELECT * FROM Post WHERE StatusFk = 1 ORDER BY PublishDate ASC";
    private static final String SQL_SELECT_POST_BY_ID
            = "SELECT * FROM Post WHERE PostId = ?";
    private static final String SQL_SELECT_POST_BY_TITLE
            = "SELECT * FROM Post WHERE Title = ?";
    private static final String SQL_SELECT_POST_BY_URLTITLE
            = "SELECT * FROM Post WHERE UrlTitle = ?";
    private static final String SQL_SELECT_POST_BY_TAG_ID
            = "select p.PostId, p.Title, p.UrlTitle, p.AuthorFk, p.Body, p.PublishDate, p.StartDate, p.EndDate, p.StatusFk "
            + " from Post p "
            + "join PostTag pt "
            + "on p.PostId=pt.PostFk "
            + "where pt.TagFk=?";
    private static final String SQL_SELECT_POSTSTAGS_TAGFK_BY_POSTFK
            = "SELECT t.Name FROM Tag t JOIN PostTag bt ON PostFk WHERE t.TagId = bt.TagFk AND bt.PostFk = ?";
    //super cool statements
    private static final String SQL_ACTIVATE_POST
            = "UPDATE Post SET StatusFk = 1 WHERE PostId = ?";
    private static final String SQL_ARCHIVE_POST
            = "UPDATE Post SET StatusFk = 2 WHERE PostId = ?";
    private static final String SQL_GET_ALL_POST_BY_CATEGORY_ID
            = "select p.PostId, p.Title, p.UrlTitle, p.AuthorFk, p.Body, p.PublishDate, p.StartDate, p.EndDate, p.StatusFk "
            + " from Post p "
            + "join PostCategory pc "
            + "on p.PostId=pc.PostFk "
            + "where pc.CategoryFk=? AND p.StatusFk = 1";
    /* StartDate and ExpDate for posts */
//    private static final String SQL_SELECT_PUBLISHED_POST_BY_DATE
//            = "select p.Title, p.AuthorFk, p.Body, p.Date, p.StatusFk"
//            + " from post p, status s"
//            + " where p.StatusFk = s.StatusId and s.StatusName in ('Published')"
//                    + " and"
//                    + " (p.StarDate is null"
//                    + " or p.StartDate <= now())"
//                    + " and (p.ExpDate is null"
//                    + " or p.ExpDate is > now())";
    private JdbcTemplate jdbcTemplate;
    private TagDao daoTag;
    private CategoryDao daoCat;
    private AuthorDao daoAuthor;
    private StatusDao daoStatus;

    public void setJdbcTemplate(JdbcTemplate JdbcTemplate) {
        this.jdbcTemplate = JdbcTemplate;
    }

    public void setDaoTag(TagDao daoTag) {
        this.daoTag = daoTag;
    }

    public void setDaoCat(CategoryDao daoCat) {
        this.daoCat = daoCat;
    }

    public void setDaoAuthor(AuthorDao daoAuthor) {
        this.daoAuthor = daoAuthor;
    }

    public void setDaoStatus(StatusDao daoStatus) {
        this.daoStatus = daoStatus;
    }

    //create post assumes that authorFK, StatusFK is set to nullable 
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post createPost(Post post) {
        try {
            String title = post.getTitle();
            post.setUrlTitle(title.replaceAll("\\s+", "+"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date today = dateFormat.parse(dateFormat.format(new Date()));
            post.setPublishDate(today);
            jdbcTemplate.update(SQL_INSERT_POST,
                    post.getTitle(),
                    post.getUrlTitle(),
                    post.getAuthor().getAuthorId(),
                    post.getBody(),
                    post.getPublishDate(),
                    post.getStatus().getStatusId(),
                    post.getStartDate(),
                    post.getEndDate());
            post.setPostId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

            List<Tag> tagList = post.getTags();
            if (tagList != null) {
                for (Tag tag : tagList) {
                    Tag tagReturned = daoTag.addTag(tag);
                    tag.setTagId(tagReturned.getTagId());
                    jdbcTemplate.update(SQL_INSERT_POSTSTAGS,
                            post.getPostId(),
                            tag.getTagId());
                }
            }

            List<Category> cList = post.getCategories();
            if (cList != null) {
                for (Category category : cList) {
                    jdbcTemplate.update(SQL_INSERT_POSTCATEGORIES,
                            post.getPostId(),
                            category.getCategoryId());
                }
            }
            post.setStatus(daoStatus.getStatusById(post.getStatus().getStatusId()));
            return post;
        } catch (ParseException ex) {
            Logger.getLogger(PostDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePost(int id) {

        jdbcTemplate.update(SQL_DELETE_POSTSTAGS, id);
        jdbcTemplate.update(SQL_DELETE_POSTSCATEGORIES, id);
        jdbcTemplate.update(SQL_DELETE_POST, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST,
                post.getTitle(),
                post.getAuthor().getAuthorId(),
                post.getBody(),
                post.getStartDate(),
                post.getEndDate(),
                post.getStatus().getStatusId(),
                post.getPostId());

        jdbcTemplate.update(SQL_DELETE_POSTSTAGS, post.getPostId());
        jdbcTemplate.update(SQL_DELETE_POSTSCATEGORIES, post.getPostId());

        List<Tag> tagList = post.getTags();
        for (Tag tag : tagList) {
            Tag tagReturned = daoTag.addTag(tag);
            tag.setTagId(tagReturned.getTagId());
            tag.setTagName(tagReturned.getTagName());
            jdbcTemplate.update(SQL_INSERT_POSTSTAGS,
                    post.getPostId(),
                    tag.getTagId());
        }

        List<Category> cList = post.getCategories();
        for (Category category : cList) {
            jdbcTemplate.update(SQL_INSERT_POSTCATEGORIES,
                    post.getPostId(),
                    category.getCategoryId());
        }
        return post;

    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> postList = jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
        for (Post post : postList) {
            post.setTags(daoTag.getTagsByPostId(post.getPostId()));
            post.setCategories(daoCat.getCategoriesByPostId(post.getPostId()));
        }
        return postList;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post getPostById(int id) {
        try {
            Post b = jdbcTemplate.queryForObject(SQL_SELECT_POST_BY_ID, new PostMapper(), id);

            List<Tag> tagList = daoTag.getTagsByPostId(id);
            b.setTags(tagList);

            List<Category> categoryList = daoCat.getCategoriesByPostId(id);
            b.setCategories(categoryList);
            return b;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post getPostByTitle(String title) {
        try {
            Post b = jdbcTemplate.queryForObject(SQL_SELECT_POST_BY_TITLE, new PostMapper(), title);
            int id = b.getPostId();
            List<Tag> tagList = daoTag.getTagsByPostId(id);
            List<Category> categoryList = daoCat.getCategoriesByPostId(id);
            b.setCategories(categoryList);
            b.setTags(tagList);
            return b;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public Post getPostByUrlTitle(String urlTitle) {
        Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST_BY_URLTITLE, new PostMapper(), urlTitle);
        Post postWithTagsAndCat = getPostById(post.getPostId());
        return postWithTagsAndCat;
    }

    @Override
    public void publishPost(int id) {
        jdbcTemplate.update(SQL_ACTIVATE_POST, id);
    }

    @Override
    public void archivePost(int id) {
        jdbcTemplate.update(SQL_ARCHIVE_POST, id);
    }

    @Override
    public List<Post> getPostsByTagId(int tagId) {
        List<Post> listOfPostsByTagId = jdbcTemplate.query(SQL_SELECT_POST_BY_TAG_ID, new PostMapper(), tagId);
        for (Post post : listOfPostsByTagId) {
            List<Tag> tagsListForPost = daoTag.getTagsByPostId(post.getPostId());
            post.setTags(tagsListForPost);

            List<Category> catListForPost = daoCat.getCategoriesByPostId(post.getPostId());
            post.setCategories(catListForPost);
        }
        return listOfPostsByTagId;
    }

    @Override
    public List<Post> getPostByTagName(String tagName) {
        Tag t = daoTag.getTagByTagName(tagName);
        List<Post> listOfPost = getPostsByTagId(t.getTagId());
        return listOfPost;
    }

    @Override
    public List<Post> getPostByCategoryId(int categoryId) {
        List<Post> postList = jdbcTemplate.query(SQL_GET_ALL_POST_BY_CATEGORY_ID, new PostMapper(), categoryId);
        for (Post post : postList) {
            int postId = post.getPostId();
            
            List<Tag> tagsListForPost = daoTag.getTagsByPostId(post.getPostId());
            post.setTags(tagsListForPost);

            List<Category> catListForPost = daoCat.getCategoriesByPostId(post.getPostId());
            post.setCategories(catListForPost);
            
            post = getPostById(postId);
        }
        return postList;
    }

    @Override
    public List<Post> getPostByCategoryName(String CategoryName) {
        Category c = daoCat.getCategoryByName(CategoryName);
        List<Post> listOfPost = getPostByCategoryId(c.getCategoryId());
        return listOfPost;
    }

    @Override
    public List<Post> getAllActivePosts() {
        List<Post> postList = jdbcTemplate.query(SQL_SELECT_ALL_ACTIVE_POSTS, new PostMapper());
        for (Post post : postList) {
            post.setTags(daoTag.getTagsByPostId(post.getPostId()));
            post.setCategories(daoCat.getCategoriesByPostId(post.getPostId()));
        }
        return postList;
    }

    @Override
    public List<Post> getAllActivePostsDateAsc() {
        List<Post> postList = jdbcTemplate.query(SQL_SELECT_ALL_ACTIVE_POSTS_ASC, new PostMapper());
        for (Post post : postList) {
            post.setTags(daoTag.getTagsByPostId(post.getPostId()));
            post.setCategories(daoCat.getCategoriesByPostId(post.getPostId()));
        }
        return postList;
    }

    @Override
    public List<Post> getEmployeePosts() {
        List<Post> pList = jdbcTemplate.query(SQL_SELECT_EMPLOYEE_POSTS, new PostMapper());
        for (Post post : pList) {
            post.setTags(daoTag.getTagsByPostId(post.getPostId()));
            post.setCategories(daoCat.getCategoriesByPostId(post.getPostId()));
        }
        return pList;
    }

    private final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post b = new Post();
            b.setPostId(rs.getInt("PostId"));
            b.setTitle(rs.getString("Title"));
            b.setUrlTitle(rs.getString("UrlTitle"));
            b.setStatus(daoStatus.getStatusById(rs.getInt("StatusFk")));
            b.setAuthor(daoAuthor.getAuthorById(rs.getInt("AuthorFk")));
            b.setBody(rs.getString("Body"));
            b.setStartDate(rs.getDate("StartDate"));
            b.setEndDate(rs.getDate("EndDate"));
            b.setPublishDate(rs.getDate("PublishDate"));

            return b;
        }
    }
    //  ***********************************commented out - pankaj***********************************************************  
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    @Override
//    public Post createPost(Post post) {
//        jdbcTemplate.update(SQL_INSERT_POST,
//                post.getTitle(),
//                post.getAuthor(),
//                post.getDate(),
//                post.getBody(),
//                //post.getStartDate(),
//                //post.getExpDate(),
//                post.getStatusId());
//        post.setPostId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
//
//        //create new tags in db
//        List<Tag> tagList = post.getTags();
//        for (Tag tag : tagList) {
//            jdbcTemplate.update(SQL_INSERT_POSTSTAGS,
//                    post.getPostId(),
//                    tag.getTagId());
//        }
//
//        List<Category> cList = post.getCategories();
//        for (Category category : cList) {
//            jdbcTemplate.update(SQL_INSERT_POSTCATEGORIES,
//                    post.getPostId(),
//                    category.getCategoryId());
//        }
//
//        return post;
//    }
//
//    
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public void updatePost(Post post) {
//        jdbcTemplate.update(SQL_UPDATE_POST,
//                post.getTitle(),
//                post.getAuthor(),
//                post.getBody(),
//                //post.getStartDate(),
//                //post.getExpDate(),
//                post.getDate().toString(),
//                post.getPostId());
//
//        jdbcTemplate.update(SQL_DELETE_POSTSTAGS, post.getPostId());
//        jdbcTemplate.update(SQL_DELETE_POSTSCATEGORIES, post.getPostId());
//
//        List<Tag> tagList = post.getTags();
//        for (Tag tag : tagList) {
//            jdbcTemplate.update(SQL_INSERT_POSTSTAGS,
//                    post.getPostId(),
//                    tag.getTagId());
//        }
//
//        List<Category> cList = post.getCategories();
//        for (Category category : cList) {
//            jdbcTemplate.update(SQL_INSERT_POSTCATEGORIES,
//                    post.getPostId(),
//                    category.getCategoryId());
//        }
//
//    }
//
//
////get postByIdIsCurrently commented out will look at it on friday.... Pankaj
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public Post getPostById(int id) {
//        try {
//            Post b = jdbcTemplate.queryForObject(SQL_SELECT_POST_BY_ID, new PostMapper(), id);
//            List<Tag> tagList = daoTag.getTagsByPostId(id);
//            List<Category> categoryList = daoCat.getCategoriesByPostId(id);
//            b.setCategories(categoryList);
//            b.setTags(tagList);
//            return b;
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//            
//    }
////get post by title is commented out as its not used in sprint 3... will work on sprint 4---- Pankaj
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public Post getPostByTitle(String title) {
//        try {
//            Post b = jdbcTemplate.queryForObject(SQL_SELECT_POST_BY_TITLE, new PostMapper(), title);
//            int id = b.getPostId();
//            List<Tag> tagList = daoTag.getTagsByPostId(id);
//            List<Category> categoryList = daoCat.getCategoriesByPostId(id);
//            b.setCategories(categoryList);
//            b.setTags(tagList);
//            return b;
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//        
//  }
//    //* List Published Post Method  */
//
//    
//
////    @Override
////    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
////    public List<Post> getPublishedPost() {
////        return jdbcTemplate.query(SQL_SELECT_PUBLISHED_POST_BY_DATE, new PostMapper());
////    }
//    **************************************commented out- Pankaj***********************************************************
}
