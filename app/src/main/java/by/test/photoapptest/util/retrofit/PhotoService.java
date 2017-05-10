package by.test.photoapptest.util.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class PhotoService {

    private static final String baseUrl = "http://213.184.248.43:9099/api/";
    public static final int RESPONSE_STATUS_OK = 200;

    public static PhotoServiceApi getPhotoServiceApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(PhotoServiceApi.class);
    }
}
