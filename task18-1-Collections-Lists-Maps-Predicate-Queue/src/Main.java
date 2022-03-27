import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/*
1) Реализуйте вывод топ-5 контактов с наибольшим количеством звонков
  (List<Pair<Contact, Integer>>, Map<Contact, Integer>).
   a) Найдите все звонки для одного контакта из общей коллекции
   b) Сохраните связь-пару контакта и количество звонков его
   c) Повторите поиск для каждого контакта из коллекции
   d) Сохраните найденные пары в линейную коллекцию
   e) Отсортируйте коллекцию по убыванию количества звонков
   f) Выведите максимум 5 элементов отсортированной коллекции
2) Реализуйте вывод топ-5 контактов с наибольшим количеством сообщений
  (List<Pair<Contact, Integer>>, Map<Contact, Integer>).
  Аналогично предыдущему заданию
3) Напишите методы для фильтрации коллекций контактов,
  звонков и сообщений с использованием функционального интерфейса Predicate<>.
  Измените написанные ранее методы поиска с использованием новых методов фильтрации.
4) Напишите функции наполнения и обработки очереди контактов,
  звонков и сообщений с использованием FIFO и LIFO методов.
  Познакомиться с методами, специфичным для Queue и Deque
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
        contacts.add(new Contact("0501234560", "Дима",
                        LocalDate.of(2002,12,25)));
        contacts.add(new Contact("0991234560", "Таня",
                        LocalDate.of(2002,12,25)));


        List<CallLog> callLogs = new ArrayList<>();
        callLogs.add(new CallLog("0671234567",
                Instant.now(), ConditionCallLog.Входящий));
        callLogs.add(new CallLog("0671234567",
                Instant.now(), ConditionCallLog.Исходящий));

        callLogs.add(new CallLog("0681234567",
                Instant.now(), ConditionCallLog.Входящий));
        callLogs.add(new CallLog("0681234567",
                Instant.now(), ConditionCallLog.Исходящий));
        callLogs.add(new CallLog("0681234567",
                Instant.now(), ConditionCallLog.Исходящий));

        callLogs.add(new CallLog("0681234560",
                Instant.now(), ConditionCallLog.Входящий));
        callLogs.add(new CallLog("0681234560",
                Instant.now(), ConditionCallLog.Исходящий));

        callLogs.add(new CallLog("0501234560",
                Instant.now(), ConditionCallLog.Входящий));
        callLogs.add(new CallLog("0501234560",
                Instant.now(), ConditionCallLog.Исходящий));

        callLogs.add(new CallLog("0971234567",
                Instant.ofEpochSecond(1576800000), // 30 лет после эпохи
                ConditionCallLog.Пропущенный));

        callLogs.add(new CallLog("0991234560",
                Instant.now(), ConditionCallLog.Входящий));
        callLogs.add(new CallLog("0991234560",
                Instant.now(), ConditionCallLog.Исходящий));

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("0671234567",
                Instant.ofEpochSecond(1576800300), "Hellow"));
        messages.add(new Message("0671234567",
                Instant.ofEpochSecond(1576801300), "Good morning"));

        messages.add(new Message("0681234567",
                Instant.ofEpochSecond(1576801300), "Good day"));
        messages.add(new Message("0681234567",
                        Instant.ofEpochSecond(1576801300), "Good day"));

        messages.add(new Message("0681234560",
                        Instant.ofEpochSecond(1576801300), "Good day"));
        messages.add(new Message("0681234560",
                        Instant.ofEpochSecond(1576801300), "Good day"));

        messages.add(new Message("0501234560",
                        Instant.ofEpochSecond(1576801300), "Good day"));
        messages.add(new Message("0501234560",
                        Instant.ofEpochSecond(1576801300), "Good day"));

        messages.add(new Message("0971234567",
                        Instant.ofEpochSecond(1576801300), "Good day"));

        messages.add(new Message("0991234560",
                        Instant.ofEpochSecond(1576801300), "Good day"));
        messages.add(new Message("0991234560",
                        Instant.ofEpochSecond(1576801300), "Good day"));
        messages.add(new Message("0991234560",
                                Instant.ofEpochSecond(1576801300), "Good day"));

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
        // Коллекция Pair заполняетс, но не используетс
        Map1 map1 = new Map1();
        map1.map1(contacts, messages, callLogs);
        List<Pair<Contact, Integer>> pairs = new ArrayList<>();
        for (Contact contact: contacts) {
            pairs.add(new Pair<>(contact, map1.callsForContact(callLogs, new Predicate<CallLog>() {
                @Override
                public boolean test(CallLog callLog) {
                        return callLog.phoneNumber.equals(contact.phoneNumber);
                }
            }).size()));
        }
        // Работа с очередями
        Queue1 queue = new Queue1();
        queue.fifoLifo(contactSet, callLogSet, messageSet);
    }

    // Поиск в контактах
    // Не используется
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

    // Predicate
    public Collection<CallLog> findCallLog(Collection<CallLog> callLogs, Predicate<CallLog> predicate) {
        Contact contact = new Contact();
        Collection<CallLog> result = new ArrayList<>();
        for (CallLog callLog: callLogs) {
                if (predicate.test(callLog)) {
                result.add(callLog);
            }
        }
        return result;
    }

    // Predicate
    public Collection<Message> findMessage(Collection<Message> messages,
                                           Predicate<Message> predicate) {
        Collection<Message> result = new ArrayList<>();
        for (Message message: messages) {
            if (predicate.test(message)) {
                result.add(message);
            }
        }
        return result;
    }
}
