package com.example.client_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText hostname_ET;
    private Button connect_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hostname_ET = (EditText) findViewById(R.id.Hostname_ET);
        connect_bt = (Button) findViewById(R.id.connect_bt);
        connect_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClientWin();
            }
        });
    }

    public void openClientWin() {
        Intent intent = new Intent(this, ClientWin.class);
        intent.putExtra("Host", hostname_ET.getText().toString());
        startActivity(intent);
    }
}