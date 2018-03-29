package com.depex.eatasmuch.user.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.depex.eatasmuch.user.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2/15/2018.
 */

public class CreatePinAdapterFromRestaurants {

    private List<Restaurant> restaurants;
    Context context;
    public CreatePinAdapterFromRestaurants(List<Restaurant> restaurants, Context context){
        this.restaurants=restaurants;
        this.context=context;
    }

    public ArrayAdapter getAdapterFromPinCode(){
        List<String> arr=new ArrayList<>();
        for(int i=0;i<restaurants.size();i++){
            arr.add(restaurants.get(i).getPin());
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line,arr);
        return  arrayAdapter;
    }
}
