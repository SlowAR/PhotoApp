package by.test.photoapptest.ui.photos;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.rx2.language.RXSQLite;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.inject.Inject;

import by.test.photoapptest.di.App;
import by.test.photoapptest.ui.model.photo.ImageDtoOut;
import by.test.photoapptest.ui.model.photo.ImageGetResponse;
import by.test.photoapptest.ui.model.user.SignUserOutDto;
import by.test.photoapptest.util.retrofit.PhotoServiceApi;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

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
                .subscribe(new Observer<ImageGetResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImageGetResponse value) {
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

    public void deleteUserPhoto(int id) {
        mPhotoServiceApi.deleteUserPhoto(id, mUser.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageGetResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ImageGetResponse value) {
                        mListener.refreshUserPhotos();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void putPhotosInDb(List<ImageDtoOut> photoList) {
        List<ImageDtoOut> list =  SQLite.select().from(ImageDtoOut.class).queryList();
        if(list.isEmpty()) {
            for (ImageDtoOut image : photoList) {
                image.insert();
            }
        }
    }

    public void getPhotosFromDb() {
        List<ImageDtoOut> list =  SQLite.select().from(ImageDtoOut.class).queryList();
        mListener.updatePhotoList(list);
    }
}
