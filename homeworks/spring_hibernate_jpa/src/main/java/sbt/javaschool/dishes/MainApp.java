package sbt.javaschool.dishes;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    @PersistenceUnit
    static EntityManager emf;

    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        DishesDAOImpl dishesDAO = new DishesDAOImpl();
        dishesDAO.setSession(session);

        int menuItem = 0;

        while (menuItem != 4) {
            System.out.println("Выберите действие с таблицей блюд:");
            System.out.println("1 - Поиск блюда по имени или части имени");
            System.out.println("2 - Добавление рецепта");
            System.out.println("3 - Удаление блюда");
            System.out.println("4 - Выход из программы");

            try {
                Scanner in = new Scanner(System.in);
                menuItem = in.nextInt();

                switch (menuItem) {
                    // Поиск блюда по имени или части имени
                    case 1:
                        System.out.println("Введите строку для поиска");

                        in = new Scanner(System.in);
                        String searchString = in.next();

                        if (!searchString.isEmpty()) {
                            // Transaction tx = session.beginTransaction();

                            List<Dish> dishList = dishesDAO.getDish(searchString);
                            for (Dish dishRecord : dishList) {
                                System.out.println(dishRecord.toString());
                            }
                            // tx.commit();
                        } else System.out.println("Введена пустая строка");
                        break;
                    // Добавление рецепта
                    case 2:
                        System.out.println("Добавление блюда в базу данных");

                        try {
                            // Transaction tx = session.beginTransaction();
                            dishesDAO.createDish(); // dish
                            // tx.commit();
                            System.out.println("Dish successfully saved");

                        } catch (Exception ex) {
                            System.out.println("Произошла ошибка при вставке записи в базу данных");
                            ex.printStackTrace();
                        }

                        break;
                    // Удаление блюда
                    case 3:
                        System.out.println("Введите id блюда, которое нужно удалить:");

                        try {
                            in = new Scanner(System.in);
                            int dishId = in.nextInt();

                            try {
                                // Transaction tx = session.beginTransaction();
                                dishesDAO.deleteDish(dishId);
                                // tx.commit();
                                System.out.println("Dish successfully deleted");
                            } catch (Exception ex) {
                                System.out.println("Произошла ошибка при удалении блюда id=" + dishId);
                                ex.printStackTrace();
                            }
                        } catch (Exception ex) {
                            System.out.println("Произошла ошибка при удалении блюда или введен неверный id");
                        }
                        break;
                    case 4:
                        session.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Ошибка при выборе пункта меню программы. Повторите попытку:");
                        menuItem = 0;
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Ошибка ввода цифры. Повторите попытку");
            }
        }
    }
}
