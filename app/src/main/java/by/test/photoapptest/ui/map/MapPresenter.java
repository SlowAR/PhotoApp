package by.test.photoapptest.ui.map;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import by.test.photoapptest.ui.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 12.05.2017.
 */

public class MapPresenter {

    private Context mContext;

    public MapPresenter(@NonNull Context context) {
        mContext = context;
    }

    public List<ImageDtoOut> getPhotosFromDb() {
        return SQLite.select().from(ImageDtoOut.class).queryList();
    }
}
