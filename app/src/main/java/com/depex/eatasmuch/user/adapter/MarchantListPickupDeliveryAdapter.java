package com.depex.eatasmuch.user.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.depex.eatasmuch.user.GlideApp;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.listener.OnMarchantItemClickListener;
import com.depex.eatasmuch.user.model.Marchant;
import com.depex.eatasmuch.user.utils.UtilMethods;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarchantListPickupDeliveryAdapter extends RecyclerView.Adapter<MarchantListPickupDeliveryAdapter.MarchantListPickupViewHolder> {
    private List<Marchant> marchants;
    private Context context;
    private OnMarchantItemClickListener onMarchantItemClickListener;
    public MarchantListPickupDeliveryAdapter(List<Marchant> marchants, Context context, OnMarchantItemClickListener onMarchantItemClickListener){
        this.marchants=marchants;
        this.context=context;
        this.onMarchantItemClickListener=onMarchantItemClickListener;
    }


    @Override
    public MarchantListPickupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.content_marchant_recycler, parent, false);
        return new MarchantListPickupViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MarchantListPickupViewHolder holder, int position) {
        final Marchant marchant=marchants.get(position);
        marchant.setMarchantPickupDeliveryFacility();
        GlideApp.with(context).load(marchant.getImageUrl()).into(holder.rastaurantImage);
        if(marchant.getRating()<1) {
            holder.ratingBar.setRating(3.5f);
            marchant.setRating(3.5f);
        }
        else
        holder.ratingBar.setRating(marchant.getRating());

        holder.rastaurantName.setText(marchant.getRestaurantName());
        String cuisine=marchant.getCuisine();
        if(cuisine.length()<28) {
            holder.cuisineNames.setText(marchant.getCuisine());
        }else {
           // int index=cuisine.indexOf(",", 21);
            cuisine=cuisine.substring(0, 26);
            holder.cuisineNames.setText(cuisine+"...");
        }
        holder.marchantAddress.setText(marchant.getStreet());
        if(marchant.isBothPickDeliveryAvailable()){
            holder.pickupMarchant.setVisibility(View.VISIBLE);
            holder.deliveryMarchant.setVisibility(View.VISIBLE);
            UtilMethods.setVisibility(View.VISIBLE, holder.pickupMarchant, holder.deliveryMarchant);
        }else if(marchant.isDeliveryAvailable()){
              UtilMethods.setVisibility(View.GONE, holder.pickupMarchant);
              UtilMethods.setVisibility(View.VISIBLE, holder.deliveryMarchant);
        }else if(marchant.isPickupAvailable()){
            UtilMethods.setVisibility(View.VISIBLE, holder.pickupMarchant);
            UtilMethods.setVisibility(View.GONE, holder.deliveryMarchant);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMarchantItemClickListener.onMarchantItemClick(marchant);
            }
        });
    }


    @Override
    public int getItemCount() {
        return marchants.size();
    }

    public class MarchantListPickupViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurant_image_marchant_list)
        ImageView rastaurantImage;

        @BindView(R.id.rating_bar)
        RatingBar ratingBar;

        @BindView(R.id.rastaurant_name_marchant_rec)
        TextView rastaurantName;

        @BindView(R.id.rastaurant_cuisine_marchant_rec)
        TextView cuisineNames;

        @BindView(R.id.address_marchant_rec)
        TextView marchantAddress;

        @BindView(R.id.delivery_marchant)
        TextView deliveryMarchant;

        @BindView(R.id.pickup_marchant)
        TextView pickupMarchant;

        public MarchantListPickupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}