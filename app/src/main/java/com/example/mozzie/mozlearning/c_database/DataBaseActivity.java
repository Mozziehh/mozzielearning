package com.example.mozzie.mozlearning.c_database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mozzie on 16/12/9.
 */

public class DataBaseActivity extends Activity{

    private DBManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mManager = new DBManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mManager.closeDB();
    }

    public void OnInit(View view){

    }

    public void OnAdd(View view){
        ArrayList<UserLoginInfoBean> userLoginInfoBeen = new ArrayList<UserLoginInfoBean>();
//        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
        UserLoginInfoBean userLoginInfoBean1 = new UserLoginInfoBean("1", "inputusername_1", "inputpwd_1", "headurl_1", "username_1", "ppu_1", System.currentTimeMillis());
//        UserLoginInfoBean user2 = new UserLoginInfoBean("2", "inputusername_2", "inputpwd_2", "headurl_2", "username_2", "ppu_2", System.currentTimeMillis());
//        LOGGER.d("OnQuery" ,"currenttime_:" + myFmt2.format(now));
        UserLoginInfoBean userLoginInfoBean3 = new UserLoginInfoBean("1", "inputusername_1", "inputpwd_1", "headurl_1", "username_1", "ppu_3", System.currentTimeMillis());
//        UserLoginInfoBean user4 = new UserLoginInfoBean("2", "inputusername_2", "inputpwd_2", "headurl_2", "username_2", "ppu_4", dateFormat.format(date));

        userLoginInfoBeen.add(userLoginInfoBean1);
//        userLoginInfoBeen.add(user2);
        userLoginInfoBeen.add(userLoginInfoBean3);
//        userLoginInfoBeen.add(user4);

        mManager.add(userLoginInfoBeen);
    }

    public void OnDelete(View view){
        ArrayList<UserLoginInfoBean> userLoginInfoBeen = new ArrayList<UserLoginInfoBean>();
//        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        UserLoginInfoBean user1 = new UserLoginInfoBean("1", "inputusername_1", "inputpwd_1", "headurl_1", "username_1", "ppu_1", dateFormat.format(date));
        UserLoginInfoBean userLoginInfoBean2 = new UserLoginInfoBean("2", "inputusername_2", "inputpwd_2", "headurl_2", "username_2", "ppu_2", System.currentTimeMillis());
//        UserLoginInfoBean user3 = new UserLoginInfoBean("1", "inputusername_1", "inputpwd_1", "headurl_1", "username_1", "ppu_3", System.currentTimeMillis());
        UserLoginInfoBean userLoginInfoBean4 = new UserLoginInfoBean("2", "inputusername_2", "inputpwd_2", "headurl_2", "username_2", "ppu_4", System.currentTimeMillis());
//        LOGGER.d("OnQuery" ,"currenttime_:" + myFmt2.format(now));
//        userLoginInfoBeen.add(user1);
        userLoginInfoBeen.add(userLoginInfoBean2);
//        userLoginInfoBeen.add(user3);
        userLoginInfoBeen.add(userLoginInfoBean4);

        mManager.add(userLoginInfoBeen);
    }

    public void OnUpdate(View view){

    }

    public void OnQuery(View view){
        List<UserLoginInfoBean> userLoginInfoBeen = mManager.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (UserLoginInfoBean userLoginInfoBean : userLoginInfoBeen) {
            LOGGER.d("OnQuery" ,"USER_ID:" + userLoginInfoBean.userId);
            LOGGER.d("OnQuery" ,"PPU:" + userLoginInfoBean.ppu);
            LOGGER.d("OnQuery" ,"current_time:" + userLoginInfoBean.currentTime);
        }
//        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        for (Person person : persons) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("name", person.name);
//            map.put("info", person.age + " years old, " + person.info);
//            list.add(map);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
//                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
//        listView.setAdapter(adapter);
    }

    public void OnClose(View view){

    }
}
