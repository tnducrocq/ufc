package fr.tnducrocq.ufc.presentation.ui.champions;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by tony on 17/10/2017.
 */

public class ChampionImageTransformation extends BitmapTransformation {
    private static final String TAG = "ChampionImageTr";

    public ChampionImageTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                               int outWidth, int outHeight) {
        Log.d(TAG, "outWidth: " + outWidth + " outHeight: " + outHeight + " toTransform.getWidth: " + toTransform.getWidth() + " toTransform.getHeight: " + toTransform.getHeight());
        final float imageHeight = toTransform.getHeight();
        float newImageHeight = outHeight * 3.0f;
        final float scaleRatio = newImageHeight / imageHeight;
        float newImageWidth = toTransform.getWidth() * scaleRatio;
        return Bitmap.createScaledBitmap(toTransform, (int) newImageWidth, (int) newImageHeight, true);
    }

    @Override
    public String getId() {
        return "fr.tnducrocq.ufc.presentation.ui.champions.ChampionImageTransformation";
    }
}
