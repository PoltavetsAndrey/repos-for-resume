import java.io.*;

/*
Реализовать копирование большого файла (более 1Гб) средствами InputStream и OutputStream
Реализовать разные подходы:
1) Побайтовое чтение и запись напрямую из/в File[Input/Output]Stream
2) Буферное (локальный байтовый массив на 4Кб) чтение и запись напрямую из/в File[Input/Output]Stream
3) Побайтовое чтение и запись через Buffered[Input/Output]Stream
4) Буферное (локальный байтовый массив на 4Кб) чтение и запись через Buffered[Input/Output]Stream
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
       try {
           readWriteByteFile();
           readWriteBuferFile();
           readWriteByteBufer();
           readWriteArrayByteBufer();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    // Побайтовое чтение и запись напрямую из/в File[Input/Output]Stream
    private void readWriteByteFile() throws IOException {
        File fileIn = new File("gradlew");
        File fileOut = new File("byte.bin");
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
        int oneByte;
        int size1Gb = 1000000000;
        // Для теста i = 20000
        for (int i = 0; i < size1Gb;) {
            // Если файл для чтения занимает меньше чем 1 Gb,
            // то данные из него будут записываться в файл для записи,
            // пока его размер не достигнет 1 Gb
            FileInputStream fileInputStream = new FileInputStream(fileIn);
            while (fileInputStream.available() > 0) {
                oneByte = fileInputStream.read();
                fileOutputStream.write(oneByte);
                i++;
            }
        }
        System.out.println("В файл: " + fileOut + " было записано побайтово: " + fileOut.length() / 1000 + " Kb" +
                 " из файла: " + fileIn);
    }

    // Буферное (локальный байтовый массив на 4Кб) чтение и запись
    // напрямую из/в File[Input/Output]Stream
    private void readWriteBuferFile() throws IOException {
        byte[] bufer = new byte[4096];
        // Здесь имя файла лучше поменять на файл размером больше 1Gb
        File fileIn = new File("gradlew");
        File fileOut = new File("byte.bin");
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
        int size1Gb = 1000000000;
        // Для теста i = 20000
        for (int i = 0; i < size1Gb;) {
            // Если файл для чтения занимает меньше чем 1 Gb,
            // то данные из него будут записываться в файл для записи,
            // пока его размер не достигнет 1 Gb
            FileInputStream fileInputStream = new FileInputStream(fileIn);
            while (fileInputStream.available() > 0) {
                int buferWrite;
                buferWrite = fileInputStream.read(bufer);
                fileOutputStream.write(bufer, 0, buferWrite);
                i = i + buferWrite;
            }
        }
        System.out.println("В файл: " + fileOut + " было записано буферно: " + fileOut.length() / 1000 + " Kb" +
                " из файла: " + fileIn);
    }

    // Побайтовое чтение и запись через Buffered[Input/Output]Stream
    private void readWriteByteBufer() {
        try {
            File fileIn = new File("gradlew");
            File fileOut = new File("byte.bin");
            FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] buffer = new byte[1];
            int bufferLength;
            int size1Gb = 1000000000;
            // Для теста i = 1000000
            for (int i = 0; i < size1Gb; ) {
                // Если файл для чтения занимает меньше чем 1 Gb,
                // то данные из него будут записываться в файл для записи,
                // пока его размер не достигнет 1 Gb
                FileInputStream fileInputStream = new FileInputStream(fileIn);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(
                        fileInputStream, 1);
                while ((bufferLength = bufferedInputStream.read(buffer)) != -1) {
                    bufferedOutputStream.write(buffer, 0, bufferLength);
                    i = i + bufferLength;
                }
            }
            System.out.println("В файл: " + fileOut + " через Buffer(I/O)Stream " +
                    "было записано побайтово: " + fileOut.length() / 1000 + " Kb" +
                    " из файла: " + fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Буферное (локальный байтовый массив на 4Кб) чтение и запись через Buffered[Input/Output]Stream
    private void readWriteArrayByteBufer() {
        try {
            File fileIn = new File("gradlew");
            File fileOut = new File("byte.bin");
            FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] buffer = new byte[4096];
            int bufferLength;
            int size1Gb = 1000000000;
            // Для теста i = 1000000
            for (int i = 0; i < size1Gb; ) {
                // Если файл для чтения занимает меньше чем 1 Gb,
                // то данные из него будут записываться в файл для записи,
                // пока его размер не достигнет 1 Gb
                FileInputStream fileInputStream = new FileInputStream(fileIn);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(
                        fileInputStream, 4096);
                while ((bufferLength = bufferedInputStream.read(buffer)) != -1) {
                    bufferedOutputStream.write(buffer, 0, bufferLength);
                    i = i + bufferLength;
                }
            }
            System.out.println("В файл: " + fileOut + " через Buffer(I/O)Stream " +
                    "было записано по 4 Kb: " + fileOut.length() / 1000 + " Kb" +
                    " из файла: " + fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
