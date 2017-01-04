package com.example.mozzie.mozlearning.h_contentprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 17/1/4.
 */

public class ContentProviderConnectActivity extends Activity{

    //textView5 textView6
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider);
        readContacts();
    }

    private void readContacts() {
        TextView contact1 = (TextView) findViewById(R.id.textView5);
        TextView contact2 = (TextView) findViewById(R.id.textView6);
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        //这里应该用while进行全局遍历；
        if(cursor != null && cursor.moveToNext()){
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String contactNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contact1.setText(contactName);
            contact2.setText(contactNum);
        }
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContentProviderConnectActivity.class);
        context.startActivity(intent);
    }
}
