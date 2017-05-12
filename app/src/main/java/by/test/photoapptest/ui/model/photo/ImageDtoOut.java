package by.test.photoapptest.ui.model.photo;

import android.graphics.drawable.Drawable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel;
import com.raizlabs.android.dbflow.structure.BaseModel;

import by.test.photoapptest.util.dbflow.PhotoDatabase;

/**
 * Created by SlowAR on 10.05.2017.
 */

@Table(database = PhotoDatabase.class)
public class ImageDtoOut extends BaseModel {

    @Column
    @JsonProperty("date")
    private int date;

    @Column
    @PrimaryKey
    @JsonProperty("id")
    private int id;

    @Column
    @JsonProperty("lat")
    private double latitude;

    @Column
    @JsonProperty("lng")
    private double longitude;

    @Column
    @JsonProperty("url")
    private String url;

    private String convertedDate;

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

    public String getConvertedDate() {
        return convertedDate;
    }

    public void setConvertedDate(String convertedDate) {
        this.convertedDate = convertedDate;
    }
}
