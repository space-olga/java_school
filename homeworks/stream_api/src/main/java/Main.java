import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Apple> listApples = new ArrayList<>();
        listApples.add(new Apple("Golden", "green", 100, 82.50));
        listApples.add(new Apple("Gala", "yellow", 50, 100.00));
        listApples.add(new Apple("Red global", "red", 70, 120.00));
        listApples.add(new Apple("Antonovka", "green", 100, 150.00));
        listApples.add(new Apple("Big apples", "red", 150, 170.00));

        Streams streamApples = Streams.of(listApples)
                                      .filter((Predicate<Apple>)apple -> "red".equals(apple.getColor()))
                                      .transform((Function<Apple, Apple>) apple-> new Apple(apple.sortName, apple.color, apple.weigth, apple.price - 20));
        System.out.println("StreamApi -> red apples");
        streamApples.stream.forEach(System.out::println);

        System.out.println("\nHashMap -> green apples");
        Map<String, Apple> map = Streams.of(listApples)
                .filter((Predicate<Apple>)apple -> "green".equals(apple.getColor()))
                .transform((Function<Apple, Apple>)apple-> new Apple(apple.sortName, apple.color, apple.weigth, apple.price * 1.05))
                .toMap((Function<Apple, String>) Apple::getSortName, apple->apple);

        map.forEach((k,v)->System.out.println(String.format("Key:%s Value:%s", k, v)));

        // После выполнения всех операций коллекция someCollection не должна поменяться.
        System.out.println("\nList<Apples>");
        listApples.forEach(System.out::println);
        }
}
