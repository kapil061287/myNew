package com.depex.eatasmuch.user.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.api.ProjectApi;
import com.depex.eatasmuch.user.drawable.CountDrawable;
import com.depex.eatasmuch.user.factory.StringConvertFactory;
import com.depex.eatasmuch.user.listener.FoodItemClickListener;
import com.depex.eatasmuch.user.model.FoodCategory;
import com.depex.eatasmuch.user.model.FoodItem;
import com.depex.eatasmuch.user.model.Marchant;
import com.depex.eatasmuch.user.screens.FoodOrderCartActivity;
import com.depex.eatasmuch.user.utils.Utils;
import com.depex.eatasmuch.user.view.FoodCategoryTextView;
import com.depex.eatasmuch.user.view.FoodItemRecyclerView;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodItemFragment extends Fragment implements FoodItemClickListener{

    Marchant marchant;

    @BindView(R.id.logo_food_item)
    ImageView logoFoodItem;

    @BindView(R.id.marchant_name_food_item)
    TextView marchantName;

    @BindView(R.id.parent_constraint_layout)
    ConstraintLayout parentConstraintLayout;

    @BindView(R.id.cuisine_name_food_item)
    TextView cuisineNames;

    @BindView(R.id.address_food_item)
    TextView addressFoodItem;

    @BindView(R.id.rating_bar_food_item)
    RatingBar ratingBar;
    private Context context;

    @BindView(R.id.parent_for_recycler_view)
    LinearLayout parentForLinearLayout;

    @BindView(R.id.avl_loading_food_item)
    AVLoadingIndicatorView avLoadingIndicatorView;

    @BindView(R.id.overlay_image_food_item)
    ImageView overlayImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_food_item_fragment, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle=getArguments();
        String json=bundle.getString("json");
        Log.i("responseData", "Json From Food Item Fragment : "+json);
        Gson gson=new Gson();
        marchant=gson.fromJson(json, Marchant.class);
        GlideApp.with(context).load(marchant.getImageUrl()).into(logoFoodItem);
        marchantName.setText(marchant.getRestaurantName());
        cuisineNames.setText(marchant.getCuisine());
        addressFoodItem.setText(marchant.getStreet());
        ratingBar.setRating(marchant.getRating());
        Toolbar toolbar= getActivity().getWindow().getDecorView().findViewById(R.id.toolbar);
        Menu menu=toolbar.getMenu();

        initFoodItems(marchant.getMarchantId());
        return  view;
    }



    private void initFoodItems(final String marchantId) {

        JSONObject requestData=new JSONObject();
        JSONObject data=new JSONObject();

        try {
            data.put("v_code", getString(R.string.v_code));
            data.put("apikey", getString(R.string.apikey));
            data.put("merchant_id", marchantId);
            requestData.put("RequestData", data);
            Log.i("requestData","Food Item Data request : "+ requestData.toString());
        } catch (JSONException e) {
            Log.e("responseDataError", "Get Food Items : "+e.toString());
        }


        new Retrofit.Builder()
                .baseUrl(Utils.SITE_URL)
                .addConverterFactory(new StringConvertFactory())
                .build()
                .create(ProjectApi.class)
                .getFoodItems(requestData.toString())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String responseString=response.body();
                        Log.i("responseData" , "Food Item Data : " +responseString);



                        try {
                            JSONObject res=new JSONObject(responseString);
                            boolean success=res.getBoolean("success");
                            if(success){
                                avLoadingIndicatorView.hide();
                                overlayImage.setVisibility(View.GONE);
                                JSONObject resObj=res.getJSONObject("response");
                                updateUi(resObj);
                            }else {
                                avLoadingIndicatorView.hide();
                                overlayImage.setVisibility(View.GONE);
                                JSONObject errorObj=res.getJSONObject("ErrorObj");
                                String code=errorObj.getString("ErrorCode");
                                String msg=errorObj.getString("ErrorMsg");
                                Snackbar.make(parentConstraintLayout, "Item Not found in this restaurant", Snackbar.LENGTH_LONG)
                                        .setAction("OK", null)
                                        .show();
                            }
                        } catch (JSONException e) {
                            avLoadingIndicatorView.hide();
                            overlayImage.setVisibility(View.GONE);
                            Log.e("responseDataError", "Food Item Fragment : "+e.toString());
                        }

                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        initFoodItems(marchantId);
                        Log.i("responseDataError","Food Item data Error : "+t.toString());
                    }
                });
    }

    private void updateUi(JSONObject resObj) throws JSONException {
        JSONArray arr=resObj.getJSONArray("list");
        Log.i("responseData", "Food Category Arr : "+arr.toString());
        Gson gson=new Gson();
        FoodCategory[]catArr=gson.fromJson(arr.toString(), FoodCategory[].class);
        for(FoodCategory foodCategory:catArr ){
            foodCategory.parsePrice();
            String catName=foodCategory.getCategoryName();
            FoodCategoryTextView foodCategoryTextView=new FoodCategoryTextView(context, R.layout.content_food_category_text_view);
            View view3=foodCategoryTextView.getView(parentForLinearLayout, catName);
            List<FoodItem> list=foodCategory.getItems();
            FoodItemRecyclerView view=new FoodItemRecyclerView(context,  R.layout.content_food_item_custom_recyclerview, this);
            View view1=view.getView(parentForLinearLayout,list);
            parentForLinearLayout.addView(view3);
            parentForLinearLayout.addView(view1);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onFoodItemClick(FoodItem foodItem) {
            Log.i("responseData", "OnFoodItemClick Listener : "+foodItem);
            Gson gson=new Gson();
            String json=gson.toJson(foodItem);
            Bundle bundle=new Bundle();
            bundle.putString("json", json);
            startFoodCardActivity(bundle);
    }

    private void startFoodCardActivity(Bundle bundle) {
        Intent intent=new Intent(context, FoodOrderCartActivity.class);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}