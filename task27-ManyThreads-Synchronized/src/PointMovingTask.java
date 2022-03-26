public class PointMovingTask implements Runnable {

    private final Point point;
    private int counter;

    public PointMovingTask(Point point, int counter) {
        this.point = point;
        this.counter = counter;
    }

    @Override
    public void run() {
        switch (counter) {
            case 0: {
                point.move0(1, 1);
                break;
            }
            case 1: {
                point.move1(1, 1);
                break;
            }
            case 2: {
                point.move2(1, 1);
                break;
            }
            case 3: {
                Point.move3(point, 1, 1);
                break;
            }
            case 4: {
                Point.move4(point, 1, 1);
                break;
            }
        }
    }
}
