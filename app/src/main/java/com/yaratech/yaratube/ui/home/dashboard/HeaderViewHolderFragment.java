package com.yaratech.yaratube.ui.home.dashboard;

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

/**
 * Created by Vah on 8/15/2018.
 */

public class HeaderViewHolderFragment extends Fragment {

    Headeritem headeritem;

    public HeaderViewHolderFragment() {
    }

    public static HeaderViewHolderFragment newInstance(Headeritem headeritem) {

        Bundle args = new Bundle();
        args.putParcelable("HEADER_ITEM", headeritem);
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
        ImageView headerImageView = view.findViewById(R.id.section_label);
        Glide.with(this).load(headeritem.getFeatureAvatar().getXxxdpi()).into(headerImageView);
    }
}
