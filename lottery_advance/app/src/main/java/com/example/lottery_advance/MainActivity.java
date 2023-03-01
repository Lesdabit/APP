package com.example.lottery_advance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"title", "num0", "num1", "num2", "num3", "num4", "num5"};
    private int[] to = {R.id.item_title, R.id.item_num0, R.id.item_num1, R.id.item_num2, R.id.item_num3, R.id.item_num4, R.id.item_num5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.List);
        initView();
    }

    private void initView() {
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this,data, R.layout.item, from, to);
        list.setAdapter(adapter);
    }

    public void lottery(View view) {
        int[] lottery = createLottery();
        HashMap<String,String> row = new HashMap<>();
        row.put(from[0], "No." + (data.size()+1));
        row.put(from[1], "" + lottery[0]);
        row.put(from[2], "" + lottery[1]);
        row.put(from[3], "" + lottery[2]);
        row.put(from[4], "" + lottery[3]);
        row.put(from[5], "" + lottery[4]);
        row.put(from[6], "" + lottery[5]);
        data.add(row);
        adapter.notifyDataSetChanged();

        //list.smoothScrollToPosition(row.size()-1);
        getListView().post(new Runnable() {
            @Override
            public void run() {
                getListView().smoothScrollToPosition(pos);
            }
        });
    }

    private int[] createLottery() {
        HashSet<Integer> nums = new HashSet<>();
        while(nums.size() < 6) {
            nums.add((int)(Math.random()*49+1));
        }
        int i = 0; int[] ret = new int[6];
        for(int num:nums) {
            ret[i] = num;
            i++;
        }
        return ret;
    }
}