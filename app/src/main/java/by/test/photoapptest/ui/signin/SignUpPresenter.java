package by.test.photoapptest.ui.signin;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import by.test.photoapptest.di.App;
import by.test.photoapptest.ui.model.user.SignUserDtoIn;
import by.test.photoapptest.ui.model.user.SignUserResponse;
import by.test.photoapptest.ui.PhotoAppActivity;
import by.test.photoapptest.util.retrofit.PhotoServiceApi;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class SignUpPresenter {

    @Inject
    PhotoServiceApi mPhotoServiceApi;
    private Context mContext;

    public SignUpPresenter(@NonNull Context context) {
        App.getAppComponent().inject(this);
        mContext = context;
    }

    public void signUpUser(@NonNull SignUserDtoIn user) {
        mPhotoServiceApi.signUpUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SignUserResponse value) {
                        ((PhotoAppActivity) mContext).onSignUpClick(value.getUser());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ((PhotoAppActivity) mContext).showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
