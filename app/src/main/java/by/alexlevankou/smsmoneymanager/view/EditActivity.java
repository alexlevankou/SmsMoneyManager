package by.alexlevankou.smsmoneymanager.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.alexlevankou.smsmoneymanager.R;
import by.alexlevankou.smsmoneymanager.model.Currency;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.viewmodel.OperationViewModel;

public class EditActivity extends AppCompatActivity {

    private TextView mPriceValue;
    private LinearLayout mDateView;
    private List<Button> mNumberButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int[] ids = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
                R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};
        for(int id : ids) {
            Button button = findViewById(id);
            mNumberButtons.add(button);
        }
        mPriceValue = findViewById(R.id.price_value);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            OperationViewModel mViewModel = ViewModelProviders.of(this).get(OperationViewModel.class);
            mViewModel.getOperation(id).observe(this, new Observer<Operation>() {
                @Override
                public void onChanged(@Nullable Operation operation) {

                    if(operation != null) {
                        Currency price = operation.getPrice();
                        if(price != null) {
                            mPriceValue.setText(price.getValue());
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onDatePickClick(View view) {
        boolean d = true;
    }

    public void onNumberClick(View view) {
        String str = mPriceValue.getText().toString();
        if(str != null)
        {
            for(int i = 0; i < mNumberButtons.size(); i++) {
                if(view.getId() == mNumberButtons.get(i).getId()) {
                    if(str.length() > 0 || i > 0){
                        mPriceValue.setText(str + Integer.toString(i));
                    }
                }
            }
        }
    }

    public void onDotClick(View view) {
        String str = mPriceValue.getText().toString();
        if(str != null && !str.contains(".")) {
            if(str.length() == 0) {
                mPriceValue.setText(str + "0.");
            } else {
                mPriceValue.setText(str + ".");
            }
        }
    }

    public void onDeleteClick(View view) {
        String str = mPriceValue.getText().toString();
        if(str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
            mPriceValue.setText(str);
        }
    }
}
