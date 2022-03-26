import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class IncrementingTask implements Runnable {

// Для проверки проблемы атомарности :
// в этом классе и в Main
// разкомментировать команды помеченные : ' // Проблема атомарности '
// и закомментировать команды помеченные : ' // Использование Atomic класса '

    //private IntContainer counter;         // Проблема атомарности
    private AtomicInteger counter;          // Использование Atomic класса
    private CountDownLatch countDownLatch;

    ///public IncrementingTask(IntContainer counter) { // Проблема атомарности
    public IncrementingTask(AtomicInteger counter) { // Использование Atomic класса
        this.counter = counter;
    }

    public IncrementingTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        //counter.run(); // Проблема атомарности
        counter.incrementAndGet(); // Использование Atomic класса
    }
}
