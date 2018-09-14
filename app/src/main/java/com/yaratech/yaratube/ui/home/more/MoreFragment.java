package com.yaratech.yaratube.ui.home.more;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.profile.ProfileFragment;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yaratech.yaratube.ui.profile.ProfileFragment.PROFILE_FRAGMENT_TAG;

public class MoreFragment extends Fragment {
    public static String MoreFragmentTag = "more_fragment";
    private onActionClickListener listener;
    @BindView(R.id.list_item)
    ListView listView;

    String[] strings = {"پروفایل", "درباره ما", "تماس با ما"};

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onActionClickListener)
            listener = (onActionClickListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return strings.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @SuppressLint("ViewHolder")
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.category_item, parent, false);
                TextView textView = view.findViewById(R.id.category_title);
                textView.setText(strings[position]);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                listener.onFragmentClickListener(getActivity().getSupportFragmentManager(), ProfileFragment.newInstance(), PROFILE_FRAGMENT_TAG);
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                });
                return view;
            }
        });
    }

    public interface onActionClickListener {

        void onFragmentClickListener(FragmentManager fragmentManager, Fragment fragment, String tag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }
}
