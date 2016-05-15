package com.tsg.flooringmastery.model;

import java.util.Objects;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class Product {

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.type);
        hash = 73 * hash + Objects.hashCode(this.sqFtLaborCost);
        hash = 73 * hash + Objects.hashCode(this.sqFtMaterialCost);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.sqFtLaborCost, other.sqFtLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.sqFtMaterialCost, other.sqFtMaterialCost)) {
            return false;
        }
        return true;
    }
    private String type;
    private Double sqFtLaborCost;
    private Double sqFtMaterialCost;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSqFtLaborCost() {
        return sqFtLaborCost;
    }

    public void setSqFtLaborCost(Double sqFtLaborCost) {
        this.sqFtLaborCost = sqFtLaborCost;
    }

    public Double getSqFtMaterialCost() {
        return sqFtMaterialCost;
    }

    public void setSqFtMaterialCost(Double sqFtMaterialCost) {
        this.sqFtMaterialCost = sqFtMaterialCost;
    }
}
