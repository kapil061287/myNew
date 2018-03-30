package com.depex.eatasmuch.user.screens;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allenliu.badgeview.BadgeFactory;
import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.drawable.CountDrawable;
import com.depex.eatasmuch.user.fragment.MarchantListFragment;
import com.depex.eatasmuch.user.utils.UtilMethods;
import com.depex.eatasmuch.user.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    SharedPreferences preferences;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        preferences=getSharedPreferences(Utils.SITE_PREF, MODE_PRIVATE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int i=navigationView.getHeaderCount();
        if(i>0){
            View view=navigationView.getHeaderView(0);
            ImageView imageView=view.findViewById(R.id.nav_header_img);
            GlideApp.with(this).load("").placeholder(R.drawable.avatar_img).circleCrop().into(imageView);
            TextView navMyProfile=view.findViewById(R.id.nav_header_my_profile);
            navMyProfile.setOnClickListener(this);
        }

        Bundle bundle=getIntent().getExtras();


        MarchantListFragment fragment=new MarchantListFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_container, fragment).addToBackStack(null).commit();


    }

    @Override
    public void onBackPressed() {
        int fragmentCount=getSupportFragmentManager().getBackStackEntryCount();

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(fragmentCount==1){
                finish();
            }else {
                showAlertForFoodItemFragment();
            }
        }
    }

    private void showAlertForFoodItemFragment() {
        if(!preferences.getBoolean(Utils.IS_FOOD_ITEM_FRAGMENT_IN_FORGROUD, false)){
            super.onBackPressed();
        }else {
                showAlert();
        }
    }
    public void showAlert(){
        AlertDialog.Builder cartClearDialog=new AlertDialog.Builder(this);
        cartClearDialog.setTitle("Cart will be Cleared!");
        cartClearDialog.setMessage("There are item in your cart. if you proceed, your items will be cleared.");
        cartClearDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Utils.shoppingCart.clear();
                UtilMethods.setCount(NavigationActivity.this, String.valueOf(Utils.shoppingCart.getTotalCartItemCount()), toolbar.getMenu());
                NavigationActivity.super.onBackPressed();
            }
        });
        cartClearDialog.setNegativeButton("Cancel", null);

        if(Utils.shoppingCart.getTotalCartItemCount()>0){
            cartClearDialog.create().show();
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        UtilMethods.setCount(this, String.valueOf(Utils.shoppingCart.getShoppingCartItemCount()), menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.shopping_cart_menu:
                    openShoppingCart();
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void openShoppingCart() {
        Intent intent=new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    private void startLoginActivity() {
        Intent loginIntent=new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.signup_menu:
                startLoginActivity();
                break;
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nav_header_my_profile:
                    startMyProfileActivity();
                break;
        }
    }

    private void startMyProfileActivity() {
        Intent myProfileIntent=new Intent(this , UserProfileActivity.class);
        startActivity(myProfileIntent);
    }

}