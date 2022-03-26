import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
Создать 2000 одновременных задач, которые увеличивают целочисленный счетчик на 1.
Подтвердить проблему атомарности.
Проверить ее решение с помощью volatile или Atomic классов.
Выполнить ожидание завершения задач с помощью CountDownLatch.
Получить доступ к singleton-объекту с “ленивой” (lazy) инициализацией
из множества потоков с использованием барьера инициализации при помощи класса CountDownLatch.
Подтвердить проблему атомарности.
Решить ее одним из известных способов.
Воспроизвести проблему dead lock любым способом.
*/

public class Main {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //final IntContainer container = new IntContainer(); // Проблема атомарности
        final AtomicInteger container = new AtomicInteger(); // Использование Atomic класса

        for (int i = 0; i < 2000; i++) {
            executorService.execute(new IncrementingTask(container));
        }
        executorService.shutdown();
        shutdownAndAwaitTermination(executorService);

        //System.out.println("counter = " + container.getCounter());// Проблема атомарности
        System.out.println("counter = " + container.get()); // Использование Atomic класса

        CountDownLatch doneSignal = new CountDownLatch(2000);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++) {
            executor.execute(new CountDownTask(doneSignal));
        }
        try {
            System.out.println("Количество незавершённых задач " +
                    "перед использованием CountDownLatch: " + doneSignal.getCount());
            doneSignal.await();
            executor.shutdown();
            shutdownAndAwaitTermination(executor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Доступ к Singleton объекту получался из " + Singleton.getSingletonCounter()
                + " потоков");

        DeadLock1 task1 = new DeadLock1(lock1, lock2);
        DeadLock2 task2 = new DeadLock2(lock1, lock2);
        ExecutorService executorDead = Executors.newFixedThreadPool(2);
        Future<?> future1 = executorDead.submit(task1);
        Future<?> future2 = executorDead.submit(task2);
        executorDead.shutdown();
        try {
            future1.get();
            future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdown();
                if (!pool.awaitTermination(60,TimeUnit.SECONDS)) {
                    System.out.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
