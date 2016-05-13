/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.PostDao;
import com.tsg.thevenue.dao.TagDao;
import com.tsg.thevenue.dto.Author;
import com.tsg.thevenue.dto.Post;
import com.tsg.thevenue.dto.Status;
import com.tsg.thevenue.dto.Tag;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class tagDaoTest {

    private TagDao dao;
    private PostDao daoPost;
    private Tag tag1;
    private Post post1;
    private Post post2;

    public tagDaoTest() {
    }

    @Before
    public void setUp() {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
            dao = ctx.getBean("tagDao", TagDao.class);
            daoPost = ctx.getBean("postDao", PostDao.class);
            JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
            cleaner.execute("delete from PostTag");
            cleaner.execute("delete from PostCategory");
            cleaner.execute("delete from Post");
            cleaner.execute("delete from Tag");
            
            tag1 = new Tag();
            tag1.setTagName("tag1");
            
            Author author1 = new Author();
            author1.setAuthorId(1);
            author1.setAuthorName("Ryan");
            
            Status status1 = new Status();
            status1.setStatusId(1);
            status1.setStatus("Published");
            
            
            List<Tag> tagList = new ArrayList<Tag>();
            Tag tag1 = new Tag();
            tag1.setTagName("#Rock");
            tagList.add(tag1);
            
            Tag tag2 = new Tag();
            tag2.setTagName("#Jazz");
            tagList.add(tag2);
            
            post1 = new Post();
            post1.setTitle("And This is the Title");
            post1.setAuthor(author1);
            post1.setBody("And this can be the body");
            post1.setStatus(status1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date startDate = sdf.parse("2016/05/04");
            //Date startDate = new Date(2016/8/5);
            post1.setStartDate(startDate);
            Date endDate = sdf.parse("2016/05/04");
            post1.setEndDate(endDate);
            post1.setTags(tagList);
        } catch (ParseException ex) {
            Logger.getLogger(tagDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void addTag() {
        // Arrange
        // nothing to do
        
        // Act
        Tag tagAfterAdd = dao.addTag(tag1);
        int tagId = tagAfterAdd.getTagId();
        tag1.setTagId(tagId);

        // Assert
        assertNotEquals( 0, tagId );
        assertEquals( tag1, tagAfterAdd );
        
        
    }
    
    @Test
    public void addDuplicateTagTest() {
        Tag tagAfterAdd1 = dao.addTag(tag1);
        Tag tagAfterAdd2 = dao.addTag(tag1);
        assertEquals(tagAfterAdd1, tagAfterAdd2);
    }

    @Test
    public void deleteTagTest(){
        //Method not used..... 
    }
    
    @Test
    public void updateTagTest(){
        //Method not used
    }

    @Test
    public void getTagById() {
        // Arrange Test Data
        Tag tagAfterAdd = dao.addTag(tag1);
        int tagId = tagAfterAdd.getTagId();
        
        // Act
        Tag tagFromDb = dao.getTagById(tagId);

        // Assert
        assertEquals(tagAfterAdd, tagFromDb);
    }   
    
    @Test
    public void getAllTagTest(){
        //Method not used yet
    }
    
    @Test
    public void getTagsByPostIdTest(){
        //Arrange 
        Post post = daoPost.createPost(post1);
        //Act
        List<Tag> tagsList = dao.getTagsByPostId(post.getPostId());
        List<Tag> postTagsList = post.getTags();
        //Assert
        assertEquals(postTagsList.size(), tagsList.size());
    }
    
    @Test
    public void getTagsByPostIdTestWhenNoTagsAreAssociatedWithThePost(){
        //Arrange
        post1.setTags(null);
        Post post = daoPost.createPost(post1);
         //Act
         List<Tag> tagsList = dao.getTagsByPostId(post.getPostId());
         
         //Assert
         assertEquals(post.getTags(), tagsList);
    }
    
    @Test
    public void getTagByTagNameTest(){
        //Arrange
        Tag tag = dao.addTag(tag1);
        //Act
        Tag tagFromDb = dao.getTagByTagName(tag1.getTagName());
        //Assert
        assertEquals(tag, tagFromDb);
        
    }
    

}
