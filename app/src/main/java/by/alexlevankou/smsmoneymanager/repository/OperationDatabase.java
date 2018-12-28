package by.alexlevankou.smsmoneymanager.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.alexlevankou.smsmoneymanager.model.Operation;

@Database(entities = {Operation.class}, version = 1)
public abstract class OperationDatabase extends RoomDatabase {
    public abstract OperationDao operationDao();
}