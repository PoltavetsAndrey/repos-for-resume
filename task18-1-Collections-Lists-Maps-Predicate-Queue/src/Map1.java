import java.util.*;

public class Map1 {

    public void map1(List<Contact> allContacts, List<Message> allMessages, List<CallLog> callLogList) {
        // Все звонки по номеру телефона
        Map<Contact, Collection<CallLog>> callLogMap = new HashMap<>();
        // Все сообщения по номеру телефона
        Map<Contact, Collection<Message>> groupsMessage = new HashMap<>();
        groupMessagesByContact(allContacts, allMessages, groupsMessage);
        List<NumberOfMessage> numberOfMessages = new ArrayList<>();
        // Вывод всех сообщений по каждому контакту
        System.out.println("");
        for (Contact contact: groupsMessage.keySet()) {
            Collection<Message> contactMessages = groupsMessage.get(contact);
            NumberOfMessage numberOfMessage1 = new NumberOfMessage(contact, contactMessages.size());
            // Сохранение пар контакт - количество сообщений в линейную коллекцию
            numberOfMessages.add(numberOfMessage1);
            System.out.println("Сообщения контакта " + contact.name);
            for (Message message: contactMessages) {
                System.out.println("Текст: " + message.text);
            }
        }

        groupCallLogsByContact(allContacts,callLogList, callLogMap);
        // Создание линейной коллекции
        List<NumberOfCalls> numberOfCalls = new ArrayList<>();
        // Вывод всех вызовов по каждому контакту
        System.out.println("");
        for (Contact contact: callLogMap.keySet()) {
            Collection<CallLog> contactCallLogs = callLogMap.get(contact);
            NumberOfCalls numberOfCalls1 = new NumberOfCalls(contact, contactCallLogs.size());
            // Сохранение пар контакт - количество вызовов в линейную коллекцию
            numberOfCalls.add(numberOfCalls1);
            System.out.println("Вызовы контакта " + contact.name);
            for (CallLog callLog: contactCallLogs) {
                System.out.println("Номер телефона " + callLog.phoneNumber);
            }
        }
        sortNumberOfCalls(numberOfCalls);
        printNumberOfCalls(numberOfCalls);
        sortNumberOfMessage(numberOfMessages);
        printNumberOfMessage(numberOfMessages);
    }

    private void printNumberOfMessage(List<NumberOfMessage> numberOfMessages) {
        System.out.println("");
        int i = 0;
        for (NumberOfMessage numberOfMessage1: numberOfMessages) {
            if (i < 5) {
                System.out.println("Имя: " + numberOfMessage1.contact.name +
                        " Количество сообщений: " + numberOfMessage1.numberOfMessage);
                i++;
            } else {
                break;
            }
        }
    }

    private void sortNumberOfMessage(List<NumberOfMessage> numberOfMessages) {
        // Переводим коллекцию в массив объектов
        NumberOfMessage[] numberOfMesageAr = new NumberOfMessage[numberOfMessages.size()];
        int k = 0;
        for (NumberOfMessage numberOfMessage1: numberOfMessages) {
            numberOfMesageAr[k] = numberOfMessage1;
            k++;
        }
        // Сортируем массив по убыванию количества звонков
        for (int i = 0; i < numberOfMesageAr.length - 1; i++) {
            for (int j = numberOfMesageAr.length - 1; j > i; j--) {
                if (numberOfMesageAr[j - 1].numberOfMessage < numberOfMesageAr[j].numberOfMessage) {
                    NumberOfMessage tmp = numberOfMesageAr[j - 1];
                    numberOfMesageAr[j - 1] = numberOfMesageAr[j];
                    numberOfMesageAr[j] = tmp;
                }
            }
        }
        numberOfMessages.clear(); // Очистка списка
        // Запись отсортированного списка
        for (int i = 0; i < k; i++) {
            numberOfMessages.add(numberOfMesageAr[i]);
        }
    }

