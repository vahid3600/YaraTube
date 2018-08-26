package com.yaratech.yaratube.ui.home.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.CategoryList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment implements CategoryContract.View,
        CategoryItemsRecyclerViewAdapter.ItemClickListener,
        View.OnClickListener {

    CategoryPresenter presenter;

    @BindView(R.id.reload)
    LinearLayout reload;
    @BindView(R.id.loading)
    ProgressBar progressBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

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
        return inflater.inflate(R.layout.fragment_category, container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.GONE);
        reload.setVisibility(View.GONE);
        presenter = new CategoryPresenter(getContext(), this);
        initRecycleview(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.fetchCategoryFromRemote();
        reload.setOnClickListener(this);
    }

    private void initRecycleview(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                view.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        categoryItemsRecyclerViewAdapter = new CategoryItemsRecyclerViewAdapter(
                this);
        recyclerView.setAdapter(categoryItemsRecyclerViewAdapter);
    }

    @Override
    public void showListCategory(List<CategoryList> category_list) {
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
    public void showReload() {
        reload.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(CategoryList category_list) {
        ((CategoryFragment.OnCategoryFragmentActionListener) getContext())
                .onCategorylistItemClicked(category_list);
    }

    @Override
    public void onDestroyView() {
        Log.e("dishab", " na parishab");
        presenter.cancelCategoryRequest();
        super.onDestroyView();
    }

    public interface OnCategoryFragmentActionListener {
        void onCategorylistItemClicked(CategoryList category);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reload) {
            presenter.fetchCategoryFromRemote();
            reload.setVisibility(View.INVISIBLE);
        }
    }
}
