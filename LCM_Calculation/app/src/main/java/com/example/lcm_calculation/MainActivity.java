package com.example.lcm_calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static int result;
    EditText number_aET;
    EditText number_bET;
    public void gotoCalculateActivity(View v) {
        EditText number_aET2 = (EditText) findViewById(R.id.number_a);
        String number_aString = number_aET2.getText().toString();
        EditText number_bET2 = (EditText) findViewById(R.id.number_b);
        String number_bString = number_bET2.getText().toString();
        number_aET = (EditText) findViewById(R.id.number_a);
        number_bET = (EditText) findViewById(R.id.number_b);
        try {
            int a = Integer.parseInt(number_aString);
            int b = Integer.parseInt(number_bString);
            if(a > 0 && b > 0) {
                Calculate(a, b);
                Intent it = new Intent(this, CalculateActivity.class);
                it.putExtra("result", Integer.toString(result));
                startActivity(it);
            } else {
                if(a < 0) number_aET.setText("");
                if(b < 0) number_bET.setText("");
                Toast.makeText(this, "Negative number", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            try {
                int a = Integer.parseInt(number_aString);
            } catch (Exception e1) {
                number_aET.setText("");
            }
            try {
                int b = Integer.parseInt(number_bString);
            } catch (Exception e2) {
                number_bET.setText("");
            }
            Toast.makeText(this, "Error number", Toast.LENGTH_SHORT).show();
        }
    }
    public void onStart() {
        super.onStart();
        updateView();
    }
    public void updateView() {
        number_aET = (EditText) findViewById(R.id.number_a);
        number_bET = (EditText) findViewById(R.id.number_b);
        number_aET.setText("");
        number_bET.setText("");
    }

    public void Calculate(int a, int b) {
        result = a*b/(gcd(a,b));
    }

    public int gcd(int a, int b) {
        if(a%b==0) return b;
        else return gcd(b, a%b);
    }
}