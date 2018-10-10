package com.example.mozzie.mozlearning.o_tabhost;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.HardwarePropertiesManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.DisplayUtil;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.TabHolder;
import com.example.mozzie.mozlearning.b_utils.TitleHolder;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.e_adapter.ListAdapter;
import com.example.mozzie.mozlearning.n_listview.MylistAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mozzie on 17/5/15.
 */

public class FragmentB extends Fragment {

    private MylistAdapter mylistAdapter;
    private TabHolder mTabHodler;
    private TitleHolder mTitleHolder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabhost_b, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        mylistAdapter = new MylistAdapter(getActivity(), initListData());
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(view.getLastVisiblePosition() == view.getCount()-1){
//                    mylistAdapter.adddata(initListData());
//                }
//            }
//        });
        listView.setAdapter(mylistAdapter);
        RelativeLayout headerLayout = new RelativeLayout(getActivity().getApplicationContext());
        headerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 400));
        listView.addHeaderView(headerLayout);
        listView.setVerticalScrollBarEnabled(false);
        listView.setFastScrollEnabled(false);
        LOGGER.d("TabHostActivity-FragmentB", "onCreateView");

        LinearLayout tabHost = ((TabHostActivity)getActivity()).getTabHost();
        mTabHodler = new TabHolder(tabHost);
        mTabHodler.setTabShow(true);
        mTabHodler.setForbidScroll(false);
//        RelativeLayout totalview = (RelativeLayout) view.findViewById(R.id.bbbbbbbbb);
//        totalview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ToastUtils.show(getActivity(), "quan");
//            }
//        });

//        <TextView
//        android:text="bbbbbb"
//        android:layout_width="match_parent"
//        android:layout_height="50dp"
//        android:id="@+id/textviewc"
//        android:textSize="50sp"
//        android:background="#ffff00"
//                />
//        <TextView
//        android:id="@+id/textviewb"
//        android:layout_below="@id/textviewc"
//        android:text="bbbbbb"
//        android:layout_width="match_parent"
//        android:layout_height="50dp"
//        android:background="@color/colorAccent"
//                />
        //通过自定义布局实现
        TextView textViewUp = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 50));
        textViewUp.setBackgroundColor(getResources().getColor(Color.YELLOW, null));
        textViewUp.setLayoutParams(layoutParams);

        TextView textViewDown = new TextView(getContext());


        mTitleHolder = new TitleHolder(textViewUp, textViewDown);
        mTitleHolder.setTabShow(true);
        mTitleHolder.setForbidScroll(false);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if(view.getLastVisiblePosition() == view.getCount()-1){
                            mylistAdapter.adddata(initListData());
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        if(mTabHodler != null){
                            mTabHodler.setForbidScroll(false);
                        }
                        if(mTitleHolder != null){
                            mTitleHolder.setForbidScroll(false);
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        if(mTabHodler != null){
                            mTabHodler.setForbidScroll(true);
                        }
                        if(mTitleHolder != null){
                            mTitleHolder.setForbidScroll(true);
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(mTabHodler != null){
                    mTabHodler.onScroll(firstVisibleItem);
                }
                if(mTitleHolder != null){
                    mTitleHolder.onScroll(firstVisibleItem);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 48){
                    mylistAdapter.adddata(initListData());
                }
                ToastUtils.show(getContext(), position + "");
            }
        });
        return view;
    }

    private ArrayList<String> initListData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ; i < 50; i++){
            list.add("" + i);
        }
        return list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGGER.d("TabHostActivity-FragmentB", "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LOGGER.d("TabHostActivity-FragmentB", "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        LOGGER.d("TabHostActivity-FragmentB", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LOGGER.d("TabHostActivity-FragmentB", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LOGGER.d("TabHostActivity-FragmentB", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LOGGER.d("TabHostActivity-FragmentB", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LOGGER.d("TabHostActivity-FragmentB", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGGER.d("TabHostActivity-FragmentB", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LOGGER.d("TabHostActivity-FragmentB", "onDetach");
    }
}
