import java.io.IOException;

/*
Объявить класс Student. Объявить сеттеры для полей.
Бросить исключение IllegalArgumentException в setAge(int), если возраст дан неверно.
Бросить исключение IllegalArgumentException в setName(String), если имя пустое или null.
Объявить метод deserialize(String), который имеет возвращаемый тип Student. Этот метод ничего не делает пока.
Объявить класс MyDomainException наследником класса Exception.
Бросить проверяемое (checked) исключение MyDomainException из метода deserialize(String).
Вызвать метод deserialize(String) в контексте main и перехватить исключение
с выводом стека вызовов и других деталей (недостаточно вызвать метод printStackTrace).
Проверить сохранение стека вызовов.
Продемонстрировать перехват разных исключений с помощью нескольких блоков catch.
Продемонстрировать использование блока finally.
 */

public class Main {

    private Object cause;

    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    public void run(String[] args) {
        String s = "";
        try {
            //deserialize(s);
            double random = Math.random();
            if (random > 0.5) {
                appealIllegalMonitorStateException(s);
            } else {
                appealDeserialize(s);
            }
        } catch (IllegalMonitorStateException i) {
            i.printStackTrace();
            System.err.println(i.fillInStackTrace());
            System.err.println("Перехват1 Unchecked исключения");
        }
        catch (MyDomainException e) {
            // Возвращает детальное сообщение о вызванном исключении
            System.err.println("Тестовая ошибка: " + e.getMessage());

            // печатает несколько строк в выходной консоли с указанием
            // строк где бросаются ошибки
            e.printStackTrace();
            e.initCause(new MyDomainException("Проверка установки причин исключения"));

            // Возвращает исходную причину исключения
            System.err.println("Возврат типа исключения и его исходной причины : " + e.getCause());

            // Выдаёт подавленное исключение
            //e.getSuppressed();

            // позволяет получить объект типа Throwable,
            // с сохранением трассировки стека вызывающего объекта в только что созданном объекте
            System.err.println(" Тип ошибки с трассировкой: " + e.fillInStackTrace());

            /* следует использовать, когда каким-то образом у нас есть параллельное выполнение,
             которое может вызвать исключение, которое подавляется
             Добавляет указанное исключение к исключениям, которые были подавлены,
             чтобы поставить это исключение.*/
            //e.addSuppressed();

            // Должен быть переопределён для выдачи сообщения об ошибке на языке пользователя
            //e.getLocalizedMessage();

            // Устанавливает микроэлементы стека, которые будут возвращены getStackTrace()
            // и напечатанный printStackTrace() и связанные методы
            //e.setStackTrace(StackTraceElement[] stackTrace);

            System.err.println("Обработка ошибки успешно завершена");
        } finally {
            System.err.println("Финальный блок, который срабатывает независимо от появления " +
                    "ошибки после проверяемого блока \n");
        }
        try {
            appealUnchecked(s);
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
            System.err.println(i.fillInStackTrace());
            System.out.println("Перехват Unchecked исключения");
        }
    }

    public Student deserialize(String string) throws MyDomainException {
        throw new MyDomainException("Checked исключение");
    }

    public Student appealDeserialize(String s) throws MyDomainException {
        deserialize(s);
        return null;
    }

    public Student appealUnchecked(String s) throws IllegalArgumentException {
        try {
            deserialize(s);
        } catch (MyDomainException e) {
            System.err.println("Перехват Checked и бросок Unchecked исключения");
            throw new IllegalArgumentException();
        }
        return null;
    }

    public Student appealIllegalMonitorStateException(String string)
            throws IllegalMonitorStateException {
        throw new IllegalMonitorStateException("Unchecked исключение");
    }
}
