package com.example.wj.baseproject;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.wj.baseproject.glide.GlideApp;
import com.example.wj.baseproject.net.UrlDefinition;

/**
 * DataBinding 适配器
 *
 * @author 王杰
 */
public class DataBindingAdapter {

    @BindingAdapter("imgUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        String imgUrl = UrlDefinition.POSTER_PATH.concat(url);
        GlideApp.with(imageView.getContext())
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
