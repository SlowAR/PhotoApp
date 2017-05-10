package by.test.photoapptest.ui.photos;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import by.test.photoapptest.di.App;
import by.test.photoapptest.model.photo.ImageResponse;
import by.test.photoapptest.model.user.SignUserOutDto;
import by.test.photoapptest.util.retrofit.PhotoServiceApi;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class PhotoListPresenter {

    @Inject
    PhotoServiceApi mPhotoServiceApi;
    private Context mContext;
    private SignUserOutDto mUser;
    private PhotosListener mListener;

    public PhotoListPresenter(@NonNull Context context) {
        App.getAppComponent().inject(this);
        mContext = context;
    }

    public void setUser(@NonNull SignUserOutDto user) {
        mUser = user;
    }

    public void setListener(@NonNull PhotosListener listener) {
        mListener = listener;
    }

    public void getUserPhotos(int page) {
        mPhotoServiceApi.getUserPhotos(page, mUser.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImageResponse value) {
                        mListener.updatePhotoList(value.getPhotos());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
