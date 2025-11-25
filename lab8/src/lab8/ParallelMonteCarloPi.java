package lab8;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelMonteCarloPi {
    public static void main(String[] args) throws Exception {

        int numThreads = Integer.parseInt(args[0]);

        long startTime = System.currentTimeMillis();

        long totalIterations = 1_000_000_000L;
        long iterationsPerThread = totalIterations / numThreads;
        AtomicLong counter = new AtomicLong(0);


        Runnable task = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                long localCount = 0;

                for (int i = 0; i < iterationsPerThread; i++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1) {
                        localCount++;
                    }
                }
                counter.addAndGet(localCount);
            }
        };

        Thread[] threads = new Thread[numThreads];

        for(int i = 0; i < numThreads; i++){
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;


        double pi = 4.0 * counter.get() / totalIterations;
        System.out.println("PI: " + pi);
        System.out.println("THREADS " + numThreads);
        System.out.println("ITERATIONS " + String.format("%,d", totalIterations));
        System.out.println("TIME " + duration + "ms");

    }
}

