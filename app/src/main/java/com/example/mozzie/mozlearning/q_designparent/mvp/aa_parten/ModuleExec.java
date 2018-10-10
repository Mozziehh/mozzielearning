package com.example.mozzie.mozlearning.q_designparent.mvp.aa_parten;

public class ModuleExec implements IModule{


    LoginResult loginResult;
    private String mUserName;
    private String mPassword;

    public void execteLogin(String username, String password, LoginResult loginResult) {
        this.loginResult = loginResult;
        mUserName = username;
        mPassword = password;

        doLogin(mUserName, mPassword);
    }

    private void doLogin(String mUserName, String mPassword) {
        loginResult.loginResult(true);
    }


    public interface LoginResult{
        public void loginResult(boolean loginResult);
    }

}
