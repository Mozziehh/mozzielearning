package com.example.mozzie.mozlearning.c_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mozzie on 16/12/9.
 */

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;
    private String TABLE = "user";

    private String USER_ID = "user_id";
    private String REMEMBER_UN = "remember_un";
    private String REMEMBER_PWD = "remember_pwd";
    private String HEAD_URL = "head_url";
    private String USER_NAME = "user_name";
    private String USER_PPU = "user_ppu";
    private String CURRENT_TIME = "cur_time";
    public static Context mContext;

    public DBManager(Context context) {
        mContext = context;
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 增加一条数据
     * @param userLoginInfoBean
     */
    public void add(UserLoginInfoBean userLoginInfoBean){
        db.beginTransaction();  //开始事务
        try {
            db.execSQL("INSERT INTO " + TABLE +" VALUES(?, ?, ?, ?, ?, ?, ?)",
                    new Object[]{userLoginInfoBean.userId, userLoginInfoBean.inputUN, userLoginInfoBean.inputPWD,
                            userLoginInfoBean.headUrl, userLoginInfoBean.userName, userLoginInfoBean.ppu, userLoginInfoBean.currentTime});
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * 增加多条数据
     * @param userLoginInfoBeen
     */
    public void add(List<UserLoginInfoBean> userLoginInfoBeen) {
        Toast.makeText(mContext,"add", Toast.LENGTH_LONG).show();
        db.beginTransaction();  //开始事务
        try {
            for (UserLoginInfoBean userLoginInfoBean : userLoginInfoBeen) {
                if (queryExist(userLoginInfoBean.userId)){
                    updateUserInfo(userLoginInfoBean.userId, userLoginInfoBean);
                }else{
                    db.execSQL("INSERT INTO " + TABLE +" VALUES(?, ?, ?, ?, ?, ?, ?)",
                            new Object[]{userLoginInfoBean.userId, userLoginInfoBean.inputUN, userLoginInfoBean.inputPWD,
                                    userLoginInfoBean.headUrl, userLoginInfoBean.userName, userLoginInfoBean.ppu, userLoginInfoBean.currentTime});
                }
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * 删除一条数据
     * @param userLoginInfoBean
     */
    public void deleteUserItem(UserLoginInfoBean userLoginInfoBean) {
        db.delete("userLoginInfoBean", USER_ID + " = ?", new String[]{String.valueOf(userLoginInfoBean.userId)});
    }

    /**
     * 修改记住的用户名
     * @param uid
     * @param rembUserName
     */
    public void updateRememberUsername(String uid, String rembUserName) {
        ContentValues cv = new ContentValues();
        cv.put(REMEMBER_UN, rembUserName);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("user", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改记住的密码
     * @param uid
     * @param rembUserPwd
     */
    public void updateRememberUserpwd(String uid, String rembUserPwd) {
        ContentValues cv = new ContentValues();
        cv.put(REMEMBER_PWD, rembUserPwd);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("user", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改用户的inputUN，inputPWD
     * @param uid
     * @param rembUserName
     * @param rembUserPwd
     */
    public void updateRememberNamePwd(String uid, String rembUserName, String rembUserPwd) {
        ContentValues cv = new ContentValues();
        cv.put(REMEMBER_UN, rembUserName);
        cv.put(REMEMBER_PWD, rembUserPwd);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("user", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改用户名
     * @param uid
     * @param userName
     */
    public void updateRememberUserName(String uid, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, userName);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("user", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改ppu
     * @param uid
     * @param ppu
     */
    public void updateUserPPU(String uid, String ppu) {
        ContentValues cv = new ContentValues();
        cv.put(USER_PPU, ppu);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("user", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改用户headurl
     * @param uid
     * @param headUrl
     */
    public void updateUserHeadUrl(String uid, String headUrl) {
        ContentValues cv = new ContentValues();
        cv.put(HEAD_URL, headUrl);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("user", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改用户第三方登录信息
     * @param uid
     * @param userLoginInfoBean
     */
    public void updateUserThirdInfo(String uid, UserLoginInfoBean userLoginInfoBean) {
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, userLoginInfoBean.userName);
        cv.put(USER_PPU, userLoginInfoBean.ppu);
        cv.put(HEAD_URL, userLoginInfoBean.headUrl);
        cv.put(CURRENT_TIME, System.currentTimeMillis());
        db.update("userLoginInfoBean", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 修改用户所有信息
     * @param uid
     * @param userLoginInfoBean
     */
    public void updateUserInfo(String uid, UserLoginInfoBean userLoginInfoBean) {
        ContentValues cv = new ContentValues();
        cv.put(REMEMBER_UN, userLoginInfoBean.inputUN);
        cv.put(REMEMBER_PWD, userLoginInfoBean.inputPWD);
        cv.put(USER_NAME, userLoginInfoBean.userName);
        cv.put(USER_PPU, userLoginInfoBean.ppu);
        cv.put(HEAD_URL, userLoginInfoBean.headUrl);
        cv.put(CURRENT_TIME, userLoginInfoBean.currentTime);
        db.update("userLoginInfoBean", cv, USER_ID + " = ?", new String[]{uid});
    }

    /**
     * 查询该uid的用户是否存在
     * @return List<Person>
     */
    public boolean queryExist(String uid) {
        if(TextUtils.isEmpty(uid)){
           return false;
        }
        Cursor c = queryAllCursor();
        while (c.moveToNext()) {
            String t_uid = c.getString(c.getColumnIndex(USER_ID));
            if(uid.equals(t_uid)){
                c.close();
                return true;
            }
        }
        c.close();
        return false;
    }

    /**
     * 根据uid查询一条数据
     * @return List<Person>
     */
    public UserLoginInfoBean queryByUID(String uid) {
        UserLoginInfoBean userLoginInfoBean = new UserLoginInfoBean();
        Cursor c = queryAllCursor();
        while (c.moveToNext()) {
            String t_uid = c.getString(c.getColumnIndex(USER_ID));
            if(!TextUtils.isEmpty(t_uid) && t_uid.equals(uid)){
                userLoginInfoBean.userId = c.getString(c.getColumnIndex(USER_ID));
                userLoginInfoBean.inputUN = c.getString(c.getColumnIndex(REMEMBER_UN));
                userLoginInfoBean.inputPWD = c.getString(c.getColumnIndex(REMEMBER_PWD));
                userLoginInfoBean.headUrl = c.getString(c.getColumnIndex(HEAD_URL));
                userLoginInfoBean.userName = c.getString(c.getColumnIndex(USER_NAME));
                userLoginInfoBean.ppu = c.getString(c.getColumnIndex(USER_PPU));
                userLoginInfoBean.currentTime = c.getString(c.getColumnIndex(CURRENT_TIME));
            }
        }
        c.close();
        return userLoginInfoBean;
    }

    /**
     * 查询所有的用户，返回列表，按时间顺序输出
     * @return List<Person>
     */
    public List<UserLoginInfoBean> query() {
        ArrayList<UserLoginInfoBean> userLoginInfoBeen = new ArrayList<UserLoginInfoBean>();
        Cursor c = queryAllCursor();
        while (c.moveToNext()) {
            UserLoginInfoBean userLoginInfoBean = new UserLoginInfoBean();
            userLoginInfoBean.userId = c.getString(c.getColumnIndex(USER_ID));
            userLoginInfoBean.inputUN = c.getString(c.getColumnIndex(REMEMBER_UN));
            userLoginInfoBean.inputPWD = c.getString(c.getColumnIndex(REMEMBER_PWD));
            userLoginInfoBean.headUrl = c.getString(c.getColumnIndex(HEAD_URL));
            userLoginInfoBean.userName = c.getString(c.getColumnIndex(USER_NAME));
            userLoginInfoBean.ppu = c.getString(c.getColumnIndex(USER_PPU));
            userLoginInfoBean.currentTime = c.getString(c.getColumnIndex(CURRENT_TIME));
            userLoginInfoBeen.add(userLoginInfoBean);
        }
        c.close();
        return userLoginInfoBeen;
    }

    /**
     * 查询所有的包含记住用户名&密码的用户数据，返回cursor对象
     * @return  Cursor
     */
    public Cursor queryAllRememberCursor() {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE +
                " WHERE " + REMEMBER_UN + " <> ''" +
                " ORDER BY " + CURRENT_TIME + " DESC",
                null);
        return c;
    }

    /**
     * 查询所有的用户数据，返回cursor对象
     * @return  Cursor
     */
    public Cursor queryAllCursor() {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE +
                        " ORDER BY " + CURRENT_TIME + " DESC",
                null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
