package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/*
Выполните вывод коллекции целых чисел в отдельной задаче, используя интерфейс ExecutorService.
Дождитесь выполнения операции в основном потоке.
Выполните подсчет минимального, максимального, среднего значения и суммы элементов коллекции.
а) Каждую операцию выполнить в отдельной задаче, используя ExecutorService.
б) Дождитесь выполнения операций в основном потоке.
в) Используйте несколько вариантов ExecutorService с разным количеством потоков внутри.
Исследуйте загрузку процессора при использовании Future.
Выполните Thread.sleep() в отдельной задаче и ожидайте завершения с использованием:
Метода Future.get()
Цикла while и метода Future.isDone()
 */

public class Main {

    public static void main(String[] args) {
       Main main = new Main();
       main.runEvaluateCollection(main.runPrintCollection());
    }

    private List<Integer> runPrintCollection() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        MyPrintIntegerCollection task = new MyPrintIntegerCollection(integers);
        // Создаём однопоточный пул
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // запускаем выполнение задачи
        Future<?> result = executor.submit(task);
        executor.shutdown();
        System.out.println("Awaiting 'MyPrintIntegerCollectionTask'...");
        try {
            result.get(); // Ожидаем завершения выполнения задачи
            System.out.println("Task 'MyPrintIntegerCollection' is completed");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 'MyPrintIntegerCollectionTask' has failed");
            e.printStackTrace();
        }
        return integers;
    }

    private void runEvaluateCollection(List<Integer> integers) {
        MinValue minValue = new MinValue(integers);
        MaxValue maxValue = new MaxValue(integers);
        AverageValue averageValue = new AverageValue(integers);
        SumValue sumValue = new SumValue(integers);
        Sleep sleep = new Sleep();

        // Первый вариант ExecutorService :
        //ExecutorService executor = Executors.newSingleThreadExecutor();

        // Второй вариант ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<Integer> minValueResult = executor.submit(minValue);
        Future<Integer> maxValueResult = executor.submit(maxValue);
        Future<Double> averageValueResult = executor.submit(averageValue);
        Future<Integer> sumValueResult = executor.submit(sumValue);
        Future<Object> sleepResult = executor.submit(sleep);
        executor.shutdown();
        try {
            Integer min = minValueResult.get();
            Integer max = maxValueResult.get();
            Double average = averageValueResult.get();
            Integer sum = sumValueResult.get();

            // Первый вариант ожидания
            // При ожидании сигнала о завершении при помощи метода Future.get()
            // процессор работает не больше, чем на 1 %
            sleepResult.get();

            // Второй вариант ожидания
            // При непрерывном опросе методом Future.isDone об окончании работы метода call()
            // из класса Sleep процессор работает на 25 %
//            while (!sleepResult.isDone()){
//            }
            System.out.println("Минимальное = " + min);
            System.out.println("Максимальное = " + max);
            System.out.println("Среднее = " + average);
            System.out.println("Сумма = " + sum);
            } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
