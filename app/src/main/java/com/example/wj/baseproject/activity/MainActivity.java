package com.example.wj.baseproject.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseActivity;
import com.example.wj.baseproject.databinding.ActivityMainBinding;
import com.example.wj.baseproject.fragment.MoviesHighestRatedFragment;
import com.example.wj.baseproject.mvp.presenter.BlankPresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<BlankPresenter, ActivityMainBinding> {

    private ArrayList<Fragment> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = new ArrayList<>();
        mData.add(new MoviesHighestRatedFragment());
        mBinding.vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initToolbar() {

//        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("高评分电影");
    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }
    }
}
