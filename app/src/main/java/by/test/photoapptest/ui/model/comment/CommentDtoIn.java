package by.test.photoapptest.ui.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class CommentDtoIn {

    @JsonProperty("text")
    private String text;

    public CommentDtoIn() {
    }

    public CommentDtoIn(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}