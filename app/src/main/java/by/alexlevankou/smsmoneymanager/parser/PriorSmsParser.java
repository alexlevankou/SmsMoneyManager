package by.alexlevankou.smsmoneymanager.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.alexlevankou.smsmoneymanager.model.Category;
import by.alexlevankou.smsmoneymanager.model.Currency;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.model.OperationType;

import static by.alexlevankou.smsmoneymanager.model.OperationType.EXPENSE;

public class PriorSmsParser extends SmsParser {

    private Operation mOperation;


    public PriorSmsParser() {
        mBankName = "Priorbank";
        mNameAnchor = "^[A-Z]{3} ";
        mDateAnchor = "Karta";
        mExpenseAnchor = "Oplata";
        mBalanceAnchor = "Dostupno";
    }

    public boolean isValid(String smsBody)
    {
        return smsBody.startsWith(mBankName);
    }

    public Operation parse(String smsBody)
    {
        mOperation = new Operation();
        final String separator = "\\. ";
        List<String> sections = Arrays.asList(smsBody.split(separator));
        getBank(sections);
        getName(sections);
        getDate(sections);
        getExpense(sections);
        getBalance(sections);
        return mOperation;
    }

    protected void getBank(List<String> stringList){
        for (String s : stringList) {
            if(s.startsWith(mBankName)) {
                if(mOperation != null) {
                    mOperation.setBankName(s);
                }
                break;
            }
        }
    }

    protected void getName(List<String> stringList) {
        for (String s : stringList) {
            Pattern pattern = Pattern.compile(mNameAnchor);
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()) {
                if(mOperation != null) {
                  // TODO: check special room table or Map, if expense with this name already exist. then set existing category
                    mOperation.setCategory(Category.UNDEFINED);
                    mOperation.setName(s);
                }
                break;
            }
        }
    }

    protected void getCard(List<String> stringList){}

    protected void getDate(List<String> stringList) {
        for (String s : stringList) {
            if(s.startsWith(mDateAnchor)) {
                if(mOperation != null) {
                    String[] strings = s.split(" ");
                    if(strings.length >= 4) {
                        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
                        Date parsingDate;
                        try {
                            parsingDate = ft.parse(strings[2]);
                            mOperation.setDate(parsingDate);
                        }catch (ParseException e) {
                            System.out.println("Нераспаршена с помощью " + ft);
                        }
                    }
                }
                break;
            }
        }
    }

    protected void getExpense(List<String> stringList) {
        for (String s : stringList) {
            if(s.startsWith(mExpenseAnchor)) {
                if(mOperation != null) {
                    String[] strings = s.split(" ", 2);
                    Currency currency = new Currency(strings[1]);
                    mOperation.setOperationType(OperationType.EXPENSE);
                    mOperation.setPrice(currency);
                }
                break;
            }
        }
    }

    protected void getBalance(List<String> stringList) {
        for (String s : stringList) {
            if(s.startsWith(mBalanceAnchor)) {
                if(mOperation != null) {
                    String[] strings = s.split(":", 2);
                    Currency currency = new Currency(strings[1]);
                    mOperation.setBalance(currency);
                }
                break;
            }
        }
    }
}
