package by.alexlevankou.smsmoneymanager.repository;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import by.alexlevankou.smsmoneymanager.model.Category;
import by.alexlevankou.smsmoneymanager.model.OperationType;

public class Converter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static OperationType fromIntToOperationType(int value) {
        return OperationType.valueOf(value);
    }

    @TypeConverter
    public static int operationTypeToInt(OperationType type) {
        return type == null ? 0 : type.getValue();
    }

    @TypeConverter
    public static Category fromIntToCategory(int value) {
        return Category.valueOf(value);
    }

    @TypeConverter
    public static int categoryToInt(Category category) {
        return category == null ? 0 : category.getValue();
    }
}