public class IntContainer implements Runnable {

    private int counter;

    public IntContainer() {
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        counter++;
    }
}
