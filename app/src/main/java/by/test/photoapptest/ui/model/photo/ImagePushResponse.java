package by.test.photoapptest.ui.model.photo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class ImagePushResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private ImageDtoOut photos;

    public ImagePushResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ImageDtoOut getPhotos() {
        return photos;
    }

    public void setPhotos(ImageDtoOut photos) {
        this.photos = photos;
    }
}
