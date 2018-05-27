package reflexion;

import java.util.HashMap;

public class GetterCounterInvocationHandler implements java.lang.reflect.InvocationHandler {
    private HashMap<Class, Object> hashMapCache = new HashMap<Class, Object>();
    private Object object;

    public GetterCounterInvocationHandler(Object obj) {
        object = obj;
    }

    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
            throws Throwable {

        try {
            Class clazzArgs = (Class) args[0];

            if (hashMapCache.containsKey(clazzArgs)) {
                System.out.println("Cash value");
                return hashMapCache.get(clazzArgs);
            }

            Object count = method.invoke(object, args);
            hashMapCache.put(clazzArgs, count);

            System.out.println(String.format("Method invoke :%s, value = %d", method.getName(), count));
            return count;

        } catch (Exception ex) {
            System.out.println(String.format("Method %s invoke with exception: %s", method.getName(), ex.toString()));
        }

        return method.invoke(object, args);
    }
}
