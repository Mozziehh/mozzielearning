package com.example.mozzie.mozlearning.n_zmcert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.z_work_drawerlayout.DrawerlayoutActivity;
import com.wuba.certify.CertifyApp;
import com.wuba.certify.CertifyItem;
import com.wuba.certify.CertifyListener;
import com.wuba.certify.PPuListener;

/**
 * Created by mozzie on 17/3/27.
 */

public class ZMCertActivity extends Activity{

    private static String APP_ID = "8";
    private static String CERTIFY_TYPE = "CertifyItemType";
    private static String CERTIFYITEM_LIST = "CertifyItem.LIST"; //列表页
    private static String CERTIFYITEM_ZHIMA = "CertifyItem.ZHIMA"; //芝麻认证
    private static String CERTIFYITEM_LEGAL = "CertifyItem.LEGAL"; //企业法人认证

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmcertlayout);
        Button zm = (Button) findViewById(R.id.button_zm);
        zm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CertifyApp.getInstance().config(APP_ID, "45931903015440", "UID=45931903015440&PPK=854c1ffc&PPT=e31c48d8&SK=6132DEA395E09C43AF6FFA8627C61BA85DA376264D92E2669&LT=1490617353217&UN=x7puxg&LV=576326c0&PBODY=ZFx10qpHIMO8gJ7Vjpb5xTX7q62R_y87cn5Mfuto8oW366Pj1uiz-KBvejseNzfAOgmzc4dGKYl4LK4C-GIWVqpufXgRuNcLw7NuSERTSU8cIF71qEM9xyvm3qWDtF-AgQ9RvyRTIOW2S7tSVOqntwbuM3kOm-UKXipWbjfK0So&VER=1");
//                LOGGER.d("ZMCertActivity", "ZM-VERSION: " + CertifyApp.getInstance().getVersion());
                CertifyApp.getInstance().setCertifyListener(mCertifyListener);
                CertifyApp.getInstance().setPPuListener(mPPUListener);
                Bundle bundle = new Bundle();
                bundle.putString(CERTIFY_TYPE, CERTIFYITEM_LIST);
                CertifyApp.getInstance().startCertify(ZMCertActivity.this, CertifyItem.ZHIMA, bundle);
            }
        });
    }

    private PPuListener mPPUListener = new PPuListener() {
        @Override
        public void onUpdate(String ppu) {
//            LoginPreferenceUtils.
        }
    };

    /**
     * 验证结束后，回调
     */
    CertifyListener mCertifyListener = new CertifyListener() {
        @Override
        public void onFinish(int code, Bundle bundle) {
            if (CERTIFYITEM_LIST.equals(bundle.getString(CERTIFY_TYPE))){
                switch (code){
                    case 0: //SUCCESS

                        break;
//                    case -100: //CANCEL
//                        setResult(Activity.RESULT_CANCELED);
//                        finish();
//                        break;
                    default:  //FAILED

                        break;
                }
            }
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ZMCertActivity.class);
        context.startActivity(intent);
    }
}
