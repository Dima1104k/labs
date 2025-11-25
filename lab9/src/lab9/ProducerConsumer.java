package lab9;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {

        CircularBuffer buffer1 = new CircularBuffer(10);
        CircularBuffer buffer2 = new CircularBuffer(10);

        for (int i = 1; i <= 5; i++) {
            final int producerId = i;
            Thread producer = new Thread(new Runnable() {
                @Override
                public void run() {
                    int messageNum = 0;
                    while (true) {
                        try {
                            messageNum++;
                            String msg = "Потік №" + producerId + " згенерував повідомлення " + messageNum;
                            buffer1.put(msg);
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            });
            producer.setDaemon(true);
            producer.start();
        }
        for (int i = 1; i <= 2; i++) {
            int translatorId = i;
            Thread translator = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msg = buffer1.get();
                            String newMsg = "Потік №" + translatorId + " переклав повідомлення: " + msg;
                            buffer2.put(newMsg);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            });
            translator.setDaemon(true);
            translator.start();
        }
        for (int i = 1; i <= 100; i++) {
            String msg = buffer2.get();
            System.out.println(i + ": " + msg);
        }
        System.out.println("\nПрочитано 100 повідомлень");
    }
}
