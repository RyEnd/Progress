package com.tsg.flooringmastery.data;

import com.tsg.flooringmastery.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class ProductAccessDAO {
    public Map<Integer, Product> productInfo = new HashMap<>();

    private static final String DELIMITER = "::";

    public void loadProducts() throws FileNotFoundException {

        String ORDER_FILE = "productInfo.txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        String currentLine;
        String[] currentTokens;
        Integer productNum = 0;

        while (sc.hasNextLine()) {
            productNum++;

            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product product = new Product();
            product.setType(currentTokens[0]);
            product.setSqFtLaborCost(Double.parseDouble(currentTokens[1]));
            product.setSqFtMaterialCost(Double.parseDouble(currentTokens[2]));
            productInfo.put(productNum, product);
        }
    }

    public String getListOfProducts() {
        
        Set<Integer> keys = productInfo.keySet();
        String string = "";
        for (Integer key : keys) {
            string += "\n\t" + key + ": " + productInfo.get(key).getType();
        }
        return string;
    }

    public Double getMaterialCost(Integer type) {
        Double cost = productInfo.get(type).getSqFtMaterialCost();
        return cost;
    }
    
    public Double getLaborCost(Integer type)
    {
        Double cost =  productInfo.get(type).getSqFtLaborCost();
        return cost;
    }

    public Product getProductInfo(Integer type) {
        return productInfo.get(type);
    }
    
    public Integer getProductListSize()
    {
        return productInfo.size();
    }
}
