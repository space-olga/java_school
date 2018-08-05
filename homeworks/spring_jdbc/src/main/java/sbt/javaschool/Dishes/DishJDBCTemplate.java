package sbt.javaschool.Dishes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DishJDBCTemplate implements DishesDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Number createProduct(Product product) {
        String SQL = "insert into DISHES.PRODUCT values (default, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject = new JdbcTemplate(this.dataSource);
        jdbcTemplateObject.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL, new String[] {"PRODUCTID"});
                        ps.setString(1, product.productName);
                        ps.setString(2, product.productDescr);
                        // ps.setInt(3, product.quantity);
                        // ps.setString(4, product.unit);
                        // ps.setString(5, product.descr);
                        return ps;
                    }
                }, keyHolder);

        // :productId, :productName, :productDescr, :quantity, :unit, :descr

        return keyHolder.getKey();
    }

    @Override
    public Number createDish(String dishName, String dishDescr) {
        String SQL = "insert into DISHES.DISH values (default, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject = new JdbcTemplate(this.dataSource);
        jdbcTemplateObject.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL, new String[] {"DISHID"});
                        ps.setString(1, dishName);
                        ps.setString(2, dishDescr);
                        return ps;
                    }
                }, keyHolder);

        return keyHolder.getKey();
    }

    @Override
    public void createDishProduct(Integer dishId, Product product) {
        String SQL = "insert into DISHES.DISH_PRODUCT values(default, ?, ?, ?, ?, ?)";
        jdbcTemplateObject = new JdbcTemplate(dataSource);

        Object[] args = new Object[]{dishId, product.getProductId(), product.getQuantity(), product.getUnit(), product.getProductDescr()};

        int out = jdbcTemplateObject.update(SQL, args);

        if (out != 0) {
            System.out.println("Relation dish id=" + dishId + " product id=" + product.getProductId() + " was saved ");
        } else
            System.out.println("Relation dish id=" + dishId + " product id=" + product.getProductId() + " was failed ");
    }

    @Override
    public List<Dish> getDish(String searchStr) {
        String SQL = "select dish.* from DISHES.DISH as dish where UPPER(dish.dishName) like ?";

        jdbcTemplateObject = new JdbcTemplate(this.dataSource);
        List<Dish> dishes = jdbcTemplateObject.query(SQL, new Object[] { "%" + searchStr.toUpperCase() + "%" }, new DishMapper());
        return dishes;
    }

    @Override
    public void deleteDish(Integer dishId) {
        // Select products related with dish
        String SQL = "delete from DISHES.DISH_PRODUCT as dish_product where dish_product.DISHID=?;";
        jdbcTemplateObject = new JdbcTemplate(dataSource);

        int out = jdbcTemplateObject.update(SQL, dishId);
        if(out != 0) {
            System.out.println("Продукты удалены для блюда с dishId=" + dishId);
        } else System.out.println("У блюда нет продуктов dishId=" + dishId);

        // Delete dish
        SQL = "delete from DISHES.DISH as dish where dish.DISHID=?";

        out = jdbcTemplateObject.update(SQL, dishId);
        if(out != 0) {
            System.out.println("Блюдо удалено dishId=" + dishId);
        } else System.out.println("Нет блюд с dishId=" + dishId);
    }

    @Override
    public Integer productExists(Product product) {
        String SQL = "select dp.PRODUCTID from DISHES.PRODUCT as dp where UPPER(dp.PRODUCTNAME)=? and UPPER(dp.PRODUCTDESCR)=?;";
        jdbcTemplateObject = new JdbcTemplate(dataSource);

        try {
            Integer productId = jdbcTemplateObject.queryForObject(SQL, new Object[]{product.productName.toUpperCase(), product.productDescr.toUpperCase()}, Integer.class);
            return productId;
        } catch (Exception ex) {
            return 0;
        }
    }
}

