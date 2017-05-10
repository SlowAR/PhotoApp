package by.test.photoapptest.model.photo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class ImageResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private ArrayList<ImageDtoOut> photos;

    public ImageResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<ImageDtoOut> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<ImageDtoOut> photos) {
        this.photos = photos;
    }
}
