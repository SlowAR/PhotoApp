package by.test.photoapptest.ui.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private Listener mListener;
    private ArrayList<ImageDtoOut> mPhotos;

    public PhotoListAdapter(@NonNull Context context,
                            ArrayList<ImageDtoOut> photos,
                            @NonNull Listener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mListener = listener;
        mPhotos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PhotoViewHolder photoListViewHolder = PhotoViewHolder.inflate(mLayoutInflater, parent);
        photoListViewHolder.setListener(mListener);
        return photoListViewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.setPhoto(mPhotos.get(position));
    }

    public void setPhotos(ArrayList<ImageDtoOut> photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public interface Listener {

        void choosePhotoItem(@NonNull ImageDtoOut photo);

        void deletePhotoItem(@NonNull ImageDtoOut photo);
    }
}