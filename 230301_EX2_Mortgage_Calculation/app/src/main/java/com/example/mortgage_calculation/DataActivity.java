package com.example.mortgage_calculation;

import static com.example.mortgage_calculation.MainActivity.mortgage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        updateView();
    }
    private void updateView() {
        mortgage = (MainActivity.Mortgage) mortgage;
        if(mortgage.getYears() == 10) {
            RadioButton rb10 = (RadioButton) findViewById(R.id.ten);
            rb10.setChecked(true);
        } else if(mortgage.getYears() == 15) {
            RadioButton rb15 = (RadioButton) findViewById(R.id.fifteen);
            rb15.setChecked(true);
        }
        EditText amountET = (EditText) findViewById(R.id.amount_input);
        String temp = String.valueOf(mortgage.getAmount());
        amountET.setText(temp);
        EditText rateET = (EditText) findViewById(R.id.rate_input);
        temp = String.valueOf(mortgage.getRate());
        rateET.setText(temp);
    }
    public void goBack(View view) {
        updateMortgageObject();
        finish();
    }
    public void updateMortgageObject() {
        mortgage = (MainActivity.Mortgage) mortgage;
        RadioButton rb10 = (RadioButton) findViewById(R.id.ten);
        RadioButton rb15 = (RadioButton) findViewById(R.id.fifteen);
        int years = 30;
        if(rb10.isChecked()) years = 10;
        else if(rb15.isChecked()) years = 15;
        mortgage.setYears(years);
        EditText amountET = (EditText) findViewById(R.id.amount_input);
        String amountString = amountET.getText().toString();
        EditText rateET = (EditText) findViewById(R.id.rate_input);
        String rateString = rateET.getText().toString();
        try {
            float amount = Float.parseFloat(amountString);
            mortgage.setAmount(amount);
            float rate = Float.parseFloat(rateString);
            mortgage.setRate(rate);
        } catch (NumberFormatException nfe) {
            mortgage.setAmount(10000.0f);
            mortgage.setRate(0.035f);
        }
    }
}