package by.alexlevankou.smsmoneymanager.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import by.alexlevankou.smsmoneymanager.model.Operation;
import io.reactivex.annotations.Nullable;


public class Repository {

    private OperationDao mOperationDao;
    private LiveData<List<Operation>> mOperationListData;

    public Repository(OperationDao operationDao) {
        this.mOperationDao = operationDao;
    }

    public LiveData<Operation> getOperation(int operationId) {
        return mOperationDao.getById(operationId);
    }

    @Nullable
    public LiveData<List<Operation>> getAllOperations() {
        mOperationListData = mOperationDao.getAll();
        return mOperationListData;
    }

    public void addOperation(Operation operation) {
        mOperationDao.insert(operation);
    }
}