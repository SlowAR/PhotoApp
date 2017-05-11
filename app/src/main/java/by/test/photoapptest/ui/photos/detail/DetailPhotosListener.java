package by.test.photoapptest.ui.photos.detail;

import java.util.ArrayList;

import by.test.photoapptest.model.comment.CommentDtoOut;
import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 11.05.2017.
 */

public interface DetailPhotosListener {

    void updateCommentsList(ArrayList<CommentDtoOut> commentList);

    void refreshPhotoComments();
}
