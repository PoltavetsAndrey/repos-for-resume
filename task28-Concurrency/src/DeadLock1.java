public class DeadLock1 implements Runnable{

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public DeadLock1(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("DeadLock1 держит lock1 ");
            try {
                lock1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("DeadLock1 ждёт lock1 и lock2");
            synchronized (lock2) {
                System.out.println("DeadLock2 держит lock1 и lock2");
            }
        }
    }

}
