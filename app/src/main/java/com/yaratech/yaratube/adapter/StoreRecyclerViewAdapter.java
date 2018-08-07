package com.yaratech.yaratube.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.dataModels.Headeritem;
import com.yaratech.yaratube.dataModels.Homeitem;
import com.yaratech.yaratube.dataModels.Product;
import com.yaratech.yaratube.dataModels.Store;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.ViewHolder> {

    private Store store;
    List<Headeritem> headeritems = new ArrayList<>();
    List<Homeitem> homeitems = new ArrayList<>();
    private Context context;
    private static final int HEADER_LIST_ITEM_VIEW = 1;
    private static final int HOME_ITEM_LIST_ITEM_VIEW = 2;

    // data is passed into the constructor
    public StoreRecyclerViewAdapter(Context context, Store store) {
        this.context = context;
        this.store = store;
        headeritems = store.getHeaderitem();
        homeitems = store.getHomeitem();
        Log.e("mag", headeritems.size() + "");
        Log.e("mag", homeitems.size() + "");
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        //list items of header list
        RecyclerView header_recyclerView;
        //list items of footer list
        RecyclerView home_recyclerView;
        TextView title_name;

        ViewHolder(View itemView) {
            super(itemView);
            header_recyclerView = itemView.findViewById(R.id.header_item_recycler);
            home_recyclerView = itemView.findViewById(R.id.home_item_recycler);
            title_name = itemView.findViewById(R.id.items_name);
        }

        public void bindViewHeaderList(int pos) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, HORIZONTAL, false);
            header_recyclerView.setLayoutManager(linearLayoutManager);
            HeaderItemsRecyclerViewAdapter headerItemsRecyclerViewAdapter = new HeaderItemsRecyclerViewAdapter(context, headeritems);
            header_recyclerView.setAdapter(headerItemsRecyclerViewAdapter);
        }

        public void bindViewHomeList(int pos, Homeitem homeitem) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, HORIZONTAL, false);
            home_recyclerView.setLayoutManager(linearLayoutManager);
            HomeItemsRecyclerViewAdapter homeItemsRecyclerViewAdapter = new HomeItemsRecyclerViewAdapter(context, homeitem.getProducts());
            home_recyclerView.setAdapter(homeItemsRecyclerViewAdapter);
            title_name.setText(homeitem.getTitle());
        }

    }

    private class HeaderListItemViewHolder extends ViewHolder {
        public HeaderListItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class HomeListItemViewHolder extends ViewHolder {
        public HomeListItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        switch (viewType) {
            case HEADER_LIST_ITEM_VIEW:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item_row, parent, false);
                return new HeaderListItemViewHolder(v);

            case HOME_ITEM_LIST_ITEM_VIEW:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row, parent, false);
                return new HomeListItemViewHolder(v);

            default:
                return null;
        }
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            if (holder instanceof HeaderListItemViewHolder) {
                HeaderListItemViewHolder vh = (HeaderListItemViewHolder) holder;
                vh.bindViewHeaderList(position);
            } else if (holder instanceof HomeListItemViewHolder) {
                HomeListItemViewHolder vh = (HomeListItemViewHolder) holder;
                vh.bindViewHomeList(position, homeitems.get(position-1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        Log.e("size", 1+homeitems.size()+"");
        return 1+homeitems.size();

    }

    @Override
    public int getItemViewType(int position) {
        Log.e("tagine", position + "");
        Log.e("tag", homeitems.size() + "");

        switch (position){
            case 0:
                return HEADER_LIST_ITEM_VIEW;

            case 1:
                return HOME_ITEM_LIST_ITEM_VIEW;

            default:
                return HOME_ITEM_LIST_ITEM_VIEW;
        }

    }

}

