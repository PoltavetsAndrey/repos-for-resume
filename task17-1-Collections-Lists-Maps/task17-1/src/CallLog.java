import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class CallLog {

    public String phoneNumber;
    public Instant callTime;
    public ConditionCallLog conditionCallLog;

    public CallLog(String phoneNumber, Instant callTime, ConditionCallLog conditionCallLog) {
        this.phoneNumber = phoneNumber;
        this.callTime = callTime;
        this.conditionCallLog = conditionCallLog;
    }

    public boolean equals(CallLog callLogFind, CallLog callLogNext) {
        // По хэшкоду
        if (hashCode(callLogFind) != hashCode(callLogNext)) {
            return false;
        }
        // По ссылкам
        if (callLogFind != callLogNext) {
            return false;
        }
        // С null
        if (callLogFind == null) {
            return false;
        }
        // На разность типов объектов
        if (callLogFind.getClass() != callLogNext.getClass()) {
            return false;
        }
        // Через промежуточеую переменную
        CallLog theOther = (CallLog) callLogFind;
        if (!Objects.equals(theOther, callLogNext))
            return false;
        // Перестановкой
        if (this.equals(callLogFind)) {
            if (!callLogFind.equals(this)) {
                return false;
            }
        }
        return true;
    }

    // Просто возвращает хэшкод
    public int hashCode(CallLog callLog) {
        return callLog.hashCode();
    }
}



