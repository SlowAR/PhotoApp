package by.test.photoapptest.util.retrofit;

import by.test.photoapptest.ui.model.comment.CommentDtoIn;
import by.test.photoapptest.ui.model.comment.CommentsGetDtoResponse;
import by.test.photoapptest.ui.model.comment.CommentsPostDtoResponse;
import by.test.photoapptest.ui.model.photo.ImageDtoIn;
import by.test.photoapptest.ui.model.photo.ImageGetResponse;
import by.test.photoapptest.ui.model.photo.ImagePushResponse;
import by.test.photoapptest.ui.model.user.SignUserDtoIn;
import by.test.photoapptest.ui.model.user.SignUserResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SlowAR on 10.05.2017.
 */

public interface PhotoServiceApi {

    @POST("account/signup")
    Observable<SignUserResponse> signUpUser(@Body SignUserDtoIn user);

    @POST("account/signin")
    Observable<SignUserResponse> signInUser(@Body SignUserDtoIn user);

    @GET("image")
    Observable<ImageGetResponse> getUserPhotos(@Query("page") int page,
                                               @Header("Access-Token") String token);

    @POST("image")
    Observable<ImagePushResponse> pushUserPhoto(@Body ImageDtoIn photo,
                                                @Header("Access-Token") String token);

    @DELETE("image/{id}")
    Observable<ImageGetResponse> deleteUserPhoto(@Path("id") int id,
                                                 @Header("Access-Token") String token);

    @GET("image/{imageId}/comment")
    Observable<CommentsGetDtoResponse> getPhotoComments(@Path("imageId") int imageId,
                                                        @Query("page") int page,
                                                        @Header("Access-Token") String token);

    @POST("image/{imageId}/comment")
    Observable<CommentsPostDtoResponse> postPhotoComments(@Path("imageId") int imageId,
                                                          @Body CommentDtoIn page,
                                                          @Header("Access-Token") String token);

    @DELETE("image/{imageId}/comment/{commentId}")
    Observable<CommentsGetDtoResponse> deletePhotoComments(@Path("imageId") int imageId,
                                                           @Path("commentId") int commentId,
                                                           @Header("Access-Token") String token);
}
