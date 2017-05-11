package by.test.photoapptest.ui.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class CommentsGetDtoResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private ArrayList<CommentDtoOut> comments;

    public CommentsGetDtoResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<CommentDtoOut> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentDtoOut> comments) {
        this.comments = comments;
    }
}
