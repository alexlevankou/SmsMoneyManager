package by.alexlevankou.smsmoneymanager.model;

public enum Category {

    UNDEFINED(0),
    CUSTOM(1),
    BILLS(2),
    FOOD(3),
    RESTAURANT(4),
    ENTERTAINMENT(5),
    CLOTHES(6),
    HOUSE(7),
    CAR(8),
    COMMUNICATION(9),
    HEALTH(10),
    SPORTS(11),
    GIFT(12),
    PET(13),
    TAXI(14),
    TRANSPORT(15),
    TOILETRY(16);

    private final int value;

    Category(int value) {
        this.value = value;
    }

    public static Category valueOf(int value) {
        for (Category category : Category.values()) {
            if (category.value == value) return category;
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
