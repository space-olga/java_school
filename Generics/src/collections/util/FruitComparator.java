package collections.util;

import java.util.Comparator;

public class FruitComparator implements Comparator<Fruit> {
    @Override
    public int compare(Fruit o1, Fruit o2) {
            if (o1.getFruitId() < o2.getFruitId()) return -1;
            else if (o1.getFruitId() > o2.getFruitId()) return 1;
            else return 0;

    }
}
