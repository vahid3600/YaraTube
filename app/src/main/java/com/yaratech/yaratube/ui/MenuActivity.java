package com.yaratech.yaratube.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.ui.dialog.enter_phone_number.EnterPhoneNumberDialog;
import com.yaratech.yaratube.ui.home.BaseFragment;
import com.yaratech.yaratube.ui.home.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.dashboard.StoreFragment;
import com.yaratech.yaratube.ui.dialog.login.LoginDialog;
import com.yaratech.yaratube.ui.product_detail.ProductDetailActivity;
import com.yaratech.yaratube.ui.product_list.ProductListFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CategoryFragment.OnCategoryFragmentActionListener,
        ProductListFragment.OnProductClickListener,
        StoreFragment.OnProductHeaderClickListener,
        StoreFragment.OnProductHomeClickListener,
        LoginDialog.DismissDialog,
        EnterPhoneNumberDialog.DismissDialog {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    public EnterPhoneNumberDialog enterPhoneNumberDialog = new EnterPhoneNumberDialog();
    public LoginDialog loginDialog = new LoginDialog();
    public List<String> addedFragmentsNames = new ArrayList<>();
    public FragmentManager fragmentManager;
    public BaseFragment baseFragment;
    public ProductListFragment productListFragment;
    public ProfileFragment profileFragment;
    public final String PROFILE_FRAGMENT_TAG = "ProfileFragment";
    public final String BASE_FRAGMENT_TAG = "BaseFragment";
    public final String PRODUCT_LIST_FRAGMENT_TAG = "ProductList";
    public static SharedPreferences USER_LOGIN;
    private FragmentTransaction fragmentTransaction;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        baseFragment = BaseFragment.newInstance();
        setFragment(baseFragment, BASE_FRAGMENT_TAG);
        USER_LOGIN = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            toolbar.getMenu().clear();
            toggle.setDrawerIndicatorEnabled(true);
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            profileFragment = ProfileFragment.newInstance();
            setFragment(profileFragment, PROFILE_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(PROFILE_FRAGMENT_TAG);
            toggle.setDrawerIndicatorEnabled(false);
            toolbar.inflateMenu(R.menu.menu_back_button);
        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_connect_with_us) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkPermissions(String permission) {

        int permissionRequest = ActivityCompat.checkSelfPermission(MenuActivity.this, permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onCategorylistItemClicked(CategoryList category) {
        productListFragment = ProductListFragment.newInstance(category.getId());
        setFragment(productListFragment, PRODUCT_LIST_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(PRODUCT_LIST_FRAGMENT_TAG);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.inflateMenu(R.menu.menu_back_button);
    }

    @Override
    public void onItemClicked(int productId) {
        boolean userLogin = USER_LOGIN.getBoolean("USER_LOGIN", false);

        if (userLogin) {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", productId);
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            loginDialog.show(fragmentManager, "login dialog");
        }
    }

    private void setFragment(Fragment fragment, String fragmentName) {
        if (!fragment.isVisible()) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();
            addedFragmentsNames.add(fragmentName);
            if (fragmentName != "Base")
                fragmentTransaction.addToBackStack(fragmentName);
        }
    }

    @Override
    public void dismissLoginDialog() {
        loginDialog.dismiss();
        enterPhoneNumberDialog.show(fragmentManager, "enter phone");
    }

    @Override
    public void dismissPhoneNumberDialog() {
        enterPhoneNumberDialog.dismiss();
        MenuActivity.USER_LOGIN.edit().putBoolean("USER_LOGIN", true).apply();
    }
}

