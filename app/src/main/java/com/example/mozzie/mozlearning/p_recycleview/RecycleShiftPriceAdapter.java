package com.example.mozzie.mozlearning.p_recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

import java.util.ArrayList;

/**
 * Created by mozzie on 17/7/29.
 */

public class RecycleShiftPriceAdapter extends RecyclerView.Adapter<RecycleShiftPriceAdapter.ViewHolder>{
    private ArrayList<RecycleBean> mRecyledata;
    private RecycleShiftPriceAdapter.OnItemClickListener mOnItemClickListener;
    private int selectedPosition = -1;
    private Context mContext;

    public RecycleShiftPriceAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<RecycleBean> recyledata) {
        mRecyledata = recyledata;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(RecycleShiftPriceAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecycleShiftPriceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recycleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pricerecycleview_item, parent, false);
        RecycleShiftPriceAdapter.ViewHolder vh = new RecycleShiftPriceAdapter.ViewHolder(recycleView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecycleShiftPriceAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(mRecyledata.get(position).getText());

        if(mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
//                    if(){
//
//                    }
                    doViewClickstate(holder);
//                    notifyItemChanged(position);
//                    selectedPosition = position; //选择的position赋值给参数，
//                    notifyItemChanged(selectedPosition);//刷新当前点击item
                }
            });
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int motionEvent = event.getAction();
                    switch (motionEvent){
                        case MotionEvent.ACTION_DOWN:
                            doPressClickstate(holder);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            doClearClickstate(holder);
                            break;

                    }
                    return false;
                }
            });
        }
    }

    private void doViewClickstate(RecycleShiftPriceAdapter.ViewHolder holder) {
        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.colorOrage));
//        holder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.colorOrageback));
        holder.mImageRightArrow.setVisibility(View.VISIBLE);
        holder.mImageBackground.setVisibility(View.VISIBLE);
        holder.mImageBackground.setBackgroundResource(R.color.colorOrageback);
    }

    private void doPressClickstate(RecycleShiftPriceAdapter.ViewHolder holder){
        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.colorOrage));
    }

    private void doClearClickstate(RecycleShiftPriceAdapter.ViewHolder holder){
        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.colorTextGray));
        holder.mImageRightArrow.setVisibility(View.INVISIBLE);
        holder.mImageBackground.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mRecyledata == null ?  0 : mRecyledata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageRightArrow;
        public ImageView mImageBackground;
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.text_shiftprice);
            mImageRightArrow = (ImageView)itemView.findViewById(R.id.recycle_rightarrow);
            mImageBackground = (ImageView)itemView.findViewById(R.id.recycle_background);
        }
    }
}
