package com.yaratech.yaratube.fragment;

import android.content.Context;
import android.net.Uri;
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
import com.yaratech.yaratube.adapter.CategoryItemsRecyclerViewAdapter;
import com.yaratech.yaratube.adapter.StoreRecyclerViewAdapter;
import com.yaratech.yaratube.dataModels.Category_list;
import com.yaratech.yaratube.presenter.GetData;
import com.yaratech.yaratube.view.CategoryFragmentsInterface;
import com.yaratech.yaratube.view.FragmentsInterface;

import java.util.List;

public class CategoryFragment extends Fragment implements FragmentsInterface, CategoryFragmentsInterface {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private GetData getData = new GetData(CategoryFragment.this, CategoryFragment.this);
    private CategoryItemsRecyclerViewAdapter categoryItemsRecyclerViewAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
      
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData.getCategoryListData(getContext());


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
    public void adaptRecyclerview(List<Category_list> category_list) {
        categoryItemsRecyclerViewAdapter = new CategoryItemsRecyclerViewAdapter(getContext(), category_list);
        recyclerView.setAdapter(categoryItemsRecyclerViewAdapter);
    }
}
