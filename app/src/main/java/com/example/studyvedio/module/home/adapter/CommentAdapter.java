package com.example.studyvedio.module.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyvedio.R;
import com.example.studyvedio.bean.CommentBean;
import com.example.studyvedio.bean.CommentChildBean;
import com.example.studyvedio.utils.MyLog;
import com.example.studyvedio.widget.CommentSpan;
import com.example.studyvedio.widget.TouchableSpan;
import com.example.studyvedio.widget.emojiview.EmojiconTextView;
import com.example.studyvedio.widget.expandable.BaseExpandableRecyclerViewAdapter;
import com.like.LikeButton;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Created by CHEN on 2019/7/11
 * @email 188669@163.com
 */
public class CommentAdapter extends BaseExpandableRecyclerViewAdapter<CommentBean, CommentChildBean,
        CommentAdapter.GroupViewHolder, CommentAdapter.ChildViewHolder> {
    private Context mContext;
    private List<CommentBean> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public CommentAdapter(Context mContext) {
        this.mContext = mContext;

        List<CommentBean> commentBeanList = new ArrayList<>();
        CommentBean commentBeanA = new CommentBean();
        CommentBean commentBeanB = new CommentBean();
        commentBeanA.setContent("第一条父评论");
        commentBeanA.setContent("第二条父评论");
        List<CommentChildBean> childList1 = new ArrayList<CommentChildBean>();
        CommentChildBean childBeanA1 = new CommentChildBean();
        CommentChildBean childBeanA2 = new CommentChildBean();
        childBeanA1.setContent("第一条子评论");
        childBeanA2.setContent("第二条子评论");
        childList1.add(childBeanA1);
        childList1.add(childBeanA2);
        commentBeanList.add(commentBeanA);
        commentBeanList.add(commentBeanB);

        /** 此处添加了1组数据 故通过size可以确定添加1个大组 每个大组里有2个小组 **/
        for (int i = 0; i < 1; i++) {
            CommentBean commentBean = new CommentBean();
            List<CommentChildBean> list = new ArrayList<CommentChildBean>();
            for (int j = 0; j < 2; j++) {
                CommentChildBean childBean = new CommentChildBean();
                childBean.setContent("child : " + j);
                list.add(childBean);
            }
            commentBean.setContent("group : " + i);
            commentBean.setList(list);
            mList.add(commentBean);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /** 通过bean的个数在此函数控制父评论个数 **/
    @Override
    public int getGroupCount() {
        return mList.size();
    }

    /** 通过bean个数在此函数控制子评论个数 **/
    @Override
    public CommentBean getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_group_comment, parent, false));
    }
    /** 如何从数据库搜索所有评论： **/
    /** 先通过vedioid搜所有评论并排除没有父id的评论 **/
    /** 再通过查找所有有父id为已搜出评论id的评论则为子评论 **/
    /** 关于如何加载所有数据： **/
    /** 获取父评论集合按顺序存入父bean 然后通过父id获取所有子评论集合  将子回复存入对应的父回复中**/

    /** 父组数据绑定 **/
    /**  需要操控的变量：**/
    /** ivHead 头像 当前使用的是默认 **/
    /** tvName 用户名 **/
    /** tvcontent 内容 需要注意的是sp变量可以控制内容样式 **/
    /** timeStr 时间 作为内容的一部分被append进字符串了 **/
    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, CommentBean groupBean, boolean isExpand) {
//        String content = "groupItem content @[蛋糕][呲牙] @chen[爱心] @chen188669  https://www.baidu.com #这是也是话题 #话题@测试  ";
//        CommentSpan sp = new CommentSpan();
//        contentClick(sp);
//        SpannableString timeStr = new SpannableString("12:00");
//        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1f);
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ABABAB"));
//        timeStr.setSpan(sizeSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        timeStr.setSpan(colorSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        holder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
//        /** tvContent显示有问题 **/
//        holder.tvContent.setText(sp.setSpan(content,  Color.parseColor("#FFA500")).append(timeStr));
        holder.tvContent.setText("少说点");
        if (!isExpand){
            holder.tvMore.setVisibility(View.VISIBLE);
        }else {
            holder.tvMore.setVisibility(View.GONE);
        }
        holder.tvName.setText("[胜利]父用户");
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvMore.setVisibility(View.GONE);
                expandGroup(groupBean);
            }
        });
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_child_comment, parent, false));
    }



    /** 子组数据绑定 **/
    /**  需要操控的变量：**/
    /** ivHead 头像 当前使用的是默认 **/
    /** tvName 用户名 **/
    /** tvcontent 内容 分为四个部分 1.固定文字“回复 ” 2,被回复对象（被设置为可点击文字） 3.固定文字“：” 4.正文内容 **/
    /** timeStr 时间 作为内容的一部分被append进字符串了 **/
    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, CommentBean groupBean, CommentChildBean commentChildBean) {

        //SpannableString replyStr = new SpannableString("被回复对象[蛋糕]");
        String replyStr ="被回复者";
//        TouchableSpan touchSpan = new TouchableSpan(Color.parseColor("#ABABAB"), Color.parseColor("#ABABAB"), Color.TRANSPARENT, Color.TRANSPARENT) {
//            /** 点击该段文字出现浮框 **/
//            @Override
//            public void onSpanClick(View widget) {
//                Toast.makeText(mContext, "回复了 :"+replyStr, Toast.LENGTH_SHORT).show();
//            }
//        };
//        /** 被回复对象被设置了点击功能 **/
//        replyStr.setSpan(touchSpan,0, replyStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        String content = "childItem content @[蛋糕][呲牙] @chen[爱心] @chen188669  https://www.baidu.com #这是也是话题 #话题@测试  ";
//        CommentSpan sp = new CommentSpan();
//        contentClick(sp);
//        SpannableString timeStr = new SpannableString("12:00");
//        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.9f);
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ABABAB"));
//        timeStr.setSpan(sizeSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        timeStr.setSpan(colorSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        holder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tvContent.setText("回复 ");
        holder.tvContent.append(replyStr);
        holder.tvContent.append("：");
//        holder.tvContent.append(sp.setSpan(content,  Color.parseColor("#FFA500")).append(timeStr));
        holder.tvContent.append("正文少说点");

        if (commentChildBean == groupBean.getList().get(groupBean.getChildCount() - 1)){
            holder.tvMore.setVisibility(View.VISIBLE);
        }else {
            holder.tvMore.setVisibility(View.GONE);
        }
        holder.tvName.setText("[蛋糕]子用户");
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvMore.setVisibility(View.GONE);
                MyLog.e("测试Expand 关闭 =======  "+foldGroup(groupBean));;
                if (onItemClickListener != null){
                    onItemClickListener.onMoreClick(groupBean);
                }
            }
        });

    }
    /** 实例化父组控件 **/
    public class GroupViewHolder extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        @BindView(R.id.ivHead)
        QMUIRadiusImageView ivHead;
        @BindView(R.id.tvName)
        EmojiconTextView tvName;
        @BindView(R.id.tvContent)
        EmojiconTextView tvContent;
        @BindView(R.id.tvLikes)
        TextView tvLikes;
        @BindView(R.id.btLikes)
        LikeButton btLikes;
        @BindView(R.id.tvMore)
        TextView tvMore;
        public GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {
            MyLog.d("isExpanding : " + isExpanding);
        }
    }

    /** 实例化子布局控件 **/
    public class ChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivHead)
        QMUIRadiusImageView ivHead;
        @BindView(R.id.tvName)
        EmojiconTextView tvName;
        @BindView(R.id.tvContent)
        EmojiconTextView tvContent;
        @BindView(R.id.tvLikes)
        TextView tvLikes;
        @BindView(R.id.btLikes)
        LikeButton btLikes;
        @BindView(R.id.tvMore)
        TextView tvMore;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener extends BaseExpandableRecyclerViewAdapter.OnItemClickListener<CommentBean, CommentChildBean>{
        void onMoreClick(CommentBean groupBean);
    }

    /**
     * span点击事件
     * 可以添加跳转等逻辑
     */
    private void contentClick(CommentSpan sp){
        sp.setOnSpanClick(new CommentSpan.OnSpanClick() {
            @Override
            public void topicClick(String topic) {
                Toast.makeText(mContext, "topic :"+topic, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void atClick(String at) {
                Toast.makeText(mContext, "at :"+at, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void urlClick(String url) {
                Toast.makeText(mContext, "url :"+url, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
