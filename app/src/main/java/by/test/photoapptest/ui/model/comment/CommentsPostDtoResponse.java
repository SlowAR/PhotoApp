package by.test.photoapptest.ui.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

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
