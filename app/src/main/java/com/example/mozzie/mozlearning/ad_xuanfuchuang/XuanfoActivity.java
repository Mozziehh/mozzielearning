package com.example.mozzie.mozlearning.ad_xuanfuchuang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

import java.util.ArrayList;


public class XuanfoActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener{

    private ListView xuanfuList;
    private XuanfuAdapter xuanfuAdapter;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanfu);
        initView();
        initData();
        config();
    }

    private void initView() {

        Button button = (Button) findViewById(R.id.xuanfu_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initData() {
//        ArrayList<XuanfuBeanList> arrayList = new ArrayList<>();
//        XuanfuBeanList xuanfuBeanList1 = new XuanfuBeanList();
//        xuanfuBeanList1.title = "title1";
//        xuanfuBeanList1.content = "content1";
//        xuanfuBeanList1.url = "http://www.58.com";
//        arrayList.add(xuanfuBeanList1);
//        XuanfuBeanList xuanfuBeanList2 = new XuanfuBeanList();
//        xuanfuBeanList2.title = "title2";
//        xuanfuBeanList2.content = "content2";
//        xuanfuBeanList2.url = "http://www.58.com";
//        arrayList.add(xuanfuBeanList2);
//        XuanfuBeanList xuanfuBeanList3 = new XuanfuBeanList();
//        xuanfuBeanList3.title = "title3";
//        xuanfuBeanList3.content = "content3";
//        xuanfuBeanList3.url = "http://www.58.com";
//        arrayList.add(xuanfuBeanList3);
//        xuanfuAdapter = new XuanfuAdapter(this, arrayList);
    }

    private void config() {
//        xuanfuList.setAdapter(xuanfuAdapter);
//        xuanfuList.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, XuanfoActivity.class);
        context.startActivity(intent);
    }

    /**
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<XuanfuBeanList> xuanfuBeanLists = xuanfuAdapter.getXuanfuBeanList();
        if(xuanfuBeanLists == null || xuanfuBeanLists.size() == 0){
            return;
        }
        XuanfuBeanList xuanfuBean = xuanfuBeanLists.get(position);
//        xuanfuBean.title
        Intent intent = new Intent(this, XuanfuWebActivity.class);
        intent.putExtra("title", xuanfuBean.title);
        intent.putExtra("content", xuanfuBean.content);
        intent.putExtra("url", xuanfuBean.url);
        startActivity(intent);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
