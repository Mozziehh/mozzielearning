package com.example.mozzie.mozlearning.j_notification;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Stack;

/**
 * @author mozzie
 */
public class NotificationColorAdaptation {


    private static final int INVALID_COLOR = 0;
    private final int ANDROID_4_CONTENT_TITLE_COLOR = 0xffffffff;
    private final int ANDROID_4_CONTENT_TEXT_COLOR = 0xff999999;
    private final int ANDROID_5_CONTENT_TITLE_COLOR = 0xde000000;
    private final int ANDROID_5_CONTENT_TEXT_COLOR = 0x8a000000;
    private final int DEFAULT_LIGHT_CONTENT_TITLE_COLOR = ANDROID_4_CONTENT_TITLE_COLOR;
    private final int DEFAULT_LIGHT_CONTENT_TEXT_COLOR = ANDROID_4_CONTENT_TEXT_COLOR;
    private final int DEFAULT_DARK_CONTENT_TITLE_COLOR = 0xff000000;
    private final int DEFAULT_DARK_CONTENT_TEXT_COLOR = 0xff666666;
    private final String fakeContentTitle = "";
    private final String fakeContentText = "";
    private int contentTitleColor = INVALID_COLOR;
    private int contentTextColor = INVALID_COLOR;

    private Context context;

    private NotificationColorAdaptation(Context context) {
        super();
        this.context = context;
    }

    /**
     * @param context
     * @return NotificationColorAdaptation 自定义color适配器
     **/
    public static NotificationColorAdaptation autoColorAdaptation(Context context) {
        return new NotificationColorAdaptation(context.getApplicationContext()).byAutomation();
    }

    /**
     * 设置TitleColor
     *
     * @param remoteViews     RemoteViews对象
     * @param contentTitleIds 布局文件
     * @return NotificationColorAdaptation 自定义color的适配器
     */
    public NotificationColorAdaptation setContentTitleColor(RemoteViews remoteViews, int contentTitleIds) {
        remoteViews.setTextColor(contentTitleIds, contentTitleColor);
        return this;
    }

    /**
     * 设置TextColor
     *
     * @param remoteViews    RemoteViews对象
     * @param contentTextIds 布局文件
     * @return NotificationColorAdaptation 自定义color的适配器
     */
    public NotificationColorAdaptation setContentTextColor(RemoteViews remoteViews, int contentTextIds) {
        remoteViews.setTextColor(contentTextIds, contentTextColor);
        return this;
    }

    /**
     * 先创建默认Notification通过不同方式判断
     * 准确度依次降低：ByText,ById,ByAnyTextView,BySdkVersion
     * @return NotificationColorAdaptation 自定义color的适配器
     */
    public NotificationColorAdaptation byAutomation() {
        RemoteViews remoteViews = buildFakeRemoteViews(context);
        if (!fetchNotificationTextColorByText(remoteViews)) {
            if (!fetchNotificationTextColorById(remoteViews)) {
                if (!fetchNotificationTextColorByAnyTextView(remoteViews)) {
                    fetchNotificationTextColorBySdkVersion();
                }
            }
        }
        return this;
    }

