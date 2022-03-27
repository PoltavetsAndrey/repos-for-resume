import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Set1 {

    // Создания множеств Set
    public Set<Contact> contactSet(List<Contact> contactList) {
        Set<Contact> contactSet = new HashSet<>();
        for (Contact contact: contactList) {
            contactSet.add(contact);
        }
        return contactSet;
    }

    public Set<CallLog> callLogSet(List<CallLog> callLogList) {
        Set<CallLog> callLogSet = new HashSet<>();
        for (CallLog callLog: callLogList) {
            callLogSet.add(callLog);
        }
        return callLogSet;
    }

    public Set<Message> messageSet(List<Message> messageList) {
        Set<Message> messageSet = new HashSet<>();
        for (Message message: messageList) {
            messageSet.add(message);
        }
        return messageSet;
    }


    public boolean checkDublicateContact(Set<Contact> contactSet) {
        for (Contact checkContact: contactSet ) {
            int max1Equals = 0;
            for (Contact contact: contactSet) {
                if (contact.equals(checkContact, contact)) {
                    max1Equals++;
                    if (max1Equals > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkDublicateCallLog(Set<CallLog> callLogSet) {
        for (CallLog checkCallLog: callLogSet ) {
            int max1Equals = 0;
            for (CallLog callLog: callLogSet) {
                if (callLog.equals(checkCallLog, callLog)) {
                    max1Equals++;
                    if (max1Equals > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkDublicateMessage(Set<Message> messageSet) {
        for (Message checkMessage: messageSet ) {
            int max1Equals = 0;
            for (Message message: messageSet) {
                if (message.equals(checkMessage, message)) {
                    max1Equals++;
                    if (max1Equals > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
