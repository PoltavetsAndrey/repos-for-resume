package task20;

import java.io.*;

/*
Открыть текстовый файл и прочитать из него максимум 100 целых чисел.
Найти среднее значение прочитанных положительных чисел
Использовать запятую в качестве разделителя между числами
Прочитать из текстового файла список контактов в заданном формате,
сортировать по году рождения и вывести на экран максимум 5 контактов
Создать 2 входные точки (функции main в разных классах) - в одну перенести задания с записью,
а в другую - с чтением.
 */

public class Write {

    public static void main(String[] args) {
        Write main = new Write();
        try {
            main.run();
            main.commaSpace();
            main.saveContact10();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void run() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("text.txt"))) {
            writer.println("Hellow, world!");
        }
    }

    private void commaSpace() throws FileNotFoundException {
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream("int1000.txt"))) {
            int q;
            for(int i = 0; i< 1000; i++) {
                q = (int) (Math.random() * 1150 - 500);
                stream.writeInt(q);
                //stream.writeUTF(" "); // Вариант с пробелом
                stream.writeUTF(","); // Вариант с запятой
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
    }

    private void saveContact10() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("contact10.txt"))) {
            writer.println("Андрей | Андреев | 0671234567 | 2000");
            writer.println("Борис | Борисенко | 0672234567 | 1999");
            writer.println("Вася | Васильев | 0673234567 | 2001");
            writer.println("Гена | Галкин | 0674234567 | 1998");
            writer.println("Дима | Дмитриенко | 0675234567 | 2002");
            writer.println("Евгений | Ермолаев | 0676234567 | 1997");
            writer.println("Жора | Жданов | 0677234567 | 2003");
            writer.println("Илья | Ильенко | 0677234567 | 1996");
            writer.println("Костя | Корниенко | 0678234567 | 2004");
            writer.println("Лев | Левченко | 0679234567 | 1995");
        }
    }
}
