package com.example;

import java.util.*;

public class CountMapIml<T> implements CountMap<T> {
    Map<T, Integer> container;

    public CountMapIml()
    {
        this.container = new HashMap<>();
    }

    @Override
    // добавляет элемент в этот контейнер.
    public void add(T t) {
        if (this.container.containsKey(t)) this.container.put(t, this.container.get(t) + 1);
        else this.container.put(t, 1);
    }

    @Override
    //Возвращает количество добавлений данного элемента
    public int getCount(T t) {
        return this.container.get(t);
    }

    @Override
    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    public int remove(T t) {
        int tcount = this.container.get(t);
        this.container.remove(t);

        return tcount;
    }

    @Override
    //количество разных элементов
    public int size() {
        return this.container.size();
    }

    @Override
    //Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
    public void addAll(CountMap<T> source) {

        Map sourceMap = source.toMap();
        Set<Map.Entry<T, Integer>> entries = sourceMap.entrySet();

        for (Map.Entry<T, Integer> item: entries)
        {
            if (this.container.containsKey(item.getKey()))
                this.container.put(item.getKey(), this.container.get(item.getKey()) + item.getValue());
            else this.container.put(item.getKey(), 1);
        }
    }

    @Override
    //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    public Map toMap() {
        return this.container;
    }

    @Override
    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    public void toMap(Map destination) {
        if (destination != null)
            destination.putAll(this.container);
    }

    public static void main(String[] args)
    {
        CountMap<Integer> map = new CountMapIml<>();

        // Добавление элемента
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        System.out.println("Integer");

        System.out.printf("count of 5 = %d\n", map.getCount(5));
        System.out.printf("count of 6 = %d\n", map.getCount(6));
        System.out.printf("count of 10 = %d\n", map.getCount(10));

        System.out.printf("collection size equals %d\n", map.size());

        System.out.printf("remove 6 count of elem = %d", map.remove(6));

        CountMap<Integer> mapOther = new CountMapIml<>();

        // Добавление элемента
        map.toMap(((CountMapIml<Integer>) mapOther).container);

        map.addAll(mapOther);

      /*  CountMap<String> map = new CountMapIml<>();

        // Добавление элемента
        map.add("10");
        map.add("10");
        map.add("5");
        map.add("6");
        map.add("5");
        map.add("10");

        System.out.println("Integer");

        System.out.printf("count of 5 = %d\n", map.getCount("5"));
        System.out.printf("count of 6 = %d\n", map.getCount("6"));
        System.out.printf("count of 10 = %d\n", map.getCount("10"));

        System.out.printf("collection size equals %d\n", map.size());

        System.out.printf("remove 6 count of elem = %d", map.remove("6"));

        CountMap<String> mapOther = new CountMapIml<>();

        // Добавление элемента
        map.toMap(((CountMapIml<String>) mapOther).container);

        map.addAll(mapOther);*/

    }
}
