package com.yaratech.yaratube.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.home.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.dashboard.StoreFragment;
import com.yaratech.yaratube.ui.home.more.MoreFragment;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.yaratech.yaratube.ui.home.more.MoreFragment.MoreFragmentTag;

public class HomeFragment extends Fragment {

    public static final String BASE_FRAGMENT_TAG = "HomeFragment";
    Unbinder unbind;
    private StoreFragment homeFragment;
    private CategoryFragment categoryFragment;
    private MoreFragment moreFragment;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbind = ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHomeFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                item.setChecked(true);
                                setHomeFragment();
                                break;
                            case R.id.navigation_category:
                                item.setChecked(true);
                                setCategoryFragment();
                                break;
                            case R.id.navigation_more:
                                item.setChecked(true);
                                setMoreFragment();
                        }
                        return false;
                    }
                });
    }

    private void setHomeFragment() {
        if (moreFragment != null && moreFragment.isVisible())
            Utils.removeFragment(getChildFragmentManager(),MoreFragmentTag);

        if (homeFragment == null) {
            if (categoryFragment != null && categoryFragment.isVisible()) {
                getChildFragmentManager()
                        .beginTransaction()
                        .hide(categoryFragment)
                        .commit();
            }

            homeFragment = StoreFragment.newInstance();
            Utils.addFragment(
                    R.id.frameLayout,
                    getChildFragmentManager(),
                    homeFragment,
                    null,
                    false);

        } else if (!homeFragment.isVisible()) {
            getChildFragmentManager()
                    .beginTransaction()
                    .hide(categoryFragment)
                    .show(homeFragment)
                    .commit();
        }
    }

    private void setCategoryFragment() {
        if (moreFragment != null && moreFragment.isVisible())
            Utils.removeFragment(getChildFragmentManager(),MoreFragmentTag);

        if (categoryFragment == null) {
            if (homeFragment != null && homeFragment.isVisible()) {
                getChildFragmentManager()
                        .beginTransaction()
                        .hide(homeFragment)
                        .commit();
            }
            categoryFragment = CategoryFragment.newInstance();
            Utils.addFragment(
                    R.id.frameLayout,
                    getChildFragmentManager(),
                    categoryFragment,
                    null,
                    false);
        } else if (!categoryFragment.isVisible()) {
            getChildFragmentManager().beginTransaction()
                    .hide(homeFragment)
                    .show(categoryFragment)
                    .commit();
        }
    }

    private void setMoreFragment() {

        if (categoryFragment != null && categoryFragment.isVisible()) {
            getChildFragmentManager()
                    .beginTransaction()
                    .hide(categoryFragment)
                    .commit();
        }

        if (homeFragment != null && homeFragment.isVisible()) {
            getChildFragmentManager()
                    .beginTransaction()
                    .hide(homeFragment)
                    .commit();
        }

        if (moreFragment == null) {
            moreFragment = MoreFragment.newInstance();
        }
        if (!moreFragment.isVisible()){
            Utils.addFragment(
                    R.id.frameLayout,
                    getChildFragmentManager(),
                    moreFragment,
                    MoreFragmentTag,
                    false
            );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }
}
