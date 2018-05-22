package collections.util;

public class Fruit implements Comparable<Fruit> {
    private int fruitId;

    public int getFruitId() {
        return this.fruitId;
    }

    public void setFruitId(int fruitId)
    {
        this.fruitId = fruitId;
    }

    public Fruit(int fruitId) {
        this.fruitId = fruitId;
    }


    @Override
    public int compareTo(Fruit o) {
        if (this.fruitId < o.fruitId) return -1;
        else if (this.fruitId > o.fruitId) return 1;
        else return 0;
    }
}