    private void printNumberOfCalls(List<NumberOfCalls> numberOfCalls) {
        System.out.println("");
        int i = 0;
        for (NumberOfCalls numberOfCalls1: numberOfCalls) {
            if (i < 5) {
                System.out.println("Имя: " + numberOfCalls1.contact.name +
                        " Количество звонков: " + numberOfCalls1.numberCalls);
                i++;
            } else {
                break;
            }
        }
    }

    private void sortNumberOfCalls(List<NumberOfCalls> numberOfCalls) {
        // Переводим коллекцию в массив объектов
        NumberOfCalls[] numberOfCallsAr = new NumberOfCalls[numberOfCalls.size()];
        int k = 0;
        for (NumberOfCalls numberOfCalls1: numberOfCalls) {
            numberOfCallsAr[k] = numberOfCalls1;
            k++;
        }
        // Сортируем массив по убыванию количества звонков
        for (int i = 0; i < numberOfCallsAr.length - 1; i++) {
            for (int j = numberOfCallsAr.length - 1; j > i; j--) {
                if (numberOfCallsAr[j - 1].numberCalls < numberOfCallsAr[j].numberCalls) {
                    NumberOfCalls tmp = numberOfCallsAr[j - 1];
                    numberOfCallsAr[j - 1] = numberOfCallsAr[j];
                    numberOfCallsAr[j] = tmp;
                }
            }
        }
        numberOfCalls.clear(); // Очистка списка
        // Запись отсортированного списка
        for (int i = 0; i < k; i++) {
            numberOfCalls.add(numberOfCallsAr[i]);
        }
    }

    // Группируем все сообщения по каждому контакту
    // Predicate
    private Map<Contact, Collection<Message>> groupMessagesByContact(List<Contact> allContacts,
                                                                    List<Message> allMessages,
                                                                    Map<Contact,
                                                                    Collection<Message>> groupsMessage) {
        Main main = new Main();
        for (Contact contact: allContacts) {
            Predicate<Contact> contactPredicate = new Predicate<Contact>() {
                @Override
                public boolean test(Contact contact1) {
                    return contact1.equals(contact, contact1);
                }
            };
            for (Contact contact1: allContacts) {
                if (contactPredicate.test(contact1)) {
                  groupsMessage.put(contact1, main.findMessage(allMessages, new Predicate<Message>() {
                      @Override
                      public boolean test(Message message) {
                          return message.phoneNumber.contains(contact1.phoneNumber);
                      }
                  }));
                }
            }
        }
        return groupsMessage;
    }

    // Predicate
    private Map<Contact, Collection<CallLog>> groupCallLogsByContact(List<Contact> allContacts,
                                                                     List<CallLog> allCallLog,
                                                                     Map<Contact,
                                                                     Collection<CallLog>> groupsCallLog) {
        Main main = new Main();
        for (Contact contact: allContacts) {
            for (Contact contact1: allContacts) {
                Predicate<Contact> contactPredicate = new Predicate<Contact>() {
                    @Override
                    public boolean test(Contact contact) {
                        return contact1.equals(contact, contact1);
                    }
                };
                if (contactPredicate.test(contact1)) {
                    groupsCallLog.put(contact1, main.findCallLog(allCallLog, new Predicate<CallLog>() {
                        @Override
                        public boolean test(CallLog callLog) {
                            return callLog.phoneNumber.contains(contact1.phoneNumber);
                        }
                    }));
                }
            }
        }
        return groupsCallLog;
    }

    // Поиск звонков по контакту
    public Collection<CallLog> callsForContact(Collection<CallLog> callLogs,
                                               Predicate<CallLog> callLogPredicate) {
        List<CallLog> calls = new ArrayList<>();
        for (CallLog callLog: callLogs) {
            if (callLogPredicate.test(callLog)) {
                calls.add(callLog);
            }
        }
        return calls;
    }
}
