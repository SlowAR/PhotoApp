package by.test.photoapptest.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class ImageDataBindingAdapter {

    @BindingAdapter(value = "srcCompat")
    public static void setIcon(@NonNull Context context, @NonNull ImageView imageView, @Nullable String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
