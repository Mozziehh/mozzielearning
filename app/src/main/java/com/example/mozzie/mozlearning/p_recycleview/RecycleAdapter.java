package com.example.mozzie.mozlearning.p_recycleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mozzie on 17/7/27.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private ArrayList<RecycleBean> mRecyledata;
    private OnItemClickListener mOnItemClickListener;
    private int selectedPosition = -1;
    private Context mContext;
    private String mItemBackIndexstring = "car_category_";
    public RecycleAdapter (ArrayList<RecycleBean> recyledata){
        mRecyledata = recyledata;
    }

    public RecycleAdapter(Context context) {
        mContext = context;

    }

    public void setData(ArrayList<RecycleBean> recyledata) {
        mRecyledata = recyledata;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recycleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycleview_item, parent, false);
        ViewHolder vh = new ViewHolder(recycleView);
        return vh;
    }


    /**
     * 文字选取
     * 图片的设计逻辑
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //设置文字
        holder.mTextView.setText(mRecyledata.get(position).getText());
        //设置图片：默认背景图是58背景图，然后解析发过来的数据协议，
        // 然后找一遍内置，如果内置存在则直接用内置，如果不存在则使用网络图片
        //// TODO: 17/8/11  设置图片
        holder.mImageView.setImageBitmap(getItemBitmap(position));
        //设置选中状态
        if (mRecyledata.get(position).isSelected()){
            doViewClickstate(holder);
        }
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    /**
     * 设置图片：默认背景图是58背景图，然后解析发过来的数据协议，
     * 然后找一遍内置，如果内置存在则直接用内置，如果不存在则使用网络图片
     * 获取图片
     * @return
     * @param position
     */
    private Bitmap getItemBitmap(int position) {
        Bitmap original = null;
        if(position == 7){
            original = BitmapFactory.decodeResource(mContext.getResources(), getIconResId(mRecyledata.get(position).getListName()));
        }else{
            original = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.car_category_buwxian);
        }


//        int resId = getIconResId(mRecyledata.get(position).getListName());
//        if (resId > 0) {
//            imageView.setNoFrequentImageURI(UriUtil.parseUriFromResId(resId));
//        }
//
//        if (!TextUtils.isEmpty(publishBean.getIcon()) && NetUtils.isConnect(mContext)) {
//            if (resId > 0) {
//                GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
//                hierarchy.setFailureImage(mContext.getResources().getDrawable(resId));
//                imageView.setHierarchy(hierarchy);
//            }
//            imageView.setNoFrequentImageURI(UriUtil.parseUri(mRecyledata.get(position).getIconUrl()));
//        }

        return original;
    }

    private Integer getIconResId(String key) {
        try {
            String name = mItemBackIndexstring + key ;
            Field field = R.drawable.class.getField(name);
            return field.getInt(null);
        } catch (SecurityException e) {
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        return -1;
    }

    private void doViewClickstate(ViewHolder holder) {
        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.colorOrage));
        holder.mImageRightArrow.setVisibility(View.VISIBLE);
        holder.mImageBackground.setBackgroundResource(R.color.colorOrageback);
    }

    @Override
    public int getItemCount() {
        return mRecyledata == null ?  0 : mRecyledata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public ImageView mImageRightArrow;
        public ImageView mImageBackground;
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.recycle_imageview);
            mTextView = (TextView)itemView.findViewById(R.id.recycle_textview);
            mImageRightArrow = (ImageView)itemView.findViewById(R.id.recycle_rightarrow);
            mImageBackground = (ImageView)itemView.findViewById(R.id.recycle_background);
        }
    }
}
