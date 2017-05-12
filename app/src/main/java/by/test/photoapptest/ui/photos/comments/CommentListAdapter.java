package by.test.photoapptest.ui.photos.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.test.photoapptest.ui.model.comment.CommentDtoOut;
import by.test.photoapptest.ui.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class CommentListAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private Listener mListener;
    private List<CommentDtoOut> mComments;

    public CommentListAdapter(@NonNull Context context,
                              List<CommentDtoOut> comments,
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

    public void appendComments(List<CommentDtoOut> comments) {
        mComments.addAll(comments);
        notifyDataSetChanged();
    }

    public void removeComment(CommentDtoOut comment) {
        mComments.remove(comment);
        notifyDataSetChanged();
    }

    public void setComments(List<CommentDtoOut> comments) {
        mComments = comments;
        notifyDataSetChanged();
    }

    public void clearComments(){
        mComments.clear();
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public interface Listener {

        void deleteCommentItem(@NonNull CommentDtoOut comment);
    }
}