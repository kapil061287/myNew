package com.depex.eatasmuch.user.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.adapter.ShoppingCartAdapter;
import com.depex.eatasmuch.user.listener.UpdatesubtotalListener;
import com.depex.eatasmuch.user.model.CartItem;
import com.depex.eatasmuch.user.model.Marchant;
import com.depex.eatasmuch.user.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener, UpdatesubtotalListener {

    @BindView(R.id.show_cart_recycler)
    RecyclerView showCartRecycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.logo_food_item)
    ImageView imageView;
    @BindView(R.id.marchant_name_food_item)
    TextView marchantName;

    @BindView(R.id.cuisine_name_food_item)
    TextView cuisineName;
    @BindView(R.id.address_food_item)
    TextView addressFoodItem;

    @BindView(R.id.rating_bar_food_item)
    RatingBar ratingBar;
    @BindView(R.id.place_order)
    Button placeOrder;
    @BindView(R.id.you_pay_text)
    TextView youPayText;
    @BindView(R.id.add_more_btn)
    Button moreBtn;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);
        preferences=getSharedPreferences(Utils.SITE_PREF, MODE_PRIVATE);
        toolbar.setTitle("Shopping Cart");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartActivity.super.onBackPressed();
            }
        });
        placeOrder.setOnClickListener(this);
        placeOrder.setText("Place Order($"+Utils.shoppingCart.getTotalPrice()+")");
        youPayText.setText("$"+Utils.shoppingCart.getTotalPrice());
        moreBtn.setOnClickListener(this);
        initShowCart();
    }

    private void initShowCart() {
        int size=Utils.shoppingCart.getItems().size();
        Marchant marchant= Utils.shoppingCart.getMarchant();
        if(marchant!=null){
            GlideApp.with(this).load(marchant.getImageUrl()).into(imageView);
            marchantName.setText(marchant.getRestaurantName());
            cuisineName.setText(marchant.getCuisine());
            addressFoodItem.setText(marchant.getStreet());
            ratingBar.setRating(marchant.getRating());
        }
        List<CartItem> cartItems=Utils.shoppingCart.getItems();
        ShoppingCartAdapter adapter=new ShoppingCartAdapter(this, cartItems, this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(this, manager.getOrientation());
        showCartRecycler.setAdapter(adapter);
        showCartRecycler.addItemDecoration(itemDecoration);
        showCartRecycler.setLayoutManager(manager);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.place_order:
                    startProceedToCheckout();
                break;
            case R.id.add_more_btn:
                finish();
                break;
        }
    }

    private void startProceedToCheckout() {
        if(!preferences.getBoolean("isLogin", false)){
            startLoginProcess();
        }
    }

    private void startLoginProcess() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateUi() {
        youPayText.setText("$"+Utils.shoppingCart.getTotalPrice());
    }
}
