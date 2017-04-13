package com.example.wj.baseproject.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wj.baseproject.BR;


/**
 * ViewHolder基类
 *
 * @param <DB> DataBinding类型
 * @param <B>  数据类型
 */
public abstract class BaseRvViewHolder<DB extends ViewDataBinding, B> extends RecyclerView.ViewHolder {

    public DB mBinding;

    public BaseRvViewHolder(View view) {
        super(view);
    }

    public BaseRvViewHolder(DB binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    void bindData(B item) {
        mBinding.setVariable(BR.item, item);
        mBinding.executePendingBindings();
    }
}
