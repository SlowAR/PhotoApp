package by.test.photoapptest.ui.photos.comments;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.test.photoapptest.R;
import by.test.photoapptest.ui.model.comment.CommentDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private final CommentItemBinding mBinding;
    private CommentListAdapter.Listener mListener;

    static CommentViewHolder inflate(@NonNull LayoutInflater inflater,
                                     @Nullable ViewGroup parent) {
        CommentItemBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.comment_item, parent, false);
        return new CommentViewHolder(binding);
    }

    private CommentViewHolder(@NonNull CommentItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.getRoot().setOnLongClickListener(this);
    }

    public void setListener(@NonNull CommentListAdapter.Listener listener) {
        mListener = listener;
        mBinding.setListener(listener);
    }

    public void setComment(@NonNull CommentDtoOut comment) {
        mBinding.setComment(comment);
    }

    @Override
    public boolean onLongClick(View v) {
        mListener.deleteCommentItem(mBinding.getComment());
        return true;
    }
}
