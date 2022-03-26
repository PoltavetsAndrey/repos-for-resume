import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

/*
Создать 2000 одновременных задач в Executor,
которые двигают единственную двухмерную точку (Point) в одном направлении.
Подтвердить проблему атомарности и решить ее с помощью ключевого слова synchronized (все варианты мониторов).
Создать и запустить поток для вычисления N-го числа Фибоначчи, наследуя класс Thread.
Создать и запустить поток для вычисления N-го числа Фибоначчи, используя класс Runnable.
Реализовать прерывание потоков из заданий выше через 3 секунды.
Вывести результаты после завершения всех потоков с отметкой прерванных вычислений.
 */

public class Main {

    public static void main(String[] args) {
        // Цикл, который запускает по очереди 5 вариантов передвижения точки
        for (int variantMove = 0; variantMove < 5; variantMove++) {
            ExecutorService executor = Executors.newCachedThreadPool();
            Point sharedPoint = new Point(0, 0);
            Collection<Future<?>> results = new ArrayList();
            for (int j = 0; j < 2_000; j++) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                Future<?> result = executor.submit(new PointMovingTask(sharedPoint, variantMove));
                results.add(result);
            }
            for (Future<?> result : results) {
                try {
                    result.get(3, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    result.cancel(true);
                    System.out.println("Время " + variantMove + "-го варианта закончилось");
                    break;
                }
            }

            if (variantMove == 0) {
                System.out.println("Вариант без synchronized : ");
            } else {
                System.out.println(variantMove + "-й вариант synchronized : ");
            }
            System.out.println("x = " + sharedPoint.x);
            System.out.println("y = " + sharedPoint.y);
            executor.shutdown();
        }

        int requiredNumberFibonacci = 78; // Требуемое число Фибоначи

        FibonachiThread thread = new FibonachiThread(requiredNumberFibonacci);
        thread.start();
        try {
            thread.join(3000);
            thread.interrupt();
            if (requiredNumberFibonacci > thread.i) {
                System.out.println("Хотели посчитать " + requiredNumberFibonacci + " , но успели только:");
            }
            System.out.println((thread.i - 1) + "-е число Fibonacci через класс Thread = "
                    + thread.current);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FibonacciRunnable runnable = new FibonacciRunnable(requiredNumberFibonacci);
        ExecutorService executorFibonacci = Executors.newSingleThreadExecutor();
        Future<?> future = executorFibonacci.submit(runnable);
        executorFibonacci.shutdown();
        try {
            future.get(3, TimeUnit.SECONDS);
            System.out.println((runnable.i - 1) + "-е число Fibonacci через интерфейс Runnable = "
                    + runnable.current);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            future.cancel(true);
        }
    }
}
