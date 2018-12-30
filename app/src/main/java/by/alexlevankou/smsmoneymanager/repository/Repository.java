package by.alexlevankou.smsmoneymanager.repository;

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
}