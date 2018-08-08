package com.yaratech.yaratube.ui.home;

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
import com.yaratech.yaratube.data.model.Store;

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

//    private GetData getData = new GetData(HomeFragment.this, HomeFragment.this);
    private StoreRecyclerViewAdapter storeRecyclerViewAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
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
        progressBar = view.findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);
        presenter = new HomePresenter(getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        storeRecyclerViewAdapter = new StoreRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(storeRecyclerViewAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.fetchHomeFromRemote();
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
}
