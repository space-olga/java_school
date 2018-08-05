package sbt.javaschool.Dishes;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        DishJDBCTemplate dishesJdbcTemplate = context.getBean("dishJDBCTemplate", DishJDBCTemplate.class);

        int menuItem = 0;

        while (menuItem != 4) {
            System.out.println("Выберите действие с таблицей блюд:");
            System.out.println("1 - Поиск блюда по имени или части имени");
            System.out.println("2 - Добавление рецепта из файла С:\\Temp\\Dishes_Input.xml");
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

                        if(!searchString.isEmpty()) {
                            List<Dish> dishList = dishesJdbcTemplate.getDish(searchString);

                            for (Dish dishRecord : dishList) {
                                System.out.println(dishRecord.toString());
                            }
                        } else System.out.println("Введена пустая строка");
                        break;
                        // Добавление рецепта из файла С:\\Temp\\Dishes_Input.xml"
                    case 2:
                        System.out.println("Чтение файла исходных данных: C:\\Temp\\Dishes_Input.xml");
                        try {
                                FileReader reader = new FileReader("C:\\Temp\\Dishes_Input.xml");  // load file
                                XStream xstream = getXstreamObject();

                                xstream.alias("Data", Data.class);
                                xstream.alias("Dish", Dish.class);
                                xstream.alias("Product", Product.class);
                                xstream.alias("Products", Products.class);

                                xstream.addImplicitCollection(Data.class, "Dishes");
                                xstream.addImplicitCollection(Products.class, "Products");

                                Data dishes = (Data)xstream.fromXML(reader);

                                for(Object dishItem : dishes.Dishes) {
                                    Dish dishInserted = (Dish) dishItem;

                                    System.out.println("Добавление блюда dishName=" + dishInserted.dishName + " dishDescr=" + dishInserted.dishDescr);
                                    Number dishKey = dishesJdbcTemplate.createDish(dishInserted.dishName, dishInserted.dishDescr);

                                    Products productsItem = dishInserted.Products;
                                    for (Object productItem : productsItem.Products) {
                                        Product productInserted = (Product) productItem;

                                        // Проверка существования продукта в БД
                                        Integer productKeyExists = dishesJdbcTemplate.productExists(productInserted);

                                        if (productKeyExists == 0){
                                            System.out.println("Добавление продукта productName=" + productInserted.productName + " productDescr=" + productInserted.productDescr);
                                            productKeyExists = (Integer)dishesJdbcTemplate.createProduct(productInserted);
                                        }
                                        productInserted.setProductId((Integer) productKeyExists);
                                        dishesJdbcTemplate.createDishProduct((Integer)dishKey, productInserted);
                                    }
                                }
                            } catch (Exception ex) {
                                System.out.println("Произошла ошибка при поиске файла с исходными данными или при вставке записи в базу данных");
                                ex.printStackTrace();
                            }

                        break;
                        // Удаление блюда
                    case 3:
                        System.out.println("Введите id блюда, которое нужно удалить:");

                        try {
                            in = new Scanner(System.in);
                            int dishId = in.nextInt();

                            System.out.println("Delete dish with id = " + dishId);
                            dishesJdbcTemplate.deleteDish(dishId);
                        } catch (Exception ex) {
                            System.out.println("Произошла ошибка при удалении блюда или введен неверный id");
                        }
                        break;
                    case 4:
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

    static XStream getXstreamObject() {
        XStream xstream = new XStream(); // DomDriver and StaxDriver instances also can be used with constructor
        return xstream;
    }
}


