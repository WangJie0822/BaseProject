package com.example.wj.baseproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wj.baseproject.base.BaseActivity;
import com.example.wj.baseproject.databinding.ActivityMainBinding;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    TestA a;

    /** DataBinding */
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Logger.d(a);

        mBinding.vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BlankFragment();
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
