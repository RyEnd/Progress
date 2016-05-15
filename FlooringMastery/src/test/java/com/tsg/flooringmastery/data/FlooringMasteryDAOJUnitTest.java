package com.tsg.flooringmastery.data;

import com.tsg.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class FlooringMasteryDAOJUnitTest {
    FlooringMasteryDAO dao;
    Order order1, order2, order3, order4;
    
    public FlooringMasteryDAOJUnitTest() {
    }
    
    @Before
    public void setUp() {
        dao = new FlooringMasteryDAO();
        order1 = new Order();
        order1.setOrderNum(1);
        order1.setCustomerName("Jimmy");
        order1.setState("OH");
        order1.setTaxRate(6.25);
        order1.setProductType("Carpet");
        order1.setArea(1500); 
        order1.setSqFtMaterialCost(2.25);
        order1.setSqFtLaborCost(2.10); 
        order1.setTotalMaterialCost(3375.0);
        order1.setTotalLaborCost(3150.0);
        order1.setTotalTax(407.8125);
        order1.setTotalTotal(6932.8125);
        order1.setDate("20160122");
        
        order2 = new Order();
        order2.setOrderNum(2);
        order2.setCustomerName("Klammy");
        order2.setState("PA");
        order2.setTaxRate(6.75);
        order2.setProductType("Tile");
        order2.setArea(2500); 
        order2.setSqFtMaterialCost(3.50);
        order2.setSqFtLaborCost(4.15); 
        order2.setTotalMaterialCost(8750.0);
        order2.setTotalLaborCost(10375.0);
        order2.setTotalTax(1290.9375);
        order2.setTotalTotal(20415.9375);
        order2.setDate("20160122");
        
        order3 = new Order();
        order3.setOrderNum(3);
        order3.setCustomerName("Crabson");
        order3.setState("IN");
        order3.setTaxRate(6.00);
        order3.setProductType("Wood");
        order3.setArea(1100); 
        order3.setSqFtMaterialCost(5.15);
        order3.setSqFtLaborCost(4.75); 
        order3.setTotalMaterialCost(5665.0);
        order3.setTotalLaborCost(5225.0);
        order3.setTotalTax(653.4);
        order3.setTotalTotal(11543.4);
        order3.setDate("20160122");
        
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
    public void removeOrderTest()
    {
        dao.addCurrentInfo(order1.getOrderNum(), order1);
        dao.addCurrentInfo(order2.getOrderNum(), order2);
        dao.addCurrentInfo(order3.getOrderNum(), order3);
        
        dao.removeOrder(order2.getOrderNum());
        
        Integer[] result = dao.getOrderList();
        Integer[] orderList = new Integer[] {1, 3};
        
        Assert.assertArrayEquals(orderList, result);
    }
    
    @Test
    public void getOrderListTest()
    {
        dao.addCurrentInfo(order1.getOrderNum(), order1);
        dao.addCurrentInfo(order2.getOrderNum(), order2);
        dao.addCurrentInfo(order3.getOrderNum(), order3);

        
        Integer[] result = dao.getOrderList();
        Integer[] orderList = new Integer[] {1, 2, 3};
        
        Assert.assertArrayEquals(orderList, result);
    }
    
    @Test
    public void addGetCurrentInfoTest()
    {
        dao.addCurrentInfo(order1.getOrderNum(), order1);
        
        Order result = dao.getCurrentInfo(order1.getOrderNum());
        
        Assert.assertEquals(order1, result);
    }

    @Test
    public void saveLoadCurrentInfo()
    {
        dao.addCurrentInfo(order1.getOrderNum(), order1);
        dao.addCurrentInfo(order2.getOrderNum(), order2);
        dao.addCurrentInfo(order3.getOrderNum(), order3);
        
        FlooringMasteryDAO readDAO = new FlooringMasteryDAO();
        
        try {
            dao.writeCurrentInfo();
        } catch (IOException ex) {
            Assert.fail();
            Logger.getLogger(FlooringMasteryDAOJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            readDAO.loadCurrentInfo("20160122");
        } catch (FileNotFoundException ex) {
            Assert.fail();
        }
        
        Integer[] result = dao.getOrderList();
        Integer[] orderList = new Integer[] {1, 2, 3};
        
        Assert.assertArrayEquals(orderList, result);
    }
}
