package by.alexlevankou.smsmoneymanager.repository;

import java.util.List;

import by.alexlevankou.smsmoneymanager.model.Operation;


public class Repository {
    private final OperationDao mOperationDao;

    public Repository(OperationDao operationDao) {
        this.mOperationDao = operationDao;
    }

    //public LiveData<Operation> getOperation(Long operationId) {
    public Operation getOperation(Long operationId) {
        return mOperationDao.getById(operationId);
    }

    public List<Operation> getAllOperations() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                List<Operation> operations = mOperationDao.getAll();
                boolean v = true;
            }
        };
        new Thread(r).start();
        return null;
        //return mOperationDao.getAll();
    }

    public void addOperation(Operation operation) {
        mOperationDao.insert(operation);
    }
}