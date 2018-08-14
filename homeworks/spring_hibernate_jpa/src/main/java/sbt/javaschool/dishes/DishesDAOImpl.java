package sbt.javaschool.dishes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DishesDAOImpl implements DishesDAO {

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void createDish() {

        Transaction tx = session.beginTransaction();

        for (int i = 0; i < 2; i++) {
            Dish dish = new Dish(i, "dish_to_add_" + i, "dish_descr_to_add_" + i);
            session.save(dish);

            for (int j = 0; j < 2; j++) {
                Product product = new Product(j, "product_to_add_" + j, "product_descr_to_add_" + j);
                DishProduct dishProduct = new DishProduct(j, dish, product, j, "шт.", " j = " + j);

                session.save(product);
                session.save(dishProduct);
            }
        }

        tx.commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dish> getDish(String searchStr) {

        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Dish where UPPER(dishName) like :searchStr");
        query.setParameter("searchStr", "%" + searchStr.toUpperCase() + "%"); //
        List dishList = query.list();

        tx.commit();

        return dishList;
    }

    @Override
    public void deleteDish(Integer dishId) {

        Transaction tx = session.beginTransaction();
        // Select products related with dish
        Query query = session.createQuery("delete DishProduct where DISHID = :dishId");
        query.setParameter("dishId", dishId);

        int result = query.executeUpdate();

        if(result != 0) {
            System.out.println("Удалены продукты для блюда dishId=" + dishId);
        } else System.out.println("Нет продуктов дя блюда с dishId=" + dishId);

        query = session.createQuery("delete Dish where DISHID = :dishId");
        query.setParameter("dishId", dishId);

        result = query.executeUpdate();

        if(result != 0) {
            System.out.println("Блюдо удалено dishId=" + dishId);
        } else System.out.println("Нет блюд с dishId=" + dishId);

        tx.commit();
    }
}