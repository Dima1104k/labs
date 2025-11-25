package lab9;

public class CircularBuffer {
    private String[] buffer;
    private int head;
    private int tail;
    private int count;
    private int capacity;

    public CircularBuffer(int capacity) {
        this.buffer = new String[capacity];
        this.capacity = capacity;
    }

    public synchronized void put(String item) throws InterruptedException {
        while (count == capacity) {
            wait();
        }
        buffer[tail] = item;
        /*     tail = (tail + 1) % capacity;*/
        tail++;
        if (tail == capacity) {
            tail = 0;
        }
        count++;
        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        String item = buffer[head];
        /*    head = (head + 1) % capacity;*/
        head++;
        if (head == capacity) {
            head = 0;
        }
        count--;
        notifyAll();
        return item;
    }

}
