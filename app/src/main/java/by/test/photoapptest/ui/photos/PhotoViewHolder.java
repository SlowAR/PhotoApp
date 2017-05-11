package by.test.photoapptest.ui.photos;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.test.photoapptest.R;
import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private final by.test.photoapptest.ui.photos.PhotoItemBinding mBinding;
    private PhotoListAdapter.Listener mListener;

    static PhotoViewHolder inflate(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup parent) {
        by.test.photoapptest.ui.photos.PhotoItemBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.photo_item, parent, false);
        return new PhotoViewHolder(binding);
    }

    private PhotoViewHolder(@NonNull by.test.photoapptest.ui.photos.PhotoItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.photoItem.setOnLongClickListener(this);
    }

    public void setListener(@NonNull PhotoListAdapter.Listener listener) {
        mListener = listener;
        mBinding.setListener(listener);
    }

    public void setPhoto(@NonNull ImageDtoOut photo) {
        mBinding.setPhoto(photo);
    }

    @Override
    public boolean onLongClick(View v) {
        mListener.deletePhotoItem(mBinding.getPhoto());
        return true;
    }
}
