import java.util.function.Function;

public class Apple {
    public String sortName;
    public String color;
    public Integer weigth;
    public Double price;

    public Apple(String sortName, String color, Integer weigth, Double price) {
        this.sortName = sortName;
        this.color = color;
        this.weigth = weigth;
        this.price = price;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getColor() {
        return  this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeigth() {
        return this.weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("sortName=%s, color=%s, weigth=%d, price=%f",
                            this.sortName, this.color, this.weigth, this.price);
    }
}
