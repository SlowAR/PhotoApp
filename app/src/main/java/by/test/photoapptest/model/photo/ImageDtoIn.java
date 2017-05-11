package by.test.photoapptest.model.photo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class ImageDtoIn {

    @JsonProperty("base64Image")
    private String image;

    @JsonProperty("date")
    private int date;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    public ImageDtoIn() {
    }

    public ImageDtoIn(String image, int date, double latitude, double longitude) {
        this.image = image;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
