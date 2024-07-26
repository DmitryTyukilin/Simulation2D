package dimant.queue.bradth_first_search;

import java.util.*;

public class Test {
    public static void main(String[] args) {


        Person oleg = new Person("oleg", 3456, false);
        Person dima = new Person("diman", 3573, false);
        Person sacha = new Person("sacha", 3579, false);
        Person vitiy = new Person("vitiy", 343, false);
        Person ivan = new Person("ivan", 65863, false);
        Person ilia = new Person("ilia", 65833, false);
        Person petr = new Person("petr", 12312, false);
        Person danil = new Person("danil", 780978, true);
        Person tanya = new Person("tanya", 213123, true);

        oleg.addFriends(dima);
        oleg.addFriends(sacha);

        vitiy.addFriends(ilia);
        vitiy.addFriends(tanya);

        ivan.addFriends(petr);
        ivan.addFriends(danil);


        List<Person> personList = new ArrayList<>();
        personList.add(oleg);
        personList.add(vitiy);
        personList.add(ivan);


        Queue<Person> peoples = new LinkedList<>();
        peoples.addAll(personList);
        int counter = 0;

        while (!peoples.isEmpty()) {
            if (!peoples.peek().isPlayGuitar() && peoples.peek().getFriends().size() != 0) {
                peoples.addAll(peoples.peek().getFriends());
                peoples.remove();
                counter++;
                System.out.println("Step + " + counter );
            } else if (peoples.peek().isPlayGuitar()){
                System.out.println("гитарист найден + " + peoples.peek().toString());
                return;
            } else peoples.remove();

        }


    }
}





