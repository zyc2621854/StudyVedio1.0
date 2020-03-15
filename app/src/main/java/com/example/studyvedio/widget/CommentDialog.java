package com.example.studyvedio.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.studyvedio.R;
import com.example.studyvedio.bean.CommentBean;
import com.example.studyvedio.bean.CommentChildBean;
import com.example.studyvedio.module.home.adapter.CommentAdapter;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * 评论弹窗
 * @author Created by CHEN on 2019/6/18
 * @email 188669@163.com
 */
public class CommentDialog extends BottomSheetDialog {

    private CommentDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public static class Builder{
        private CommentDialog mDialog;
        private Context context;
        private OnDismissListener onDismissListener;
        private CommentAdapter adapter;
        private OnDialogListener onDialogListener;
        public Builder(Context context) {
            this.context = context;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener){
            this.onDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnDialogListener(OnDialogListener onDialogListener) {
            this.onDialogListener = onDialogListener;
            return this;
        }

        private void create(){
            mDialog = new CommentDialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null);
            ConstraintLayout clLocation = view.findViewById(R.id.clLocation);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvMark = view.findViewById(R.id.tvMark);
            TextView tvMark1 = view.findViewById(R.id.tvMark1);
            TextView tvMark2 = view.findViewById(R.id.tvMark2);
            TextView tvNum = view.findViewById(R.id.tvNum);
            ImageView ivEmoji = view.findViewById(R.id.ivEmoji);
            ImageView ivAt = view.findViewById(R.id.ivAt);
            ImageView ivDismiss = view.findViewById(R.id.ivDismiss);
            LinearLayout llComment = view.findViewById(R.id.llComment);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, QMUIDisplayHelper.getScreenHeight(context) / 5 * 3);
            lp.topToBottom = R.id.tvNum;
            lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;

            recyclerView.setLayoutParams(lp);
            /** 从这新建了commentAdapter 并从这传入RecycleView **/
            /** adpter.notify xx来控制增删item **/
            adapter = new CommentAdapter(context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
            
            int count = adapter.getGroupCount();
            for (int i = 0; i < count; i++) {
                adapter.expandGroup(adapter.getGroupItem(i));
            }

            adapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {
                @Override
                public void onMoreClick(CommentBean groupBean) {
                    int index = adapter.getGroupIndex(groupBean);
                    recyclerView.scrollToPosition(index);
                }

                @Override
                public boolean onGroupLongClicked(CommentBean groupItem) {
                    return false;
                }

                @Override
                public boolean onInterceptGroupExpandEvent(CommentBean groupItem, boolean isExpand) {
                    return true;
                }

                @Override
                public void onGroupClicked(CommentBean groupItem) {

                }

                @Override
                public void onChildClicked(CommentBean groupItem, CommentChildBean childItem) {

                }
            });

            ivEmoji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDialogListener != null){
                        onDialogListener.emojiClick();
                    }
                }
            });
            ivAt.setOnClickListener(v -> {
                if (onDialogListener != null){
                    onDialogListener.atClick();
                }
            });
            llComment.setOnClickListener(v -> {
                if (onDialogListener != null){
                    onDialogListener.commentClick();
                }
            });

            mDialog.setContentView(view);

            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (onDismissListener != null){
                        onDismissListener.dismiss();
                    }
                }
            });
            ivDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (onDismissListener != null){
                        onDismissListener.dismiss();
                    }
                }
            });
        };
        public CommentDialog show(){
            create();
            mDialog.show();
            return mDialog;
        }
        public void dismiss(){
            mDialog.dismiss();
        }


        public interface OnDialogListener{
            void emojiClick();
            void atClick();
            void commentClick();
        }
    }
    public interface OnDismissListener{
        void dismiss();
    }
}
