package by.test.photoapptest.model.photo;

import android.graphics.drawable.Drawable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class ImageDtoOut {

    @JsonProperty("date")
    private int date;

    @JsonProperty("id")
    private int id;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    @JsonProperty("url")
    private String url;

    public ImageDtoOut() {
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
