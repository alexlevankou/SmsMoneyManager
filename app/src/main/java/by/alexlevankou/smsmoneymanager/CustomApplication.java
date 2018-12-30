package by.alexlevankou.smsmoneymanager;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.Executor;

import by.alexlevankou.smsmoneymanager.repository.OperationDatabase;
import by.alexlevankou.smsmoneymanager.repository.Repository;

public class CustomApplication extends Application {

    public static CustomApplication mInstance;
    private OperationDatabase mDatabase;
    private Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mDatabase = Room.databaseBuilder(this, OperationDatabase.class, "operation_database")
                .build();
        mRepository = new Repository(mDatabase.operationDao());
    }

    public static CustomApplication getInstance() {
        return mInstance;
    }

    public OperationDatabase getDatabase() {
        return mDatabase;
    }

    public Repository getRepository() {
        return mRepository;
    }
}
