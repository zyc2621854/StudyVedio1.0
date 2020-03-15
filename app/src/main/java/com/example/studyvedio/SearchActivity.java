package com.example.studyvedio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.module.home.adapter.SearchListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.view2)
    View view2;


    @Override
    protected void initView() {
        searchView.setIconified(false);

        SearchListAdapter adapter = new SearchListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new SearchListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                startActivity(new Intent(SearchActivity.this, SearchDetailActivity.class));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_search;
    }



    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }
}
