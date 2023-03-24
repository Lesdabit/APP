package com.example.hw3_houses_for_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button ENT, NX, BK;
    private TextView[][] TV = new TextView[4][4];
    private TextView page;
    private EditText ET;
    private String url;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ArrayList<ArrayList<String>> csv = new ArrayList<ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TV[0][0] = (TextView) findViewById(R.id.textView11);
        TV[0][1] = (TextView) findViewById(R.id.textView12);
        TV[0][2] = (TextView) findViewById(R.id.textView13);
        TV[0][3] = (TextView) findViewById(R.id.textView14);
        TV[0][0].setText("No.");
        TV[0][1].setText("Address");
        TV[0][2].setText("Size");
        TV[0][3].setText("Price");

        page = (TextView) findViewById(R.id.page_tv);
        page.setText("1");

        ENT = (Button) findViewById(R.id.et_bt);
        ENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ET = (EditText) findViewById(R.id.web_et);
                url = ET.getText().toString();
                if(url.matches(""))
                    Toast.makeText(MainActivity.this, "URL is empty", Toast.LENGTH_SHORT).show();
                else
                    sendAndRequestResponse();
            }
        });
        NX = (Button) findViewById(R.id.nxt_bt);
        NX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_s = page.getText().toString();
                int p = Integer.parseInt(p_s);
                if(p == csv.size()/12/3+1) {
                    Toast.makeText(MainActivity.this, "This is the last page", Toast.LENGTH_SHORT).show();
                } else {
                    p++;
                    page.setText(p);
                }
            }
        });
        BK = (Button) findViewById(R.id.bk_bt);
        BK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_s = page.getText().toString();
                int p = Integer.parseInt(p_s);
                if(p == 1) {
                    Toast.makeText(MainActivity.this, "This is the first page", Toast.LENGTH_SHORT).show();
                } else {
                    p--;
                    page.setText(p);
                }
            }
        });
    }

    private void sendAndRequestResponse() {
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    FileOutputStream outfile = openFileOutput("Result.txt", Context.MODE_APPEND);
                    outfile.write(response.getBytes(StandardCharsets.UTF_8));
                    outfile.close();
                    parsing_file();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void parsing_file() {
        TV[1][1] = (TextView) findViewById(R.id.textView21);
        TV[1][2] = (TextView) findViewById(R.id.textView22);
        TV[1][3] = (TextView) findViewById(R.id.textView23);
        TV[1][4] = (TextView) findViewById(R.id.textView24);
        TV[2][1] = (TextView) findViewById(R.id.textView31);
        TV[2][2] = (TextView) findViewById(R.id.textView32);
        TV[2][3] = (TextView) findViewById(R.id.textView33);
        TV[2][4] = (TextView) findViewById(R.id.textView34);
        TV[3][1] = (TextView) findViewById(R.id.textView41);
        TV[3][2] = (TextView) findViewById(R.id.textView42);
        TV[3][3] = (TextView) findViewById(R.id.textView43);
        TV[3][4] = (TextView) findViewById(R.id.textView44);

        ArrayList<String> store = new ArrayList<String>();

        int i = 0;
        try {
            File file = getBaseContext().getFileStreamPath("Result.txt");
            FileInputStream stream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(stream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            while((line = br.readLine()) != null) {
                String[] apartment = line.split(",");
                if(i == 1) {
                    for(int j = 0; j < 12; j++) {
                        store.clear();
                        if(j == 0) store.add(apartment[0]);
                        else if(j == 1) store.add(apartment[1]);
                        else if(j == 2) store.add(apartment[2]);
                        else if(j == 3) store.add(apartment[3]);
                        else if(j == 4) store.add(apartment[4]);
                        else if(j == 5) store.add(apartment[5]);
                        else if(j == 9) store.add(apartment[9]);
                        csv.add(store);
                    }
                }
                i++;
            }

            for(int j = 0; j < csv.size()/12/3; j++) {
                TV[j%3+1][0].setText(j+1);
                TV[j%3+1][1].setText(csv.get(j).get(0) + ", " +csv.get(j).get(1) + ", " + csv.get(j).get(2) + ", " + csv.get(j).get(3));
                TV[j%3+2][2].setText(csv.get(j).get(4) + " bed, " + csv.get(j).get(1) + );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}