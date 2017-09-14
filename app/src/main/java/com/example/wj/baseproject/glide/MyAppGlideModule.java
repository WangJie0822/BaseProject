package com.example.wj.baseproject.glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author 王杰
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    /**
     * 清单解析的开启
     * <p>
     * 这里不开启，避免添加相同的modules两次
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
