package sbt.javaschool.dishes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT", schema="DISHES")
public class Product {
    @Id
    @Column(name="PRODUCTID", nullable = false, insertable = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer productId;
    @Column (name="PRODUCTNAME", nullable = false, insertable = true, updatable = true, length = 100)
    String productName;
    @Column (name="PRODUCTDESCR", nullable = true, insertable = true, updatable = true, length = 200)
    String productDescr;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<DishProduct> productDish = new HashSet<>();

    public Product() {
    }

    public Product(Integer productId, String productName, String productDescr) {
        this.productId = productId;
        this.productName = productName;
        this.productDescr = productDescr;
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

    public Set<DishProduct> getProductDish() {
        return productDish;
    }

    public void setProductDish(Set<DishProduct> productDish) {
        this.productDish = productDish;
    }

    public void addProductDish(DishProduct dishProduct) {
        dishProduct.setProduct(this);
        getProductDish().add(dishProduct);
    }

    public void removeProductDish(DishProduct dishProduct) {
        getProductDish().remove(dishProduct);
    }

    @Override
    public String toString() {
        return "sbt.javaschool.Data.Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescr='" + productDescr + '\'' +
                '}';
    }
}