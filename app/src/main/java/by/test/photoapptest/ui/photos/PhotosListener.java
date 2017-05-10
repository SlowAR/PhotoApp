package by.test.photoapptest.ui.photos;

import java.util.ArrayList;

import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 11.05.2017.
 */

public interface PhotosListener {

    void updatePhotoList(ArrayList<ImageDtoOut> photoList);
}
