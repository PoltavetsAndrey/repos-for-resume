import java.io.File;
import java.util.concurrent.*;

/*
Реализовать подсчет факториала в отдельном классе с поддержкой отмены.
Подробно выводить каждый шаг программы.
Реализовать подсчет чисел Фибоначчи с сохранением последней пары чисел в полях класса задачи
(отдельный класс для задачи; поддержка отмены).
Подробно выводить каждый шаг программы.
Реализовать секундомер (отдельный класс для задачи; поддержка отмены).
Подробно выводить каждый шаг программы.
Реализовать копирование файловых потоков (отдельный класс для задачи; поддержка отмены).
Подробно выводить каждый шаг программы.
 */

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Main main = new Main();
        main.run();
    }

    private void run() throws ExecutionException {
        int i = 20; // Число для которого ищем факториал
        Factorial factorial = new Factorial(i);
        Fibonacci fibonacci = new Fibonacci();
        Stopwatch stopwatch = new Stopwatch();

        File fileIn = new File("Hydrangeas.jpg");
        File fileOut = new File("copy.jpg");
        CopyFile copyFile = new CopyFile(fileIn, fileOut);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Long> futureFactorial = executor.submit(factorial);
        Future futureFibonacci = executor.submit(fibonacci);
        Future futureStopwatch = executor.submit(stopwatch);
        Future futureCopyFile = executor.submit(copyFile);

        executor.shutdown();

         try {
            long factorial1 = 0;
            factorial1 = futureFactorial.get(27000, TimeUnit.MICROSECONDS);
            System.out.println("\n Факториал числа: " + i + " = "
                    + factorial1 + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            futureFactorial.cancel(true);
        }

        try {
            futureFibonacci.get(5, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            futureFibonacci.cancel(true);
        }

        try {
            futureStopwatch.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            futureStopwatch.cancel(true);
        }

        try {
            futureCopyFile.get(1, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            futureCopyFile.cancel(true);
        }
    }
}
