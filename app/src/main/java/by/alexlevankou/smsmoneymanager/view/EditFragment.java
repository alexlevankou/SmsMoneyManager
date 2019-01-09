package by.alexlevankou.smsmoneymanager.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import by.alexlevankou.smsmoneymanager.viewmodel.EditViewModel;
import by.alexlevankou.smsmoneymanager.viewmodel.OperationViewModel;

public class EditFragment extends Fragment {

    private EditViewModel mEditViewModel;

    private TextView mPriceValue;
    private TextView mDateText;
    private EditText mDescription;
    private List<Button> mNumberButtons = new ArrayList<>();
    private Operation mOperation;
    private DateFormat mDateFormat = new SimpleDateFormat("dd MMMM");

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mEditViewModel = ViewModelProviders.of(getActivity()).get(EditViewModel.class);
        mEditViewModel.getOperation().observe(getActivity(), new Observer<Operation>() {
            @Override
            public void onChanged(@Nullable Operation operation) {
                mOperation = operation;
            }
        });

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("id");
            OperationViewModel viewModel = ViewModelProviders.of(getActivity()).get(OperationViewModel.class);
            viewModel.getOperation(id).observe(getActivity(), new Observer<Operation>() {
                @Override
                public void onChanged(@Nullable Operation operation) {
                    if(operation != null) {
                        mEditViewModel.updateOperation(operation);

                        Currency price = operation.getPrice();
                        String description = operation.getName();
                        Date date = operation.getDate();

                        if(price != null) {
                            mPriceValue.setText(price.getValue());
                        } else {
                            mPriceValue.setText("0");
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
        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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
        if(str != null) {
            if(str.length() > 1) {
                str = str.substring(0, str.length() - 1);
            } else {
                str = "0";
            }
            mPriceValue.setText(str);
        }
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void initView(View view) {
        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick(view);
            }
        };
        final int[] ids = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
                R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};

        for(int id : ids) {
            Button button = view.findViewById(id);
            button.setOnClickListener(numberButtonListener);
            mNumberButtons.add(button);
        }
        View deleteButton = view.findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClick(view);
            }
        });
        View dotButton = view.findViewById(R.id.button_dot);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDotClick(view);
            }
        });
        View categoryButton = view.findViewById(R.id.button_category);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open new fragment
                setOperationUpdates();
            }
        });
        View dateView = view.findViewById(R.id.date);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDatePickClick(view);
            }
        });
        mDateText = view.findViewById(R.id.date_text);
        mPriceValue = view.findViewById(R.id.price_value);
        mDescription = view.findViewById(R.id.description);
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
    }

    private void setOperationUpdates() {
        String description = mDescription.getText().toString();
        String priceValue = mPriceValue.getText().toString();
        mOperation.setName(description);
        Currency price = mOperation.getPrice();
        price.setValue(priceValue);
        mEditViewModel.updateOperation(mOperation);
    }
}
