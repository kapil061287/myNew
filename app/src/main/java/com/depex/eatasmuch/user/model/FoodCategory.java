package com.depex.eatasmuch.user.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class FoodCategory {

    @SerializedName("cat_id")
    private String catID;
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("photo")
    private String photoUrl;
    @SerializedName("mt_price_size")
    private String mtPriceSize;
    @SerializedName("items")
    private List<FoodItem> items;

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMtPriceSize() {
        return mtPriceSize;
    }

    public void setMtPriceSize(String mtPriceSize) {
        this.mtPriceSize = mtPriceSize;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void setItems(List<FoodItem> items) {
        this.items = items;
    }

    @Override
    public String toString(){

        StringBuilder sb=new StringBuilder();
        sb.append("Category : "+getCategoryName());
        for(FoodItem foodItem : getItems()){
            sb.append(" \n-> Food Item : "+foodItem.toString());
        }
        return sb.toString();
    }

    public void parsePrice(){
        Map<String, String> map=new HashMap<>();

        if(mtPriceSize!=null){
            String[]outerArr=mtPriceSize.split(":");
            for(String priceItem : outerArr){
                String[]priceArr=priceItem.split("=>");
                if(priceArr.length>1) {
                    map.put(priceArr[0], priceArr[1]);
                }
            }
        }
        for(FoodItem foodItem : items){
            //foodItem.setPriceMap(map);
            Map<String, String> priceMap=new HashMap<>();
            StringBuilder sb=new StringBuilder();
            String priceInStr=foodItem.getPrice();
            try {
                JSONObject jsonObject=new JSONObject(priceInStr);
                for(Iterator<String>i=jsonObject.keys();i.hasNext();){
                    String key=i.next();
                    String value=jsonObject.getString(key);
                    Log.i("responseData", "\nFood Category Key :"+map.get(key)+"  Value :  "+value);
                    priceMap.put(map.get(key), value);

                    if(i.hasNext()) {
                        sb.append(map.get(key)).append("-").append(value).append(" , ");

                    }else {
                        if(!i.hasNext()){
                            sb.append(map.get(key)).append("-").append(value);
                        }
                    }
                }
                foodItem.setFoodCatName(getCategoryName());
                foodItem.setPriceMap(priceMap);
            } catch (JSONException e) {
                Log.i("responseDataError","Food Category Error : " +e.toString());
            }
                foodItem.setFoodPrice(sb.toString());
            Log.i("responseData", "stringbuilder : "+sb.toString());
        }
    }
}