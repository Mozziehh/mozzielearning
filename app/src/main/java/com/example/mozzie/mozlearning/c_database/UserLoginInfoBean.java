package com.example.mozzie.mozlearning.c_database;

import android.text.TextUtils;

/**
 * Created by mozzie on 16/12/9.
 */

public class UserLoginInfoBean {
    public String userId;
    public String inputUN;
    public String inputPWD;
    public String headUrl;
    public String userName;
    public String ppu;
    public String currentTime;

    public UserLoginInfoBean(){
    }

    public UserLoginInfoBean(String userId, String inputUN, String inputPWD, String headUrl, String userName, String ppu, long currentTime){
        if(TextUtils.isEmpty(userId)){
            userId = "";
        }
        if(TextUtils.isEmpty(inputUN)){
            inputUN = "";
        }
        if(TextUtils.isEmpty(inputPWD)){
            inputPWD = "";
        }
        if(TextUtils.isEmpty(headUrl)){
            headUrl = "";
        }
        if(TextUtils.isEmpty(userName)){
            userName = "";
        }
        if(TextUtils.isEmpty(ppu)){
            ppu = "";
        }
        this.userId = userId;
        this.inputUN = inputUN;
        this.inputPWD = inputPWD;
        this.headUrl = headUrl;
        this.userName = userName;
        this.ppu = ppu;
        this.currentTime = currentTime + "";
    }
}
