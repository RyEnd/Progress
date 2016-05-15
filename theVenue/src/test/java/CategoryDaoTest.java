/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.CategoryDao;

import com.tsg.thevenue.dao.PostDao;
import com.tsg.thevenue.dao.TagDao;
import com.tsg.thevenue.dto.Author;

import com.tsg.thevenue.dto.Category;
import com.tsg.thevenue.dto.Post;
import com.tsg.thevenue.dto.Status;
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
public class CategoryDaoTest {

    CategoryDao dao;
    PostDao daoPost;
    Category cat1;
    Category cat2;
    Category cat3;
    List<Category> categoryList;
    private Post post1;

    public CategoryDaoTest() {

    }

    @Before
    public void setUp() {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
            dao = ctx.getBean("categoryDao", CategoryDao.class);
            daoPost = ctx.getBean("postDao", PostDao.class);
            JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");

           cleaner.execute("delete from PostTag");
            cleaner.execute("delete from PostCategory");
            cleaner.execute("delete from Post");
            cleaner.execute("delete from Tag");
            cleaner.execute("delete from Category");
            cat1 = new Category();
            cat2 = new Category();
            cat3 = new Category();

            cat1.setCategoryName("Music");
            cat2.setCategoryName("Food");
            cat3.setCategoryName("Food");

            Author author1 = new Author();
            author1.setAuthorId(1);
            author1.setAuthorName("Ryan");

            Status status1 = new Status();
            status1.setStatusId(1);
            status1.setStatus("Published");

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

        } catch (ParseException ex) {
            Logger.getLogger(CategoryDaoTest.class.getName()).log(Level.SEVERE, null, ex);
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
    public void createCategoriesTest() {
        //Arrange and Act
        Category catAfterAdd = dao.createCategory(cat1);
        int cat1Id = catAfterAdd.getCategoryId();

        assertNotEquals(0, cat1Id);
        assertEquals(cat1, catAfterAdd);
    }

    @Test
    public void TestGetAllCategories() {
        dao.createCategory(cat1);
        dao.createCategory(cat2);

        List<Category> catList = dao.getAllCategories();
        assertEquals(catList.size(), 2);

    }

    @Test
    public void testCreateWithDuplicateCategories() {
        Category catA = dao.createCategory(cat1);
        Category catB = dao.createCategory(cat2);
        Category catC = dao.createCategory(cat3);

        List<Category> catList = dao.getAllCategories();
        assertEquals(catList.size(), 2);
        assertEquals(catList.get(0), catA);
        assertEquals(catList.get(1), catB);
        assertEquals(catB, catC);

    }

    @Test
    public void testGetCategoryById() {
        Category catA = dao.createCategory(cat1);
        Category catFromDb = dao.getCategoryById(catA.getCategoryId());

        assertEquals(catA, catFromDb);

    }

    @Test
    public void testGetCategoryByName() {
        Category catA = dao.createCategory(cat1);
        Category categoryFromDb = dao.getCategoryByName(catA.getCategoryName());
        assertEquals(categoryFromDb, catA);
    }

    @Test
    public void deleteCategoryTest() {
        //Method not used anywhere....
    }

    @Test
    public void updateCategoryTest() {
        //Method not used anywhere....
    }

    @Test
    public void getCategoriesByPostIdTest() {
        //Arrange
        categoryList = new ArrayList<Category>();
        Category catA = dao.createCategory(cat1);
        Category catB = dao.createCategory(cat2);
        categoryList.add(catA);
        categoryList.add(catB);
        post1.setCategories(categoryList);

        Post post = daoPost.createPost(post1);
        //Act
        List<Category> categoriesListFromDb = dao.getCategoriesByPostId(post.getPostId());
        //Assert
        assertEquals(post.getCategories(), categoriesListFromDb);
    }

    @Test
    public void getCategoryByNameTest() {
        //Arrange
        Category catAfterAdd = dao.createCategory(cat1);
        //Act
        Category categoryFromDb = dao.getCategoryByName(catAfterAdd.getCategoryName());
        //Assert
        assertEquals(catAfterAdd, categoryFromDb);
        
    }
}
