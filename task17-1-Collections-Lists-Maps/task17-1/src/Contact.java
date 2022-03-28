import java.time.LocalDate;
import java.util.Objects;

public class Contact {

    public String phoneNumber;
    public String name;
    public LocalDate birthday;

    public Contact(String phoneNumber, String name, LocalDate birthday) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.birthday = birthday;
    }

    public Contact() {
    }

    public boolean equals(Contact contactFind, Contact contactNext) {
        // По хэшкоду
        if (hashCode(contactFind) != hashCode(contactNext)) {
            return false;
        }
        // По ссылкам
        if (contactFind != contactNext) {
            return false;
        }
        // С null
        if (contactFind == null) {
            return false;
        }
        // На разность типов объектов
        if (contactFind.getClass() != contactNext.getClass()) {
            return false;
        }
        // Через промежуточеую переменную
        Contact theOther = (Contact) contactFind;
        if (!Objects.equals(theOther, contactNext))
            return false;
        // Перестановкой
        if (this.equals(contactFind)) {
            if (!contactFind.equals(this)) {
                return false;
            }
        }
        return true;
    }

    // Просто возвращает хэшкод
    public int hashCode(Contact contact) {
        return contact.hashCode();
    }
}
