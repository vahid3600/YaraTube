package com.yaratech.yaratube.ui.productlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.Connects;
import com.yaratech.yaratube.ui.MenuActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends Fragment implements ProductListContract.View,
        ProductListRecyclerViewAdapter.OnItemClickListener {

    GridLayoutManager gridLayoutManager;
    CategoryList categoryList;
    private int offset = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    public static final String PRODUCT_LIST_FRAGMENT_TAG = "ProductList";
    public static final String KEY_ID = "category_id";
    private ProductListRecyclerViewAdapter productListRecyclerViewAdapter;
    ProductListPresenter productListPresenter;
    Connects.OnProductItemClick onProductItemClick;
    Unbinder unbind;
    @BindView(R.id.loading)
    ProgressBar progressBar;
    @BindView(R.id.product_list_recycleview)
    RecyclerView recyclerView;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(CategoryList categoryList) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_ID, categoryList);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbind = ButterKnife.bind(this, view);
        progressBar.setVisibility(View.GONE);
        categoryList = getArguments().getParcelable(KEY_ID);
        productListPresenter = new ProductListPresenter(getContext(), this);
        initRecycleView();
    }

    private void initRecycleView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 2,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (productListRecyclerViewAdapter.getItemViewType(position)) {
                    case ProductListRecyclerViewAdapter.loading:
                        return 2;
                    case ProductListRecyclerViewAdapter.item:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        productListRecyclerViewAdapter = new ProductListRecyclerViewAdapter(getContext()
                , this);
        productListRecyclerViewAdapter.setHasStableIds(true);
        recyclerView.setAdapter(productListRecyclerViewAdapter);
        recyclerView.addOnScrollListener(new PageScrollListener(gridLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                productListRecyclerViewAdapter.addLoadingFooter();
                productListPresenter.fetchNextProductListPageFromRemote(
                        categoryList.getId(),
                        offset);
            }

            @Override
            protected boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        productListPresenter.fetchProductListFromRemote(categoryList.getId(), offset);
    }

    @Override
    public void showListProducts(List<Product> productList) {
        isLoading = false;
        productListRecyclerViewAdapter.setData(productList);
        offset += productList.size();
    }

    @Override
    public void showNextListProducts(List<Product> productList) {
        productListRecyclerViewAdapter.removeLoadingFooter();
        isLoading = false;
        productListRecyclerViewAdapter.updateData(productList);
        offset += productList.size();
    }

    @Override
    public void showMessage(String msg) {
        if (isLoading)
            productListRecyclerViewAdapter.removeLoadingFooter();
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        isLoading = false;
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
    public void getProductItem(Product product) {
        onProductItemClick.onClick(product);
    }

    @Override
    public void onDestroyView() {
        unbind.unbind();
        productListPresenter.cancelProductListRequest();
        productListPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle(categoryList.getTitle());
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(R.string.app_name);
    }
}
