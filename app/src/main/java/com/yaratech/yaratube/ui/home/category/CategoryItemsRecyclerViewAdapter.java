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
import com.yaratech.yaratube.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class CategoryItemsRecyclerViewAdapter extends
        RecyclerView.Adapter<CategoryItemsRecyclerViewAdapter.ViewHolder> {

    private List<CategoryList> category_lists = new ArrayList<>();
    private ItemClickListener itemClickListener;
    private Context context;

    // data is passed into the constructor
    public CategoryItemsRecyclerViewAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<CategoryList> category_lists){
        this.category_lists = category_lists;
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
        String image_url = Util.BASE_URL+category_lists.get(position).getAvatar();
        String title = category_lists.get(position).getTitle();
        Glide.with(context).load(image_url).into(holder.category_avatar);
        holder.category_title.setText(title);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return category_lists.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView category_avatar;
        TextView category_title;

        ViewHolder(View itemView) {
            super(itemView);
            category_avatar = itemView.findViewById(R.id.category_avatar);
            category_title = itemView.findViewById(R.id.category_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(category_lists.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        void onItemClick(CategoryList category_list);
    }

}

