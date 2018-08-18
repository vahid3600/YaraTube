package com.yaratech.yaratube.ui.home.dashboard;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Homeitem;
import com.yaratech.yaratube.data.model.Store;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.LinearLayoutManager.*;

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    FragmentManager fragmentManager;
    HeaderItemAdapter headerItemAdapter;
    private Store store;
    List<Headeritem> headeritems = new ArrayList<>();
    List<Homeitem> homeitems = new ArrayList<>();
    HomeItemsRecyclerViewAdapter.OnHomeItemClickListener onHomeItemClickListener;
    HeaderItemsRecyclerViewAdapter.OnHeaderItemClickListener onHeaderItemClickListener;
    private static final int HEADER_LIST_ITEM_VIEW = 1;
    private static final int HOME_ITEM_LIST_ITEM_VIEW = 2;

    public StoreRecyclerViewAdapter(
            Context context,
            FragmentManager fragmentManager,
            HomeItemsRecyclerViewAdapter.OnHomeItemClickListener onHomeItemClickListener,
            HeaderItemsRecyclerViewAdapter.OnHeaderItemClickListener onHeaderItemClickListener) {
        this.onHomeItemClickListener = onHomeItemClickListener;
        this.onHeaderItemClickListener = onHeaderItemClickListener;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public void setData(Store store) {
        headeritems = store.getHeaderitem();
        homeitems = store.getHomeitem();
        notifyDataSetChanged();
    }

    public class HeaderListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header_item_viewpager)
        ViewPager headerViewPager;

        HeaderListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindViewHeaderList() {

            headerItemAdapter = new HeaderItemAdapter(fragmentManager);
            headerViewPager.setAdapter(headerItemAdapter);
            headerItemAdapter.setData(headeritems);
        }
    }

    public class HomeListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_item_recycler)
        RecyclerView homeRecyclerView;
        @BindView(R.id.items_name)
        TextView titleName;

        HomeListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindViewHomeList(Homeitem homeitem) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, HORIZONTAL,
                    false);
            homeRecyclerView.setLayoutManager(linearLayoutManager);
            HomeItemsRecyclerViewAdapter homeItemsRecyclerViewAdapter =
                    new HomeItemsRecyclerViewAdapter(context, homeitem.getProducts(),
                            onHomeItemClickListener);
            homeRecyclerView.setAdapter(homeItemsRecyclerViewAdapter);
            titleName.setText(homeitem.getTitle());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        switch (viewType) {
            case HEADER_LIST_ITEM_VIEW:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item_row,
                        parent, false);
                return new HeaderListItemViewHolder(v);

            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_row, parent,
                        false);
                return new HomeListItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (holder instanceof HeaderListItemViewHolder) {
                HeaderListItemViewHolder viewHolder = (HeaderListItemViewHolder) holder;
                viewHolder.bindViewHeaderList();
            } else if (holder instanceof HomeListItemViewHolder) {
                HomeListItemViewHolder viewHolder = (HomeListItemViewHolder) holder;
                viewHolder.bindViewHomeList(homeitems.get(position - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 1 + homeitems.size();
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return HEADER_LIST_ITEM_VIEW;

            default:
                return HOME_ITEM_LIST_ITEM_VIEW;
        }

    }

}

