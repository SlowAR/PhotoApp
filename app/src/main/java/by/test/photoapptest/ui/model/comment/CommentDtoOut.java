package by.test.photoapptest.ui.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import by.test.photoapptest.util.dbflow.PhotoDatabase;

/**
 * Created by SlowAR on 11.05.2017.
 */

@Table(database = PhotoDatabase.class)
public class CommentDtoOut extends BaseModel {

    @Column
    @JsonProperty("date")
    private int date;

    @Column
    @PrimaryKey
    @JsonProperty("id")
    private int id;

    @Column
    @JsonProperty("text")
    private String text;

    public CommentDtoOut() {
    }

    public CommentDtoOut(int date, int id, String text) {
        this.date = date;
        this.id = id;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}