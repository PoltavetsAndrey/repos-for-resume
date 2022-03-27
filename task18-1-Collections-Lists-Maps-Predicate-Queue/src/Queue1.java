import java.util.*;


public class Queue1 {

    public Queue1() {
    }

    public void fifoLifo(Set<Contact> contacts, Set<CallLog> callLogs, Set<Message> messages) {
        fifoContact(contacts);
        lifoContact(contacts);
        fifoCallLog(callLogs);
        lifoCallLog(callLogs);
        fifoMessage(messages);
        lifoMessage(messages);
    }

    public void fifoContact(Set<Contact> contactSet) {
        Queue<Contact> contactQueue = new LinkedList<>();
        for (Contact contact: contactSet) {
            contactQueue.add(contact);
        }
        System.out.println("Очередь из контактов fifo");
        Contact contact = new Contact();
        while (contactQueue.peek() != null) {
            contact = contactQueue.poll();
            System.out.println(contact.name);
        }
    }

    public void fifoCallLog(Set<CallLog> callLogSet) {
        Queue<CallLog> callLogQueue = new LinkedList<>();
        for (CallLog callLog: callLogSet) {
            callLogQueue.add(callLog);
        }
        System.out.println("Очередь из звонков fifo");
        CallLog callLog = new CallLog();
        while (callLogQueue.peek() != null) {
            callLog = callLogQueue.poll();
            System.out.println("Звонок с номера: " + callLog.phoneNumber + " был - "
                    + callLog.conditionCallLog);
        }
    }

    public void fifoMessage(Set<Message> messageSet) {
        Queue<Message> messageQueue = new LinkedList<>();
        for (Message message: messageSet) {
            messageQueue.add(message);
        }
        System.out.println("Очередь из сообщений fifo");
        Message message = new Message();
        //  возвращает без удаления элемент из начала очереди
        while (messageQueue.peek() != null) {
            // возвращает с удалением элемент из начала очереди
            message = messageQueue.poll();
            System.out.println("В сообщении с номера: " + message.phoneNumber + " было написано:  "
                    + message.text);
        }
    }

    public void lifoContact(Set<Contact> contactSet) {
         Deque<Contact> contactDeque = new LinkedList<>();
         for (Contact contact: contactSet) {
            contactDeque.addLast(contact);
        }
        System.out.println("Очередь из контактов lifo");
        Contact contact = new Contact();
        // возвращает  удалением элемент из конца очереди
        while (contactDeque.peekLast() != null) {
            // возвращает без удаления элемент из конца очереди
            contact = contactDeque.pollLast();
            System.out.println(contact.name);
        }
    }

    public void lifoCallLog(Set<CallLog> callLogSet) {
         Deque<CallLog> callLogDeque = new LinkedList<>();
         for (CallLog callLog: callLogSet) {
            callLogDeque.addLast(callLog);
        }
        System.out.println("Очередь из контактов lifo");
        CallLog callLog = new CallLog();
        // возвращает  удалением элемент из конца очереди
        while (callLogDeque.peekLast() != null) {
            // возвращает без удаления элемент из конца очереди
            callLog = callLogDeque.pollLast();
            System.out.println("Звонок с номера: " + callLog.phoneNumber + " был - "
                    + callLog.conditionCallLog);
        }
    }

    public void lifoMessage(Set<Message> messageSet) {
        Deque<Message> messageDeque = new LinkedList<>();
        for (Message message: messageSet) {
            messageDeque.addLast(message);
        }
        System.out.println("Очередь из сообщений lifo");
        Message message = new Message();
        //  возвращает без удаления элемент из конца очереди
        while (messageDeque.peekLast() != null) {
            // возвращает с удалением элемент из конца очереди
            message = messageDeque.pollLast();
            System.out.println("В сообщении с номера: " + message.phoneNumber + " было написано:  "
                    + message.text);
        }
    }
}
