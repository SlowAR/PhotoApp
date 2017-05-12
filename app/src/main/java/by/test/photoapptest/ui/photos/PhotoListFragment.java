package by.test.photoapptest.ui.photos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.ui.camera.CameraActivity;
import by.test.photoapptest.ui.model.photo.ImageDtoOut;
import by.test.photoapptest.ui.model.user.SignUserOutDto;
import by.test.photoapptest.util.EndlessRecyclerViewScrollListener;

public class PhotoListFragment extends Fragment implements PhotoListAdapter.Listener, PhotosListener {

    @Inject
    PhotoListPresenter mPresenter;
    private by.test.photoapptest.ui.photos.FragmentPhotoListBinding mBinding;
    private OnFragmentInteractionListener mListener;
    private PhotoListAdapter mAdapter;
    private SignUserOutDto mUser;

    public PhotoListFragment() {
        // Required empty public constructor
    }

    public static PhotoListFragment newInstance(@NonNull SignUserOutDto user) {
        PhotoListFragment photoListFragment = new PhotoListFragment();
        photoListFragment.setUser(user);
        return photoListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        mPresenter.setListener(this);
        mPresenter.setUser(mUser);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter != null) {
            mAdapter.clearPhotos();
        }
        if(mPresenter != null) {
            mPresenter.getUserPhotos(0);
        }
    }

    public void setUser(@NonNull SignUserOutDto user) {
        mUser = user;
    }

    @Override
    public void updatePhotoList(List<ImageDtoOut> photoList) {
        mAdapter.appendPhotos(photoList);
        mPresenter.putPhotosInDb(photoList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_list, null, false);
        mAdapter = new PhotoListAdapter(getContext(), new ArrayList<>(), this);
        mBinding.photosRecyclerView.setAdapter(mAdapter);
        EndlessRecyclerViewScrollListener mScrollListener =
                new EndlessRecyclerViewScrollListener((GridLayoutManager) (mBinding
                .photosRecyclerView.getLayoutManager())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.getUserPhotos(page);
            }
        };
        mBinding.photosRecyclerView.addOnScrollListener(mScrollListener);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void choosePhotoItem(@NonNull ImageDtoOut photo) {
        if (mListener != null) {
            mListener.onPhotoItemClicked(photo, mUser);
        }
    }

    @Override
    public void deletePhotoItem(@NonNull ImageDtoOut photo) {
        mPresenter.deleteUserPhoto(photo.getId());
        mPresenter.deletePhotoFromDb(photo);
        mAdapter.removePhoto(photo);
    }

    public interface OnFragmentInteractionListener {
        void onPhotoItemClicked(@NonNull ImageDtoOut photo, @NonNull SignUserOutDto user);
    }
}
