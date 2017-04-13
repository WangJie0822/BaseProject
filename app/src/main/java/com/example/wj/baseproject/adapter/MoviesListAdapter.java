package com.example.wj.baseproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseRvAdapter;
import com.example.wj.baseproject.base.BaseRvViewHolder;
import com.example.wj.baseproject.databinding.ItemBinding;
import com.example.wj.baseproject.handler.MoviesItemHandler;
import com.example.wj.baseproject.mvp.bean.MoviesBean;
import com.example.wj.baseproject.rx.RxUrlDefinition;

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

        @Override
        protected void bindData(MoviesBean item) {
            super.bindData(item);
            Context context = mBinding.iv.getContext();
            Glide.with(context)
                    .load(RxUrlDefinition.POSTER_PATH.concat(item.getPoster_path()))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new BitmapImageViewTarget(mBinding.iv) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            super.onResourceReady(resource, glideAnimation);
                            Palette.from(resource).generate(palette ->
                                    mBinding.v.setBackgroundColor(
                                            palette.getVibrantColor(
                                                    context.getResources().getColor(
                                                            R.color.colorPrimaryDark))));
                        }
                    });
        }
    }
}
