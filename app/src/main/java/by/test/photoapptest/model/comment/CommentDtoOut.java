package by.test.photoapptest.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class CommentDtoOut {

    @JsonProperty("date")
    private int date;

    @JsonProperty("id")
    private int id;

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