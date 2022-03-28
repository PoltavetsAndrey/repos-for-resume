import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/*
1) Определить и вывести уникальные элементы в коллекциях контактов, звонков и сообщений
    (List<Contact>, Set<Contact>).
  a) Реализовать equals и hashCode в классах контакта, звонка и сообщения.
  b) Создать экземпляры Set<> на основе соответствующих коллекций.
  c) Проверить отсутствие одинаковых элементов в наборах Set<>
2) Используя отображения, сгруппируйте все сообщения по контактам
   (Map<String, Contact>, Map<String, List<Message>>).
  a) Найдите все сообщения для одного контакта из общей коллекции
  b) Повторите поиск для каждого контакта из коллекции
  c) Сохраните результаты поисков в Map<>, где ключом выступает либо номер телефона,
     либо сам Contact (при условии реализации equals/hashCode)
3) Используя отображения, сгруппируйте все звонки по контактам
   (Map<String, Contact>, Map<String, List<CallLog>>).
   Аналогично предыдущему заданию
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0671234567", "Вася",
                LocalDate.of(2000,10,23)));
        contacts.add(new Contact("0971234567", "Петя",
                LocalDate.of(2001,11,24)));
        contacts.add(new Contact("0681234567", "Костя",
                LocalDate.of(2002,12,25)));
        contacts.add(new Contact("0681234560", "Витя",
                LocalDate.of(2002,12,25)));


        List<CallLog> callLogs = new ArrayList<>();
        callLogs.add(new CallLog("0671234567",
                Instant.now(), ConditionCallLog.Входящий));
        callLogs.add(new CallLog("0971234567",
                Instant.ofEpochSecond(1576800000), // 30 лет после эпохи
                ConditionCallLog.Пропущенный));

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("0671234567",
                Instant.ofEpochSecond(1576800300), "Hellow"));
        messages.add(new Message("0971234567",
                Instant.ofEpochSecond(1576801300), "Good morning"));
        messages.add(new Message("0971234567",
                Instant.ofEpochSecond(1576801300), "Good day"));

        // Ввод имени или номера телефона, который нужно найти
//        String findText = "Вася";
//
//        Collection<Contact> contactCollection = new ArrayList<>();
//        contactCollection = findContact(contacts, findText);
//        System.out.println(" Результат поиска контактов по параметру: " + findText);
//        for (Contact contact: contactCollection) {
//            System.out.println(" Имя: " + contact.name +
//                    "\n Номер телефона: " + contact.phoneNumber +
//                    "\n Дата рождения: " + contact.birthday);
//        }
//
//        Collection<CallLog> callLogCollection = new ArrayList<>();
//        callLogCollection = findCallLog(callLogs, findText);
//        System.out.println("\n Результат поиска звонков по параметру: " + findText);
//        for (CallLog callLog: callLogCollection) {
//            contactCollection = findContact(contacts, callLog.phoneNumber);
//            for (Contact contact: contactCollection) {
//                System.out.println(" Имя: " + contact.name +
//                        "\n Номер телефона: " + callLog.phoneNumber +
//                        "\n Дата и время разговора: " + callLog.callTime +
//                        "\n Статус звонка: " + callLog.conditionCallLog);
//            }
//        }
//
//        Collection<Message> messageCollection = new ArrayList<>();
//        messageCollection = findMessage(messages, findText);
//        System.out.println("\n Результат поиска сообщений по параметру: " + findText);
//        for (Message message: messageCollection) {
//            contactCollection = findContact(contacts, message.phoneNumber);
//            for (Contact contact: contactCollection) {
//                System.out.println(" Имя: " + contact.name +
//                        "\n Номер телефона: " + message.phoneNumber +
//                        "\n Дата и время сообщения: " + message.messageTime +
//                        "\n Текст сообщения: " + message.text);
//            }
//        }

        // Работа с уникальными элементами
        Set<Contact> contactSet = new HashSet<Contact>(contacts);
        Set1 set1 = new Set1();
        contactSet = set1.contactSet(contacts);
        System.out.println("\n В Set<Contact> одинаковые элементы отсутсвуют: "
                + set1.checkDublicateContact(contactSet));

        Set<CallLog> callLogSet = new HashSet<CallLog>(callLogs);
        callLogSet = set1.callLogSet(callLogs);
        System.out.println(" В Set<CallLog> одинаковые элементы отсутсвуют: "
                + set1.checkDublicateCallLog(callLogSet));

        Set<Message> messageSet = new HashSet<Message>(messages);
        messageSet = set1.messageSet(messages);
        System.out.println(" В Set<Message> одинаковые элементы отсутсвуют: "
                + set1.checkDublicateMessage(messageSet));

        // Сохраняем найденные пары в линейную коллекцию
        // Не готово
        Map1 map1 = new Map1();
        map1.map1(contacts, messages, callLogs);
        List<Pair<Contact, Integer>> pairs = new ArrayList<>();
        for (Contact contact: contacts) {
            pairs.add(new Pair<>(contact, map1.callsForContact(callLogs, contact).size()));
        }
    }

    // Поиск в контактах
    public Collection<Contact> findContact(Collection<Contact> contacts, String findText) {
        Collection<Contact> result = new ArrayList<>();
        for (Contact contact: contacts) {
            if (contact.name.contains(findText) ||
                    contact.phoneNumber.contains(findText)) {
                result.add(contact);
            }
        }
        return result;
    }

    // Поиск в звонках
    public Collection<CallLog> findCallLog(Collection<CallLog> callLogs, String findText) {
        Contact contact = new Contact();
        Collection<CallLog> result = new ArrayList<>();
        for (CallLog callLog: callLogs) {
            if (callLog.phoneNumber.contains(findText)) {
                result.add(callLog);
            }
        }
        return result;
    }

    // Поиск в сообщениях по текстовому значению
    public Collection<Message> findMessage(Collection<Message> messages, String findText) {
        Collection<Message> result = new ArrayList<>();
        for (Message message: messages) {
            if (message.phoneNumber.contains(findText) ||
                    message.text.contains(findText)) {
                result.add(message);
            }
        }
        return result;
    }
}
