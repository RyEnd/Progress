package com.tsg.flooringmastery.data;

import com.tsg.flooringmastery.model.Product;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class ProductAccessDAOJUnitTest {
    ProductAccessDAO dao;
    Product product1, product2, product3, product4;
    
    public ProductAccessDAOJUnitTest() {
    }
    
    @Before
    public void setUp() {
        dao = new ProductAccessDAO();
        
        product1 = new Product();
        product1.setType("Carpet");
        product1.setSqFtLaborCost(2.25);
        product1.setSqFtMaterialCost(2.10);
        
        product2 = new Product();
        product2.setType("Laminate");
        product2.setSqFtLaborCost(1.75);
        product2.setSqFtMaterialCost(2.10);
        
        product3 = new Product();
        product3.setType("Tile");
        product3.setSqFtLaborCost(3.50);
        product3.setSqFtMaterialCost(4.15);
        
        product4 = new Product();
        product4.setType("Wood");
        product4.setSqFtLaborCost(5.15);
        product4.setSqFtMaterialCost(4.75);
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
    public void loadGetProductAccessTest()
    {
        ProductAccessDAO readDAO = new ProductAccessDAO();
        
        try {
            readDAO.loadProducts();
        } catch (FileNotFoundException ex) {
            Assert.fail();
        }
        
        Product readS1 = readDAO.getProductInfo(1);
        Product readS2 = readDAO.getProductInfo(2);
        Product readS3 = readDAO.getProductInfo(3);
        Product readS4 = readDAO.getProductInfo(4);
        
        Assert.assertEquals(product1.getSqFtLaborCost(), readS1.getSqFtLaborCost());
        Assert.assertEquals(product2.getSqFtLaborCost(), readS2.getSqFtLaborCost());
        Assert.assertEquals(product3.getSqFtLaborCost(), readS3.getSqFtLaborCost());
        Assert.assertEquals(product4.getSqFtLaborCost(), readS4.getSqFtLaborCost());
        
        Assert.assertEquals(product1.getSqFtMaterialCost(), readS1.getSqFtMaterialCost());
        Assert.assertEquals(product2.getSqFtMaterialCost(), readS2.getSqFtMaterialCost());
        Assert.assertEquals(product3.getSqFtMaterialCost(), readS3.getSqFtMaterialCost());
        Assert.assertEquals(product4.getSqFtMaterialCost(), readS4.getSqFtMaterialCost());
    }
}
