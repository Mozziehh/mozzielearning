package com.example.mozzie.mozlearning.e_adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mozzie on 16/12/28.
 */

public class ListAdapter extends BaseAdapter {
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    //标准写法
//    ArrayList<UserLoginInfoBean> userList;
//
//    public ListAdapter(ArrayList<UserLoginInfoBean> list) {
//        userList = list;
//    }
//
//    public void setListData(ArrayList<UserLoginInfoBean> list) {
//        userList = list;
//    }
//
//    @Override
//    public int getCount() {
//        return userList == null ? 0 : userList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return userList == null ? 0 : userList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return userList == null ? 0 : position;
//    }
//
//    @Override
//    public View getView(final int position, View contentView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        UserLoginInfoBean bean = userList.get(position);
//        View view = null;
//        if (contentView == null) {
//            view = LayoutInflater.from(UserAccountListActivity.this).inflate(R.layout.loginsdk_account_userlist_item, null);
//            viewHolder = new ViewHolder();
//            viewHolder.circleHead = (CircleImageView) view.findViewById(R.id.loginsdk_user_head);
//            viewHolder.userName = (TextView) view.findViewById(R.id.loginsdk_user_name);
//            viewHolder.redPoint = (RecycleImageView) view.findViewById(R.id.user_red_point);
//            view.setTag(viewHolder);
//        } else {
//            view = contentView;
//            viewHolder = (ViewHolder) view.getTag();
//        }
//
//        if (position == 0) {
//            viewHolder.redPoint.setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.redPoint.setVisibility(View.INVISIBLE);
//        }
//
//        String userStr = !TextUtils.isEmpty(bean.inputUN) ? bean.inputUN : bean.userName;
//        viewHolder.userName.setText(userStr);
//
//        Uri uri = Uri.parse(bean.headUrl);
//        loadImageUrlAsync(viewHolder.circleHead, uri);
//        return view;
//    }
}
