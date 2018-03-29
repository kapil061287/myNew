package com.depex.eatasmuch.user.view;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.adapter.FoodAdapter;
import com.depex.eatasmuch.user.decoration.EatAsMuchDividerItemDecoration;
import com.depex.eatasmuch.user.listener.FoodItemClickListener;
import com.depex.eatasmuch.user.model.FoodItem;

import java.util.List;



public class FoodItemRecyclerView extends ViewRender<List<FoodItem>> {

    private Context context;
    private int layoutRes;
    private FoodItemClickListener foodItemClickListener;

    public FoodItemRecyclerView(Context context, int layoutRes, FoodItemClickListener foodItemClickListener) {
        super(context, layoutRes);
        this.context=context;
        this.foodItemClickListener=foodItemClickListener;
        this.layoutRes=layoutRes;
    }

    @Override
    public void bindView(View v, List<FoodItem> data) {
        if(v instanceof RecyclerView){
            RecyclerView recyclerView= (RecyclerView) v;
            FoodAdapter adapter=new FoodAdapter(data, context, foodItemClickListener);
            recyclerView.setAdapter(adapter);

            LinearLayoutManager manager=new LinearLayoutManager(context);
//            DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(context, manager.getOrientation());
            EatAsMuchDividerItemDecoration dividerItemDecoration1=new EatAsMuchDividerItemDecoration(context,adapter);
            recyclerView.addItemDecoration(dividerItemDecoration1);
            recyclerView.setLayoutManager(manager);
        }
    }
}
