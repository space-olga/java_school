package sbt.javaschool.Dishes;

public class Product {

    Integer productId;
    String productName;
    String productDescr;
    Integer quantity;
    String unit;
    String descr;

    public Product(Integer productId, String productName, String productDescr, Integer quantity, String unit, String descr) {
        this.productId = productId;
        this.productName = productName;
        this.productDescr = productDescr;
        this.quantity = quantity;
        this.unit = unit;
        this.descr = descr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescr() {
        return productDescr;
    }

    public void setProductDescr(String productDescr) {
        this.productDescr = productDescr;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "sbt.javaschool.Data.Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescr='" + productDescr + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", descr='" + descr + '\'' +
                '}';
    }
}