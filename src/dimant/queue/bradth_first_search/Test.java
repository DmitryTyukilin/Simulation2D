package dimant.queue.bradth_first_search;

import java.util.*;

public class Test {
    public static void main(String[] args) {


        Person oleg = new Person("oleg", 3456, false);
        Person dima = new Person("diman", 3573, false);
        Person vitiy = new Person("vitiy", 343, false);
        Person ivan = new Person("ivan", 65863, false);
        Person petr = new Person("petr", 12312, false);
        Person danil = new Person("danil", 780978, true);
        Person tanya = new Person("tanya", 213123, true);

        oleg.addFriends(dima);
        oleg.addFriends(vitiy);

        vitiy.addFriends(ivan);
        vitiy.addFriends(tanya);

        ivan.addFriends(petr);
        ivan.addFriends(danil);


        List<Person> personList = new ArrayList<>();
        personList.add(oleg);


        Queue<Person> peoples = new LinkedList<>();
        List<Person> resultList = new LinkedList<>();
        List<Person> tempQueuePerson = new LinkedList<>();

        for (Person person : personList) {
            peoples.add(person);
            if (peoples.peek().isPlayGuitar()) {
                resultList.add(peoples.remove());
                return;
            } else if (peoples.peek().getFriends().size() != 0) {
                tempQueuePerson.add(peoples.remove());
                    } else if (peoples.peek().getFriends().size() != 0) {
                        peoples.addAll(peoples.element().getFriends());
                        resultList.add(peoples.remove());
                    } else peoples.remove();
                }
            }
        }



