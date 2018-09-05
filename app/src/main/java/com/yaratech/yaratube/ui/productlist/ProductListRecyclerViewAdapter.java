package com.yaratech.yaratube.ui.productlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class ProductListRecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnItemClickListener onItemClickListener;
    private boolean isLoadingAdded = false;
    public static final int item = 0;
    public static final int loading = 1;
    private List<Product> productList = new ArrayList<>();
    private Context context;

    // data is passed into the constructor
    public ProductListRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void updateData(List<Product> productList) {
        this.productList.addAll(productList);
        notifyItemRangeInserted(this.productList.size(), productList.size());
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case item:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item, parent, false);
                viewHolder = new ItemViewHolder(view);
                break;
            case loading:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(view);
                break;
        }
        return viewHolder;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case item:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.onBind(productList.get(position));
                break;
            case loading:
                break;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView productAvatar;
        TextView productTitle;
        TextView productDescription;

        ItemViewHolder(View itemView) {
            super(itemView);
            productAvatar = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productDescription = itemView.findViewById(R.id.description_product);

            itemView.setOnClickListener(this);
        }

        public void onBind(Product product) {
            if (product.getFeatureAvatar() != null)
                Glide
                        .with(context)
                        .load(product.getFeatureAvatar().getXxxdpi())
                        .into(productAvatar);
            productTitle.setText(product.getName());
            productDescription.setText(product.getShortDescription());
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.getProductItem(productList.get(getAdapterPosition()));
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == productList.size() - 1 && isLoadingAdded) ? loading : item;

    }

    @Override
    public long getItemId(int position) {
        Product product = productList.get(position);
        return product.getId();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Product());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = productList.size() - 1;
        Product item = getItem(position);

        if (item != null) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(Product product) {
        this.productList.add(product);
        notifyItemInserted(productList.size() - 1);
    }

    private Product getItem(int position) {
        return productList.get(position);
    }

    public interface OnItemClickListener {
        void getProductItem(Product product);
    }
}

