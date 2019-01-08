package by.alexlevankou.smsmoneymanager.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import by.alexlevankou.smsmoneymanager.R;
import by.alexlevankou.smsmoneymanager.model.Currency;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.viewmodel.OperationViewModel;

public class EditActivity extends AppCompatActivity implements DatePickerFragment.OnDatePickListener {

    private TextView mPriceValue;
    private TextView mDateText;
    private EditText mDescription;
    private List<Button> mNumberButtons = new ArrayList<>();
    private Operation mOperation;
    private DateFormat mDateFormat = new SimpleDateFormat("dd MMMM");

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
        mDateText = findViewById(R.id.date_text);
        mPriceValue = findViewById(R.id.price_value);
        mDescription = findViewById(R.id.description);
        mDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showSoftKeyboard(v);
                } else {
                    hideSoftKeyboard(v);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            OperationViewModel mViewModel = ViewModelProviders.of(this).get(OperationViewModel.class);
            mViewModel.getOperation(id).observe(this, new Observer<Operation>() {
                @Override
                public void onChanged(@Nullable Operation operation) {
                    if(operation != null) {
                        mOperation = operation;
                        Currency price = operation.getPrice();
                        String description = operation.getName();
                        Date date = operation.getDate();

                        if(price != null) {
                            mPriceValue.setText(price.getValue());
                        }
                        if(description != null) {
                            mDescription.setText(description);
                        }
                        if(date != null) {
                            mDateText.setText(mDateFormat.format(date));
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
        Date date = mOperation.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Bundle bundle = new Bundle();
        bundle.putInt("year", cal.get(Calendar.YEAR));
        bundle.putInt("month", cal.get(Calendar.MONTH));
        bundle.putInt("day", cal.get(Calendar.DAY_OF_MONTH));
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
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

    @Override
    public void onDialogPositiveClick(Date date) {
        if(date != null) {
            mDateText.setText(mDateFormat.format(date));
            mOperation.setDate(date);
        }
    }

    @Override
    public void onDialogCancelClick(DialogFragment dialog) {

    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
