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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vah on 8/4/2018.
 */

public class HeaderItemsRecyclerViewAdapter extends RecyclerView.Adapter<HeaderItemsRecyclerViewAdapter.ViewHolder> {

    private HeaderItemsRecyclerViewAdapter.OnHeaderItemClickListener onHeaderItemClickListener;
    private List<Headeritem> headerItems;
    private Context context;

    HeaderItemsRecyclerViewAdapter(
            Context context,
            List<Headeritem> headerItems,
            HeaderItemsRecyclerViewAdapter.OnHeaderItemClickListener onHeaderItemClickListener) {

        this.context = context;
        this.headerItems = headerItems;
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
        holder.onBind(headerItems.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return headerItems.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_header)
                ImageView header_image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void onBind(Headeritem headeritem) {
            String image_url = headeritem.getFeatureAvatar().getXxxdpi();
            Glide.with(context).load(image_url).into(header_image);
        }

        @Override
        public void onClick(View v) {
            onHeaderItemClickListener.getHeaderProductItem(headerItems.get(getAdapterPosition()).getId());
        }
    }

    public interface OnHeaderItemClickListener {
        void getHeaderProductItem(int productId);
    }

}

