package by.alexlevankou.smsmoneymanager.parser;

abstract class SmsParser {

    protected abstract boolean isValid(String string);
    public abstract void parse(String string);

    protected abstract void getBank(String string);
    protected abstract void getCard(String string);
    protected abstract void getDate(String string);
    protected abstract void getExpense(String string);
    protected abstract void getExpenseCurrency(String string);
    protected abstract void getExpenseName(String string);
    protected abstract void getBalance(String string);
    protected abstract void getBalanceCurrency(String string);

    protected String mBankName;

}
