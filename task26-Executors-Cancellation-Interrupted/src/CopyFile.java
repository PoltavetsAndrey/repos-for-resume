import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile implements Runnable {

    private File fileIn;
    private File fileOut;

    public CopyFile(File fileIn, File fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    @Override
    public void run() {
        byte[] bufer = new byte[4096];
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
            FileInputStream fileInputStream = new FileInputStream(fileIn);
                while (fileInputStream.available() > 0) {
                    int bufferWrite;
                    bufferWrite = fileInputStream.read(bufer);
                    fileOutputStream.write(bufer, 0, bufferWrite);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("\n Время для копирования файла " + fileIn + " в файл " + fileOut
                                + " закончилось");
                        break;
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n В файл: " + fileOut + " было записано буферно: " + fileOut.length() / 1000
                + " Kb из файла: " + fileIn + " длиной " + fileIn.length() / 1000 + " Kb");
    }
}
