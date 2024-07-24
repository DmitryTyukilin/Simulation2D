package dimant.queue.bradth_first_search;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
       List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        boolean isValid = true;

        for (int i = 0; i < list.size(); i++) {
            if (isValid) {
                list.add(11);
                list.add(12);
                list.add(13);
                list.add(14);
                list.add(15);
                isValid = false;
            }
            System.out.println(list.get(i));
        }
    }
}
