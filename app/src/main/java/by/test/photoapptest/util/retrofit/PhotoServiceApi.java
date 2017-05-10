package by.test.photoapptest.util.retrofit;

import by.test.photoapptest.model.photo.ImageDtoIn;
import by.test.photoapptest.model.photo.ImageResponse;
import by.test.photoapptest.model.user.SignUserDtoIn;
import by.test.photoapptest.model.user.SignUserResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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
    Observable<ImageResponse> getUserPhotos(@Query("page") int page, @Header("Access-Token") String token);

    @POST("image")
    Observable<ImageResponse> loadUserPhoto(@Body ImageDtoIn photo, @Header("Access-Token") String token);
}
