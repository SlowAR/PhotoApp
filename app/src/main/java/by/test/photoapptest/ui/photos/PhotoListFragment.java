package by.test.photoapptest.ui.photos;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.model.photo.ImageDtoOut;
import by.test.photoapptest.model.user.SignUserOutDto;

public class PhotoListFragment extends Fragment implements PhotoListAdapter.Listener, PhotosListener {

    @Inject
    PhotoListPresenter mPresenter;
    private FragmentPhotoListBinding mBinding;
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
        mPresenter.getUserPhotos(0);
    }

    public void setUser(@NonNull SignUserOutDto user) {
        mUser = user;
    }

    @Override
    public void updatePhotoList(ArrayList<ImageDtoOut> photoList) {
        mAdapter.setPhotos(photoList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ArrayList<ImageDtoOut> photoList = new ArrayList<>();
//        for(int i = 0; i < 14; i++) {
//            photoList.add(new ImageDtoOut(ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher),
//                    "photo " + (i+1)));
//        }

        mAdapter = new PhotoListAdapter(getContext(), new ArrayList<ImageDtoOut>(), this);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_list, null, false);
        mBinding.photosRecyclerView.setAdapter(mAdapter);
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
            mListener.onPhotoItemClicked(photo);
        }
    }

    public interface OnFragmentInteractionListener {
        void onPhotoItemClicked(@NonNull ImageDtoOut photo);
    }
}
