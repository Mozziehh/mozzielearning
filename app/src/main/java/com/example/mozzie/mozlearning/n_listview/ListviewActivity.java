package com.example.mozzie.mozlearning.n_listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mozzie on 17/6/19.
 */

public class ListviewActivity extends Activity{

    private HackedListView mListview ;
    private MylistAdapter mylistAdapter;
    private TabHolder mTabHolder;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListview = (HackedListView)findViewById(R.id.listview);
        mylistAdapter = new MylistAdapter(this, initListData());
//        view = (View) findViewById(R.id.my_title);
//        view.setBackground(getResources().getDrawable(R.color.colorBlack));
//        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout.setLayoutParams(layoutParams);
//        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//        linearLayout.addView(new TextView(getApplicationContext()));
//        linearLayout.addView(new TextView(getApplicationContext()));
        mListview.addHeaderView(new TextView(getApplicationContext()));
        mListview.addHeaderView(new TextView(getApplicationContext()));
        mListview.addHeaderView(new TextView(getApplicationContext()));
        mListview.addHeaderView(new TextView(getApplicationContext()));
        mListview.setAdapter(mylistAdapter);
    }

    private ArrayList<String> initListData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ; i < 1000; i++){
            list.add("ff" + i);
        }
        return list;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ListviewActivity.class);
        context.startActivity(intent);
    }


}
