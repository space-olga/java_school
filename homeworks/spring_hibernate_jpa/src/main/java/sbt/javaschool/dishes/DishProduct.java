package sbt.javaschool.dishes;

import javax.persistence.*;

@Entity
@Table(name="DISH_PRODUCT", schema="DISHES")
public class DishProduct {
    @Id
    @Column(name="DISH_PRODUCTID", nullable = false, insertable = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer dish_productId;

    @ManyToOne
    @JoinColumn(name="DISHID")
    Dish dish;

    @ManyToOne
    @JoinColumn(name="PRODUCTID")
    Product product;

    @Column(name="QUANTITY", nullable = true, insertable = true, updatable = true)
    Integer quantity;
    @Column(name="UNIT", nullable = true, insertable = true, updatable = true, length = 20)
    String unit;
    @Column(name="DESCR", nullable = true, insertable = true, updatable = true, length = 50)
    String descr;

    public DishProduct() {
    }

    public DishProduct(Integer dish_productId, Dish dish, Product product,
                       Integer quantity, String unit, String descr) {

        this.dish_productId = dish_productId;
        this.dish = dish;
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.descr = descr;
    }

    public Integer getDish_productId() {
        return dish_productId;
    }

    public void setDish_productId(Integer dish_productId) {
        this.dish_productId = dish_productId;
    }

    public Dish getDish() {
        return this.dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
