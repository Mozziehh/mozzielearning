package com.example.mozzie.mozlearning.n_listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mozzie on 17/6/19.
 */

public class ListviewActivity extends Activity{

    private ListView mListview ;
    private MylistAdapter mylistAdapter;
    private TabHolder mTabHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListview = (ListView)findViewById(R.id.listview);
        mylistAdapter = new MylistAdapter(this, initListData());
        mListview.setAdapter(mylistAdapter);
    }

    private ArrayList<String> initListData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ; i < 1000; i++){
            list.add("" + i);
        }
        return list;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ListviewActivity.class);
        context.startActivity(intent);
    }


}
