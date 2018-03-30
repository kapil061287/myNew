package com.depex.eatasmuch.user.model;



import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {

    private List<CartItem> items=new ArrayList<>();
    private Marchant marchant;

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Marchant getMarchant() {
        return marchant;
    }

    public void setMarchant(Marchant marchant) {
        this.marchant = marchant;
    }

    public void addItem(CartItem cartItem){
        if(items.contains(cartItem)){
                int index=items.indexOf(cartItem);
                cartItem.quantityIncrement();
                items.set(index, cartItem);
        }else {
            items.add(cartItem);
        }
    }
    public List<CartItem> getItems(){
        return items;
    }

    public int getTotalCartItemCount(){
        int count=0;
        for(CartItem cartItem : items){
            count+=cartItem.getQuantity();
        }
        return count;
    }




    public int getShoppingCartItemCount(){
        return items.size();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }

    public void clear(){
        items.clear();
        if(marchant!=null){
            marchant=null;
        }
    }
    public float getTotalPrice(){
        float price=0.0f;
        for(CartItem cartItem : items){
            price+=(cartItem.getPrice()*cartItem.getQuantity());
        }
        return price;
    }
}
