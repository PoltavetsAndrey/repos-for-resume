package task20;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/*
Создать текстовый файл и записать в него “Hello, world!”
Создать текстовый файл и записать в него 1000 случайных целых чисел в диапазоне от -500 до 650,
разделенных пробелами.
Использовать запятую в качестве разделителя между числами
Сохранить в текстовый файл список контактов (минимум 10, каждый с новой строки) в заданном формате:
Имя | Фамилия | Телефон | Год рождения
Создать 2 входные точки (функции main в разных классах) - в одну перенести задания с записью,
а в другую - с чтением.
 */

public class Read {

    public static void main(String[] args) {
        Read main = new Read();
        try {
            main.printHellow();
            System.out.println("Среднее из 100 положительных: " + main.average100());
            Collection<Contact> contacts = new ArrayList<Contact>();
            main.readContact(contacts);
            main.sortContacts(contacts);
            main.print5Contact(contacts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printHellow() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("text.txt"),
                StandardCharsets.UTF_8.name())) {
            while (scanner.hasNext()) {
                System.out.print(scanner.next() + " ");
            }
            System.out.println();
            }
        }


    // Берёт из файла 100 положительных чисел и считает средннее значение
    private double average100() throws FileNotFoundException {
        int sum = 0;
        try (DataInputStream stream = new DataInputStream(new FileInputStream("int1000.txt"))) {
            int i = 0;
            while (i < 100) {
               int bufer = stream.readInt();
               if (bufer >= 0) {
                   sum = sum + bufer;
                   i++;

               }
                stream.readUTF();
            }
        } catch (IOException e) {
            System.out.println("Ошибка вывода");
        }
        return sum / 100;
    }

    private void readContact(Collection<Contact> contacts) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("contact10.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String str = "";
                int passage = 0;
                Contact contact = new Contact();
                for (int i = 0; i < line.length(); i++) {
                    String char1 = String.valueOf(line.charAt(i));
                    if (!char1.equals("|")) {
                        if (i == line.length() - 1) {
                            str = String.valueOf(str.concat(char1));
                            contact.yearBirth = Integer.parseInt(str.trim());
                        } else {
                            str = String.valueOf(str.concat(char1));
                        }
                    } else {
                        i++;
                        passage++;
                        switch (passage) {
                            case 1: {
                                contact.name = str.trim();
                                break;
                            }
                            case 2: {
                                contact.sername = str.trim();
                                break;
                            }
                            case 3: {
                                contact.telephone = str.trim();
                                break;
                            }
                        }
                        str = "";
                    }
                }
                contacts.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Сортирует контакты по возростанию года рождения
    public void sortContacts(Collection<Contact> contacts) {
        Collection<Contact> collection = new ArrayList<>();
        int[] year = new int[contacts.size()];
        int i = 0;
        for (Contact contact: contacts) {
            year[i] =contact.yearBirth;
            collection.add(contact);
            i++;
        }
        contacts.clear();
        int bufer = 0;
        for (i = 0; i < collection.size() - 1; i++) {
            for (int j = i + 1; j < collection.size(); j++) {
                if (year[i] > year[j]) {
                    bufer = year[i];
                    year[i] = year[j];
                    year[j] = bufer;
                }
            }
        }
        for (i = 0; i < year.length; i++) {
            for (Contact contact : collection) {
                if (contact.yearBirth == year[i]) {
                    contacts.add(contact);
                }
            }
        }
    }

    public void print5Contact(Collection<Contact> contacts) {
        int i = 0;
        for (Contact contact: contacts) {
            if (contact != null && i < 5) {
                System.out.println("Имя: " + contact.name + " Фамилия: " + contact.sername
                        + " Телефон: " + contact.telephone + " Год рождения: " + contact.yearBirth);
                i++;
            }
        }
    }
}
