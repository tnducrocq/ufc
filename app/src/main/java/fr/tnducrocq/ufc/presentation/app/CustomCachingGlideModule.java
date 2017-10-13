package fr.tnducrocq.ufc.presentation.app;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by tony on 16/08/2017.
 */
public class CustomCachingGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // set size & external vs. internal

        // 100 megabytes
        int cacheSize100MegaBytes = 104857600;

        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // nothing to do here
    }
}
