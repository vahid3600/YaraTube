package com.yaratech.yaratube.data.sourse.product_detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class ProductDetailRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductDetailRecyclerViewAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    private List<ProductList> productLists = new ArrayList<>();
    private Context context;

    // data is passed into the constructor
    public ProductDetailRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
        String image_url = productLists.get(position).getAvatar().getXxxdpi();
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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView product_avatar;
        TextView product_title;

        ViewHolder(View itemView) {
            super(itemView);
            product_avatar = itemView.findViewById(R.id.product_image);
            product_title = itemView.findViewById(R.id.product_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.getProductItem(productLists.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener{
        void getProductItem(ProductList product);
    }
}

