package com.example.studyvedio;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studyvedio.bean.LevideoData;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends PagerAdapter {

    private List<LevideoData> mList = new ArrayList<>();
    public TestAdapter(List<LevideoData> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(container.getContext(),R.layout.item_home,null);
        TextView tv1 = view.findViewById(R.id.tvLikes);
        tv1.setText(mList.get(position).getTvLike());
        container.addView(view);

        //返回填充的对象
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
