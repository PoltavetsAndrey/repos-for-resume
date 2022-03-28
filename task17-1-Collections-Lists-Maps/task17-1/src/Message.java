import java.time.Instant;
import java.util.Objects;

public class Message {

    public String phoneNumber;
    public Instant messageTime;
    public String text;

    public Message(String phoneNumber, Instant messageTime, String text) {
        this.phoneNumber = phoneNumber;
        this.messageTime = messageTime;
        this.text = text;
    }

    public boolean equals(Message messageFind, Message messageNext) {
        // По хэшкоду
        if (hashCode(messageFind) != hashCode(messageNext)) {
            return false;
        }
        // По ссылкам
        if (messageFind != messageNext) {
            return false;
        }
        // С null
        if (messageFind == null) {
            return false;
        }
        // На разность типов объектов
        if (messageFind.getClass() != messageNext.getClass()) {
            return false;
        }
        // Через промежуточеую переменную
        Message theOther = (Message) messageFind;
        if (!Objects.equals(theOther, messageNext))
            return false;
        // Перестановкой
        if (this.equals(messageFind)) {
            if (!messageFind.equals(this)) {
                return false;
            }
        }
        return true;
    }

    // Просто возвращает хэшкод
    public int hashCode(Message message) {
        return message.hashCode();
    }
}
