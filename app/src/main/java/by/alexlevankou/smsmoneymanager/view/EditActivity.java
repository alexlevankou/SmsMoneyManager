package by.alexlevankou.smsmoneymanager.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import by.alexlevankou.smsmoneymanager.R;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.viewmodel.OperationViewModel;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long id = extras.getLong("id");
            OperationViewModel mViewModel = ViewModelProviders.of(this).get(OperationViewModel.class);
            mViewModel.getOperation(id).observe(this, new Observer<Operation>() {
                @Override
                public void onChanged(@Nullable Operation operation) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
