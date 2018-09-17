package com.yaratech.yaratube.ui.home.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.ui.Connects;
import com.yaratech.yaratube.ui.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreFragment extends Fragment implements StoreContract.View,
        HomeItemsRecyclerViewAdapter.OnHomeItemClickListener,
        View.OnClickListener {

    private StoreContract.Presenter presenter;
    public static boolean firstLoad = false;
    Connects.OnProductItemClick onProductItemClick;

    @BindView(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.reload)
    LinearLayout reload;
    @BindView(R.id.loading)
    ProgressBar progressBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    //    private GetData getData = new GetData(StoreFragment.this, StoreFragment.this);
    private StoreRecyclerViewAdapter storeRecyclerViewAdapter;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance() {

        Bundle args = new Bundle();

        StoreFragment fragment = new StoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.GONE);
        reload.setVisibility(View.GONE);
        presenter = new StorePresenter(getContext(), this);
        initRecycleview();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                firstLoad = true;
                presenter.fetchHomeFromRemote();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void initRecycleview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(linearLayoutManager);
        storeRecyclerViewAdapter = new StoreRecyclerViewAdapter(getContext(), getFragmentManager(),
                this);
        recyclerView.setAdapter(storeRecyclerViewAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.fetchHomeFromRemote();
        reload.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof MenuActivity)
            onProductItemClick = (Connects.OnProductItemClick) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        onProductItemClick = null;
        super.onDetach();
    }

    @Override
    public void showListHome(Store store) {
        reload.setVisibility(View.GONE);
        storeRecyclerViewAdapter.setData(store);
    }

    @Override
    public void showMessage(String msg) {
        reload.setVisibility(View.GONE);
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        if (!firstLoad)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showReload() {
        reload.setVisibility(View.VISIBLE);
    }

    @Override
    public void getHomeProductItem(Product product) {
        onProductItemClick.onClick(product);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reload) {
            presenter.fetchHomeFromRemote();
            reload.setVisibility(View.GONE);
        }
    }
}
