package com.example.mozzie.mozlearning.v_file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.cert.TrustAnchor;

/**
 * Created by mozzie on 18/3/16.
 */

public class FileActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        ImageView imageView = (ImageView) findViewById(R.id.file_image);
        String sdDir = Environment.getExternalStorageDirectory().getPath();
        String fileDir = sdDir + "/screenshot.png";

        File file = new File(fileDir);
        ToastUtils.show(this, "图片是否存在:" + file.exists());

        if(file.exists()){
            imageView.setImageBitmap(decodeBitmap(fileDir));
        }
    }

    private Bitmap decodeBitmap(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = 0;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //避免出现内存溢出的情况，进行相应的属性设置。
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FileActivity.class);
        context.startActivity(intent);
    }
}
