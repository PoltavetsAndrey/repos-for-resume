public class Point {

    // Для 2-го варианта
    private final Object lock2 = new Object();
    // Для 3-го варианта
    private static final Object lock3 = new Object();
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Вариант без synchronized
    public void move0(int dx, int dy) {
        x += dx;
        y += dy;
    }

    // 1-й вариант
    public synchronized void move1(int dx, int dy) {
        x += dx;
        y += dy;
    }

    // 2-й вариант
    public void move2(int dx, int dy) {
        synchronized (lock2) {
            x += dx;
            y += dy;
        }
    }

    // 3-й вариант
    public static void move3(Point point, int dx, int dy) {
        synchronized (lock3) {
            point.x += dx;
            point.y += dy;
        }
    }

    // 4-й вариант
    public static synchronized void move4(Point point, int dx, int dy) {
        point.x += dx;
        point.y += dy;
    }
}
