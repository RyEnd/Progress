/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsg.thevenue.dao.AuthorDao;
import com.tsg.thevenue.dto.Author;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class AuthorTestDao {
    private AuthorDao dao;
    
    public AuthorTestDao() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("authorDao", AuthorDao.class);
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
    public void testAuthorByID(){
        Author author = dao.getAuthorById(2);
        assertEquals(author.getAuthorId(), 2);
    }
    
    @Test
    public void testGetAllAuthors(){
        
        List<Author> authorList = dao.getAllAuthor();        
        assertEquals(authorList.size(), 2);
    }
    
}
