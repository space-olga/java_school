package sbt.javaschool.dishes;

import java.util.List;

public interface DishesDAO {

    // add dish with products
    public void createDish();

    // search dish and dish's products by part of search string
    public List<Dish> getDish(String searchStr);

    // to delete a records from the sbt.javaschool.Data.Dish, Dish_Product
    public void deleteDish(Integer dishId);
}