package by.test.photoapptest.ui.photos.detail;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import by.test.photoapptest.di.App;
import by.test.photoapptest.model.comment.CommentDtoIn;
import by.test.photoapptest.model.comment.CommentsGetDtoResponse;
import by.test.photoapptest.model.comment.CommentsPostDtoResponse;
import by.test.photoapptest.model.user.SignUserOutDto;
import by.test.photoapptest.util.retrofit.PhotoServiceApi;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class DetailPhotoPresenter {

    @Inject
    PhotoServiceApi mPhotoServiceApi;
    private DetailPhotosListener mListener;
    private SignUserOutDto mUser;
    private Context mContext;

    public DetailPhotoPresenter(@NonNull Context context) {
        App.getAppComponent().inject(this);
        mContext = context;
    }

    public void setUser(@NonNull SignUserOutDto user) {
        mUser = user;
    }

    public void setListener(@NonNull DetailPhotosListener listener) {
        mListener = listener;
    }

    public void getPhotoComments(int imageId, int page) {
        mPhotoServiceApi.getPhotoComments(imageId, page, mUser.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentsGetDtoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommentsGetDtoResponse value) {
                        mListener.updateCommentsList(value.getComments());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void postPhotoComment(int imageId, @NonNull CommentDtoIn comment) {
        mPhotoServiceApi.postPhotoComments(imageId, comment, mUser.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentsPostDtoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommentsPostDtoResponse value) {
                        mListener.refreshPhotoComments();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void deletePhotoComment(int imageId, int commentId) {
        mPhotoServiceApi.deletePhotoComments(imageId, commentId, mUser.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentsGetDtoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommentsGetDtoResponse value) {
                        mListener.refreshPhotoComments();
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
