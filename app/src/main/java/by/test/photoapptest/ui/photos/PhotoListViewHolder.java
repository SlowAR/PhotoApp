package by.test.photoapptest.ui.photos;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import by.test.photoapptest.R;
import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class PhotoListViewHolder extends RecyclerView.ViewHolder {

    private final by.test.photoapptest.ui.photos.PhotoItemBinding mBinding;

    static PhotoListViewHolder inflate(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup parent) {
        by.test.photoapptest.ui.photos.PhotoItemBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.photo_item, parent, false);
        return new PhotoListViewHolder(binding);
    }

    private PhotoListViewHolder(@NonNull by.test.photoapptest.ui.photos.PhotoItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setListener(@NonNull PhotoListAdapter.Listener listener) {
        mBinding.setListener(listener);
    }

    public void setPhoto(@NonNull ImageDtoOut photo, @NonNull Context context) {
        mBinding.setPhoto(photo);
        String date = "" + photo.getDate();
        mBinding.photoDate.setText(date);
        Glide.with(context)
                .load(photo.getUrl())
                .into(mBinding.photoItem);
    }
}
