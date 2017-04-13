package com.example.wj.baseproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wj.baseproject.BR;

import java.util.ArrayList;

/**
 * RecyclerView 适配器基类
 *
 * @param <B>  数据类型
 * @param <VH> ViewHolder类型 继承{@link BaseRvViewHolder}
 * @param <H>  事件处理类型 Handler
 * @param <V>  DataBinding类型 与 VH 一致 继承{@link ViewDataBinding}
 */
public abstract class BaseRvAdapter
        <B,
                VH extends BaseRvViewHolder,
                H,
                V extends ViewDataBinding> extends RecyclerView.Adapter<VH> {

    /** 布局类型-头布局 */
    protected static final int VIEW_TYPE_HEADER = 0x00038;
    /** 布局类型-正常 */
    protected static final int VIEW_TYPE_NORMAL = 0x00046;
    /** 布局类型-脚布局 */
    protected static final int VIEW_TYPE_FOOTER = 0x00508;

    /** 数据集合 */
    protected ArrayList<B> mData;

    /** 事件处理 */
    protected H handler;

    private ArrayList<View> headers;
    private ArrayList<View> footers;

    private int mHeaderPos;
    private int mFooterPos;

    /**
     * 绑定数据
     *
     * @param data 数据集合
     */
    public void bindData(ArrayList<B> data) {
        mData = data;
    }

    /**
     * 绑定事件处理
     *
     * @param handler 事件处理对象
     */
    public void bindHandler(H handler) {
        this.handler = handler;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return VIEW_TYPE_HEADER;
        } else if (isFooter(position)) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_NORMAL) {
            V binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    layoutResId(), parent, false);
            binding.setVariable(BR.handler, handler);
            return createViewHolder(binding);
        } else if(viewType == VIEW_TYPE_HEADER) {
            return createViewHolder(headers.get(mHeaderPos++));
        } else {
            return createViewHolder(footers.get(mFooterPos++));
        }

    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (isHeader(position) || isFooter(position)) {
            return;
        }

        int realPosition;

        if (headers == null) {
            realPosition = position;
        } else {
            realPosition = position - headers.size();
        }

        convert(holder, getItem(realPosition));
    }

    @Override
    public int getItemCount() {
        if (headers == null || headers.size() <= 0) {
            return mData == null ? 0 : mData.size();
        } else {
            return mData == null ? headers.size() : mData.size() + headers.size();
        }
    }

    /**
     * 绑定数据
     *
     * @param holder ViewHolder
     * @param item   数据对象
     */
    protected void convert(VH holder, B item) {
        // noinspection unchecked
        holder.bindData(item);
    }

    protected boolean isHeader(int position) {
        return headers != null && position < headers.size();
    }

    protected boolean isFooter(int position) {
        return footers != null && position > getItemCount() - footers.size() - 1;
    }

    public B getItem(int position) {
        return mData.get(position);
    }

    /**
     * 添加头布局
     *
     * @param headerView 头布局
     */
    public void addHeader(View headerView) {

        if (null == createViewHolder(headerView)) {
            throw new RuntimeException("the method createViewHolder(view) in your adapter must be overwrite!");
        }

        if (headers == null) {
            headers = new ArrayList<>();
        }
        headers.add(headerView);
    }

    /**
     * 添加脚布局
     *
     * @param footerView 脚布局
     */
    public void addFooter(View footerView) {

        if (null == createViewHolder(footerView)) {
            throw new RuntimeException("the method createViewHolder(view) in your adapter must be overwrite!");
        }

        if (footers == null) {
            footers = new ArrayList<>();
        }
        footers.add(footerView);
    }

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int layoutResId();

    /**
     * 创建ViewHolder
     *
     * @param binding DataBinding对象
     *
     * @return ViewHolder
     */
    protected abstract VH createViewHolder(V binding);

    /**
     * 创建ViewHolder 使用头布局时必须重写
     *
     * @param view View对象
     *
     * @return ViewHolder
     */
    protected VH createViewHolder(View view) {
        return null;
    }


}
