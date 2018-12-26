package by.alexlevankou.smsmoneymanager.parser;

public class PriorSmsParser extends SmsParser {

    public PriorSmsParser(){
        mBankName = "Priorbank";
    }

    public PriorSmsParser(String bank){}

    public boolean isValid(String smsBody)
    {
        return smsBody.startsWith(mBankName);
    }

    public void parse(String smsBody)
    {
        final String separator = "\\. ";
        String[] strings = smsBody.split(separator);
    }

    public void getBank(String string){}

    public void getCard(String string){}

    public void getDate(String string){}

    public void getExpense(String string){}

    public void getExpenseCurrency(String string){}

    public void getExpenseName(String string){}

    public void getBalance(String string){}

    public void getBalanceCurrency(String string){}
}
