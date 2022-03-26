import java.util.concurrent.TimeoutException;

class FibonachiThread  extends Thread {

    public long current = 1;
    public long previous = 0;
    public long bufer = 0;
    public int n;
    public int i;

    public FibonachiThread(int n) {
        this.n = n;
    }

    public void run() {
        for (i = 2; i <= n; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Время вычисления числа Fibonacci через класс Thread вышло ");
                    break;
                }
                    bufer = current;
                    current = current + previous;
                    previous = bufer;
        }
    }
}
