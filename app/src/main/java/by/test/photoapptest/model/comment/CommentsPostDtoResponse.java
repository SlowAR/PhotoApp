package by.test.photoapptest.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class CommentsPostDtoResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private CommentDtoOut comments;

    public CommentsPostDtoResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CommentDtoOut getComments() {
        return comments;
    }

    public void setComments(CommentDtoOut comments) {
        this.comments = comments;
    }
}
