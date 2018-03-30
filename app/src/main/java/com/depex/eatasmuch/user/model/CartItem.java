package com.depex.eatasmuch.user.model;

public class CartItem {

    private FoodItem foodItem;
    private int quantity;
    //for Full or Half
    private float price;
    private String size=new String();

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

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
            quantity++;
    }

    public void quantityDecrement(){
        quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return foodItem != null ? foodItem.equals(cartItem.foodItem) : cartItem.foodItem == null;
    }

    @Override
    public int hashCode() {
        return foodItem != null ? foodItem.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "foodItem=" + foodItem +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}