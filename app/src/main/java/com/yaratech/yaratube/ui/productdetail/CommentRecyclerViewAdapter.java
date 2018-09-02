package com.yaratech.yaratube.ui.productdetail;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.productlist.ProductListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vah on 8/4/2018.
 */

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private boolean isLoadingAdded = false;
    private static final int item = 0;
    private static final int loading = 1;
    private List<Comment> commentList = new ArrayList<>();

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        CommentRecyclerViewAdapter.ViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case item:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case loading:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_progress, parent, false);
                viewHolder = new CommentRecyclerViewAdapter.ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    public void updateComments(List<Comment> commentList){
        List<Comment> newCommentList = new ArrayList<>();
        newCommentList.addAll(this.commentList);
        newCommentList.addAll(commentList);
        DiffUtil.DiffResult diffResult
                = DiffUtil.calculateDiff(new CommentRecyclerViewAdapter
                .DiffUtilfCallBack(this.commentList, newCommentList));
        this.commentList = newCommentList;
        diffResult.dispatchUpdatesTo(this);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case item:
                holder.onBind(commentList.get(position));
                break;
            case loading:
                break;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return commentList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_title)
        TextView product_title;
        @BindView(R.id.product_text)
        TextView product_text;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Comment comment) {
            product_title.setText(comment.getUser());
            product_text.setText(comment.getCommentText());
        }
    }

    class DiffUtilfCallBack extends DiffUtil.Callback {


        private List<Comment> oldComments = new ArrayList<>();
        private List<Comment> newComments = new ArrayList<>();

        DiffUtilfCallBack(List<Comment> oldComments, List<Comment> newComments) {
            this.oldComments = oldComments;
            this.newComments = newComments;
        }

        @Override
        public int getOldListSize() {
            return oldComments.size();
        }

        @Override
        public int getNewListSize() {
            return newComments.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldComments.get(oldItemPosition).getId()
                    == newComments.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldComments.get(oldItemPosition).equals(newComments.get(newItemPosition));
        }

        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == commentList.size() - 1 && isLoadingAdded) ? loading : item;

    }

    @Override
    public long getItemId(int position) {
        Comment comment = commentList.get(position);
        return comment.getId();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
    }
}


