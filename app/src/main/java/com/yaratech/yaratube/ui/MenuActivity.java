package com.yaratech.yaratube.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.home.HomeFragment;
import com.yaratech.yaratube.ui.home.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.more.MoreFragment;
import com.yaratech.yaratube.ui.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.ui.productlist.ProductListFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;
import com.yaratech.yaratube.utils.Utils;

import butterknife.ButterKnife;

import static com.yaratech.yaratube.ui.home.HomeFragment.BASE_FRAGMENT_TAG;
import static com.yaratech.yaratube.ui.productlist.ProductListFragment.PRODUCT_LIST_FRAGMENT_TAG;

public class MenuActivity extends AppCompatActivity
        implements
        CategoryFragment.OnCategoryFragmentActionListener,
        Connects.OnProductItemClick,
        MoreFragment.onActionClickListener{

    public HomeFragment homeFragment;
    public ProductListFragment productListFragment;
    private MenuActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ButterKnife.bind(this);
        initActivity();
        homeFragment = HomeFragment.newInstance();
        presenter = new MenuActivityPresenter(getApplicationContext());
        Utils.addFragment(
                R.id.fragment_container,
                getSupportFragmentManager(),
                homeFragment,
                BASE_FRAGMENT_TAG,
                false);
    }

    private void initActivity() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_profile) {
//            profileFragment = ProfileFragment.newInstance();
//            Utils.addFragment(
//                    R.id.fragment_container,
//                    getSupportFragmentManager(),
//                    profileFragment,
//                    PROFILE_FRAGMENT_TAG,
//                    true);

//            boolean userLogin = presenter.getUserLoginStatus();
//            if (userLogin) {
//                startActivity(new Intent(MenuActivity.this, ProfileFragment.class));
//            } else {
//                LoginDialogContainer.newInstance(getSupportFragmentManager());
//            }
//        } else if (id == R.id.nav_about_us) {
//
//        } else if (id == R.id.nav_connect_with_us) {
//
//        } else if (id == R.id.nav_sign_out){
//            presenter.signOut();
//        }
//        return true;
//    }

    public boolean checkPermissions(String permission) {

        int permissionRequest = ActivityCompat.checkSelfPermission(
                MenuActivity.this,
                permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onCategorylistItemClicked(CategoryList category) {
        productListFragment = ProductListFragment.newInstance(
                category.getId());
        Utils.addFragment(
                R.id.fragment_container,
                getSupportFragmentManager(),
                productListFragment,
                PRODUCT_LIST_FRAGMENT_TAG,
                true);
    }

    @Override
    public void onClick(Product product) {

        Utils.addFragment(
                R.id.fragment_container,
                getSupportFragmentManager(),
                ProductDetailFragment.newInstance(product),
                BASE_FRAGMENT_TAG,
                true);
    }

    @Override
    public void onFragmentClickListener(Fragment fragment) {
        Utils.replaceFragment(
                R.id.fragment_container,
                getSupportFragmentManager(),
                fragment,
                "",
                false);
    }
}


