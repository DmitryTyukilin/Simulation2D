package main.java.org.simulation.utils;

import java.util.Random;

 public class RandomIntValue {

     public static int randomIndex(int minIndex, int maxIndex) {
         Random random = new Random();
         if (maxIndex > 1) {
             return random.nextInt(minIndex, maxIndex);
         } else {
             return 0;
         }
     }
 }

