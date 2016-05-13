/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.NavBarLocationDao;
import com.tsg.thevenue.dao.PageDao;
import com.tsg.thevenue.dto.NavBarLocation;
import com.tsg.thevenue.dto.Page;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class NavBarLocationDaoUnitTest {

    NavBarLocationDao dao;
    PageDao daoPage;
    NavBarLocation navBarLoc1;
    NavBarLocation navBarLoc2;
    Page pageAfterAdd2;

    public NavBarLocationDaoUnitTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("navBarLocationDao", NavBarLocationDao.class);
        daoPage = ctx.getBean("pageDao", PageDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from Page");

        Page page1 = new Page();
        page1.setTitle("This is title 1");
        page1.setBody("This is body for the title 1");
        page1.setNavName("NavName");

        Page pageAfterAdd1 = daoPage.createPage(page1);

        Page page2 = new Page();
        page2.setTitle("This is title 2");
        page2.setBody("This is body for the title 2");
        page2.setNavName("NavName2");

        pageAfterAdd2 = daoPage.createPage(page2);

        dao.clearAllSlots();

        navBarLoc1 = new NavBarLocation();
        navBarLoc1.setNavBarLocationId(1);
        navBarLoc1.setPageFk(pageAfterAdd1.getPageId());
        navBarLoc1.setPage(pageAfterAdd1);

        navBarLoc2 = new NavBarLocation();
        navBarLoc2.setNavBarLocationId(2);
        navBarLoc2.setPageFk(pageAfterAdd2.getPageId());

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
    public void getAllTest() {
        List<NavBarLocation> nvblList = dao.getAll();
        int nvblSize = nvblList.size();

        Assert.assertEquals(nvblSize, 20);
    }

    @Test
    public void setSlotTest() {
        NavBarLocation nav = dao.setSlot(navBarLoc1);

        Assert.assertNotNull(nav);

    }

    @Test
    public void getFilledSlotsTest() {
        //Arrange
        NavBarLocation nav1 = dao.setSlot(navBarLoc1);
        NavBarLocation nav2 = dao.setSlot(navBarLoc2);

        //Act
        List<NavBarLocation> listOfFilledNavBars = dao.getFilledSlots();

        //Assert
        Assert.assertEquals(listOfFilledNavBars.size(), 2);
    }

    @Test
    public void getHomeSlotTest() {
        //Arrange  
        NavBarLocation nav1 = dao.setSlot(navBarLoc1); //nav1 is set to home
        NavBarLocation nav2 = dao.setSlot(navBarLoc2);
        //Act
        NavBarLocation homeNavBar = dao.getHomeSlot();

        //assert
        Assert.assertEquals(nav1, homeNavBar);
    }

    @Test
    public void clearSlotTest() {
        //Arrange
        NavBarLocation nav1 = dao.setSlot(navBarLoc1);

        //Act
        dao.clearSlot(nav1.getNavBarLocationId());

        List<NavBarLocation> listOfFilledNavBars = dao.getFilledSlots();

        //Assert
        Assert.assertEquals(listOfFilledNavBars.size(), 0);

    }

    @Test
    public void clearAllSlotsTest() {
        //Arrange
        NavBarLocation nav1 = dao.setSlot(navBarLoc1); //nav1 is set to home
        NavBarLocation nav2 = dao.setSlot(navBarLoc2);

        //Act
        dao.clearAllSlots();
        List<NavBarLocation> listOfFilledNavBars = dao.getFilledSlots();
        //Assert

        Assert.assertEquals(listOfFilledNavBars.size(), 0);
    }

    @Test
    public void getPageByNavBarLocationTest() {
        //Arrange 
        NavBarLocation nav1 = dao.setSlot(navBarLoc1); //nav1 is set to home
        NavBarLocation nav2 = dao.setSlot(navBarLoc2);

        //Act
        Page page = dao.getPageByNvbl(nav1);

        //Assert
        Assert.assertEquals(page, nav1.getPage());

    }

    @Test
    public void getUnassignedPagesTest() {
        List<Page> getUnassignedListOfPages1 = dao.getUnassignedPages();
        //Assert1
        Assert.assertEquals(getUnassignedListOfPages1.size(), 2);

        NavBarLocation nav1 = dao.setSlot(navBarLoc1); //nav1 is set to home
        List<Page> getUnassignedListOfPages2 = dao.getUnassignedPages();

        //Assert2
        Assert.assertEquals(getUnassignedListOfPages2.size(), 1);

    }

    @Test
    public void setSlotToPageTest() {
        //Arrange 
        NavBarLocation nav1 = dao.setSlot(navBarLoc1);
        //Act
        dao.setSlotToPage(pageAfterAdd2.getPageId(), 5);
        List<NavBarLocation> listOfFilledNavBars = dao.getFilledSlots();
        //Assert
        Assert.assertEquals(listOfFilledNavBars.size(), 2);

    }

}
