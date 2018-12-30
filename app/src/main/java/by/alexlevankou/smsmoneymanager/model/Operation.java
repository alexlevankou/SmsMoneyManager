package by.alexlevankou.smsmoneymanager.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Operation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "bank")
    private String mBankName;

    @Embedded(prefix = "price")
    private Currency mPrice;

    @Embedded(prefix = "balance")
    private Currency mBalance;

    public Operation(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return mBankName;
    }
    public void setBankName(String bankName) {
        this.mBankName = bankName;
    }

    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        this.mDate = date;
    }

    public Currency getPrice() {
        return mPrice;
    }
    public void setPrice(Currency price) {
        this.mPrice = price;
    }

    public Currency getBalance() {
        return mBalance;
    }
    public void setBalance(Currency balance) {
        this.mBalance = balance;
    }

}
