package com.example.studyvedio.module.my.ui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studyvedio.ChatActivity;
import com.example.studyvedio.R;
import com.example.studyvedio.base.BaseFragment;
import com.example.studyvedio.base.BasePresenter;
import com.example.studyvedio.module.message.adapter.MessageListAdapter;
import com.example.studyvedio.module.my.adapter.WorkListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkListFragment extends BaseFragment {

    public static final int USER_LIST =10;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int type;
    private WorkListAdapter mWorkListAdapter;
    public String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };



    public WorkListFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public WorkListFragment(int type) {
        this.type=type;
    }

    @Override
    protected void initListerner() {

    }

    @Override
    protected int setView() {
        return R.layout.fragment_work_list;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        switch (type){
            case USER_LIST:
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                MessageListAdapter adapter = new MessageListAdapter(getActivity());
                adapter.setOnItemClickListener(new MessageListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        startActivity(new Intent(getActivity(), ChatActivity.class));
                    }
                });
                recyclerView.setAdapter(adapter);
                break;
        }
//        ArrayList<String> data = new ArrayList<>();
//        for (String next : eatFoodyImages) {
//            data.add(next);
//        }

//        取消图片
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
//        mWorkListAdapter = new WorkListAdapter(getActivity(),data);
//        recyclerView.setAdapter(mWorkListAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }
}
