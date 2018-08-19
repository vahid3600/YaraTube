package com.yaratech.yaratube.ui.home.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.ui.MenuActivity;
import com.yaratech.yaratube.ui.OnProductItemClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreFragment extends Fragment implements StoreContract.View,
        HomeItemsRecyclerViewAdapter.OnHomeItemClickListener{

    private StoreContract.Presenter presenter;
    OnProductItemClick onProductItemClick;

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
        presenter = new StorePresenter(getContext(), this);
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
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof MenuActivity)
            onProductItemClick = (OnProductItemClick) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        onProductItemClick = null;
        super.onDetach();
    }

    @Override
    public void showListHome(Store store) {
        storeRecyclerViewAdapter.setData(store);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getHomeProductItem(Product product) {
        onProductItemClick.onClick(product);
    }
}
