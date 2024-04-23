package search.linear.concurrent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        ConcurrentLinearSearch concurrentLinearSearch = new ConcurrentLinearSearch();
        int valueToFind = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        ArrayList<AtomicInteger> results = concurrentLinearSearch.concurrentLinearSearchAlgorithm(valueToFind);

        if (results.isEmpty()) {
            System.out.println(STR."Unable to find \{valueToFind}");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println(STR."Input value: \{valueToFind}, found at array position: \{i}");
            }
        }

    }

}
