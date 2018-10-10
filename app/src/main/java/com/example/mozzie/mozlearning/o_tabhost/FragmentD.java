package com.example.mozzie.mozlearning.o_tabhost;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/5/15.
 */

public class FragmentD extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabhost_d, container, false);
        LOGGER.d("TabHostActivity-FragmentD", "onCreateView");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGGER.d("TabHostActivity-FragmentD", "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LOGGER.d("TabHostActivity-FragmentD", "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        LOGGER.d("TabHostActivity-FragmentD", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LOGGER.d("TabHostActivity-FragmentD", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LOGGER.d("TabHostActivity-FragmentD", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LOGGER.d("TabHostActivity-FragmentD", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LOGGER.d("TabHostActivity-FragmentD", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGGER.d("TabHostActivity-FragmentD", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LOGGER.d("TabHostActivity-FragmentD", "onDetach");
    }
}
