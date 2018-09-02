package com.yaratech.yaratube.ui.home.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yaratech.yaratube.R;
import com.bumptech.glide.Glide;
import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.Connects;
import com.yaratech.yaratube.ui.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vah on 8/15/2018.
 */

public class HeaderViewHolderFragment extends Fragment implements View.OnClickListener {

    Product headeritem;
    @BindView(R.id.section_label)
    ImageView headerImageView;
    Connects.OnProductItemClick onProductItemClick;

    public HeaderViewHolderFragment() {
    }

    public static HeaderViewHolderFragment newInstance(Product product) {

        Bundle args = new Bundle();
        args.putParcelable("HEADER_ITEM", product);
        HeaderViewHolderFragment fragment = new HeaderViewHolderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headeritem = getArguments().getParcelable("HEADER_ITEM");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(this).load(headeritem.getFeatureAvatar().getXxxdpi()).into(headerImageView);
        headerImageView.setScaleX(-1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MenuActivity)
            onProductItemClick = (Connects.OnProductItemClick) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onProductItemClick = null;
    }

    @Override
    public void onClick(View v) {
        onProductItemClick.onClick(headeritem);
    }
}

