package classroom.c04;

import java.util.HashMap;
//import classroom.c04.kotlin.Pair;

public class Main {
    public static void main(String[] args) {
        Pair<String, Integer> pair1 = new Pair<String, Integer>("a", 5);
        // In Java you can't use primitive types as arguments of generics.
        /// Pair pair2 = new Pair<String, int>("a", 5);
        // But can use arrays of primitive types
        //Pair pair3 = new Pair<String, int[]>("a", new int[] {5, 7});
        Pair<String, Integer> pair1_new = new Pair<String, Integer>("a", 5);
        System.out.println("pair1 == pair1_new:         " + (pair1 == pair1_new));
        System.out.println("pair1.equals(pair1_new):    " + pair1.equals(pair1_new));
        System.out.println("pair1.compareTo(pair1_new): " + pair1.compareTo(pair1_new));

        // For real equality we can use a pool of objects.
        HashMap<Pair, Integer> hashMap = new HashMap<Pair, Integer>();
        hashMap.put(pair1, 1);
        hashMap.put(pair1, 2);
        System.out.println("HashMap size: " + hashMap.size());

        hashMap.put(pair1_new, 3);
        System.out.println("HashMap size: " + hashMap.size());
    }
}
