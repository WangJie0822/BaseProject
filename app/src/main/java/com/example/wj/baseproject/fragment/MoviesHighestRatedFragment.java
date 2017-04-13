package com.example.wj.baseproject.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.adapter.MoviesListAdapter;
import com.example.wj.baseproject.base.BaseFragment;
import com.example.wj.baseproject.databinding.FragmentMoviesHighestRatedBinding;
import com.example.wj.baseproject.handler.MoviesItemHandler;
import com.example.wj.baseproject.mvp.bean.MoviesBean;
import com.example.wj.baseproject.mvp.bean.MoviesListBean;
import com.example.wj.baseproject.mvp.presenter.MoviesHighestRatedPresenter;
import com.example.wj.baseproject.mvp.view.MoviesHighestRatedView;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * 高评分电影
 */
public class MoviesHighestRatedFragment extends BaseFragment<MoviesHighestRatedPresenter, FragmentMoviesHighestRatedBinding>
        implements MoviesHighestRatedView {

    ArrayList<MoviesBean> mData;

    @Inject
    MoviesListAdapter adapter;

    @Override
    protected int layoutResId() {
        return R.layout.fragment_movies_highest_rated;
    }

    @Override
    protected void initView(View childView) {

        presenter.onAttach(this);

        mData = new ArrayList<>();
        adapter.bindData(mData);
        adapter.bindHandler(new HighestRateHandler());

        mBinding.rv.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.rv.setAdapter(adapter);

        presenter.getHighestRatedMovies();
    }

    @Override
    public void notifyData(MoviesListBean data) {
        mData.clear();
        mData.addAll(data.getResults());
        adapter.notifyDataSetChanged();
    }

    private class HighestRateHandler implements MoviesItemHandler{
        @Override
        public void onMoviesItemClick(MoviesBean item) {
            // TODO
            showToast("---");
        }
    }
}
