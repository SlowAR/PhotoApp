package by.test.photoapptest.ui.photos.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.test.photoapptest.R;
import by.test.photoapptest.model.photo.ImageDtoOut;

public class DetailPhotoFragment extends Fragment {

    private DetailPhotoFragmentBinding mBinding;
    private ImageDtoOut mPhoto;

    public DetailPhotoFragment() {
        // Required empty public constructor
    }

    public static DetailPhotoFragment newInstance(@NonNull ImageDtoOut photo) {
        DetailPhotoFragment detailPhotoFragment = new DetailPhotoFragment();
        detailPhotoFragment.setPhoto(photo);
        return detailPhotoFragment;
    }

    private void setPhoto(@NonNull ImageDtoOut photo) {
        mPhoto = photo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_photo, null, false);
        mBinding.setPhoto(mPhoto);
        return mBinding.getRoot();
    }
}