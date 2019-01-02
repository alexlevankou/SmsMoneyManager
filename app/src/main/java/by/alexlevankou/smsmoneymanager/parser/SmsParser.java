package by.alexlevankou.smsmoneymanager.parser;

import java.util.List;

import by.alexlevankou.smsmoneymanager.model.Operation;

abstract class SmsParser {

    protected abstract boolean isValid(String string);
    public abstract Operation parse(String string);

    protected abstract void getName(List<String> stringList);
    protected abstract void getBank(List<String> stringList);
    protected abstract void getCard(List<String> stringList);
    protected abstract void getDate(List<String> stringList);
    protected abstract void getExpense(List<String> stringList);
    protected abstract void getBalance(List<String> stringList);

    String mBankName;
    String mNameAnchor;
    String mDateAnchor;
    String mExpenseAnchor;
    String mBalanceAnchor;
}
