import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
1. Создать проект, используя Gradle как систему сборки
2. Подключить библиотеку “org.apache.commons:commons-collections4” в проект.
Использовать 2-3 класса из этой библиотеки, например, BidiMap.
3. Подключить библиотеку “com.google.code.gson:gson” в проект.
Использовать его для преобразования экземпляров CallLog в JSON и обратно.
4. Создать JAR архив и продемонстрировать его выполнение из командной строки.
5. Реализовать метод создания экземпляра CallLog со случайными данными.
6. Создать Collection<CallLog> и заполнить ее случайными экземплярами до определенного размера,
переданного в качестве единственного аргумента командной строки.
Обработать отсутствие аргументов с помощью значения по умолчанию.
7. Используя gson, преобразовать созданную Collection<CallLog> в JSON и обратно.
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) {
        try {
            bidiMap();
            gsonToFrom();
            // Запуск jar-архива из командной строки Terminale
            // Вызов:
//            C:\Users\HP_ProBook\Documents\task23-1>java -cp out\artifacts\task23_jar\task23.jar Main

            // Результат:
//            Значение по ключу 'One': 1
//            Ключ по значению '1': One
//            Оригинальная карта: {One=1, Three=3, Two=2}
//            изменённая карта: {Three=3, Two=2}
//            Обратная карта: {2=Two, 3=Three}
//            изменённая карта: {Two=II, Three=3}
//
//            Переводим в GSON формат и обратно:
//            {"phoneNumber":"0671234567","conditionCallLog":"Входящий"}
//            Телефон: 0671234567 Состояние: Входящий
//            {"phoneNumber":"0671234567","conditionCallLog":"исходящий"}
//            Телефон: 0671234567 Состояние: исходящий
//            {"phoneNumber":"0681234567","conditionCallLog":"Пропущенный"}
//            Телефон: 0681234567 Состояние: Пропущенный
//
//            C:\Users\HP_ProBook\Documents\task23-1>
            CallLogFactory callLogFactory = new CallLogFactory();
            CallLog callLog = new CallLog();
            callLog = callLogFactory.create();
            System.out.println("\n Экземпляр CallLog со случайными данными: ");
            System.out.println(callLog.phoneNumber + ", " + callLog.conditionCallLog);
            int collectionSize = 10;
            if (args.length != 0) {
                collectionSize = Integer.parseInt(args[0]);
                System.out.println("\n Коллекция с размером: " + collectionSize + " из командной строки");
            } else {
                System.out.println("\n Коллекция с размером: " + collectionSize + " по умолчанию");
            }
            Collection<CallLog> callLogCollection = new ArrayList<>(collectionSize);
            for (int i = 0; i < collectionSize; i++) {
                callLogCollection.add(callLogFactory.create());
            }
            for (CallLog callLog1 : callLogCollection) {
                System.out.println(callLog1.phoneNumber + ", " + callLog1.conditionCallLog);
            }

            collectionGsonToFrom(callLogCollection);
        } catch (InaccessibleObjectException e) {
                e.printStackTrace();
            } finally {
            }
    }

    private void bidiMap() {
        BidiMap<String, String> bidiMap = new TreeBidiMap<>();
        bidiMap.put("One", "1");
        bidiMap.put("Two", "2");
        bidiMap.put("Three", "3");
        System.out.println("Значение по ключу 'One': " + bidiMap.get("One"));
        System.out.println("Ключ по значению '1': " + bidiMap.getKey("1"));
        System.out.println("Оригинальная карта: " + bidiMap);
        // Удаляем элемент по значению
        bidiMap.removeValue("1");
        System.out.println("изменённая карта: " + bidiMap);
        BidiMap<String, String> inverseBidiMap = bidiMap.inverseBidiMap();
        System.out.println("Обратная карта: " + inverseBidiMap);
        DualHashBidiMap<String, String> dualHashBidiMap =
                new DualHashBidiMap<>(bidiMap);
        dualHashBidiMap.replace("Two", "II");
        System.out.println("изменённая карта: " + dualHashBidiMap);
    }

    private List<CallLog> inputCallLog() {
        List<CallLog> callLogs = new ArrayList<>();
        callLogs.add(new CallLog("0671234567",
                "входящий"));
        callLogs.add(new CallLog("0671234567",
                "исходящий"));
        callLogs.add(new CallLog("0681234567",
                "пропущенный"));
        return callLogs;
    }

    private void gsonToFrom() {
        System.out.println("\n Переводим в GSON формат и обратно: ");
        for (CallLog callLog : inputCallLog()) {
            Gson gson = new Gson();
            String json = gson.toJson(callLog);
            System.out.println(json);
            CallLog callLog1 = new CallLog();
            callLog1 = gson.fromJson(json, CallLog.class);
            System.out.println("Телефон: " + callLog1.phoneNumber
                    + " Состояние: " + callLog1.conditionCallLog);
        }
    }

    private void collectionGsonToFrom(Collection<CallLog> callLogCollection) {
            System.out.println("\n Переводим коллекцию в GSON формат и обратно: ");
            Gson gson = new Gson();
            String json = gson.toJson(callLogCollection);
            System.out.println(json);
            Type collectionType = new TypeToken<Collection<CallLog>>(){}.getType();
            Collection<CallLog> callLogCollection1 = gson.fromJson(json, collectionType);
            for (CallLog callLog : callLogCollection1) {
                System.out.println("Телефон: " + callLog.phoneNumber
                    + " Состояние: " + callLog.conditionCallLog);
        }
    }
}
