package com.depex.eatasmuch.user.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class FoodItem  {

    @SerializedName("item_id")
    private String itemId;
    @SerializedName("merchant_id")
    private String marchantId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_description")
    private String itemDescription;
    @SerializedName("status")
    private String status;
    @SerializedName("price")
    private String price;
    @SerializedName("photo")
    private String photo;
    @SerializedName("sequence")
    private String sequence;
    @SerializedName("not_available")
    private String notAvailable;
    @SerializedName("gallery_photo")
    private String gallaryPhoto;
    @SerializedName("is_veg")
    private String isVeg;

    private String foodCatName;

    public String getFoodCatName() {
        return foodCatName;
    }

    public void setFoodCatName(String foodCatName) {
        this.foodCatName = foodCatName;
    }

    private Map<String, String> priceMap;


    public Map<String, String> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, String> priceMap) {
        this.priceMap = priceMap;
    }

    public String getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(String isVeg) {
        this.isVeg = isVeg;
    }

    private String foodPrice;

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(String marchantId) {
        this.marchantId = marchantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getNotAvailable() {
        return notAvailable;
    }

    public void setNotAvailable(String notAvailable) {
        this.notAvailable = notAvailable;
    }

    public String getGallaryPhoto() {
        return gallaryPhoto;
    }

    public void setGallaryPhoto(String gallaryPhoto) {
        this.gallaryPhoto = gallaryPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodItem foodItem = (FoodItem) o;

        if (getItemId() != null ? !getItemId().equals(foodItem.getItemId()) : foodItem.getItemId() != null)
            return false;
        if (getMarchantId() != null ? !getMarchantId().equals(foodItem.getMarchantId()) : foodItem.getMarchantId() != null)
            return false;
        if (getItemName() != null ? !getItemName().equals(foodItem.getItemName()) : foodItem.getItemName() != null)
            return false;
        if (getItemDescription() != null ? !getItemDescription().equals(foodItem.getItemDescription()) : foodItem.getItemDescription() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(foodItem.getStatus()) : foodItem.getStatus() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(foodItem.getPrice()) : foodItem.getPrice() != null)
            return false;
        if (getPhoto() != null ? !getPhoto().equals(foodItem.getPhoto()) : foodItem.getPhoto() != null)
            return false;
        if (getSequence() != null ? !getSequence().equals(foodItem.getSequence()) : foodItem.getSequence() != null)
            return false;
        if (getNotAvailable() != null ? !getNotAvailable().equals(foodItem.getNotAvailable()) : foodItem.getNotAvailable() != null)
            return false;
        return getGallaryPhoto() != null ? getGallaryPhoto().equals(foodItem.getGallaryPhoto()) : foodItem.getGallaryPhoto() == null;
    }

    @Override
    public int hashCode() {
        int result = getItemId() != null ? getItemId().hashCode() : 0;
        result = 31 * result + (getMarchantId() != null ? getMarchantId().hashCode() : 0);
        result = 31 * result + (getItemName() != null ? getItemName().hashCode() : 0);
        result = 31 * result + (getItemDescription() != null ? getItemDescription().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getPhoto() != null ? getPhoto().hashCode() : 0);
        result = 31 * result + (getSequence() != null ? getSequence().hashCode() : 0);
        result = 31 * result + (getNotAvailable() != null ? getNotAvailable().hashCode() : 0);
        result = 31 * result + (getGallaryPhoto() != null ? getGallaryPhoto().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
