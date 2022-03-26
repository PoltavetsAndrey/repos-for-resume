import java.util.concurrent.Callable;

public class Factorial implements Callable<Long> {

    private int i;

    public Factorial(int i) {
        this.i = i;
    }

    @Override
    public Long call() throws Exception {
        long factorial = 1;
        System.out.println("Шаги вычисления факториала:");
        for (int j = 1; j <= i; j++) {
            System.out.print(factorial + " * " + j + " = ");
            factorial = factorial * j;
            System.out.println(factorial);
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Время для вычисления факториала закончилось");
                break;
            }
        }
        return factorial;
    }
}
