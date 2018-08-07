package com.yaratech.yaratube.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.Utils;
import com.yaratech.yaratube.dataModels.Category_list;
import com.yaratech.yaratube.dataModels.Product;

import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class CategoryItemsRecyclerViewAdapter extends RecyclerView.Adapter<CategoryItemsRecyclerViewAdapter.ViewHolder> {

    private List<Category_list> category_lists;
    private Context context;

    // data is passed into the constructor
    public CategoryItemsRecyclerViewAdapter(Context context, List<Category_list> category_lists) {
        this.context = context;
        this.category_lists = category_lists;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String image_url = Utils.BASE_URL+category_lists.get(position).getAvatar();
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView category_avatar;
        TextView category_title;

        ViewHolder(View itemView) {
            super(itemView);
            category_avatar = itemView.findViewById(R.id.category_avatar);
            category_title = itemView.findViewById(R.id.category_title);
        }
    }

}

