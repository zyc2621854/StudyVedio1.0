package com.example.studyvedio.module.message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyvedio.R;
import com.example.studyvedio.module.my.adapter.WorkListAdapter;

import java.util.ArrayList;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public MessageListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message_list,parent,false);

        return new MessageListAdapter.ViewHolder(view,mOnItemClickListener) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTvTime.setText("小王");
        holder.mTvContent.setText("上哪嗨啊");
        holder.mTvTime.setText("11-28");
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvContent;
        TextView mTvTime;

        public ViewHolder(View itemView, final OnItemClickListener onClickListener) {
            super(itemView);
            mTvName=itemView.findViewById(R.id.tvName);
            mTvContent=itemView.findViewById(R.id.tvContent);
            mTvTime=itemView.findViewById(R.id.tvTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        int position = getAdapterPosition();
                        //确保position值有效
                        if (position != RecyclerView.NO_POSITION) {
                            onClickListener.onItemClicked(view, position);
                        }
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.mOnItemClickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }
}
