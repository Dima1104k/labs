package lab8;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long>{
    private int[] array;
    private int start;
    private int end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int len = end - start;
        if (len < 20) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
        int middle = start + len / 2;
        SumTask leftTask = new SumTask(array, start, middle);
        SumTask rightTask = new SumTask(array, middle, end);
        leftTask.fork();

        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();

        return rightResult + leftResult;

    }
}

