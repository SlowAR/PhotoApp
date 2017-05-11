package by.test.photoapptest.ui.photos.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import by.test.photoapptest.model.comment.CommentDtoOut;
import by.test.photoapptest.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class CommentListAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private Listener mListener;
    private ArrayList<CommentDtoOut> mComments;

    public CommentListAdapter(@NonNull Context context,
                              ArrayList<CommentDtoOut> comments,
                              @NonNull Listener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mListener = listener;
        mComments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommentViewHolder photoListViewHolder = CommentViewHolder.inflate(mLayoutInflater, parent);
        photoListViewHolder.setListener(mListener);
        return photoListViewHolder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.setComment(mComments.get(position));
    }

    public void setComments(ArrayList<CommentDtoOut> comments) {
        mComments = comments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public interface Listener {

        void deleteCommentItem(@NonNull CommentDtoOut comment);
    }
}