package by.test.photoapptest.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Single;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class ImageStorage {

    public static Single<Uri> saveUserPhotoFile(@NonNull Bitmap bitmap) {
        try {
            File file = newFile(getPhotoNameCurrentDate());
            OutputStream outStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();

            return null;//Single.just(getUriForFile(file));
        } catch (IOException e) {
            return Single.error(e);
        }
    }

    private static File newFile(@NonNull String imageFileNameFormat, Object... args) throws IOException {
        String fileName = imageFileNameFormat;
        if (args.length > 0) {
            fileName = String.format(Locale.ENGLISH, imageFileNameFormat, args);
        }
//        File file = new File(mImagesDir, fileName);
//        if (file.exists() && !file.delete()) {
//            throw new IOException("File can't be deleted");
//        } else {
//            return file;
//        }
        return null;
    }

    private static String getPhotoNameCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String photoName = currentDate + ".jpg";
        return photoName;
    }
}