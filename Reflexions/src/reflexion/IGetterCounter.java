package reflexion;

import java.util.HashMap;

// Интерфейс счетчика геттеров в классе
public interface IGetterCounter {
    // кэш для хранения результатов вызова функции calcGetterCount(Class<?> clazz)
    HashMap<Class, Object> hashMapCache = new HashMap<>();
    /*
     * Возвращает колличество геттеров в переданном класс
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    int calcGetterCount(Class<?> clazz);
}
