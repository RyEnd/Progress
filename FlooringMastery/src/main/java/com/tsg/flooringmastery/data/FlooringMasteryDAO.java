package com.tsg.flooringmastery.data;

import com.tsg.flooringmastery.ui.ConsoleIO;
import com.tsg.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class FlooringMasteryDAO {

    ConsoleIO io = new ConsoleIO();
    static Map<Integer, Order> currentSessionInfo = new HashMap<>();
    static HashMap<String, String> fileMap = new HashMap<>();
    private Set<Integer> keys;
    private Set<String> filekey;
    static final String DELIMITER = "::";
    public Integer orderNum = 1;

    public void loadCurrentInfo(String date) throws FileNotFoundException {
        String ORDER_FILE = "order_" + date + ".txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order order = new Order();
            order.setOrderNum(Integer.parseInt(currentTokens[0]));
            order.setCustomerName(currentTokens[1]);
            order.setState(currentTokens[2]);
            order.setTaxRate(Double.parseDouble(currentTokens[3]));
            order.setProductType(currentTokens[4]);
            order.setArea(Integer.parseInt(currentTokens[5]));
            order.setSqFtMaterialCost(Double.parseDouble(currentTokens[6]));
            order.setSqFtLaborCost(Double.parseDouble(currentTokens[7]));
            order.setTotalLaborCost(Double.parseDouble(currentTokens[8]));
            order.setTotalMaterialCost(Double.parseDouble(currentTokens[9]));
            order.setTotalTax(Double.parseDouble(currentTokens[10]));
            order.setTotalTotal(Double.parseDouble(currentTokens[11]));
            order.setDate(currentTokens[12]);
        }
    }

    public void writeCurrentInfo() throws IOException {
        filekey = fileMap.keySet();
        keys = currentSessionInfo.keySet();
        for (String key : filekey) {
            PrintWriter writer = new PrintWriter(new FileWriter(fileMap.get(key)));
            
            for (Integer o : keys) {

                Order order = currentSessionInfo.get(o);
                String something = "order_" + order.getDate() + ".txt";
                if(something.equals(fileMap.get(key)))
                {
                writer.println(order.getOrderNum() + DELIMITER
                        + order.getCustomerName() + DELIMITER
                        + order.getState() + DELIMITER
                        + order.getTaxRate() + DELIMITER
                        + order.getProductType() + DELIMITER
                        + order.getArea() + DELIMITER
                        + order.getSqFtMaterialCost() + DELIMITER
                        + order.getSqFtLaborCost() + DELIMITER
                        + order.getTotalLaborCost() + DELIMITER
                        + order.getTotalMaterialCost() + DELIMITER
                        + order.getTotalTax() + DELIMITER
                        + order.getTotalTotal() + DELIMITER
                        + order.getDate());
                writer.flush();
                }
            }
            writer.close();
        }
    }

    public Order getCurrentInfo(Integer index) {
        return currentSessionInfo.get(index);
    }

    public Integer[] getOrderList() {
        Set<Integer> keySet = currentSessionInfo.keySet();
        Integer[] keyArray = new Integer[keySet.size()];
        keyArray = keySet.toArray(keyArray);
        return keyArray;
    }

    public void addCurrentInfo(Integer index, Order order) {
        currentSessionInfo.put(index, order);
    }

    public String viewDateInfo(String date) throws FileNotFoundException {
        keys = currentSessionInfo.keySet();
        String string = "";

        for (int key : keys) {
            if (currentSessionInfo.get(key).getDate().equals(date)) {
                string += "\n********************************************\n* Order #: " + currentSessionInfo.get(key).getOrderNum() + "\t\tDate: " + currentSessionInfo.get(key).getDate() + "\n********************************************\n* Customer Name:\t\t" + currentSessionInfo.get(key).getCustomerName() + "\n* State:\t\t\t" + currentSessionInfo.get(key).getState() + "\n* Floor Type:\t\t\t" + currentSessionInfo.get(key).getProductType() + "\n* Project Size:\t\t\t" + currentSessionInfo.get(key).getArea() + "sq.ft\n\n* Total materials @$" + currentSessionInfo.get(key).getSqFtMaterialCost() + "/sq.ft\t$" + currentSessionInfo.get(key).getTotalMaterialCost() + "\n* Total labor @$" + currentSessionInfo.get(key).getSqFtLaborCost() + "/sq.ft\t$" + currentSessionInfo.get(key).getTotalLaborCost() + "\n* Tax @" + currentSessionInfo.get(key).getTaxRate() + "%\t\t\t$" + currentSessionInfo.get(key).getTotalTax() + "\n* Total:\t\t\t$" + currentSessionInfo.get(key).getTotalTotal() + "\n********************************************";
            }
        }
        return string;
    }

    public String viewOrderInfo(int orderNum) {
        String string = "";
        Order dave = currentSessionInfo.get(orderNum);
        string += "\n********************************************\n*\t      Date: " + dave.getDate() + "\n********************************************\n* Order #:\t\t\t" + dave.getOrderNum() + "\n* Customer Name:\t\t" + dave.getCustomerName() + "\n* State:\t\t\t" + dave.getState() + "\n* Floor Type:\t\t\t" + dave.getProductType() + "\n* Project Size:\t\t\t" + dave.getArea() + "sq.ft\n\n* Total materials @$" + dave.getSqFtMaterialCost() + "/sq.ft\t$" + dave.getTotalMaterialCost() + "\n* Total labor @$" + dave.getSqFtLaborCost() + "/sq.ft\t$" + dave.getTotalLaborCost() + "\n* Tax @" + dave.getTaxRate() + "%\t\t\t$" + dave.getTotalTax() + "\n* Total:\t\t\t$" + dave.getTotalTotal() + "\n********************************************";
        return string;

    }

    public void removeOrder(int index) {
        currentSessionInfo.remove(index);

    }

    public void commitOrder(Order order) {
        orderNum++;
        String string = "order_" + order.getDate() + ".txt";
        currentSessionInfo.put(order.getOrderNum(), order);
        fileMap.put(order.getDate(), string);
    }
    
    public void commitEdit(Order order) {
        String string = "order_" + order.getDate() + ".txt";
        currentSessionInfo.put(order.getOrderNum(), order);
        fileMap.put(order.getDate(), string);
    }

    public void loadOrderNum() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("orderNum.txt")));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            orderNum = Integer.parseInt(currentTokens[0]);
        }
    }

    public void writeOrderNum() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("orderNum.txt"));
        writer.println(orderNum);
        writer.flush();
        writer.close();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
