package com.depex.eatasmuch.user.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.drawable.CountDrawable;
import com.depex.eatasmuch.user.fragment.FoodItemFragment;
import com.depex.eatasmuch.user.model.Marchant;
import com.depex.eatasmuch.user.receivers.NetworkReceiver;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class UtilMethods {

    public static void registerNetworkReceiver(NetworkReceiver receiver, Context context) {

        IntentFilter filter=new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(receiver, filter);
    }

    public static void unregisterNetworkReceiver(Context context, NetworkReceiver receiver){
            context.unregisterReceiver(receiver);
    }

    public static List<Marchant> getListForPickup(List<Marchant> marchants) {
            List<Marchant> list=new ArrayList<>();
            for(int i=0;i<marchants.size();i++){
                Marchant marchant=marchants.get(i);
                marchant.setMarchantPickupDeliveryFacility();
                if(marchant.isPickupAvailable()){
                    list.add(marchant);
                }
            }
            return list;
    }


    public static List<Marchant> getListForDelivery(List<Marchant> marchants) {
        List<Marchant> list=new ArrayList<>();
        for(int i=0;i<marchants.size();i++){
            Marchant marchant=marchants.get(i);
            marchant.setMarchantPickupDeliveryFacility();
            if(marchant.isDeliveryAvailable()){
                list.add(marchant);
            }
        }
        return list;
    }
    public static List<Marchant> getListForPickupAndDeliveryBoth(List<Marchant> marchants) {
        List<Marchant> list=new ArrayList<>();
        for(int i=0;i<marchants.size();i++){
            Marchant marchant=marchants.get(i);
            marchant.setMarchantPickupDeliveryFacility();
            if(marchant.isBothPickDeliveryAvailable()){
                list.add(marchant);
            }
        }
        return list;
    }

    public static void setVisibility(int visibility, View... views){
        for (View view : views){
            view.setVisibility(visibility);
        }
    }

    public static void startFoodItemFragment(Fragment fragment, Marchant marchant){
        Log.i("responseData", "Marchant Id on marchant item click  : "+marchant.getMarchantId());

        FoodItemFragment foodItemFragment=new FoodItemFragment();
        Bundle bundle=new Bundle();
        Gson gson=new Gson();
        String json=gson.toJson(marchant);
        bundle.putString("json", json);
        Log.i("responseData", "Bundle Json  : "+json);
        foodItemFragment.setArguments(bundle);
        fragment
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_container, foodItemFragment)
                .addToBackStack(null)
                .commit();
    }


    public static void setCount(Context context, String count, Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.shopping_cart_menu);
        if(count.equalsIgnoreCase("0")){
            menuItem.setVisible(false);
        }
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        menuItem.setVisible(false);
        CountDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_group_count);
        if (reuse != null && reuse instanceof CountDrawable) {
            badge = (CountDrawable) reuse;
        } else {
            badge = new CountDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_group_count, badge);
    }
}