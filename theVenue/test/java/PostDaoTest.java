/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.CategoryDao;
import com.tsg.thevenue.dao.PostDao;
import com.tsg.thevenue.dto.Author;
import com.tsg.thevenue.dto.Category;
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
public class PostDaoTest {

    private PostDao dao;
    private CategoryDao daoCat;
    private Post post1;
    private Post post2;

    public PostDaoTest() {

    }

    @Before
    public void setUp() {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
            dao = ctx.getBean("postDao", PostDao.class);
            daoCat = ctx.getBean("categoryDao", CategoryDao.class);
            JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
            cleaner.execute("delete from PostTag");
            cleaner.execute("delete from PostCategory");
            cleaner.execute("delete from Post");
            cleaner.execute("delete from Tag");
            cleaner.execute("delete from Category");

            Author author1 = new Author();
            author1.setAuthorId(1);
            author1.setAuthorName("Ryan");

            Status status1 = new Status();
            status1.setStatusId(1);
            status1.setStatus("Published");

            List<Tag> tagList = new ArrayList<>();
            Tag tag1 = new Tag();
            tag1.setTagName("#Rock");
            tagList.add(tag1);

            Tag tag2 = new Tag();
            tag2.setTagName("#Jazz");
            tagList.add(tag2);

            List<Category> catList = new ArrayList<>();
            Category cat1 = new Category();
            cat1.setCategoryName("Music");
            Category catA = daoCat.createCategory(cat1);
            catList.add(catA);

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
            post1.setCategories(catList);

            post2 = new Post();
            post2.setTitle("And This is the Title for post 2");
            post2.setAuthor(author1);
            post2.setBody("And this can be the body for Post 2");
            post2.setStatus(status1);
            post2.setTags(tagList);
            post2.setCategories(catList);

        } catch (ParseException ex) {
            Logger.getLogger(PostDaoTest.class.getName()).log(Level.SEVERE, null, ex);
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
    //This method is to test if DAO is talking with the database
//    @Test
//    public void addPostTest(){
//       Post post = dao.createPost(post1);
//       
//       Post postFromDB = dao.getPostById(post.getPostId());
//        assertEquals(0, 0);
//       // assertEquals(post1, postFromDB);
//    }
    @Test
    public void createPostTest() {
        Post post = dao.createPost(post1);
        int postId = post.getPostId();
        post1.setPostId(postId);

        assertNotEquals(post.getPostId(), 0);
        assertEquals(post, post1);

    }

    @Test
    public void deletePostTest() {
        Post post = dao.createPost(post1);
        dao.deletePost(post.getPostId());
        Post postFromDb = dao.getPostById(post.getPostId());
        assertNull(postFromDb);
    }
    @Ignore
    @Test
    public void getPostByIdTest() {
        Post post = dao.createPost(post1);
        Post postFromDb = dao.getPostById(post.getPostId());
        assertEquals(post, postFromDb);
    }
    @Ignore
    @Test
    public void getPostByTitleTest() {
        Post post = dao.createPost(post1);
        Post postFromDb = dao.getPostByTitle(post.getTitle());
        assertEquals(post, postFromDb);
    }
    @Ignore
    @Test
    public void getPostByUrlTitleTest() {
        Post post = dao.createPost(post1);
        Post postFromDb = dao.getPostByUrlTitle(post.getUrlTitle());
        assertEquals(post, postFromDb);
    }
    @Ignore
    @Test
    public void getAllPost() {
        //Arrange
        Post postA = dao.createPost(post1);
        Post postB = dao.createPost(post2);
        //Arrange
        List<Post> listOfPosts = dao.getAllPosts();
        
        //Assert
        assertEquals(listOfPosts.size(), 2);
        assertEquals(postA, listOfPosts.get(0));
        assertEquals(postB, listOfPosts.get(1));
    }

    @Test
    public void publishPostTest() {
        //Arrange
        Status status = new Status();
        status.setStatusId(2);
        status.setStatus("Archived");
        post1.setStatus(status);
        Post postAdded = dao.createPost(post1);

        //Act
        dao.publishPost(postAdded.getPostId());
        Post postFromDb = dao.getPostById(postAdded.getPostId());
        assertEquals(postFromDb.getStatus().getStatus(), "Published");
    }

    @Test
    public void archivePostTest() {
        //Arrange
        Status status = new Status();
        status.setStatusId(1);
        status.setStatus("Published");
        post1.setStatus(status);
        Post postAdded = dao.createPost(post1);

        //Act
        dao.archivePost(postAdded.getPostId());
        Post postFromDb = dao.getPostById(postAdded.getPostId());
        assertEquals(postFromDb.getStatus().getStatus(), "Archived");
    }

    @Test
    public void updatePostTest() {
        //Arrange
        Post postAfterAdd = dao.createPost(post1);
        Post postFromDBAfterAdd = dao.getPostById(postAfterAdd.getPostId());

        List<Tag> tListUpdate = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setTagName("#Food");
        tListUpdate.add(tag1);

        Tag tag2 = new Tag();
        tag2.setTagName("#Drinks");
        tListUpdate.add(tag2);

        postFromDBAfterAdd.setTags(tListUpdate);

        Post postAfterUpdate = dao.updatePost(postFromDBAfterAdd);
        Post postAfterUpdateFromDb = dao.getPostById(postAfterAdd.getPostId());

        assertNotEquals(postAfterAdd, postAfterUpdateFromDb);
        assertEquals(postAfterAdd.getPostId(), postAfterUpdateFromDb.getPostId());
    }
    @Ignore
    @Test
    public void getPostByTagName() {
        //Arrange     
        Post postA = dao.createPost(post1);
        Post postB = dao.createPost(post2);
        //Act
        List<Post> listOfPostWithTagName = dao.getPostByTagName(postA.getTags().get(0).getTagName());
        
        //Assert
        assertEquals(listOfPostWithTagName.size(), 2);
        assertEquals(listOfPostWithTagName.get(0), postA);

    }
    @Ignore
    @Test
    public void getPostByTagId() {
        //Arrange     
        Post postA = dao.createPost(post1);
        Post postB = dao.createPost(post2);
        //Act
        List<Post> listOfPostWithTagName = dao.getPostsByTagId(postA.getTags().get(0).getTagId());
        
        //Assert
        assertEquals(listOfPostWithTagName.size(), 2);
        assertEquals(listOfPostWithTagName.get(0), postA);

    }
    @Ignore
    @Test
    public void getPostByCategoryIdTest(){
        Post postA = dao.createPost(post1);
        Post postB = dao.createPost(post2);
        
        List<Category> catList = postA.getCategories();
        Category category1 = catList.get(0);
        List<Post> listOfPostWithCat = dao.getPostByCategoryId(category1.getCategoryId());
        
        assertEquals(postA, listOfPostWithCat.get(0));
        assertEquals(postB, listOfPostWithCat.get(1));
        assertEquals(listOfPostWithCat.size(), 2);
        
    }
    @Ignore
    @Test
    public void getPostByCategoryNameTest(){
        Post postA = dao.createPost(post1);
        Post postB = dao.createPost(post2);
        
        List<Category> catList = postA.getCategories();
        Category category1 = catList.get(0);
        List<Post> listOfPostWithCat = dao.getPostByCategoryName(category1.getCategoryName());
        
        assertEquals(postA, listOfPostWithCat.get(0));
        assertEquals(postB, listOfPostWithCat.get(1));
        assertEquals(listOfPostWithCat.size(), 2);
        
    }
    
    @Test
    public void getAllActivePostTest(){
        //Arrange both the post are currently active
        Post postA = dao.createPost(post1);
        Post postB = dao.createPost(post2);
        
        List<Post> activePostList = dao.getAllActivePosts();
        
        assertEquals(activePostList.size(), 2);
        //change the post 1 from active to archive
        
        
        
        
    }
    @Ignore
    @Test
    public void getActivePostCondition2Test(){
        Status status = new Status();
        status.setStatus("Archive");
        status.setStatusId(2);        
        post1.setStatus(status);
        
        
        Post postC = dao.createPost(post1);
        Post postD = dao.createPost(post2);
        List<Post> activePostList = dao.getAllActivePosts();
        
        assertEquals(activePostList.size(), 1);
        assertEquals(activePostList.get(0), postD);
        assertNotEquals(activePostList.get(0), postC);
    }
    
    
    
    
    
    

}
