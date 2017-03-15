package com.example.mozzie.mozlearning;

import android.content.Intent;

import com.example.mozzie.mozlearning.a_activity.LifeCycleActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by mozzie on 17/3/10.
 */

@RunWith(RobolectricTestRunner.class)
//@Config(manifest="./src/main/AndroidManifest.xml")
@Config(constants = BuildConfig.class, sdk = 22)
public class MainActivityRoboletricTest {
    @Test
    public void clickingLogin_shouldStartActivity() {
//        MainActivity sampleActivity = Robolectric.buildActivity(MainActivity.class).
//                create().resume().get();
//        assertNotNull(sampleActivity);
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.activity_lifecycle).performClick();

//        Intent expectedIntent = new Intent(activity, LifeCycleActivity.class);
//        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
//    WelcomeActivity activity = Robolectric.setupActivity(WelcomeActivity.class);
//    activity.findViewById(R.id.login).performClick();
//
//    Intent expectedIntent = new Intent(activity, LoginActivity.class);
//    assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
}
