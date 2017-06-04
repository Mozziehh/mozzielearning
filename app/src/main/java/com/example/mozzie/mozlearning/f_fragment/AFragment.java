package com.example.mozzie.mozlearning.f_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 16/12/29.
 */

public class AFragment extends Fragment implements View.OnClickListener{

    Button mButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment_layout, container, false);
        mButton = (Button)view.findViewById(R.id.left_fragment_button);
        mButton.setOnClickListener(this);
        return view;
    }

    /**
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        switch (clickId){
            case R.id.left_fragment:
                OtherFragment otherFragment = new OtherFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.right_fragment,otherFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }
}
