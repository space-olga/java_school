package collections.util;

import java.util.*;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source); }

    public static <T> List newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T t) {
        return source.indexOf(t);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T t) {
        source.add(t);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        return c1.retainAll(c2); // !Collections.disjoint(c1, c2);
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static<T extends Comparable<? super T>> List range(List<? extends T> list, T min, T max) {
        Collections.sort(list);
        List<T> listResult = CollectionUtils.newArrayList();

        for (T item: list) {
            if (item.compareTo(min) >= 0 && item.compareTo(max) <= 0) listResult.add(item);
        }
        return listResult;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static<T> List range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        Collections.sort(list, comparator);
        List<T> listResult = CollectionUtils.newArrayList();

        for (T item: list) {
            if(comparator.compare(item, min) >= 0 && comparator.compare(item, max) <= 0) listResult.add(item);
            // if (item.compareTo(min) >= 0 && item.compareTo(max) <= 0) listResult.add(item);
        }
        return listResult;
    }


    public static void main(String[] args) {

        List<Fruit> listApple = CollectionUtils.newArrayList();
        CollectionUtils.add(listApple, new Apple(8));
        CollectionUtils.add(listApple, new Apple(7));
        CollectionUtils.add(listApple, new Apple(5));
        CollectionUtils.add(listApple, new Apple(3));
        CollectionUtils.add(listApple, new Apple( 5));

        List<Fruit> listMelon = CollectionUtils.newArrayList();
        CollectionUtils.add(listMelon, new Melon(4));
        CollectionUtils.add(listMelon, new Melon(2));
        CollectionUtils.add(listMelon, new Melon( 3));

        CollectionUtils.addAll(listMelon, listApple);

        List<Integer> rangeList = CollectionUtils.range(listApple, new Apple(3), new Apple( 5));
        List<Integer> rangeListR = CollectionUtils.range(listApple, new Apple(3), new Apple( 7), new FruitComparator());

        /* List<Integer> listC1 = CollectionUtils.newArrayList();
        CollectionUtils.add(listC1, 8);
        CollectionUtils.add(listC1, 7);
        CollectionUtils.add(listC1, 5);
        CollectionUtils.add(listC1, 3);
        CollectionUtils.add(listC1, 5);

        int index5 = CollectionUtils.indexOf(listC1, 5);

        List<Integer> rangeList = CollectionUtils.range(listC1, 3, 5);
        List<Integer> rangeListR = CollectionUtils.range(listC1, 3, 7, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        boolean containsAllItems = CollectionUtils.containsAll(rangeListR, rangeList);
        CollectionUtils.removeAll(rangeListR, rangeList);

        List<Integer> listC2 = CollectionUtils.newArrayList();
        CollectionUtils.add(listC2, 4);
        CollectionUtils.add(listC2, 2);
        CollectionUtils.add(listC2, 3);

        boolean containsAnyItems = CollectionUtils.containsAny(listC1, listC2);
        CollectionUtils.addAll(listC1, listC2);
        List limitList = CollectionUtils.limit(listC2, 3);
        */
    }
}
