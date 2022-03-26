public class Singleton {

    private static int singletonCounter;
    private static final Object lock = new Object();
    private static Singleton singleton;

    private Singleton() {
        singletonCounter = 1;
    }

    public static Singleton getSingleton() {
// Если убрать synchronized, то появится проблема атомарности
        synchronized (lock) {
            if (singleton == null) {
                singleton = new Singleton();
            } else {
                    singletonCounter = singletonCounter + 1;
            }
        }
        return singleton;
    }

    public static int getSingletonCounter() {
        return singletonCounter;
    }
}
