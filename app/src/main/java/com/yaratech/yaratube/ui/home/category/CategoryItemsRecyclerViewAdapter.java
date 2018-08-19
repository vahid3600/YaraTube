package com.yaratech.yaratube.ui.home.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vah on 8/4/2018.
 */

public class CategoryItemsRecyclerViewAdapter extends
        RecyclerView.Adapter<CategoryItemsRecyclerViewAdapter.ViewHolder> {

    private List<CategoryList> categoryList = new ArrayList<>();
    private ItemClickListener itemClickListener;

    // data is passed into the constructor
    public CategoryItemsRecyclerViewAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<CategoryList> category_lists){
        this.categoryList = category_lists;
        notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent,
                false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(categoryList.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.category_avatar)
        ImageView categoryAvatar;
        @BindView(R.id.category_title)
        TextView categoryTitle;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(categoryList.get(getAdapterPosition()));
        }

        public void onBind(CategoryList category) {
            String image_url = Utils.BASE_URL+category.getAvatar();
            String title = category.getTitle();
            Glide.with(itemView.getContext()).load(image_url).into(categoryAvatar);
            categoryTitle.setText(title);
        }
    }

    public interface ItemClickListener {
        void onItemClick(CategoryList category_list);
    }

}

