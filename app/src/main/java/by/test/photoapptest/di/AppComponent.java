package by.test.photoapptest.di;

import javax.inject.Singleton;

import by.test.photoapptest.di.module.PresenterModule;
import by.test.photoapptest.ui.camera.CameraActivity;
import by.test.photoapptest.ui.camera.CameraPresenter;
import by.test.photoapptest.ui.photos.PhotoListFragment;
import by.test.photoapptest.ui.photos.PhotoListPresenter;
import by.test.photoapptest.ui.photos.PhotosActivity;
import by.test.photoapptest.ui.signin.SignInPageFragment;
import by.test.photoapptest.ui.signin.SignInPresenter;
import by.test.photoapptest.ui.signin.SignUpPageFragment;
import by.test.photoapptest.ui.signin.SignUpPresenter;
import by.test.photoapptest.di.module.RetrofitModule;
import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, RetrofitModule.class})
public interface AppComponent {

    void inject(CameraActivity activity);

    void inject(SignInPageFragment fragment);

    void inject(SignUpPageFragment fragment);

    void inject(PhotoListFragment fragment);

    void inject(SignInPresenter presenter);

    void inject(SignUpPresenter presenter);

    void inject(PhotoListPresenter presenter);

    void inject(CameraPresenter presenter);
}