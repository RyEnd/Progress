/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.NavBarLocationDao;
import com.tsg.thevenue.dao.PageDao;
import com.tsg.thevenue.dto.NavBarLocation;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class TestForTempNavBarLocation {
    private NavBarLocationDao dao;
    public TestForTempNavBarLocation() {
    }
    
    @Before
    public void setUp() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("navBarLocationDao", NavBarLocationDao.class);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Ignore
    @Test
    public void getAllNavBarLocations(){
        
     List<NavBarLocation> list=  dao.getFilledSlots();
        assertEquals(list.size(), 13);
    }
}
