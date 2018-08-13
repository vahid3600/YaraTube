package com.yaratech.yaratube.ui.product_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class ProductListRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder> {

    private List<ProductList> productLists = new ArrayList<>();
    private Context context;

    // data is passed into the constructor
    public ProductListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ProductList> productLists){
        this.productLists = productLists;
        notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent,
                false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String image_url = Util.BASE_URL+productLists.get(position).getAvatar().getXxxdpi();
        String title = productLists.get(position).getName();
        Glide.with(context).load(image_url).into(holder.product_avatar);
        holder.product_title.setText(title);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return productLists.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_avatar;
        TextView product_title;

        ViewHolder(View itemView) {
            super(itemView);
            product_avatar = itemView.findViewById(R.id.product_image);
            product_title = itemView.findViewById(R.id.product_title);
        }
    }
}

