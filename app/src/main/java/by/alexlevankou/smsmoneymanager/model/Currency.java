package by.alexlevankou.smsmoneymanager.model;

public class Currency {

    private int mUnit = 0;
    private int mCent = 0;
    private boolean hasCent = false;
    private String mCurrency;
    private String mCentSeparator;
    private String mThousandSeparator;

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
        builder.append(mUnit);
        if(hasCent) {
            builder.append(mCentSeparator);
            builder.append(mCent / 10);
            builder.append(mCent % 10);
        }
        builder.append(" ");
        builder.append(mCurrency);
        return builder.toString();
    }
}
