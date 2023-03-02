package com.example.mortgage_calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.time.Month;

public class MainActivity extends AppCompatActivity {
    public static Mortgage mortgage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mortgage = new Mortgage(this);
    }

    public void modifyData(View view) {
        Intent myIntent = new Intent(this, DataActivity.class);
        this.startActivity(myIntent);
    }

    public void onStart() {
        super.onStart();
        updateView();
    }

    public void updateView() {
        TextView amountTV = (TextView) findViewById(R.id.amount);
        amountTV.setText(mortgage.getFormattedAmount());
        TextView yearsTV = (TextView) findViewById(R.id.years);
        yearsTV.setText(""+mortgage.getYears());
        TextView rateTV = (TextView) findViewById(R.id.rate);
        rateTV.setText(mortgage.getRate()+"%");
        TextView monthlyTV = (TextView) findViewById(R.id.monthly_payment);
        monthlyTV.setText(mortgage.formattedMonthlyPayment());
        TextView totalTV = (TextView) findViewById(R.id.total_payment);
        totalTV.setText(mortgage.formattedTotalPayment());
    }

    public static class Mortgage {
        public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");
        public static final String PREFERENCE_AMOUNT = "amount";
        public static final String PREFERENCE_YEARS = "years";
        public static final String PREFERENCE_RATE = "rate";
        private float amount, rate;
        private int years;

        public Mortgage(Context context) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            setAmount(pref.getFloat(PREFERENCE_AMOUNT, 10000.0f));
            setYears(pref.getInt(PREFERENCE_YEARS, 30));
            setRate(pref.getFloat(PREFERENCE_RATE, 0.35f));
        }

        public void setPreference(Context context) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putFloat(PREFERENCE_AMOUNT, amount);
            editor.putInt(PREFERENCE_YEARS, years);
            editor.putFloat(PREFERENCE_RATE, rate);
            editor.apply();
        }

        public void setAmount(float newAmount) {
            if(newAmount > 0) amount = newAmount;
        }
        public void setYears(int newYears) {
            if(newYears > 0) years = newYears;
        }
        public void setRate(float newRate) {
            if(newRate > 0) rate = newRate;
        }
        public float getAmount() {return amount;}
        public int getYears() {return years;}
        public float getRate() {return rate;}
        public String getFormattedAmount() {return MONEY.format(amount);}
        public float monthlyPayment() {
            float mRate = rate/12;
            double temp = Math.pow(1/(1+mRate), years*12);
            return amount*mRate/(float) (1-temp);
        }
        public String formattedMonthlyPayment() {return MONEY.format(monthlyPayment());}
        public float totalPayment() {return monthlyPayment()*years*12;}
        public String formattedTotalPayment() {return MONEY.format(totalPayment());}
    }
}