package com.example.mozzie.mozlearning.o_tabhost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/5/15.
 */

public class FragmentC extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabhost_c, container, false);
        LOGGER.d("TabHostActivity-FragmentC", "onCreateView");
        TextView ccc = (TextView) view.findViewById(R.id.cccccccccc);
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.mozzie.mozlearning.o_tabhost.NivagationActivity.startActivity(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGGER.d("TabHostActivity-FragmentC", "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LOGGER.d("TabHostActivity-FragmentC", "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        LOGGER.d("TabHostActivity-FragmentC", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LOGGER.d("TabHostActivity-FragmentC", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LOGGER.d("TabHostActivity-FragmentC", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LOGGER.d("TabHostActivity-FragmentC", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LOGGER.d("TabHostActivity-FragmentC", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGGER.d("TabHostActivity-FragmentC", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LOGGER.d("TabHostActivity-FragmentC", "onDetach");
    }
}
