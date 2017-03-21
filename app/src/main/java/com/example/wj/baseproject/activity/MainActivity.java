package com.example.wj.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.Toast;

import com.example.wj.baseproject.A;
import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseActivity;
import com.example.wj.baseproject.databinding.ActivityMainBinding;
import com.example.wj.baseproject.fragment.MoviesHighestRatedFragment;
import com.example.wj.baseproject.mvp.presenter.BlankPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<BlankPresenter, ActivityMainBinding> {

    private ArrayList<Fragment> mData;

    @Inject
    A a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(mContext, "a:" + a, Toast.LENGTH_SHORT).show();

        mData = new ArrayList<>();
        mData.add(new MoviesHighestRatedFragment());
        mBinding.vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initToolbar() {

        showTitle();
        titleBar.setTitle("高评分电影");
    }

    public void btn(View view) {
        startActivity(new Intent(mContext, Main2Activity.class));
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
