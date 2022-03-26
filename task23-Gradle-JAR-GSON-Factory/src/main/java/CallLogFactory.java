public class CallLogFactory {

    public String phoneNumber;
    public String conditionCallLog;

    public CallLog create() {
        CallLog callLog = new CallLog();
        double random = Math.random();
        int randomIndex = (int) (random * 9.99);
        switch (randomIndex) {
            case 0, 1: {
                phoneNumber = "050";
                break;
            }
            case 2: {
                phoneNumber = "063";
                break;
            }
            case 3: {
                phoneNumber = "066";
                break;
            }
            case 4: {
                phoneNumber = "067";
                break;
            }
            case 5: {
                phoneNumber = "068";
                break;
            }
            case 6: {
                phoneNumber = "093";
                break;
            }
            case 7: {
                phoneNumber = "096";
                break;
            }
            case 8: {
                phoneNumber = "097";
                break;
            }
            case 9: {
                phoneNumber = "098";
                break;
            }
            case 10: {
                phoneNumber = "099";
            }
        }
        for (int i = 0; i < 7; i++) {
            random = Math.random();
            randomIndex = (int) (random * 9.99);
            phoneNumber = phoneNumber + (Integer.toString(randomIndex));
        }
        random = Math.random();
        randomIndex = (int) (random * 10);
        switch (randomIndex) {
            case 0, 1, 2: {
                conditionCallLog = "входящий";
                break;
            }
            case 3, 4, 5: {
                conditionCallLog = "исходящий";
                break;
            }
            case 6, 7, 8, 9: {
                conditionCallLog = "пропущенный";
            }
        }
        callLog.conditionCallLog = conditionCallLog;
        callLog.phoneNumber = phoneNumber;
        return callLog;
    }
}
