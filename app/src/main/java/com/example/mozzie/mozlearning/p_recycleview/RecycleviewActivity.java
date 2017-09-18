package com.example.mozzie.mozlearning.p_recycleview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.d_intent.Aactivity;
import com.example.mozzie.mozlearning.o_tabhost.BottomNavigationActivity;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by mozzie on 17/7/27.
 */

public class RecycleviewActivity extends Aactivity{

    RecyclerView mRecycleView;
    RecycleAdapter mRecycleAdapter;
    private ArrayList<RecycleBean> mRecyledata;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        initdata();
        mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
        mRecycleAdapter = new RecycleAdapter(this);
        mRecycleAdapter.setData(mRecyledata);
//        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(new GridLayoutManager(this,3,VERTICAL,false));
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecycleView.setAdapter(mRecycleAdapter);
        mRecycleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(getApplicationContext(), "click-position = " + position);
            }
        });
        Button button = (Button)findViewById(R.id.recycle_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyledata = new ArrayList<>();
                for(int i = 0 ; i < 13; i ++){
                    RecycleBean recycleBean = new RecycleBean();
                    if(i == 4){
                        recycleBean.setText(i + "55");
                        recycleBean.setUrl("");
                        recycleBean.setSelected(true);
                    }else{
                        recycleBean.setText(i + "");
                        recycleBean.setUrl("");
                    }

                    mRecyledata.add(recycleBean);
                }
                mRecycleAdapter.setData(mRecyledata);
                mRecycleAdapter.notifyDataSetChanged();
            }
        });
        Button button2 = (Button)findViewById(R.id.recycle_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecycleviewActivity.this, CarShiftpriceActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initdata() {
        mRecyledata = new ArrayList<>();
        for(int i = 0 ; i < 13; i ++){
            RecycleBean recycleBean = new RecycleBean();
            recycleBean.setText(i + "");
            recycleBean.setUrl("");
            if(i == 7){
                recycleBean.setListName("huochec");
            }
            mRecyledata.add(recycleBean);
        }

    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RecycleviewActivity.class);
        context.startActivity(intent);
    }
}
