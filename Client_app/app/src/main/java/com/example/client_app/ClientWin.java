package com.example.client_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClientWin extends AppCompatActivity {
    private final int port = 2345;
    private String hostname;
    private RunClient client;

    private EditText send_ET;
    private Button send_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        this.hostname = intent.getStringExtra("Host");
        this.client = new RunClient(this, hostname, port);
        this.client.start();

        send_ET = (EditText) findViewById(R.id.send_ET);
        send_bt = (Button) findViewById(R.id.send_bt);
        send_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = send_ET.getText().toString();
                client.write(message);
                if(message.equals("CLOSE")) {
                    client.loop = false;
                    client.interrupt();
                    finish();
                }
            }
        });
    }

    protected void onDestroy() {
        client.write("CLOSE");
        client.loop = false;
        client.interrupt();
        finish();
        super.onDestroy();
    }
}