package com.example.mozzie.mozlearning.q_designparent.mvp.aa_parten;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.R;

public class PartenActivity extends Activity implements View.OnClickListener, VPatternInterface{

    IPresenter loginPresenter;
    private Button mBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patern);
        loginPresenter = new LoginPresenter(this);
        mBtn = (Button)findViewById(R.id.btn_pattern);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_pattern){
            loginPresenter.executeLogin("","");
        }
    }

    @Override
    public void onLoginResult(boolean isSuccess) {
        if(isSuccess){
            mBtn.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        }else{
            mBtn.setBackgroundColor(getResources().getColor(R.color.colorOrageback));
        }
    }
}
