package by.alexlevankou.smsmoneymanager.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import by.alexlevankou.smsmoneymanager.CustomApplication;
import by.alexlevankou.smsmoneymanager.model.Operation;

public class OperationViewModel extends ViewModel {


    public LiveData<List<Operation>> getAllOperations() {
        return CustomApplication.getInstance().getRepository().getAllOperations();
    }

    public LiveData<Operation> getOperation(Long id) {
        return CustomApplication.getInstance().getRepository().getOperation(id);
    }
}
