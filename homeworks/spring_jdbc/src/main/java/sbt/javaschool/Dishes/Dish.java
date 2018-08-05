package sbt.javaschool.Dishes;

import java.util.ArrayList;
import java.util.List;

public class Dish {

    Integer dishId;
    String dishName;
    String dishDescr;
    Products Products;
    //List Products = new ArrayList<Product>();

    public Integer getDishId() {
        return this.dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDescr() {
        return dishDescr;
    }

    public void setDishDescr(String dishDescr) {
        this.dishDescr = dishDescr;
    }
    public Products getProducts() {
        return Products;
    }

    public void setProducts(Products products) {
        this.Products = products;
    }

    // public List<Product> getProducts() { return Products;    }

   // public void setProducts(List<Product> products) { this.Products = products;  }

    @Override
    public String toString() {
        //    String productsString = "";
        //    if (products != null && products.size() > 0) {
        //        for (Product productItem : products) productsString += "\n" + productItem.toString();
        //    }

        return "sbt.javaschool.Data.Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", dishDescr='" + dishDescr +
                //'\''  + ", products=" + productsString +
                '}';
    }
}
