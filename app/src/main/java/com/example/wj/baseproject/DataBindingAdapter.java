package com.example.wj.baseproject;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wj.baseproject.rx.RxUrlDefinition;

/**
 * DataBinding 适配器
 *
 * @author 王杰
 */
public class DataBindingAdapter {

    @BindingAdapter("imgUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        String imgUrl = RxUrlDefinition.POSTER_PATH.concat(url);
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
