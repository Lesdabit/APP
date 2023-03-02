package com.example.lcm_calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoCalculateActivity(View v) {
        EditText number_aET2 = (EditText) findViewById(R.id.number_a);
        String number_aString = number_aET2.getText().toString();
        int a = Integer.parseInt(number_aString);
        EditText number_bET2 = (EditText) findViewById(R.id.number_b);
        String number_bString = number_bET2.getText().toString();
        int b = Integer.parseInt(number_bString);
        if(number_aString == "" || number_bString == "") {
            Toast.makeText(this, "Empty number", Toast.LENGTH_SHORT).show();
        } else {
            Intent it = new Intent(this, CalculateActivity.class);
            startActivity(it);
        }
    }
    public void onStart() {
        super.onStart();
        updateView();
    }
    public void updateView() {
        EditText number_aET = (EditText) findViewById(R.id.number_a);
        number_aET.setText("");
        EditText number_bET = (EditText) findViewById(R.id.number_b);
        number_bET.setText("");
    }

    public class Calculate {
        public void Calculate() {

        }

        public int gcd(int a, int b) {

        }
    }
}