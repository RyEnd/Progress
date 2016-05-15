package com.tsg.flooringmastery.controller;

import com.tsg.flooringmastery.data.ProductAccessDAO;
import com.tsg.flooringmastery.data.StateAccessDAO;
import com.tsg.flooringmastery.model.Order;
import com.tsg.flooringmastery.ui.ConsoleIO;
import java.io.IOException;
import java.text.DecimalFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class BusinessLogicJUnitTest {

    ProductAccessDAO productInfo = new ProductAccessDAO();
    StateAccessDAO stateInfo = new StateAccessDAO();
    ConsoleIO io = new ConsoleIO();
    DecimalFormat df = new DecimalFormat("#.##");
    BusinessLogic biz = new BusinessLogic(productInfo, stateInfo, io);

    public BusinessLogicJUnitTest() {
    }

    @Before
    public void setUp() {

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
    public void createOrderTest() throws IOException {

        productInfo.loadProducts();
        stateInfo.loadStates();

        Order order;
        String date = "12/12/2015";
        String name = "Dave";
        Integer area = 4372;
        Integer orderNum = 5;
        Integer product = 2;
        Integer state = 1;

        Order order1 = new Order();
        order1.setOrderNum(5);
        order1.setCustomerName("Dave");
        order1.setState("OH");
        order1.setTaxRate(6.25);
        order1.setProductType("Laminate");
        order1.setArea(4372);
        order1.setSqFtMaterialCost(2.1);
        order1.setSqFtLaborCost(1.75);
        order1.setTotalMaterialCost(Double.parseDouble(df.format(2.1 * 4372.0)));
        order1.setTotalLaborCost(Double.parseDouble(df.format(1.75 * 4372.0)));
        order1.setTotalTax(Double.parseDouble(df.format((6.25 / 100) * ((1.75 * 4372.0) + (2.1 * 4372.0)))));
        order1.setTotalTotal(Double.parseDouble(df.format((6.25 / 100) * ((1.75 * 4372.0) + (2.1 * 4372.0)) + (1.75 * 4372.0) + (2.1 * 4372.0))));
        order1.setDate("12122015");

        Order test = biz.createOrder(orderNum, name, state, product, date, area);

        Assert.assertEquals(test.getTotalTotal(), order1.getTotalTotal());
        Assert.assertEquals(test.getTotalTax(), order1.getTotalTax());
        Assert.assertEquals(test.getTotalLaborCost(), order1.getTotalLaborCost());
        Assert.assertEquals(test.getTotalMaterialCost(), order1.getTotalMaterialCost());


        Integer orderNum2 = 4;
        String name2 = "Super Dave";
        Integer state2 = 3;
        Integer product2 = 3;
        Integer area2 = 4999;
        String date2 = "01012016";

        Order order2 = new Order();
        order2.setCustomerName(name2);
        order2.setDate(date2);
        order2.setArea(4999);
        order2.setOrderNum(orderNum2);
        order2.setProductType(productInfo.getProductInfo(product2).getType());
        order2.setState(stateInfo.getStateInfo(state2).getName());
        Double laborNum = productInfo.getProductInfo(product2).getSqFtLaborCost();
        order2.setSqFtLaborCost(laborNum);
        Double materialNum = productInfo.getProductInfo(product2).getSqFtMaterialCost();
        order2.setSqFtMaterialCost(materialNum);
        Double tax = stateInfo.getStateInfo(state2).getTax();
        order2.setTaxRate(tax);
        order2.setTotalLaborCost(Double.parseDouble(df.format(laborNum * area2)));
        order2.setTotalMaterialCost(Double.parseDouble(df.format(materialNum * area2)));
        order2.setTotalTax(Double.parseDouble(df.format((tax / 100) * ((materialNum * area2) + (laborNum * area2)))));
        order2.setTotalTotal(Double.parseDouble(df.format((tax / 100) * ((materialNum * area2) + (laborNum * area2)) + (laborNum * area2) + (materialNum * area2))));

        Order test2 = biz.createOrder(orderNum2, name2, state2, product2, date2, area2);

        Assert.assertEquals(order2.getTotalLaborCost(), test2.getTotalLaborCost());
        Assert.assertEquals(order2.getTotalMaterialCost(), test2.getTotalMaterialCost());
        Assert.assertEquals(order2.getTotalTax(), test2.getTotalTax());
        Assert.assertEquals(order2.getTotalTotal(), test2.getTotalTotal());
    }
}
