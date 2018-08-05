package sbt.javaschool.Dishes;

import javax.sql.DataSource;
import java.util.List;

public interface DishesDAO {

    // initialize database resources ie. connection.
    public void setDataSource(DataSource ds);

    // create a record in the sbt.javaschool.Data.Product table.
    public Number createProduct(Product product); // List<sbt.javaschool.Data.Product> products

    // create a record in the sbt.javaschool.Data.Dish table.
    public Number createDish(String dishName, String dishDescr);

    // create a record in the Dish_Product table.
    public void createDishProduct(Integer dishId, Product product);

    // search dish and dish's products by part of search string
    public List<Dish> getDish(String searchStr);

    // to delete a records from the sbt.javaschool.Data.Dish, Dish_Product
    public void deleteDish(Integer dishId);

    // search products exists
    public Integer productExists(Product product);
}

