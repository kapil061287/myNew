package com.depex.eatasmuch.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.listener.UpdatesubtotalListener;
import com.depex.eatasmuch.user.model.CartItem;
import com.depex.eatasmuch.user.model.FoodItem;
import com.depex.eatasmuch.user.utils.Utils;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;



public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private UpdatesubtotalListener listener;


    public ShoppingCartAdapter(Context context, List<CartItem> cartItems, UpdatesubtotalListener listener){
            this.context=context;
            this.cartItems=cartItems;
            this.listener=listener;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.content_cart_item_rec, parent, false);
        ShoppingCartViewHolder cartViewHolder=new ShoppingCartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, int position) {
        final CartItem item=cartItems.get(position);
        FoodItem foodItem= item.getFoodItem();
        String foodItemName=foodItem.getItemName();
        String size=item.getSize();
        holder.foodItemName.setText(foodItemName);
        holder.foodItemSize.setText(size);
        int quantity=item.getQuantity();
        float price=item.getPrice();
        holder.itemPriceWithQuantity.setText(quantity+" X $"+price);
        float itemTotalPrice=quantity*price;
        holder.totalPrice.setText("$"+itemTotalPrice);
        holder.itemRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //removeFromCart(item, holder);
            }
        });
        holder.itemAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(item, holder);
            }
        });


    }

    private void removeFromCart(CartItem item, ShoppingCartViewHolder holder) {



        int quantity=item.getQuantity();
        float price=item.getPrice();
        holder.itemPriceWithQuantity.setText(quantity+" X $"+price);
        float itemTotalPrice=quantity*price;
        holder.totalPrice.setText("$"+itemTotalPrice);
    }

    private void addToCart(CartItem item, ShoppingCartViewHolder holder) {
        Utils.shoppingCart.addItem(item);

        int position=cartItems.indexOf(item);
        CartItem item1=cartItems.get(position);
        int quantity=item1.getQuantity();
        float price=item1.getPrice();
        holder.itemPriceWithQuantity.setText(quantity+" X $"+price);
        float itemTotalPrice=quantity*price;
        holder.totalPrice.setText("$"+itemTotalPrice);
        listener.updateUi();
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ShoppingCartViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.food_name_category)
        TextView foodItemName;
        @BindView(R.id.total_price)
        TextView totalPrice;
        @BindView(R.id.size_food_item)
        TextView foodItemSize;
        @BindView(R.id.item_price_with_quantity)
        TextView itemPriceWithQuantity;

        @BindView(R.id.item_add_btn)
        Button itemAddBtn;

        @BindView(R.id.item_remove_btn)
        Button itemRemoveBtn;

        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
