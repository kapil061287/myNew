package com.depex.eatasmuch.user.model;

public class CartItem {

    private FoodItem foodItem;
    private int quantity;
    //for Full or Half
    private float price;

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void quantityIncrement(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        if (Float.compare(cartItem.price, price) != 0) return false;
        return foodItem != null ? foodItem.equals(cartItem.foodItem) : cartItem.foodItem == null;
    }

    @Override
    public int hashCode() {
        int result = foodItem != null ? foodItem.hashCode() : 0;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}