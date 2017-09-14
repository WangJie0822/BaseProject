package com.example.wj.baseproject.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * DataBinding适配器
 */
public class DataBindingAdapter {

    @BindingAdapter("selected")
    public static void selected(View v, boolean selected) {
        v.setSelected(selected);
    }

    @BindingAdapter("res_img")
    public static void resImg(ImageView iv, int resId) {
        iv.setImageResource(resId);
    }

    @BindingAdapter("path_img")
    public static void pathImg(ImageView iv, String imgPath) {
        try {
            InputStream in = iv.getContext().getAssets().open(imgPath);
            Bitmap bmp = BitmapFactory.decodeStream(in);
            iv.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
