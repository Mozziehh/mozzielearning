package com.example.mozzie.mozlearning.n_listview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by mozzie on 17/6/19.
 */

public class MylistAdapter extends BaseAdapter{

    HashMap<String,String> mListdata;
    Context mContext;
    public MylistAdapter(Context context, HashMap<String, String> listdata){
        mListdata = listdata;
        mContext = context;
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mListdata.size();
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
        return mListdata.get(position);
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
        View view = null;
        ViewHolder myViewHolder = null;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_listview_item, null);
            myViewHolder = new ViewHolder();
            myViewHolder.title = (TextView) view.findViewById(R.id.title);
            myViewHolder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(myViewHolder);
        }else{
            view = convertView;
            myViewHolder = (ViewHolder) view.getTag();
        }

        myViewHolder.title.setText("" + position);
        myViewHolder.content.setText(mListdata.get(position));

        return view;
    }

    public static class ViewHolder {
        private TextView title;
        private TextView content;
    }
}
