public class Stopwatch implements Runnable {

    public int seconds = 0;
    public int minuts = 0;

    public Stopwatch() {
    }

    @Override
    public void run() {
        System.out.println("\n Включился секундомер:");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                seconds++;
                if (seconds == 60) {
                    minuts++;
                    seconds = 0;
                }
            System.out.println(minuts + " minuts " + seconds + " seconds");
            } catch (InterruptedException e) {
                System.out.println("У секундомера вышло время");
                break;
            }
        }
    }
}
