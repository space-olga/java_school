import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetElements {
    List<Integer> arrayElements = new ArrayList<Integer>();

    void addElement(Integer x)
    {
        this.arrayElements.add(x);
    }

    int deleteElement()
    {
        // int minIndex = list.indexOf(Collections.min(list));
        Integer minElement = Collections.min(this.arrayElements);
        this.arrayElements.remove(minElement);

        return minElement;
    }
}
