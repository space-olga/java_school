import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Класс надо параметризовать используя правило PECS
public class Streams<T> {

    Stream<T> stream;

    public Streams(List<T> list) {
        this.stream = list.stream();
    }

    // Streams.of() - статический метод, который принимает коллекцию и создает новый объект Streams
    public static <T> Streams of(List<T> list) {
        return new Streams(list);
    }

    // filter() - оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде.
    public Streams filter(Predicate<? super T> predicate) {
        this.stream = this.stream.filter(predicate);
        return this;
    }

    // transform() - преобразует элемент в другой
    public Streams transform(Function<? super T, ? extends T> function) {
        this.stream = this.stream.map(function);
        return this;
    }

    // toMap - принимает 2 лямбды для создания мапы, в одной указывается, что использовать в качестве ключа,
    // в другой, что в качестве значения
    public Map<T, T> toMap(Function<? super T, ? extends T> mapperKey, Function<? super T, ? extends T> mapperValue) {
        return this.stream.collect(Collectors.toMap(mapperKey, mapperValue));
    }
}
