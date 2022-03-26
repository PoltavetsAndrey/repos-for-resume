public class FibonacciRunnable implements Runnable {

    public long current = 1;
    public long previous = 0;
    public long bufer = 0;
    public int n;
    public int i;

    public FibonacciRunnable(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (i = 2; i <= n; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Время вычисления числа Fibonacci через интерфейс Runnable вышло");
                System.out.println("Хотели посчитать " + n + " , но успели только:");
                System.out.println((i - 1) + "-е число Fibonacci через интерфейс Runnable = "
                        + current);
                break;
            }
            bufer = current;
            current = current + previous;
            previous = bufer;
        }
    }
}
