package com.example.wj.baseproject.fragment;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseFragment;
import com.example.wj.baseproject.databinding.FragmentMoviesHighestRatedBinding;
import com.example.wj.baseproject.databinding.ItemBinding;
import com.example.wj.baseproject.mvp.bean.MoviesBean;
import com.example.wj.baseproject.mvp.bean.MoviesListBean;
import com.example.wj.baseproject.mvp.presenter.MoviesHighestRatedPresenter;
import com.example.wj.baseproject.mvp.view.MoviesHighestRatedView;

import java.util.ArrayList;


/**
 * 高评分电影
 */
public class MoviesHighestRatedFragment extends BaseFragment<MoviesHighestRatedPresenter, FragmentMoviesHighestRatedBinding>
        implements MoviesHighestRatedView {

    ArrayList<MoviesBean> mData;
    private MyAdapter adapter;

    @Override
    protected int initLayoutResId() {
        return R.layout.fragment_movies_highest_rated;
    }

    @Override
    protected void initView(View childView) {

        presenter.onAttach(this);

        mData = new ArrayList<>();
        mBinding.rv.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new MyAdapter();
        mBinding.rv.setAdapter(adapter);

        presenter.getHighestRatedMovies();

    }

    @Override
    public void notifyData(MoviesListBean data) {
        mData.clear();
        mData.addAll(data.getResults());
        adapter.notifyDataSetChanged();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item, parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.onBind(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ItemBinding binding;

            ViewHolder(ItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            void onBind(MoviesBean movies) {
                binding.setMovies(movies);
                binding.executePendingBindings();
            }
        }
    }
}
