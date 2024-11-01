package dimant.simulation.utils;

import java.util.Random;

 public class RandomIntValue {

     public static int randomIndex(int maxRandomIndex) {
         Random random = new Random();
         if (maxRandomIndex > 1) {
             return random.nextInt(0, maxRandomIndex);
         } else {
             return 0;
         }
     }
 }

