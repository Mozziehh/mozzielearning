package com.example.mozzie.mozlearning.q_designparent.mvp.aa_parten;

import android.app.Activity;

public class LoginPresenter implements IPresenter{

    private IModule moduleExec;
    private VPatternInterface mActivity;

    public LoginPresenter(Activity activity){
        mActivity = (VPatternInterface)activity;
        moduleExec = new ModuleExec();
    }


    @Override
    public void executeLogin(String username, String password) {
        moduleExec.execteLogin(username, password, new ModuleExec.LoginResult() {
            @Override
            public void loginResult(boolean loginResult) {
                mActivity.onLoginResult(loginResult);
            }
        });
    }
}
