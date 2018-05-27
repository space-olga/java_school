package reflexion;

import java.lang.reflect.*;

public class GetterCounter implements IGetterCounter {
    public GetterCounter() { }

    @Override
    public int calcGetterCount(Class<?> clazz) {
        int gettersCount = 0;

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            // Название метода начинается с get, без параметров и возвращает значение
            if (method.getAnnotation(Skip.class) == null && method.getName().startsWith("get")
                    && method.getParameterTypes().length == 0 && !void.class.equals(method.getReturnType()) ) {
                gettersCount++;
            }
        }
        return gettersCount;
    }
}
