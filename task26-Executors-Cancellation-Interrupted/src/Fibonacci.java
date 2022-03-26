public class Fibonacci implements Runnable {

    public long before = 0;
    public long last = 1;

    public Fibonacci() {
    }

    @Override
    public void run() {
        long bufer = 0;
        System.out.println("\n Числа Фибоначи: ");
        while (last < 2_000_000_000) {
            System.out.println("Предпоследнее: " + before + " Последнее: " + last);
            bufer = last;
            last = last + before;
            before = bufer;
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Для вычисления чисел Фибоначи не хватило времени");
                break;
            }
        }
    }
}
