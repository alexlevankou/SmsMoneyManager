package by.alexlevankou.smsmoneymanager.model;

public enum OperationType {

    EXPENSE(0),
    INCOME(1);

    private final int value;

    OperationType(int value) {
        this.value = value;
    }

    public static OperationType valueOf(int value) {
        for (OperationType type : OperationType.values()) {
            if (type.value == value) return type;
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
