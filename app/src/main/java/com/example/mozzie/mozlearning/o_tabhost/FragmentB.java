package com.example.mozzie.mozlearning.o_tabhost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.TabHolder;
import com.example.mozzie.mozlearning.b_utils.TitleHolder;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.e_adapter.ListAdapter;
import com.example.mozzie.mozlearning.n_listview.MylistAdapter;

import java.util.HashMap;

/**
 * Created by mozzie on 17/5/15.
 */

public class FragmentB extends Fragment{

    private MylistAdapter mylistAdapter;
    private TabHolder mTabHodler;
    private TitleHolder mTitleHolder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabhost_b, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        mylistAdapter = new MylistAdapter(getActivity(), initListData());
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
        RelativeLayout totalview = new RelativeLayout(getContext());
        View textViewUp = (View) view.findViewById(R.id.textviewc);
        textViewUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                ToastUtils.show(getActivity(), "shang" + ":" + displayMetrics.density);
            }
        });


        View textViewDown = (View) view.findViewById(R.id.textviewb);
        textViewDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(getActivity(), "xia");
            }
        });

        mTitleHolder = new TitleHolder(textViewUp, textViewDown);
        mTitleHolder.setTabShow(true);
        mTitleHolder.setForbidScroll(false);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
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
        return view;
    }

    private HashMap<String, String> initListData() {
        HashMap<String, String> list = new HashMap<>();
        for (int i = 0 ; i < 1000; i++){
            list.put("" + i, i + 100 + "");
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
