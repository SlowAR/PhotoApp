package by.test.photoapptest.ui.photos.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.model.comment.CommentDtoIn;
import by.test.photoapptest.model.comment.CommentDtoOut;
import by.test.photoapptest.model.photo.ImageDtoOut;
import by.test.photoapptest.model.user.SignUserOutDto;
import by.test.photoapptest.ui.photos.comments.CommentListAdapter;

public class DetailPhotoFragment extends Fragment implements CommentListAdapter.Listener,
        DetailPhotosListener, View.OnClickListener {

    @Inject
    DetailPhotoPresenter mPresenter;
    private DetailPhotoFragmentBinding mBinding;
    private ImageDtoOut mPhoto;
    private SignUserOutDto mUser;
    private CommentListAdapter mAdapter;

    public DetailPhotoFragment() {
        // Required empty public constructor
    }

    public static DetailPhotoFragment newInstance(@NonNull ImageDtoOut photo, @NonNull SignUserOutDto user) {
        DetailPhotoFragment detailPhotoFragment = new DetailPhotoFragment();
        detailPhotoFragment.setPhoto(photo);
        detailPhotoFragment.setUser(user);
        return detailPhotoFragment;
    }

    private void setPhoto(@NonNull ImageDtoOut photo) {
        mPhoto = photo;
    }

    public void setUser(@NonNull SignUserOutDto user) {
        mUser = user;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        mPresenter.setUser(mUser);
        mPresenter.setListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mPresenter != null) {
            mPresenter.getPhotoComments(mPhoto.getId(), 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_photo, null, false);
        mBinding.setPhoto(mPhoto);

//        ArrayList<CommentDtoOut> commentList = new ArrayList<>();
//        for(int i = 0; i < 7; i++) {
//            commentList.add(new CommentDtoOut(123123123, i, "text" + (i+1)));
//        }

        mAdapter = new CommentListAdapter(getContext(), new ArrayList<CommentDtoOut>(), this);
        mBinding.commentsRecyclerView.setAdapter(mAdapter);
        mBinding.sendComment.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void deleteCommentItem(@NonNull CommentDtoOut comment) {
        mPresenter.deletePhotoComment(mPhoto.getId(), comment.getId());
    }

    @Override
    public void updateCommentsList(ArrayList<CommentDtoOut> commentList) {
        mAdapter.setComments(commentList);
    }

    @Override
    public void refreshPhotoComments() {
        mPresenter.getPhotoComments(mPhoto.getId(), 0);
    }

    @Override
    public void onClick(View v) {
        CommentDtoIn comment = new CommentDtoIn(mBinding.commentField.getText().toString());
        mPresenter.postPhotoComment(mPhoto.getId(), comment);
        mBinding.commentField.setText("");
    }
}