package by.test.photoapptest.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by SlowAR on 12.05.2017.
 */

public class Utils {

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
