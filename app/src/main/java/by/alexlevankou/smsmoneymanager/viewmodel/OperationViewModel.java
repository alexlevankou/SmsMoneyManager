package by.alexlevankou.smsmoneymanager.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import by.alexlevankou.smsmoneymanager.CustomApplication;
import by.alexlevankou.smsmoneymanager.model.Operation;

public class OperationViewModel extends ViewModel {

    private LiveData<List<Operation>> mOperationListData;

    public LiveData<List<Operation>> getAllOperations() {
        mOperationListData = CustomApplication.getInstance().getRepository().getAllOperations();
        return mOperationListData;
    }
}
