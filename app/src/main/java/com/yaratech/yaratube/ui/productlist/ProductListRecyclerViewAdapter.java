package com.yaratech.yaratube.ui.productlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
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
    private boolean isLoadingAdded = false;
    private static final int item = 0;
    private static final int loading = 1;
    private List<Product> productList = new ArrayList<>();
    private Context context;

    // data is passed into the constructor
    public ProductListRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void updateData(List<Product> productList) {
        List<Product> newProductList = new ArrayList<>();
        newProductList.addAll(this.productList);
        newProductList.addAll(productList);
        DiffUtil.DiffResult diffResult
                = DiffUtil.calculateDiff(new DiffUtilfCallBack(this.productList, newProductList));
        this.productList = newProductList;
        diffResult.dispatchUpdatesTo(this);
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case item:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case loading:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_progress, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case item:
                holder.onBind(productList.get(position));
                break;
            case loading:
                break;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return productList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView productAvatar;
        TextView productTitle;
        TextView productDescription;

        ViewHolder(View itemView) {
            super(itemView);
            productAvatar = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productDescription = itemView.findViewById(R.id.description_product);

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
            onItemClickListener.getProductItem(productList.get(getAdapterPosition()));
        }
    }


    class DiffUtilfCallBack extends DiffUtil.Callback {


        private List<Product> oldProducts = new ArrayList<>();
        private List<Product> newProducts = new ArrayList<>();

        DiffUtilfCallBack(List<Product> oldProducts, List<Product> newProducts) {
            this.oldProducts = oldProducts;
            this.newProducts = newProducts;
        }

        @Override
        public int getOldListSize() {
            return oldProducts.size();
        }

        @Override
        public int getNewListSize() {
            return newProducts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldProducts.get(oldItemPosition).getId()
                    == newProducts.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldProducts.get(oldItemPosition).equals(newProducts.get(newItemPosition));
        }

        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
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
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
    }

    public interface OnItemClickListener {
        void getProductItem(Product product);
    }
}

