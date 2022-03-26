public class DeadLock2 implements Runnable {

    private  Object lock1 = new Object();
    private  Object lock2 = new Object();

    public DeadLock2(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock2) {
            System.out.println("DeadLock2 держит lock2 ");
            try {
                lock2.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("DeadLock2 ждёт lock2 и lock1");
            synchronized (lock1) {
                System.out.println("DeadLock1 держит lock2 и lock1");
            }
        }
    }
}
