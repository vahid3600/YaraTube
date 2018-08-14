package com.yaratech.yaratube.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import com.yaratech.yaratube.data.model.Category_list;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.sourse.product_detail.ProductDetailFragment;
import com.yaratech.yaratube.ui.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.HomeFragment;
import com.yaratech.yaratube.ui.image_picker.ImagePickerDialog;
import com.yaratech.yaratube.ui.login.LoginDialog;
import com.yaratech.yaratube.ui.product_detail.ProductDetailActivity;
import com.yaratech.yaratube.ui.product_list.ProductListFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CategoryFragment.OnCategoryFragmentActionListener,
        ProductListFragment.OnProductClickListener,
        HomeFragment.OnProductHeaderClickListener,
        HomeFragment.OnProductHomeClickListener {

    Toolbar toolbar;
    public static SharedPreferences USER_LOGIN;
    FragmentTransaction fragmentTransaction;
    ActionBarDrawerToggle toggle;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setFragment(BaseFragment.newInstance());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        USER_LOGIN = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
        fragmentTransaction.addToBackStack("fragment");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            setFragment(ProfileFragment.newInstance());
            fragmentTransaction.addToBackStack("profile");
            toggle.setDrawerIndicatorEnabled(false);
            toolbar.inflateMenu(R.menu.menu_back_button);
        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_connect_with_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkPermissions(String permission){

        int permissionRequest = ActivityCompat.checkSelfPermission(MenuActivity.this, permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void onCategorylistItemClicked(Category_list category) {
        setFragment(ProductListFragment.newInstance(category.getId()));
        fragmentTransaction.addToBackStack("product_list");
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.inflateMenu(R.menu.menu_back_button);
    }

    @Override
    public void onItemClicked(int productId) {
        boolean userLogin = USER_LOGIN.getBoolean("USER_LOGIN", false);
        Log.e("tag", productId + " " + userLogin);
        if (userLogin) {
            Intent intent = new Intent(MenuActivity.this, ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", productId);
            startActivity(intent);
        } else {
            LoginDialog loginDialog = new LoginDialog();
            FragmentManager fragmentManager = getSupportFragmentManager();
            loginDialog.show(fragmentManager, "login dialog");
        }
    }
}
