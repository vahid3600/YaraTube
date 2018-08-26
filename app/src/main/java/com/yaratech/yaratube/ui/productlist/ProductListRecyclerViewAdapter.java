package com.yaratech.yaratube.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vah on 8/4/2018.
 */

public class ProductListRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    private List<Product> productLists = new ArrayList<>();
    private Context context;

    // data is passed into the constructor
    public ProductListRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<Product> productLists) {
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
        holder.onBind(productLists.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return productLists.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.product_image)
        ImageView productAvatar;
        @BindView(R.id.product_title)
        TextView productTitle;
        @BindView(R.id.description_product)
        TextView productDescription;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void onBind(Product product) {
            Glide
                    .with(context)
                    .load(product.getFeatureAvatar().getXxxdpi())
                    .into(productAvatar);
            productTitle.setText(product.getName());
            productDescription.setText(product.getShortDescription());
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.getProductItem(productLists.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void getProductItem(Product product);
    }
}

