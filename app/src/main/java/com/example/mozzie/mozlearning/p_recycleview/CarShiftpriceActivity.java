package com.example.mozzie.mozlearning.p_recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.BubbleRangeSeekBar;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by mozzie on 17/7/29.
 */

public class CarShiftpriceActivity extends Activity{

    RecyclerView mRecycleView;
    RecycleShiftPriceAdapter mRecycleAdapter;
    private ArrayList<RecycleBean> mRecyledata;
    private RecyclerView.LayoutManager mLayoutManager;
    private BubbleRangeSeekBar mBubbleRangeSeekBar;
    private String[] mDataSource = {"不限","1万元以内","1-2万元","2-3万元","3-5万元",
            "5-8万元","8-12万元","12-18万元","18-24万元","24-40万元","40万以上"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carshiftprice_layout);
        initdata();

        mRecycleView = (RecyclerView) findViewById(R.id.carshiftprice_layout);
        mRecycleAdapter = new RecycleShiftPriceAdapter(this);
        mRecycleAdapter.setData(mRecyledata);
        mRecycleView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));
        mRecycleView.setAdapter(mRecycleAdapter);
        mRecycleAdapter.setOnItemClickListener(new RecycleShiftPriceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(getApplicationContext(), "click-position = " + position);
            }
        });

        mBubbleRangeSeekBar = (BubbleRangeSeekBar) findViewById(R.id.seekBar);
        mBubbleRangeSeekBar.setRules(0, 60, 0, 60);
    }

    private void initdata() {
        mRecyledata = new ArrayList<>();
        for(int i = 0 ; i < 11; i ++){
            RecycleBean recycleBean = new RecycleBean();
            recycleBean.setText(mDataSource[i]);
            recycleBean.setUrl("");
            mRecyledata.add(recycleBean);
        }
    }
}
