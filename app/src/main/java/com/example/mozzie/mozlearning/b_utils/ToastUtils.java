package com.example.mozzie.mozlearning.b_utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mozzie on 16/12/30.
 */

public class ToastUtils {

    public static void show(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
