package com.yaratech.yaratube.ui.category;

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
import com.yaratech.yaratube.data.model.Category_list;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContract.View,
        CategoryItemsRecyclerViewAdapter.ItemClickListener{

    CategoryPresenter categoryPresenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

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
        categoryPresenter = new CategoryPresenter(getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryItemsRecyclerViewAdapter = new CategoryItemsRecyclerViewAdapter(getContext(), this);
        recyclerView.setAdapter(categoryItemsRecyclerViewAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryPresenter.fetchCategoryFromRemote();
    }

    @Override
    public void showListCategory(List<Category_list> category_list) {
        categoryItemsRecyclerViewAdapter.setData(category_list);
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
    public void onItemClick(Category_list category_list) {
        ((CategoryFragment.OnCategoryFragmentActionListener) getContext()).onCategorylistItemClicked(category_list);
    }

    public interface OnCategoryFragmentActionListener{
        void onCategorylistItemClicked(Category_list category);
    }
}
