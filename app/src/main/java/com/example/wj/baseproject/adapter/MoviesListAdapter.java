package com.example.wj.baseproject.adapter;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseRvAdapter;
import com.example.wj.baseproject.base.BaseRvViewHolder;
import com.example.wj.baseproject.databinding.ItemBinding;
import com.example.wj.baseproject.handler.MoviesItemHandler;
import com.example.wj.baseproject.mvp.bean.MoviesBean;

import javax.inject.Inject;

/**
 * @author 王杰
 */
public class MoviesListAdapter extends BaseRvAdapter
        <MoviesBean,
                MoviesListAdapter.ViewHolder,
                MoviesItemHandler,
                ItemBinding> {

    @Inject
    MoviesListAdapter() {
    }

    @Override
    protected int layoutResId() {
        return R.layout.item;
    }

    @Override
    protected ViewHolder createViewHolder(ItemBinding binding) {
        return new ViewHolder(binding);
    }

    static class ViewHolder extends BaseRvViewHolder<ItemBinding, MoviesBean> {
        ViewHolder(ItemBinding binding) {
            super(binding);
        }
    }
}
