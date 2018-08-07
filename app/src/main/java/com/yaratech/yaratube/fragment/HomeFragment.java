package com.yaratech.yaratube.fragment;

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
import com.yaratech.yaratube.adapter.StoreRecyclerViewAdapter;
import com.yaratech.yaratube.dataModels.Store;
import com.yaratech.yaratube.presenter.GetData;
import com.yaratech.yaratube.view.FragmentsInterface;
import com.yaratech.yaratube.view.HomeFragmentsInterface;

public class HomeFragment extends Fragment implements FragmentsInterface, HomeFragmentsInterface {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private GetData getData = new GetData(HomeFragment.this, HomeFragment.this);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData.getStoreData(getContext());


    }

    @Override
    public void adaptRecyclerview(Store store) {
        storeRecyclerViewAdapter = new StoreRecyclerViewAdapter(getContext(), store);
        recyclerView.setAdapter(storeRecyclerViewAdapter);
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
