package by.alexlevankou.smsmoneymanager.parser;

import java.util.List;

abstract class SmsParser {

    protected abstract boolean isValid(String string);
    public abstract void parse(String string);

    protected abstract void getBank(List<String> stringList);
    protected abstract void getCard(List<String> stringList);
    protected abstract void getDate(List<String> stringList);
    protected abstract void getExpense(List<String> stringList);
    protected abstract void getBalance(List<String> stringList);

    String mBankName;
    String mDateAnchor;
    String mExpenseAnchor;
    String mBalanceAnchor;
}
