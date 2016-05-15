/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.PageDao;
import com.tsg.thevenue.dto.Page;
import java.util.List;
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
public class PageDaoUnitTest {

    private PageDao dao;
    private Page page1;
    private Page page2;
    private Page page3;
    private Page page4;

    Page p1;
    Page p2;

    public PageDaoUnitTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("pageDao", PageDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from Page");
        cleaner.execute("delete from PostTag");
        cleaner.execute("delete from PostCategory");
        cleaner.execute("delete from Post");
        cleaner.execute("delete from Tag");
        cleaner.execute("delete from Category");

        p1 = new Page();
        p1.setTitle("Ab  cd");
        p1.setBody("Body 1");
        p1.setNavName("NavName1");

        p2 = new Page();
        p2.setTitle("title for page2");
        p2.setNavName("Page2");
        p2.setBody("Body for page2");

//
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addPageTest() {

        Page page1 = dao.createPage(p1);
        int idPage1 = page1.getPageId();
        Page fromDB1 = dao.getPageById(idPage1);
        assertNotEquals(fromDB1.getPageId(), 0);
        assertEquals(fromDB1.getUrlTitle(), "Ab+cd");
//        
    }

    @Test
    public void getPageByIdTest() {
        //Arrange
        Page page1 = dao.createPage(p1);
        //Act
        Page pageFromDb = dao.getPageById(page1.getPageId());

        //Assert
        assertEquals(page1, pageFromDb);

    }

    @Test
    public void getPageByUrlTitleTest() {
        //Arrange
        Page page1 = dao.createPage(p1);
        //Act
        Page pageFromDb = dao.getPageByURLTitle(page1.getUrlTitle());

        //Assert
        assertEquals(page1, pageFromDb);

    }

    @Test
    public void getPageByTitleTest() {
        //Arrange
        Page page1 = dao.createPage(p1);
        //Act
        Page pageFromDb = dao.getPageByTitle(page1.getTitle());

        //Assert
        assertEquals(page1, pageFromDb);

    }

    @Test
    public void deleteTestPage() {
        //Arrange
        Page page1 = dao.createPage(p1);
        //Act
        dao.deletePage(page1.getPageId());
        Page pageFromDB = dao.getPageById(page1.getPageId());
        //Assert
        assertNull(pageFromDB);
    }

    @Test
    public void previewpage() {
        //Arrange 
        Page page = dao.previewPage(p1);
        //Assert
        assertEquals(p1.getBody(), page.getBody());
        assertEquals(p1.getTitle(), page.getTitle());

    }

    @Test
    public void updatePage() {
        //Arrange 
        Page page = dao.createPage(p1);
        page.setNavName("UpdatedPage");
        page.setBody("This is the new Body");
        //Act
        dao.updatePage(page);
        Page pageFromDb = dao.getPageById(page.getPageId());
        assertEquals(page, pageFromDb);

    }

    @Test
    public void getChildPages() {
        Page page1 = dao.createPage(p1);
        p2.setParentPageFk(page1.getPageId());
        Page page2 = dao.createPage(p2);

        //Act
        List<Page> listOfChildPages = dao.getChildPages(page1.getPageId());
        //Assert
        assertEquals(listOfChildPages.size(), 1);
        assertEquals(listOfChildPages.get(0), page2);

    }

    @Test
    public void getAllPagesTest() {
        //Arrange
        Page page1 = dao.createPage(p1);
        p2.setParentPageFk(page1.getPageId());
        Page page2 = dao.createPage(p2);

        //Act
        List<Page> listOfAllPages = dao.getAllPages();

        //Assert
        assertEquals(listOfAllPages.size(), 2);

    }

}
