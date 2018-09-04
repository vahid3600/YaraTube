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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    public void updateComments(List<Comment> commentList){
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(commentList.get(position));
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
}


