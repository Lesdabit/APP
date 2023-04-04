package com.example.hw3_houses_for_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
        TV[1][0] = (TextView) findViewById(R.id.textView21);
        TV[1][1] = (TextView) findViewById(R.id.textView22);
        TV[1][2] = (TextView) findViewById(R.id.textView23);
        TV[1][3] = (TextView) findViewById(R.id.textView24);
        TV[2][0] = (TextView) findViewById(R.id.textView31);
        TV[2][1] = (TextView) findViewById(R.id.textView32);
        TV[2][2] = (TextView) findViewById(R.id.textView33);
        TV[2][3] = (TextView) findViewById(R.id.textView34);
        TV[3][0] = (TextView) findViewById(R.id.textView41);
        TV[3][1] = (TextView) findViewById(R.id.textView42);
        TV[3][2] = (TextView) findViewById(R.id.textView43);
        TV[3][3] = (TextView) findViewById(R.id.textView44);

        page = (TextView) findViewById(R.id.page_tv);
        page.setText("1");

        ENT = (Button) findViewById(R.id.et_bt);
        ENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                csv.clear();
                ET = (EditText) findViewById(R.id.web_et);
                ET.setText("https://web.cs.wpi.edu/~cs1004/a16/Resources/SacramentoRealEstateTransactions.csv");
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
                String[] p_ss = p_s.split("/");
                int p = Integer.parseInt(p_ss[0]);
                if(p == csv.size()/3+1) {
                    Toast.makeText(MainActivity.this, "This is the last page", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Page++", "Successful");
                    p++;
                    page.setText(String.valueOf(p) + "/" + String.valueOf((csv.size()/3/12)+1));
                    setText(p);
                }
            }
        });
        BK = (Button) findViewById(R.id.bk_bt);
        BK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_s = page.getText().toString();
                String[] p_ss = p_s.split("/");
                int p = Integer.parseInt(p_ss[0]);
                if(p == 1) {
                    Toast.makeText(MainActivity.this, "This is the first page", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Page--", "Successful");
                    p--;
                    page.setText(String.valueOf(p) + "/" + String.valueOf((csv.size()/3/12)+1));
                    setText(p);
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
                    setText(1);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", "Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void parsing_file() {
        int i = 0;
        try {
            File file = getBaseContext().getFileStreamPath("Result.txt");
            FileInputStream stream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(stream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            br.readLine();

            while((line = br.readLine()) != null) {
                String[] apartment = line.split(",");
                csv.add(new ArrayList<>());
                csv.get(i).add(apartment[0]);
                csv.get(i).add(apartment[1]);
                csv.get(i).add(apartment[2]);
                csv.get(i).add(apartment[3]);
                csv.get(i).add(apartment[4]);
                csv.get(i).add(apartment[5]);
                csv.get(i).add(apartment[6]);
                csv.get(i).add(apartment[9]);
                i++;
            }
            page.setText("1/" + String.valueOf((csv.size()/3/12)+1));
            System.out.println((csv.size()/3)+1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setText(int p) {
        int j = 3*(p-1);
        TV[1][0].setText(Integer.toString(j+1));
        TV[1][1].setText(csv.get(j).get(0) + "\n, " +csv.get(j).get(1) + ", " + csv.get(j).get(2) + ", " + csv.get(j).get(3));
        TV[1][2].setText(csv.get(j).get(4) + " bed, " + csv.get(j).get(5) + " bath, \n" + csv.get(j).get(6) + " sqft");
        TV[1][3].setText("$" + csv.get(j).get(7));
        j++;
        TV[2][0].setText(Integer.toString(j+1));
        TV[2][1].setText(csv.get(j).get(0) + "\n, " +csv.get(j).get(1) + ", " + csv.get(j).get(2) + ", " + csv.get(j).get(3));
        TV[2][2].setText(csv.get(j).get(4) + " bed, " + csv.get(j).get(5) + " bath, \n" + csv.get(j).get(6) + " sqft");
        TV[2][3].setText("$" + csv.get(j).get(7));
        j++;
        TV[3][0].setText(Integer.toString(j+1));
        TV[3][1].setText(csv.get(j).get(0) + "\n, " +csv.get(j).get(1) + ", " + csv.get(j).get(2) + ", " + csv.get(j).get(3));
        TV[3][2].setText(csv.get(j).get(4) + " bed, " + csv.get(j).get(5) + " bath, \n" + csv.get(j).get(6) + " sqft");
        TV[3][3].setText("$" + csv.get(j).get(7));
    }
}