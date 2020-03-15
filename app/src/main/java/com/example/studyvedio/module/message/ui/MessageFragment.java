package com.example.studyvedio.module.message.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studyvedio.ChatActivity;
import com.example.studyvedio.ContactActivity;
import com.example.studyvedio.EditMessageActivity;
import com.example.studyvedio.R;
import com.example.studyvedio.base.BaseFragment;
import com.example.studyvedio.base.BasePresenter;
import com.example.studyvedio.module.message.adapter.MessageListAdapter;
import com.example.studyvedio.widget.emojiview.EmojiconTextView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MessageFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;



    @Override
    protected void initListerner() {

    }

    @Override
    protected int setView() {
        return R.layout.fragment_message;
        //return R.layout.layout_head_user;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessageListAdapter adapter = new MessageListAdapter(getActivity());
        adapter.setOnItemClickListener(new MessageListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }







}
