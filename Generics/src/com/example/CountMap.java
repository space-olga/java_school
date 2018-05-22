package com.example;

import java.util.*;

public interface CountMap<T> {
    // добавляет элемент в этот контейнер.
    void add(T t); // Object o

    //Возвращает количество добавлений данного элемента
    int getCount(T t); // Object o

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    int remove(T t); // Object o

    //количество разных элементов
    int size();

    //Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
    void addAll(CountMap<T> source);

    //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    Map toMap();

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map destination);
}

/*
пример использования
        CountMap<Integer> map = new CountMapIml<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
/*
        int count = map.getCout(5); // 2
        int count = map.getCout(6); // 1
        int count = map.getCout(10); // 3*/
