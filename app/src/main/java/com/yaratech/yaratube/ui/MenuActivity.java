package com.yaratech.yaratube.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.dialog.loginphone.EnterPhoneNumberDialog;
import com.yaratech.yaratube.ui.home.HomeFragment;
import com.yaratech.yaratube.ui.home.category.CategoryFragment;
import com.yaratech.yaratube.ui.dialog.login.LoginDialog;
import com.yaratech.yaratube.ui.productdetail.ProductDetailFragment;
import com.yaratech.yaratube.ui.productlist.ProductListFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;
import com.yaratech.yaratube.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yaratech.yaratube.ui.home.HomeFragment.BASE_FRAGMENT_TAG;
import static com.yaratech.yaratube.ui.productlist.ProductListFragment.PRODUCT_LIST_FRAGMENT_TAG;
import static com.yaratech.yaratube.ui.profile.ProfileFragment.PROFILE_FRAGMENT_TAG;
import static com.yaratech.yaratube.utils.Utils.LOGIN_KEY;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CategoryFragment.OnCategoryFragmentActionListener,
        OnProductItemClick,
        LoginDialog.DismissDialog,
        EnterPhoneNumberDialog.DismissDialog {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    public EnterPhoneNumberDialog enterPhoneNumberDialog =
            new EnterPhoneNumberDialog();
    public LoginDialog loginDialog = new LoginDialog();
    public FragmentManager fragmentManager;
    public HomeFragment homeFragment;
    public ProductListFragment productListFragment;
    public ProfileFragment profileFragment;
    public static SharedPreferences USER_LOGIN;
    private FragmentTransaction fragmentTransaction;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ButterKnife.bind(this);
        initActivity();
        homeFragment = HomeFragment.newInstance();
        Utils.setFragment(
                R.id.fragment_container,
                getSupportFragmentManager(),
                homeFragment,
                BASE_FRAGMENT_TAG,
                false);
        USER_LOGIN = getSharedPreferences(LOGIN_KEY, MODE_PRIVATE);
    }

    private void initActivity() {
        setSupportActionBar(toolbar);
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
            Utils.setFragment(
                    R.id.fragment_container,
                    getSupportFragmentManager(),
                    profileFragment,
                    PROFILE_FRAGMENT_TAG,
                    true);
            toggle.setDrawerIndicatorEnabled(false);
            toolbar.inflateMenu(R.menu.menu_back_button);
        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_connect_with_us) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

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
        productListFragment = ProductListFragment.newInstance(category.getId());
        Utils.setFragment(
                R.id.fragment_container,
                getSupportFragmentManager(),
                productListFragment,
                PRODUCT_LIST_FRAGMENT_TAG,
                true);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.inflateMenu(R.menu.menu_back_button);
    }

    @Override
    public void dismissLoginDialog() {
        loginDialog.dismiss();
        enterPhoneNumberDialog
                .show(fragmentManager, EnterPhoneNumberDialog.ENTER_PHONE_DIALOG_TAG);
    }

    @Override
    public void dismissPhoneNumberDialog() {
        enterPhoneNumberDialog.dismiss();
        MenuActivity.USER_LOGIN.edit().putBoolean(LOGIN_KEY, true).apply();
    }

    @Override
    public void onClick(Object product) {
        boolean userLogin = USER_LOGIN.getBoolean(LOGIN_KEY, false);
        if (userLogin) {
            Utils.setFragment(
                    R.id.fragment_container,
                    getSupportFragmentManager(),
                    ProductDetailFragment.newInstance((Product) product),
                    BASE_FRAGMENT_TAG,
                    true);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            loginDialog.show(fragmentManager, LoginDialog.LOGIN_DIALOG_TAG);
        }
    }
}

