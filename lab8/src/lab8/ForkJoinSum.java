package lab8;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinSum {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }
        ForkJoinPool pool = new ForkJoinPool();

        SumTask task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();

        long sum = pool.invoke(task);
        long endTime = System.currentTimeMillis();

        System.out.println("SUM: " + sum);
        System.out.println("TIME: " + (endTime - startTime) + "ms");

        pool.shutdown();
    }



}
