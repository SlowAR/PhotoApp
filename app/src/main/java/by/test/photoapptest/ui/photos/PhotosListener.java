package by.test.photoapptest.ui.photos;

import java.util.List;

import by.test.photoapptest.ui.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 11.05.2017.
 */

public interface PhotosListener {

    void updatePhotoList(List<ImageDtoOut> photoList);
}
