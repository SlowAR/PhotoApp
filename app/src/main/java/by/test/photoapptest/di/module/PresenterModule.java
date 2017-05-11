package by.test.photoapptest.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import by.test.photoapptest.ui.camera.CameraActivity;
import by.test.photoapptest.ui.camera.CameraPresenter;
import by.test.photoapptest.ui.photos.PhotoListPresenter;
import by.test.photoapptest.ui.signin.SignInPresenter;
import by.test.photoapptest.ui.signin.SignUpPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 05.05.2017.
 */

@Module
public class PresenterModule {

    private Context mContext;

    public PresenterModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    CameraPresenter provideCameraPresenter() {
        return new CameraPresenter(mContext);
    }

    @Provides
    SignInPresenter provideSignInPresenter() {
        return new SignInPresenter(mContext);
    }

    @Provides
    SignUpPresenter provideSignUpPresenter() {
        return new SignUpPresenter(mContext);
    }

    @Provides
    PhotoListPresenter providePhotoListPresenter() {
        return new PhotoListPresenter(mContext);
    }
}
