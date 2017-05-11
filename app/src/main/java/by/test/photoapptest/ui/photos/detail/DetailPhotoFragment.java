package by.test.photoapptest.ui.photos.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.ui.model.comment.CommentDtoIn;
import by.test.photoapptest.ui.model.comment.CommentDtoOut;
import by.test.photoapptest.ui.model.photo.ImageDtoOut;
import by.test.photoapptest.ui.model.user.SignUserOutDto;
import by.test.photoapptest.ui.photos.comments.CommentListAdapter;
import by.test.photoapptest.util.EndlessRecyclerViewScrollListener;

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

        mAdapter = new CommentListAdapter(getContext(), new ArrayList<CommentDtoOut>(), this);
        mBinding.commentsRecyclerView.setAdapter(mAdapter);

        EndlessRecyclerViewScrollListener mScrollListener =
                new EndlessRecyclerViewScrollListener((LinearLayoutManager) (mBinding
                .commentsRecyclerView.getLayoutManager())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.getPhotoComments(mPhoto.getId(), page);
            }
        };
        mBinding.commentsRecyclerView.addOnScrollListener(mScrollListener);

        mBinding.sendComment.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void deleteCommentItem(@NonNull CommentDtoOut comment) {
        mPresenter.deletePhotoComment(mPhoto.getId(), comment.getId());
    }

    @Override
    public void updateCommentsList(List<CommentDtoOut> commentList) {
        mAdapter.appendComments(commentList);
        mPresenter.putCommentsInDb(commentList);
        //mPresenter.getCommentsFromDb();
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