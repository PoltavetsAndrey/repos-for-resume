import java.util.*;

// Класс ещё не готов
public class Map1 {

    public void map1(List<Contact> allContacts, List<Message> allMessages, List<CallLog> callLogList) {
        // Все звонки по номеру телефона
        Map<Contact, Collection<CallLog>> callLogMap = new HashMap<>();
        //Map<String, Contact> contactMap = new HashMap<>();
        // Все сообщения по номеру телефона
        Map<Contact, Collection<Message>> groupsMessage = new HashMap<>();
        groupMessagesByContact(allContacts, allMessages, groupsMessage);
        // Вывод всех сообщений по каждому контакту
        System.out.println("");
        for (Contact contact: groupsMessage.keySet()) {
            Collection<Message> contactMessages = groupsMessage.get(contact);
            System.out.println("Сообщения контакта " + contact.name);
            for (Message message: contactMessages) {
                System.out.println("Текст: " + message.text);
            }
        }

        groupCallLogsByContact(allContacts,callLogList, callLogMap);
        System.out.println("");
        for (Contact contact: callLogMap.keySet()) {
            Collection<CallLog> contactCallLogs = callLogMap.get(contact);
            System.out.println("Вызовы контакта " + contact.name);
            for (CallLog callLog: contactCallLogs) {
                System.out.println("Номер телефона " + callLog.phoneNumber);
            }
        }
    }

    // Группируем все сообщения по каждому контакту
    private Map<Contact, Collection<Message>> groupMessagesByContact(List<Contact> allContacts,
                                                                    List<Message> allMessages,
                                                                    Map<Contact,
                                                                    Collection<Message>> groupsMessage) {
        Main main = new Main();
        int i = 0;
        for (Contact contact: allContacts) {
            if (i == 0) {
                groupsMessage.put(contact, main.findMessage(allMessages, contact.phoneNumber));
                i++;
                continue;
            }
            for (Contact contact1: allContacts) {
                if (contact1.equals(contact, contact1)) {
                    groupsMessage.put(contact1, main.findMessage(allMessages, contact1.phoneNumber));
                }
            }
        }
        return groupsMessage;
    }

    private Map<Contact, Collection<CallLog>> groupCallLogsByContact(List<Contact> allContacts,
                                                                     List<CallLog> allCallLog,
                                                                     Map<Contact,
                                                                     Collection<CallLog>> groupsCallLog) {
        Main main = new Main();
        int i = 0;
        for (Contact contact: allContacts) {
            if (i == 0) {
                groupsCallLog.put(contact, main.findCallLog(allCallLog, contact.phoneNumber));
                i++;
                continue;
            }
            for (Contact contact1: allContacts) {
                if (contact1.equals(contact, contact1)) {
                    groupsCallLog.put(contact1, main.findCallLog(allCallLog, contact1.phoneNumber));
                }
            }
        }
        return groupsCallLog;
    }

    // Поиск звонков по контакту
    public Collection<CallLog> callsForContact(Collection<CallLog> callLogs, Contact contact) {
        List<CallLog> calls = new ArrayList<>();
        for (CallLog callLog: callLogs) {
            if (callLog.phoneNumber.equals(contact.phoneNumber)) {
                calls.add(callLog);
            }
        }
        return calls;
    }
}
