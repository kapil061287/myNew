package com.depex.eatasmuch.user.screens;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.model.CartItem;
import com.depex.eatasmuch.user.model.FoodItem;
import com.depex.eatasmuch.user.utils.Utils;
import com.depex.eatasmuch.user.view.EatAsMuchRadioButton;
import com.google.gson.Gson;

import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodOrderCartActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.food_item_name)
    TextView foodItemName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    FoodItem foodItem;

    @BindView(R.id.food_item_add_btn)
    Button addCartBtn;

    @BindView(R.id.food_item_remove_btn)
    Button removeCartBtn;

    @BindView(R.id.food_item_image)
    ImageView foodItemImage;

    @BindView(R.id.cart_quantity)
    TextView cartQuantity;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.total_price)
    TextView totalPrice;

    @BindView(R.id.add_to_cart_btn)
    Button addToCartBtn;
    CartItem cartItem=new CartItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_cart);
        ButterKnife.bind(this);
        Bundle bundle=getIntent().getExtras();
        String json=bundle.getString("json");
        foodItem=new Gson().fromJson(json, FoodItem.class);
        cartItem.setFoodItem(foodItem);
        String foodCat=foodItem.getFoodCatName();
        foodCat=Utils.confirtStringProperFormat(foodCat);
        toolbar.setTitle(foodCat);
        setSupportActionBar(toolbar);
        String imageUrl=foodItem.getPhoto();
        GlideApp.with(this).load(imageUrl).into(foodItemImage);
        foodItemName.setText(foodItem.getItemName());
        addCartBtn.setOnClickListener(this);
        removeCartBtn.setOnClickListener(this);
        Map<String, String> priceMap=foodItem.getPriceMap();
        radioGroup.setOnCheckedChangeListener(this);
        addToCartBtn.setOnClickListener(this);
        int id=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton1=radioGroup.findViewById(id);
        if(radioButton1!=null) {
            String priceStr=radioButton1.getTag().toString();
            float price=Float.parseFloat(priceStr);
            cartItem.setPrice(price);
            initTotalPrice(cartQuantity.getText().toString(), String.valueOf(radioButton1.getTag()));
        }
        boolean isFirstChecked=false;
        for(String key : priceMap.keySet()){
            String value=priceMap.get(key);
            key=Utils.confirtStringProperFormat(key);
            String priceByCat=key+" - $"+value;
            EatAsMuchRadioButton radioButton=new EatAsMuchRadioButton(this);
            View view=radioButton.getView(radioGroup, priceByCat);
            view.setTag(value);
            radioGroup.addView(view);
            RadioButton radioButton2= (RadioButton) view;
            if(!isFirstChecked) {
                radioButton2.setChecked(true);
                isFirstChecked=true;
            }

        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }



    private void initTotalPrice(String i, String s) {

            float quantity = Integer.parseInt(i);
            float price = Float.parseFloat(s);

            float totalPrice = quantity * price;

            String totalPriceStr = "$" + totalPrice;
            this.totalPrice.setText(totalPriceStr);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.food_item_add_btn:
                increasCartQuantity();
                break;
            case R.id.food_item_remove_btn:
                decreaseCartQuantity();
                break;
            case R.id.add_to_cart_btn:
                String quanStr=cartQuantity.getText().toString();
                int id=radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=radioGroup.findViewById(id);
                String str=radioButton.getText().toString();
                String strArr[]=str.split(" - $");
                String size="";
                if(strArr.length>0)
                size=strArr[0];
                cartItem.setSize(size);
                int quanity=Integer.parseInt(quanStr);
                cartItem.setQuantity(quanity);
                Utils.shoppingCart.addItem(cartItem);
                Log.i("shoppingCartItems", "Cart Uses : "+Utils.shoppingCart);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    private void decreaseCartQuantity() {
        String quantity=cartQuantity.getText().toString();
        int quantityInt=Integer.parseInt(quantity);
        if(cartItem.getQuantity()>0){
            cartItem.quantityDecrement();
        }
        if(quantityInt>0) {
            quantityInt--;
        }
        cartQuantity.setText(String.valueOf(quantityInt));
        int id=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=radioGroup.findViewById(id);

        //cartItem.setQuantity(quantityInt);
        initTotalPrice(cartQuantity.getText().toString(), radioButton.getTag().toString());
    }

    private void increasCartQuantity() {
        String quantity=cartQuantity.getText().toString();
        int quantityInt=Integer.parseInt(quantity);

        quantityInt++;
        cartQuantity.setText(String.valueOf(quantityInt));
        int id=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=radioGroup.findViewById(id);
        //cartItem.setQuantity(quantityInt);
        initTotalPrice(cartQuantity.getText().toString(), radioButton.getTag().toString());
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton radioButton=radioGroup.findViewById(i);
        String priceStr=radioButton.getTag().toString();
        float price=Float.parseFloat(priceStr);
        cartItem.setPrice(price);
        initTotalPrice(cartQuantity.getText().toString(),priceStr);
    }
}