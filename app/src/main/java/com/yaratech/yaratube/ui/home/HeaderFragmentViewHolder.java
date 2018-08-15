package com.yaratech.yaratube.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yaratech.yaratube.R;
import com.bumptech.glide.Glide;
import com.yaratech.yaratube.utils.GlideApp;
import com.yaratech.yaratube.utils.MyGlideApp.*;
/**
 * Created by Vah on 8/15/2018.
 */

public class HeaderFragmentViewHolder extends Fragment {

    public HeaderFragmentViewHolder() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HeaderFragmentViewHolder newInstance(String imageUrl) {

        Bundle args = new Bundle();
        args.putString("HEADER_ITEM", imageUrl);
        HeaderFragmentViewHolder fragment = new HeaderFragmentViewHolder();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_header, container, false);
        ImageView headerImageView = rootView.findViewById(R.id.section_label);
        GlideApp.with(getContext()).load(getArguments().getString("HEADER_ITEM")).into(headerImageView);
        return rootView;
    }
}
