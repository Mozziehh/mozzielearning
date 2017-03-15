package com.example.mozzie.mozlearning;


import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by mozzie on 17/3/10.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest extends InstrumentationTestCase{

    private MainActivity mainActivity;
    private Button button;

    public MainActivityInstrumentationTest(){
        super();
    }
    /**
     * 用来初始设置，如启动一个Activity,初始化资源等
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent();
        intent.setClassName("com.example.mozzie.mozlearning", MainActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainActivity = (MainActivity) getInstrumentation().startActivitySync(intent);
        button = (Button) mainActivity.findViewById(R.id.activity_lifecycle);
    }

    /**
     * 用来垃圾清理与资源回收
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mainActivity.finish();
    }

    @Test
    public void test_buttonClick() throws Exception{
        assertEquals(mainActivity, null);
        getInstrumentation().runOnMainSync(new PerformClick(button));
    }

    /*
     * 模拟按钮点击的接口
     */
    private class PerformClick implements Runnable {
        Button btn;
        public PerformClick(Button button) {
            btn = button;
        }

        public void run() {
            btn.performClick();
        }
    }
}