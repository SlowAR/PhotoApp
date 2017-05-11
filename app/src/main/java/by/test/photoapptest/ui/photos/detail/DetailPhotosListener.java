package by.test.photoapptest.ui.photos.detail;

import java.util.ArrayList;
import java.util.List;

import by.test.photoapptest.ui.model.comment.CommentDtoOut;

/**
 * Created by SlowAR on 11.05.2017.
 */

public interface DetailPhotosListener {

    void updateCommentsList(List<CommentDtoOut> commentList);

    void refreshPhotoComments();
}
