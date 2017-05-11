package by.test.photoapptest.util.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by SlowAR on 11.05.2017.
 */

@Database(name = PhotoDatabase.NAME, version = PhotoDatabase.VERSION)
public class PhotoDatabase {

    public static final String NAME = "PhotoDatabase";
    public static final int VERSION = 1;
}
