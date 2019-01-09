package by.alexlevankou.smsmoneymanager.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import by.alexlevankou.smsmoneymanager.model.Operation;

public class EditViewModel extends ViewModel {
    private final MutableLiveData<Operation> liveData = new MutableLiveData<>();

    public void updateOperation(Operation operation) {
        liveData.setValue(operation);
    }

    public LiveData<Operation> getOperation() {
        return liveData;
    }
}
