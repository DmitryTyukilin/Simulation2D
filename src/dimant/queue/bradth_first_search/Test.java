package dimant.queue.bradth_first_search;

import java.util.*;

public class Test {
    public static void main(String[] args) {


        Person oleg = new Person("oleg", 3456, false);
        Person dima = new Person("diman", 3573, false);
        Person vitiy = new Person("vitiy", 343, false);
        Person ivan = new Person("ivan", 65863, false);
        Person ilia = new Person("ilia", 65833, false);
        Person petr = new Person("petr", 12312, true);
        Person danil = new Person("danil", 780978, false);
        Person tanya = new Person("tanya", 213123, false);

        oleg.addFriends(dima);
        oleg.addFriends(vitiy);

        vitiy.addFriends(ilia);
        vitiy.addFriends(tanya);
        vitiy.addFriends(ivan);

        ivan.addFriends(petr);
        ivan.addFriends(danil);


        Queue<Person> peoples = new LinkedList<>();
        Queue<Person> tempFriends = new LinkedList<>();
        Person parent = null;
        Person tempPerson = oleg;

        if (tempPerson.isPlayGuitar()) {
            System.out.println("гитарист найден");
        } else if (tempPerson.getFriends().size() != 0) {
            parent = tempPerson;
        }
        peoples.addAll(tempPerson.getFriends());

        while (!peoples.isEmpty()) {

            if (peoples.peek().isPlayGuitar()) {
                peoples.peek().setFriendCall(parent);
                Person gutar = peoples.remove();

                System.out.println("гитарист найден " + gutar.toString());
                System.out.println("Список контактов в обратном порядке");
                for(Person person : gutar.myFriends()) {
                    System.out.println(person);
                }

                return;
            } else if (peoples.peek().getFriends().size() != 0) {
                Person temp = peoples.remove();
                temp.setFriendCall(parent);
                tempFriends.add(temp);
            } else peoples.remove();

            if (peoples.isEmpty()) {
                parent = tempFriends.remove();
                peoples.addAll(parent.getFriends());
            }
        }
    }
}








