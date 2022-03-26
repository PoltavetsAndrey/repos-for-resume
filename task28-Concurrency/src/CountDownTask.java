import java.util.concurrent.CountDownLatch;

public class CountDownTask implements Runnable{

    private CountDownLatch countDownLatch;

    public CountDownTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
        Singleton.getSingleton();
    }
}
