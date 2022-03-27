import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/*
Записать в бинарный файл несколько разнотипных значений, используя DataOutputStream.
Прочитать записанные данные из предыдущего задания, используя DataInputStream.
Записать в бинарный файл коллекцию строк (String) и прочитать ее обратно,
используя DataOutputStream и DataInputStream.
Записать в бинарный файл коллекцию контактов (Contact) и прочитать ее обратно,
используя DataOutputStream и DataInputStream.
Записать в бинарный файл коллекцию контактов (Contact) и прочитать ее обратно,
используя Serializable, ObjectOutputStream и ObjectInputStream.
Посчитать количество файлов в директории и во всех вложенных папках.
 */

public class Main {

    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.run(args);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка файла");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка класса");;
        }
    }

    public void run(String[] args) throws FileNotFoundException, ClassNotFoundException {
        try {
            writeVariosTypes();
            readVariosTypes();
            writeCollectionStrings();
            readCollectionStrings();
            Collection<Contact> collection = new ArrayList<>();
            writeCollectionContactData(collection);
            readCollectionContact();
            int numberObject = 0;
            numberObject = writeCollectionContactObject(collection);
            readCollectionContactObject(numberObject);
            definitionNumberOfFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeVariosTypes() throws FileNotFoundException {
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(
                "variosTypes.bin"))) {
            stream.writeInt(12345);
            stream.writeUTF("Строка");
            stream.writeBoolean(true);
            stream.writeFloat(12345.12f);
            stream.writeChar('A');
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
    }

    public void readVariosTypes() throws FileNotFoundException {
        try (DataInputStream stream = new DataInputStream(new FileInputStream(
                "variosTypes.bin"))) {
            System.out.println("Чтение разных типов: ");
            System.out.println("int: " + stream.readInt());
            System.out.println("Текст: " + stream.readUTF());
            System.out.println("Boolean: " + stream.readBoolean());
            System.out.println("Float: " + stream.readFloat());
            System.out.println("Char: " + stream.readChar());
        } catch (IOException e) {
            System.out.println("Ошибка вывода");
        }
    }

    public void writeCollectionStrings() {
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(
                "collectionStrings.bin"))) {
            Collection<String> collection = new ArrayList<>();
            collection.add("One");
            collection.add("Two");
            collection.add("Three");
            for (String s : collection) {
                stream.writeUTF(s);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
    }

    public void readCollectionStrings() {
        try (DataInputStream stream = new DataInputStream(new FileInputStream(
                "collectionStrings.bin"))) {
            System.out.println("Вывод коллекции строк: ");
            while (stream.available() > 0) {
                System.out.println(stream.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Ошибка вывода");
        }
    }

    public void writeCollectionContactData(Collection<Contact> collection) throws FileNotFoundException {
        Contact contact = new Contact("Вася", "0671234567", 1234);
        Contact contact1 = new Contact("Петя", "0681234567", 5678);
        collection.add(contact);
        collection.add(contact1);
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(
                "contactData.bin"))) {
            for (Contact contact2 : collection) {
                stream.writeUTF(contact2.name);
                stream.writeUTF(contact2.telephone);
                stream.writeInt(contact2.password);
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }

    public void readCollectionContact() throws FileNotFoundException {
        Collection<Contact> collection1 = new ArrayList<>();
        try (DataInputStream stream = new DataInputStream(new FileInputStream(
                    "contactData.bin"))) {
            System.out.println("Вывод коллекции контактов: ");
            while (stream.available() > 0) {
                String name = stream.readUTF();
                String telephone = stream.readUTF();
                int password = stream.readInt();
                Contact contact;
                contact = new Contact(name, telephone, password);
                collection1.add(contact);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
        for (Contact contact : collection1) {
            System.out.println("Name: " + contact.name + "  Telephone: " +
                    contact.telephone + " Password: " + contact.password);
        }
    }

    public int writeCollectionContactObject(Collection<Contact> collection)
            throws FileNotFoundException {
        int numberObject = 0;
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(
                "collectionContactObject.bin"))) {
            for (Contact contact : collection) {
                stream.writeObject(contact);
                numberObject++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
        return numberObject;
    }

    public void readCollectionContactObject(int numberObject) throws IOException,
            ClassNotFoundException {
        Collection<Contact> collection3 = new ArrayList<>();
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(
                "collectionContactObject.bin"))) {
            while (numberObject > 0) {
                collection3.add((Contact) stream.readObject());
                numberObject--;
            }
            System.out.println("Вывод коллекции контактов прочитанных через Object");
            for (Contact contact : collection3) {
                System.out.println("Name: " + contact.name + " Telephone: " + contact.telephone +
                         " Password: " + contact.password);
            }
        }
    }

    public void definitionNumberOfFiles() {
        File dir = new File("F://task21");
        int number = 0;
        if (dir.isDirectory()) {
            for (File item1 : dir.listFiles()) {
                if (item1.isDirectory()) {
                    for (File item2 : item1.listFiles()) {
                        if (item2.isDirectory()) {
                            for (File item3 : item2.listFiles()) {
                                if (item3.isDirectory()) {
                                    for (File item4 : item3.listFiles()) {
                                        if (item4.isDirectory()) {
                                            for (File item5 : item4.listFiles()) {
                                                if (item5.isDirectory()) {
                                                    for (File item6 : item5.listFiles()) {
                                                        if (item6.isDirectory()) {
                                                            System.out.println("Переделать программу");
                                                        } else {
                                                            number++;
                                                        }
                                                    }
                                                } else {
                                                    number++;
                                                }
                                            }
                                        } else {
                                            number++;
                                        }
                                    }
                                } else {
                                    number++;
                                }
                            }
                        } else {
                            number++;
                        }
                    }
                } else {
                    number++;
                }
            }
        } else {
            number++;
        }
        System.out.println("В директории: " + dir.getName() + " найдено " + number + " файлов");
    }
}