    /**
     * 创建默认的Notification获取默认通知内RemoteViews
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    private RemoteViews buildFakeRemoteViews(Context context) {
        Notification.Builder builder;
        builder = new Notification.Builder(context);
        builder.setContentTitle(fakeContentTitle)
                .setContentText(fakeContentText)
                .setTicker("fackTicker");
        RemoteViews remoteViews = null;

        if (builder != null) {
            //notification.contentView 在android N 被弃用 返回的remoteViews有可能是空的
            //翻遍官方文档找到builder.createContentView() 来获取remoteViews
            // 官方文档原话：Construct a RemoteViews for the final 1U notification layout.
            //理论上返回为默认RemoteViews 经测试 有效

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                remoteViews = builder.createContentView();
            } else {
                Notification notification = builder.getNotification();
                remoteViews = notification.contentView;
            }
        }
        return remoteViews;
    }

    /**
     * 通过我们设置的contentTitle和contentText的文字来获取对应的textView
     * 如果context是AppCompatActivity则可能会出错，这个没有实际测试
     * fetchMode = "ByText";
     * @param remoteViews
     * @return boolean
     */
    private boolean fetchNotificationTextColorByText(final RemoteViews remoteViews) {
        try {
            if (remoteViews != null) {
                TextView contentTitleTextView = null, contentTextTextView = null;
                View notificationRootView = remoteViews.apply(context, new FrameLayout(context));

                Stack<View> stack = new Stack<View>();
                stack.push(notificationRootView);
                while (!stack.isEmpty()) {
                    View v = stack.pop();
                    if (v instanceof TextView) {
                        final TextView childTextView = ((TextView) v);
                        final CharSequence charSequence = childTextView.getText();
                        if (TextUtils.equals(fakeContentTitle, charSequence)) {
                            contentTitleTextView = childTextView;
                        } else if (TextUtils.equals(fakeContentText, charSequence)) {
                            contentTextTextView = childTextView;
                        }
                        if ((contentTitleTextView != null) && (contentTextTextView != null)) {
                            break;
                        }

                    }
                    if (v instanceof ViewGroup) {
                        ViewGroup vg = (ViewGroup) v;
                        final int count = vg.getChildCount();
                        for (int i = 0; i < count; i++) {
                            stack.push(vg.getChildAt(i));
                        }
                    }
                }
                stack.clear();
                return checkAndGuessColor(contentTitleTextView, contentTextTextView);

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkAndGuessColor(TextView contentTitleTextView, TextView contentTextTextView) {

        if (contentTitleTextView != null) {
            contentTitleColor = contentTitleTextView.getTextColors().getDefaultColor();

        }
        if (contentTextTextView != null) {
            contentTextColor = contentTextTextView.getTextColors().getDefaultColor();
        }
        if (contentTitleColor != INVALID_COLOR && contentTextColor != INVALID_COLOR) {
            return true;
        }

        if (contentTitleColor != INVALID_COLOR) {
            if (isLightColor(contentTitleColor)) {
                contentTextColor = DEFAULT_LIGHT_CONTENT_TEXT_COLOR;
            } else {
                contentTextColor = DEFAULT_DARK_CONTENT_TEXT_COLOR;
            }
            return true;
        }
        if (contentTextColor != INVALID_COLOR) {
            if (isLightColor(contentTextColor)) {
                contentTitleColor = DEFAULT_LIGHT_CONTENT_TITLE_COLOR;
            } else {
                contentTitleColor = DEFAULT_DARK_CONTENT_TITLE_COLOR;
            }
            return true;
        }
        return false;
    }

    private static boolean isLightColor(int color) {
        return isLightAverageColor(toAverageColor(color));
    }

    private static boolean isLightAverageColor(int averageColor) {
        return averageColor >= 0x80;
    }

    private static int toAverageColor(int color) {
        return (int) ((Color.red(color) + Color.green(color) + Color.blue(color)) / 3f + 0.5f);
    }

    /**
     * 通过contentTitle/contentText(反射获取)的id来取得TextView
     * fetchMode = "ById";
     * @param remoteViews
     * @return boolean
     */
    private boolean fetchNotificationTextColorById(final RemoteViews remoteViews) {
        try {
            final int systemNotificationContentTitleId = getAndroidInternalResourceId("title");
            final int systemNotificationContentTextId = getAndroidInternalResourceId("text");
            if (remoteViews != null && remoteViews.getLayoutId() > 0) {
                TextView contentTitleTextView = null, contentTextTextView = null;
                View notificationRootView = LayoutInflater.from(context).inflate(remoteViews.getLayoutId(), null);
                View titleView = notificationRootView.findViewById(systemNotificationContentTitleId);
                if (titleView instanceof TextView) {
                    contentTitleTextView = (TextView) titleView;
                }
                if (systemNotificationContentTextId > 0) {
                    View contentView = notificationRootView.findViewById(systemNotificationContentTextId);
                    contentTextTextView = (TextView) contentView;
                }
                return checkAndGuessColor(contentTitleTextView, contentTextTextView);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getAndroidInternalResourceId(String resourceName) {
        //获取"android"包名里的id
        //即com.android.internal.R.id.resourceName
        //实际上如果getIdentifier没有的话，下面反射的方式也应该是没有的
        //defType = "id"，还可以有"layout","drawable"之类的
        //defType和defPackage必须指定
        final int id = Resources.getSystem().getIdentifier(resourceName, "id", "android");
        if (id > 0) {
            return id;
        }

        try {
            // 如果上面的方法没有返回id 通过反射获取
            // 反射的方法取com.android.internal.R.id.resourceName
            // 通知栏的大图标imageView的id="icon"
            // 标题是"title" 内容是"text"
            Class<?> clazz = Class.forName("com.android.internal.R$id");
            Field field = clazz.getField(resourceName);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 随意取一个textView判断是light或者是dark
     * fetchMode = "ByAnyTextView";
     * @param remoteViews
     * @return
     */
    private boolean fetchNotificationTextColorByAnyTextView(final RemoteViews remoteViews) {

        try {
            if (remoteViews != null && remoteViews.getLayoutId() > 0) {
                TextView anyTextView = null;
                View notificationRootView = LayoutInflater.from(context).inflate(remoteViews.getLayoutId(), null);
                Stack<View> stack = new Stack<View>();
                stack.push(notificationRootView);
                while (!stack.isEmpty()) {
                    View v = stack.pop();
                    if (v instanceof TextView) {
                        anyTextView = (TextView) v;
                        break;
                    }
                    if (v instanceof ViewGroup) {
                        ViewGroup vg = (ViewGroup) v;
                        final int count = vg.getChildCount();
                        for (int i = 0; i < count; i++) {
                            stack.push(vg.getChildAt(i));
                        }
                    }
                }
                stack.clear();
                if (anyTextView != null) {
                    if (isLightColor(anyTextView.getTextColors().getDefaultColor())) {
                        contentTitleColor = DEFAULT_LIGHT_CONTENT_TITLE_COLOR;
                        contentTextColor = DEFAULT_LIGHT_CONTENT_TEXT_COLOR;
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            contentTitleColor = ANDROID_5_CONTENT_TITLE_COLOR;
                            contentTextColor = ANDROID_5_CONTENT_TEXT_COLOR;
                        } else {
                            contentTitleColor = DEFAULT_DARK_CONTENT_TITLE_COLOR;
                            contentTextColor = DEFAULT_DARK_CONTENT_TEXT_COLOR;
                        }
                    }
                    return true;
                }

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按照安卓版本纯猜测 如果是原生安卓准确度100%
     * fetchMode = "BySdkVersion";
     * 安卓3.0到4.4之间是黑色通知栏
     */
    private void fetchNotificationTextColorBySdkVersion() {
        final int SDK_INT = Build.VERSION.SDK_INT;
        final boolean isLightColor = (SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                && (SDK_INT < Build.VERSION_CODES.LOLLIPOP);
        if (isLightColor) {
            contentTitleColor = DEFAULT_LIGHT_CONTENT_TITLE_COLOR;
            contentTextColor = DEFAULT_LIGHT_CONTENT_TEXT_COLOR;
        } else {
            if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                contentTitleColor = ANDROID_5_CONTENT_TITLE_COLOR;
                contentTextColor = ANDROID_5_CONTENT_TEXT_COLOR;
            } else {
                contentTitleColor = DEFAULT_DARK_CONTENT_TITLE_COLOR;
                contentTextColor = DEFAULT_DARK_CONTENT_TEXT_COLOR;
            }

        }
    }

}
