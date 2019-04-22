package com.example.mozzie.mozlearning;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;

import com.example.mozzie.mozlearning.a_activity.FoucesActivity;
import com.example.mozzie.mozlearning.a_activity.LaunchModeActivity;
import com.example.mozzie.mozlearning.a_activity.LifeCycleActivity;
import com.example.mozzie.mozlearning.ad_xuanfuchuang.XuanfoActivity;
import com.example.mozzie.mozlearning.af_zifuyushuzi.CharNumActivity;
import com.example.mozzie.mozlearning.agjni.JNITestActivity;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.c_database.DataBaseActivity;
import com.example.mozzie.mozlearning.d_intent.Aactivity;
import com.example.mozzie.mozlearning.g_broadcastreceiver.BroadcastRecevierActivity;
import com.example.mozzie.mozlearning.h_contentprovider.ContentProviderConnectActivity;
import com.example.mozzie.mozlearning.i_sharepreference.SharePreferenceActivity;
import com.example.mozzie.mozlearning.j_notification.NotificationActivity;
import com.example.mozzie.mozlearning.k_service.MyServiceActivity;
import com.example.mozzie.mozlearning.l_http.HttpUrlConnectionAcitivity;
import com.example.mozzie.mozlearning.n_listview.ListviewActivity;
import com.example.mozzie.mozlearning.o_tabhost.BottomNavigationActivity;
import com.example.mozzie.mozlearning.o_tabhost.TabHostActivity;
import com.example.mozzie.mozlearning.p_recycleview.RecycleviewActivity;
import com.example.mozzie.mozlearning.s_overdraw.OverdrawActivity;
import com.example.mozzie.mozlearning.v_file.FileActivity;
import com.example.mozzie.mozlearning.w_leak.LeakTestActivity;
import com.example.mozzie.mozlearning.w_lottie.LottieActivity;
import com.example.mozzie.mozlearning.z_work_drawerlayout.DrawerlayoutActivity;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppBuildTime();
    }

    private String getAppBuildTime() {
        String result = "";
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
//            long time = ze.getTime();
//            String s = SimpleDateFormat.getInstance().format(new java.util.Date(time));
            zf.close();
            ToastUtils.show(MainActivity.this, "打包时间1::::" + ze.getTime());
        } catch (Exception e) {
            ToastUtils.show(MainActivity.this, "打包时间" + e.getMessage());
        }

        return result;
    }

    public void OnLifeCycle(View view){
        Intent intent = new Intent();
        intent.setClass(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    public void OnDatabase(View view){
        Intent intent = new Intent();
        intent.setClass(this, DataBaseActivity.class);
        startActivity(intent);
    }

    public void OnFocuse(View view){
        Intent intent = new Intent();
        intent.setClass(this, FoucesActivity.class);
        startActivity(intent);
    }

    public void OnIntent(View view){
        Intent intent = new Intent();
        intent.setClass(this, Aactivity.class);
        startActivity(intent);
    }

    /**
     * 优雅的启动activity
     * @param view
     */
    public void OnLaunchMode(View view){
        LaunchModeActivity.startActivity(this);
    }

    /**
     * 优雅的启动activity
     * @param view
     */
    public void OnFragmentLaunch(View view){
//        FragmentActivity.startActivity(this);
    }

    /**
     * 优雅的启动activity
     * @param view
     */
    public void OnBroadcastLaunch(View view){
        BroadcastRecevierActivity.startActivity(this);
    }

    public void OnSharePreferenceLaunch(View view){
        SharePreferenceActivity.startActivity(this);
    }

    public void OnContentProviderLaunch(View view){
        ContentProviderConnectActivity.startActivity(this);
    }

    public void OnNotificationLaunch(View view){
        NotificationActivity.startActivity(this);
    }

    public void OnServiceLaunch(View view){
        MyServiceActivity.startActivity(this);
    }

    public void OnHttpLaunch(View view){
        HttpUrlConnectionAcitivity.startActivity(this);
    }

    public void OnDrawerlayoutLaunch(View view){
        DrawerlayoutActivity.startActivity(this);
    }

    public void OnTabHostLaunch(View view){
        TabHostActivity.startActivity(this);
    }

    public void OnListviewLaunch(View view){
        ListviewActivity.startActivity(this);
    }
    public void OnNavigationViewLaunch(View view){
        BottomNavigationActivity.startActivity(this);
    }
    public void OnRecycleviewLaunch(View view){
        RecycleviewActivity.startActivity(this);
    }
    public void OnOverdrawLaunch(View view){
        OverdrawActivity.startActivity(this);
    }




    public void OnFileLaunch(View view){
        FileActivity.startActivity(this);
    }

    public void OnLeakTest(View view){
        LeakTestActivity.startActivity(this);
    }

    public void OnLottieClick(View view){
        LottieActivity.startActivity(this);
    }

    public void OnXuanfuClick(View view) { XuanfoActivity.startActivity(this);}

    public void onZifuClick(View view) {
        CharNumActivity.startActivity(this);
    }

    public void onJniClick(View view){
        JNITestActivity.startActivity(this);
    }
}
