package by.test.photoapptest.di.module;

import javax.inject.Singleton;

import by.test.photoapptest.util.retrofit.PhotoService;
import by.test.photoapptest.util.retrofit.PhotoServiceApi;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 10.05.2017.
 */

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    PhotoServiceApi provideWeatherApi() {
        return PhotoService.getPhotoServiceApi();
    }
}
