package com.yaratech.yaratube.ui.home.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;

import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class HeaderItemsRecyclerViewAdapter extends RecyclerView.Adapter<HeaderItemsRecyclerViewAdapter.ViewHolder> {

    HeaderItemsRecyclerViewAdapter.OnHeaderItemClickListener onHeaderItemClickListener;
    private List<Headeritem> headeritems;
    private Context context;

    // data is passed into the constructor
    HeaderItemsRecyclerViewAdapter(
            Context context,
            List<Headeritem> headeritems,
            HeaderItemsRecyclerViewAdapter.OnHeaderItemClickListener onHeaderItemClickListener) {
        this.context = context;
        this.headeritems = headeritems;
        this.onHeaderItemClickListener = onHeaderItemClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String image_url = headeritems.get(position).getFeatureAvatar().getXxxdpi();
        Glide.with(context).load(image_url).into(holder.header_image);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return headeritems.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView header_image;

        ViewHolder(View itemView) {
            super(itemView);
            header_image = itemView.findViewById(R.id.image_header);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onHeaderItemClickListener.getHeaderProductItem(headeritems.get(getAdapterPosition()).getId());
        }
    }

    public interface OnHeaderItemClickListener {
        void getHeaderProductItem(int productId);
    }

}

