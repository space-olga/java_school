public class SerializationDemo {

    public static void main(String[] args) {
        Person person = new Person("User1", 1, 22);
        final String fileName = "C://temp//person.ser";

        System.out.println("About to serialize...");
        Util.serializeObject(person, fileName);

        try {
            System.out.println("About to deserialize...");
            person = (Person)Util.deserializeObject(fileName);
           // System.out.println("id " + person.getId() + " Name " + person.getName() + " Age " + person.getAge());
            System.out.println(person);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
