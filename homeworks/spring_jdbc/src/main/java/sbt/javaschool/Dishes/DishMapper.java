package sbt.javaschool.Dishes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DishMapper implements RowMapper<Dish> {
    public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dish dish = new Dish();
        dish.setDishId(rs.getInt("dishId"));
        dish.setDishName(rs.getString("dishName"));
        dish.setDishDescr(rs.getString("dishDescr"));

        return dish;
    }
}

