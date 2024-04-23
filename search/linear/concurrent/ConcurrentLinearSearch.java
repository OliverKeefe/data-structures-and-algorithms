package search.linear.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentLinearSearch {
    public int[] array;
    public int findValue;
    int middleOfArray;
    public ConcurrentLinearSearch() {
        this.array = array;
        this.findValue = findValue;
    }


    private static int[] randomlyFillArray() {
        int randomInt = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        int[] array = new int[randomInt];

        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        }

        return array;
    }

    public static ArrayList<AtomicInteger> concurrentLinearSearchAlgorithm(int findValue) {
        int[] array = randomlyFillArray();
        ArrayList<AtomicInteger> resultsArrayList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        int startOfArray = 0;
        int endOfArray = array.length - 1;
        int middleOfArray = array.length / 2;


        executorService.execute(() -> {
            for (int i = 0; i <= middleOfArray; i++) {
                if( array[i] == findValue) {
                    resultsArrayList.add(new AtomicInteger(i));
                }
            }
                });

        executorService.execute(() -> {
            for (int i = endOfArray; i > middleOfArray - 1; i--) {
                if (array[i] == findValue) {
                    resultsArrayList.add(new AtomicInteger(i));
                }
            }
        });

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return resultsArrayList;
        }
    }

