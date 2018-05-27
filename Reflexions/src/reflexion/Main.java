package reflexion;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        GetterCounter getterCounter = new GetterCounter();

        try {
            // Количество геттеров в классе
            /*System.out.printf("Getters count in reflexion.Person = %d\n",
                    getterCounter.calcGetterCount(Class.forName("reflexion.Person")));
            System.out.printf("Getters count in reflexion.Cat = %d\n",
                    getterCounter.calcGetterCount(Class.forName("reflexion.Cat")));*/

            IGetterCounter getterCounterProxy = (IGetterCounter) Proxy.newProxyInstance(GetterCounter.class.getClassLoader(),
                    GetterCounter.class.getInterfaces(), new GetterCounterInvocationHandler(getterCounter));

            System.out.println(String.format("Getters count in reflexion.Person = %d",
                    getterCounterProxy.calcGetterCount(Class.forName("reflexion.Person"))));
            System.out.println(String.format("Getters count in reflexion.Cat = %d",
                    getterCounterProxy.calcGetterCount(Class.forName("reflexion.Cat"))));

            System.out.println(getterCounterProxy.calcGetterCount(Class.forName("reflexion.Person")));
            System.out.println(getterCounterProxy.calcGetterCount(Class.forName("reflexion.Cat")));

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
