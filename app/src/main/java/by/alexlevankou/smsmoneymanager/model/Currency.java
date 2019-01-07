package by.alexlevankou.smsmoneymanager.model;

import android.arch.persistence.room.ColumnInfo;

public class Currency {

    @ColumnInfo(name = "unit")
    private int mUnit = 0;

    @ColumnInfo(name = "cent")
    private int mCent = 0;

    private boolean hasCent = false;

    @ColumnInfo(name = "currency")
    private String mCurrency;

    @ColumnInfo(name = "centSeparator")
    private String mCentSeparator;
    @ColumnInfo(name = "thousandSeparator")
    private String mThousandSeparator;

    public int getUnit() {
        return mUnit;
    }
    public void setUnit(int mUnit) {
        this.mUnit = mUnit;
    }

    public int getCent() {
        return mCent;
    }
    public void setCent(int mCent) {
        this.mCent = mCent;
    }

    public boolean isHasCent() {
        return hasCent;
    }
    public void setHasCent(boolean hasCent) {
        this.hasCent = hasCent;
    }

    public String getCurrency() {
        return mCurrency;
    }
    public void setCurrency(String mCurrency) {
        this.mCurrency = mCurrency;
    }

    public String getCentSeparator() {
        return mCentSeparator;
    }
    public void setCentSeparator(String mCentSeparator) {
        this.mCentSeparator = mCentSeparator;
    }

    public String getThousandSeparator() {
        return mThousandSeparator;
    }
    public void setThousandSeparator(String mThousandSeparator) {
        this.mThousandSeparator = mThousandSeparator;
    }

    public Currency(){}

    public Currency(String money) {
        String[] parts = money.split(" ",2);
        try {
            String[] numbers = parts[0].split("\\.",2);
            if(numbers[0] != null) {
                mUnit = Integer.parseInt(numbers[0]);
            }
            if(numbers[1] != null) {
                mCent = Integer.parseInt(numbers[1]);
                hasCent = true;
            }
            mCurrency = parts[1];

        }catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        mCentSeparator = ".";
        mThousandSeparator = ",";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getValue());
        builder.append(" ");
        builder.append(mCurrency);
        return builder.toString();
    }

    public String getValue() {
        StringBuilder builder = new StringBuilder();
        builder.append(mUnit);
        if(hasCent) {
            builder.append(mCentSeparator);
            builder.append(mCent / 10);
            builder.append(mCent % 10);
        }
        return builder.toString();
    }
}
