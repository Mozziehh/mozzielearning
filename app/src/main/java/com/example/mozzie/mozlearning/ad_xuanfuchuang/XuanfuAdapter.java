package com.example.mozzie.mozlearning.ad_xuanfuchuang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

import java.util.ArrayList;

public class XuanfuAdapter extends BaseAdapter{

    private ArrayList<XuanfuBeanList> xuanfuBeanLists;
    private Context mContext;
    public XuanfuAdapter(Context context, ArrayList<XuanfuBeanList> xuanfuBeanLists){
        this.mContext = context;
        this.xuanfuBeanLists = xuanfuBeanLists;
    }

    public ArrayList<XuanfuBeanList> getXuanfuBeanList(){
        return  this.xuanfuBeanLists;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return xuanfuBeanLists == null ? 0 : xuanfuBeanLists.size();
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
        return xuanfuBeanLists.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
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

        myViewHolder.title.setText(xuanfuBeanLists.get(position).title);
        myViewHolder.content.setText(xuanfuBeanLists.get(position).content);
        myViewHolder.url = xuanfuBeanLists.get(position).url;
        return view;
    }

    public static class ViewHolder{
        TextView title;
        TextView content;
        String url;
    }
}
