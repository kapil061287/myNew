package com.depex.eatasmuch.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.allenliu.badgeview.BadgeFactory;
import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.listener.FoodItemClickListener;
import com.depex.eatasmuch.user.model.FoodItem;
import com.depex.eatasmuch.user.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodItems;
    private Context context;
    private FoodItemClickListener foodItemClickListener;

    public FoodAdapter(List<FoodItem> foodItems, Context context, FoodItemClickListener foodItemClickListener){
        this.foodItems=foodItems;
        this.context=context;
        this.foodItemClickListener=foodItemClickListener;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.content_recy_food_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
            final FoodItem foodItem=foodItems.get(position);
            String foodName=foodItem.getItemName();
            foodItem.setItemName(Utils.confirtStringProperFormat(foodName));
            String itemName=foodItem.getItemName();
            itemName = Utils.confirtStringProperFormat(itemName);
            holder.foodName.setText(itemName);
            String photo=foodItem.getPhoto();
            GlideApp.with(context).load(photo).into(holder.foodImage);

            holder.foodPrice.setText(foodItem.getFoodPrice());
            if(foodItem.getIsVeg().equalsIgnoreCase("0")){
                holder.imageView.setImageResource(R.drawable.ic_radio_button_checked_green_24dp);
            }else {
                //holder.imageView.setBackgroundResource(R.drawable.ic_radio_button_checked_red_24dp);
                holder.imageView.setImageResource(R.drawable.ic_radio_button_checked_red_24dp);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foodItemClickListener.onFoodItemClick(foodItem);
                }
            });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.food_name)
        TextView foodName;

        @BindView(R.id.veg_or_non_vag)
        ImageView imageView;

        @BindView(R.id.half_or_full)
        TextView foodPrice;

        @BindView(R.id.img_food_item)
        ImageView foodImage;

        public FoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}