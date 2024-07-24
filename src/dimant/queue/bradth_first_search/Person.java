package dimant.queue.bradth_first_search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private List<Person> friends = new ArrayList<>();
    private int numberPhone;
    private boolean isPlayGuitar;
    private boolean isOpen;

    public Person(String name, int numberPhone, boolean isPlayGuitar) {
        this.name = name;
        this.numberPhone = numberPhone;
        this.isPlayGuitar = isPlayGuitar;


    }

    public boolean isPlayGuitar(){
        return this.isPlayGuitar;
    }

    public void addFriends(Person person) {
        this.friends.add(person);

    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public boolean isHasOpen() {
        return isOpen;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public int getNumberPhone() {
        return numberPhone;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", numberPhone=" + numberPhone +
                ", isPlayGuitar=" + isPlayGuitar +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
