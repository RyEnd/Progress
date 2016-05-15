package com.tsg.flooringmastery.controller;

import com.tsg.flooringmastery.data.*;
import com.tsg.flooringmastery.ui.*;
import com.tsg.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class FlooringMasteryController {

    StateAccessDAO stateDAO;
    ProductAccessDAO productDAO;
    FlooringMasteryDAO floorDAO;
    ConsoleIO io;
    BusinessLogic logic;
    Date today = new Date();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String config;
    static final String DELIMITER = "::";
    boolean keepAlive = true;
    
    public FlooringMasteryController(BusinessLogic logic, FlooringMasteryDAO floorDAO, ProductAccessDAO productDAO, StateAccessDAO stateDAO, ConsoleIO io)
    {
        this.stateDAO = stateDAO;
        this.productDAO = productDAO;
        this.floorDAO = floorDAO;
        this.io = io;
        this.logic = logic;
    }

    void run() throws Exception {

        stateDAO.loadStates();
        productDAO.loadProducts();
        floorDAO.loadOrderNum();
        loadConfig();

        while (keepAlive) {
            printMenu();
            int userInput = io.getAndReturnBoundInt("\nPlease select an option 1-6.", 1, 6);
            switch (userInput) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveCurrentWork();
                    break;
                case 6:
                    quit();
                    break;
                default:
                    io.printString("I don't recognize your choice, please try again.");
                    break;
            }
        }
    }

    private void printMenu() {
        io.printString("\n********************************************");
        io.printString("*\tWelcome to The Flooring Company");
        io.printString("*\t\tToday: " + df.format(today));
        io.printString("*\t\t   Mode: " + config + "\n*");
        io.printString("* 1. Display Orders");
        io.printString("* 2. Add Orders");
        io.printString("* 3. Edit Orders");
        io.printString("* 4. Remove Order");
        io.printString("* 5. Save your current work");
        io.printString("* 6. Quit");
        io.printString("*");
        io.printString("********************************************");

    }

    private void displayOrders() {
        try {
            String date;
            String answer = io.getAndReturnString("\nPlease enter the date of the orders you wish to display, or hit enter to view today's orders.\nYYYY/MM/DD");
            if ("".equals(answer)) {
                date = df.format(today).replaceAll("/", "");
            } else {
                date = answer.replaceAll("/", "");
            }
            floorDAO.loadCurrentInfo(date);
            io.printString(floorDAO.viewDateInfo(date));
        } catch (FileNotFoundException e) {
            io.printString("\nThere were no orders found for the date you entered.");
        }
    }

    private void addOrder() throws Exception {
        try {
            String name;
            String date;
            Order order;
            Integer orderNum = floorDAO.getOrderNum();
            name = io.getAndReturnString("\nPlease enter your full name:");
            io.printString("\n" + stateDAO.getListOfStates());
            Integer state = io.getAndReturnBoundInt("Please select your billing state from the options above.", 1, stateDAO.stateInfo.size());
            io.printString("\n" + productDAO.getListOfProducts());
            Integer type = io.getAndReturnBoundInt("Please select the product type from the list above.", 1, productDAO.productInfo.size());
            Integer area = io.getAndReturnBoundInt("\nPlease input project area in square feet. We accomodate projects up to 10,000sq.ft.", 1, 10000);
            io.printString("\n" + productDAO.productInfo.get(type).getType() + " will cost $" + productDAO.getProductInfo(type).getSqFtMaterialCost() + "/sqft of materials, and labor costs $" + productDAO.getProductInfo(type).getSqFtLaborCost() + "/sqft to install.");
            String answer = io.getAndReturnString("\nIf you would like to set a date for your order other than today, enter it now. Otherwise, press enter.\nYYYY/MM/DD");

            if ("".equals(answer)) {
                date = df.format(today).replaceAll("/", "");
            } else {
                date = answer.replaceAll("/", "");
            }

            order = logic.createOrder(orderNum, name, state, type, date, area);
            io.printString("\n" + order.toString());
            String userInput = io.getAndReturnString("\nPlease look over your order and then hit enter to submit it, or type 'toss' to delete your order.\nYou can edit your order after it has been submitted.");
            if (userInput.equals("")) {
                io.printString("\nYour order was submitted successfully...");
                floorDAO.commitOrder(order);
            } else {
                String userInput2 = io.getAndReturnString("\nAre you sure you want to delete your order and return to the main menu? Y/N");
                if (userInput2.equalsIgnoreCase("Y")) {
                    io.printString("\nthrew ya order out, returning you to the main menu.");
                } else {
                    io.printString("\nYour order has been submitted. If you would like to make changes to your order, please use our edit order feature.");
                    floorDAO.commitOrder(order);
                }
            }
        } catch (FileNotFoundException e) {
            io.printString("\nFile not found");

        }
    }

    private void editOrder() throws Exception {
        String date;
        String newDate;
        String newName;
        Order order, newOrder;

        String answer = io.getAndReturnString("\nPlease enter the date of the order you wish to display, or hit enter to return today's date.\nYYYY/MM/DD");

        if ("".equals(answer)) {
            date = df.format(today).replaceAll("/", "");
        } else {
            date = answer.replaceAll("/", "");
        }

        try {
            floorDAO.loadCurrentInfo(date);
        } catch (FileNotFoundException ex) {
            io.printString("\nWe didn't find any orders for that date.");
            return;
        }
        int orderNum = io.getAndReturnInt("\nPlease enter your order number.");
        io.printString(floorDAO.viewOrderInfo(orderNum));
        io.printString("\nWe found your order!");
        order = floorDAO.getCurrentInfo(orderNum);
        io.printString("\n" + stateDAO.getListOfStates());
        Integer newState = io.getAndReturnBoundInt("Please select your new billing state from the options above.", 1, stateDAO.stateInfo.size());
        io.printString("\n" + productDAO.getListOfProducts());
        Integer newType = io.getAndReturnBoundInt("Please select the new product type from the list above:", 1, productDAO.productInfo.size());
        io.printString("\nEnter your new information below or hit enter to keep the old data.");
        newName = io.getAndReturnString("\nPlease enter your full name:");
        if ("".equals(newName)) {
            newName = order.getCustomerName();
        }
        String newAnswer = io.getAndReturnString("\nPlease enter the new date of the order, hit enter to use the old date, or type 'today' to enter today's date.\nYYYY/MM/DD");
        if ("".equals(newAnswer)) {
            newDate = order.getDate();
        } else if ("today".equalsIgnoreCase(newAnswer)) {
            newDate = df.format(today).replaceAll("/", "");
        } else {
            newDate = newAnswer.replaceAll("/", "");
        }
        Integer newArea = io.getAndReturnBoundInt("\nPlease input new project area. (below 10,000)", 1, 10000);
        if (Objects.equals(newArea, "")) {
            newArea = order.getArea();
        }
        newOrder = logic.createOrder(orderNum, newName, newState, newType, newDate, newArea);
        io.printString(newOrder.toString());
        String userInput = io.getAndReturnString("\nPlease look over your order and then hit enter to submit your changes, or type 'toss' to delete your changes.\nYou can edit your order again after it has been submitted.");
        if (userInput.equals("")) {
            io.printString("\nYour order was submitted successfully...");
            floorDAO.commitOrder(newOrder);
        } else {
            String userInput2 = io.getAndReturnString("\nAre you sure you want to delete your changes and return to the main menu? Y/N");
            if (userInput2.equalsIgnoreCase("Y")) {
                io.printString("\nI tossed your changes, returning you to the main menu.");
            } else {
                io.printString("\nYour changes have been submitted. If you would like to make more changes to your order, please use our edit order feature.");
                floorDAO.commitEdit(newOrder);
            }
        }
    }

    private void removeOrder() {
        String date;
        String answer = io.getAndReturnString("\nPlease enter the date of the order you wish to display, or hit enter to return today's date.\nYYYY/MM/DD");

        if ("".equals(answer)) {
            date = df.format(today).replaceAll("/", "");
        } else {
            date = answer.replaceAll("/", "");
        }

        try {
            floorDAO.loadCurrentInfo(date);
        } catch (FileNotFoundException ex) {
            io.printString("\nWe didn't find any orders for that date.");
            return;
        }
        int orderNum = io.getAndReturnInt("\nPlease enter your order number.");
        io.printString("\nWe found your order!\nHere it is:");
        io.printString(floorDAO.viewOrderInfo(orderNum));
        boolean alive = true;
        while (alive) {
            String userInput = io.getAndReturnString("\nIs this the order you wish to delete? Y/N");
            if (userInput.equalsIgnoreCase("y")) {
                floorDAO.removeOrder(orderNum);
                alive = false;
            } else {
                io.printString("\nThe order was not deleted. Returning you to the main menu.");
                alive = false;
            }

        }

    }

    private void saveCurrentWork() {
        if (config.equals("PROD"))
        {
            try {
                floorDAO.writeCurrentInfo();
                floorDAO.writeOrderNum();
                io.printString("\nAll of your changes have been saved.");
            } catch (IOException ex) {
                io.printString("You either don't have any changes to be saved, or something bork!");
            }
        } else if (config.equals("TEST"))
        {
            io.printString("\nProgram is in TEST mode, no data was written to file.");
        }
    }
    
    public void loadConfig() {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("config.txt")));
            String currentLine;
            String[] currentTokens;
            
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                config = (currentTokens[0]);
            }
        } catch (FileNotFoundException ex) {
            io.printString("There was a problem with the config file, please contact customer support.");
            quickQuit();
        }
    }

    private void quit() {
        String userInput = io.getAndReturnString("Have you made sure to save all of your changes? Y/N");
        if (userInput.equalsIgnoreCase("y")) {
                io.printString("Thank you for your business!");
                keepAlive = false;
            } else {
            saveCurrentWork();
            }
    }
    
    private void quickQuit() {
        keepAlive = false;
    }
}
