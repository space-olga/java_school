package sbt.javaschool.dishes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="DISH", schema="DISHES")
public class Dish {
    @Id
    @Column(name="DISHID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer dishId;
    @Column(name="DISHNAME", nullable = false, insertable = true, updatable = true, length = 100)
    String dishName;
    @Column(name="DISHDESCR", nullable = true, insertable = true, updatable = true, length = 200)
    String dishDescr;

    @OneToMany(mappedBy = "dish", cascade=CascadeType.ALL, orphanRemoval = true)
    Set<DishProduct> dishProducts = new HashSet<>();

    public Dish() {
    }

    public Dish(Integer dishId, String dishName, String dishDescr) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishDescr = dishDescr;
    }

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

    public Set<DishProduct> getDishProducts() {
        return dishProducts;
    }

    public void setDishProducts(Set<DishProduct> dishProducts) {
        this.dishProducts = dishProducts;
    }

    public void addDishProduct(DishProduct dishProduct) {
        dishProduct.setDish(this);
        getDishProducts().add(dishProduct);
    }

    public void removeDishProduct(DishProduct dishProduct) {
        getDishProducts().remove(dishProduct);
    }

    @Override
    public String toString() {

        return "sbt.javaschool.Data.Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", dishDescr='" + dishDescr +
                '}';
    }
}
