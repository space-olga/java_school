package reflexion;

import java.util.HashMap;

public class GetterCounterInvocationHandler implements java.lang.reflect.InvocationHandler {
    private Object object;

    public GetterCounterInvocationHandler(Object obj) {
        object = obj;
    }

    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
            throws Throwable {

        try {
            Class clazzArgs = (Class) args[0];

            if (IGetterCounter.hashMapCache.containsKey(clazzArgs)) {
                System.out.println(String.format("Cash value of %s", clazzArgs.getName()));
                return IGetterCounter.hashMapCache.get(clazzArgs);
            }

            Object count = method.invoke(object, args);
            IGetterCounter.hashMapCache.put(clazzArgs, count);

            return count;

        } catch (Exception ex) {
            System.out.println(String.format("Method %s invoke with exception: %s", method.getName(), ex.toString()));
        }

        return method.invoke(object, args);
    }
}
