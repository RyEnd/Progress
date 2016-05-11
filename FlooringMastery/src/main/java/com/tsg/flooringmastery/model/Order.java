package com.tsg.flooringmastery.model;

import java.util.Objects;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class Order {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.orderNum;
        hash = 71 * hash + Objects.hashCode(this.customerName);
        hash = 71 * hash + Objects.hashCode(this.state);
        hash = 71 * hash + Objects.hashCode(this.taxRate);
        hash = 71 * hash + Objects.hashCode(this.productType);
        hash = 71 * hash + Objects.hashCode(this.area);
        hash = 71 * hash + Objects.hashCode(this.sqFtMaterialCost);
        hash = 71 * hash + Objects.hashCode(this.sqFtLaborCost);
        hash = 71 * hash + Objects.hashCode(this.totalMaterialCost);
        hash = 71 * hash + Objects.hashCode(this.totalLaborCost);
        hash = 71 * hash + Objects.hashCode(this.totalTax);
        hash = 71 * hash + Objects.hashCode(this.totalTotal);
        hash = 71 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNum != other.orderNum) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.sqFtMaterialCost, other.sqFtMaterialCost)) {
            return false;
        }
        if (!Objects.equals(this.sqFtLaborCost, other.sqFtLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalMaterialCost, other.totalMaterialCost)) {
            return false;
        }
        if (!Objects.equals(this.totalLaborCost, other.totalLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.totalTotal, other.totalTotal)) {
            return false;
        }
        return true;
    }
    private int orderNum;
    private String customerName;
    private String state;
    private Double taxRate;
    private String productType;
    private Integer area;
    private Double sqFtMaterialCost;
    private Double sqFtLaborCost;
    private Double totalMaterialCost;
    private Double totalLaborCost;
    private Double totalTax;
    private Double totalTotal;
    private String date;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Double getSqFtMaterialCost() {
        return sqFtMaterialCost;
    }

    public void setSqFtMaterialCost(Double sqFtMaterialCost) {
        this.sqFtMaterialCost = sqFtMaterialCost;
    }

    public Double getSqFtLaborCost() {
        return sqFtLaborCost;
    }

    public void setSqFtLaborCost(Double sqFtLaborCost) {
        this.sqFtLaborCost = sqFtLaborCost;
    }

    public Double getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(Double totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public Double getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(Double totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotalTotal() {
        return totalTotal;
    }

    public void setTotalTotal(Double totalTotal) {
        this.totalTotal = totalTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString()
    {
        String string;
        string = "\n********************************************\n* Order #: " + orderNum + "\t\tDate: " + date + "\n********************************************\n* Customer Name:\t\t" + customerName + "\n* State:\t\t\t" + state + "\n* Floor Type:\t\t\t" + productType + "\n* Project Size:\t\t\t" + area + "sq.ft\n\n* Total materials @$" + sqFtMaterialCost+ "/sq.ft\t$" + totalMaterialCost + "\n* Total labor @$" + sqFtLaborCost + "/sq.ft\t$" + totalLaborCost + "\n* Tax @" + taxRate + "%\t\t\t$" + totalTax + "\n* Total:\t\t\t$" + totalTotal + "\n********************************************";
        return string;
    }
}