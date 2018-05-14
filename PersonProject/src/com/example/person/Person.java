package com.example.person;

import java.util.*;

public class Person {
    private final boolean man;
    private final String name;
    private Person spouse; // супруг/супруга

    public boolean getMan()
    { return man; }

    public String getName()
    { return name; }

    public Person getSpouse()
    { return spouse; }

    public void setSpouse(Person spouse)
    { this.spouse = spouse; }

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }
    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife.
     * Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife,
     * false otherwise
     */

    public boolean marry(Person person) {
        if (this.getMan() != person.getMan()) {
            if (this.getSpouse() != person) {
                if (this.getSpouse() != null) this.getSpouse().divorce();
                this.divorce();

                if (person.getSpouse() != null) person.getSpouse().divorce();
                person.divorce();

                // marry(person);
                this.setSpouse(person);
                person.setSpouse(this);

                return true;
            }
            // return true;
        }

        return false;
    }

    /**
     * Sets spouse = null if spouse is not null
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (this.getSpouse() != null) {
            this.setSpouse(null);
            return true;
        }
        return false;
    }

    public static void printList(ArrayList<Person> personList) {
        for (Person person: personList) {
            String spousename = "";
            if (person.getSpouse() != null) spousename = person.getSpouse().getName();

            String manwoman = "";
            if (person.getMan()) manwoman = "man";
            else manwoman = "woman";

            System.out.printf("Man/woman: %s Name: %s Spouse: %s \n", manwoman, person.getName(), spousename);
        }
    }

    public static void main(String[] args) {

        Boolean man = false;
        ArrayList<Person> personList = new ArrayList();

        for (int i = 0; i < 5; i++) {
            man = !man;
            personList.add(new Person(man, "person " + (i + 1)));

            if (i > 0 && i != 4 && personList.get(i - 1).getSpouse() == null) {
                personList.get(i).setSpouse(personList.get(i - 1));
                personList.get(i - 1).setSpouse(personList.get(i));
            }
        }

        System.out.println("Source array:");
        Person.printList(personList);

        System.out.printf("\nmarry person%d - person%d : %s\n", 1, 2, personList.get(0).marry(personList.get(1)));
        System.out.printf("marry person%d - person%d : %s\n", 1, 4, personList.get(0).marry(personList.get(3)));
        System.out.printf("marry person%d - person%d : %s\n", 5, 2, personList.get(4).marry(personList.get(1)));

        System.out.println("\nResult array:");
        Person.printList(personList);
        System.out.println();
    }
}
