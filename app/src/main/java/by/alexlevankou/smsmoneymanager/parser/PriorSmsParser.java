package by.alexlevankou.smsmoneymanager.parser;

import by.alexlevankou.smsmoneymanager.model.Expense;

public class PriorSmsParser extends SmsParser {

    private Expense expense;
    private String[] sections;

    public PriorSmsParser() {
        mBankName = "Priorbank";
        mDateAnchor = "Karta";
        mExpenseAnchor = "Oplata";
        mBalanceAnchor = "Dostupno";
    }

    protected boolean isValid(String smsBody)
    {
        return smsBody.startsWith(mBankName);
    }

    public void parse(String smsBody)
    {
        expense = new Expense();
        final String separator = "\\. ";
        sections = smsBody.split(separator);

    }

    protected void getBank(String string){}

    protected void getCard(String string){}

    protected void getDate(String string){}

    protected void getExpense(String string){}

    protected void getExpenseCurrency(String string){}

    protected void getExpenseName(String string){}

    protected void getBalance(String string){}

    protected void getBalanceCurrency(String string){}
}
