package by.alexlevankou.smsmoneymanager.model;

import java.util.Date;

public class Expense {

    private int id;
    private Date mDate;
    private String mBankName;
    private Currency mExpense;
    private Currency mBalance;

    public Expense(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return mBankName;
    }
    public void setBankName(String mBankName) {
        this.mBankName = mBankName;
    }

    public Date getDate() {
        return mDate;
    }
    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public Currency getExpense() {
        return mExpense;
    }
    public void setExpense(Currency mExpense) {
        this.mExpense = mExpense;
    }

    public Currency getBalance() {
        return mBalance;
    }
    public void setBalance(Currency mBalance) {
        this.mBalance = mBalance;
    }

}
