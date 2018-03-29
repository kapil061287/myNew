package com.depex.eatasmuch.user.decoration;


import android.content.Context;
import android.graphics.Color;

import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.adapter.FoodAdapter;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class EatAsMuchDividerItemDecoration extends Y_DividerItemDecoration{


    private Context context;
    private FoodAdapter adapter;
    public EatAsMuchDividerItemDecoration(Context context, FoodAdapter adapter) {
        super(context);
        this.context=context;
        this.adapter=adapter;
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider=null;
        if((adapter.getItemCount()-1)!=itemPosition){

            divider = new Y_DividerBuilder()
                    .setBottomSideLine(true, context.getResources().getColor(R.color.color_gray), 1, 0, 0)
                    .create();

        }else {
            divider = new Y_DividerBuilder()
                    .setBottomSideLine(true, context.getResources().getColor(R.color.colorPrimaryDark), 0, 0, 0)
                    .create();
        }
        return divider;
    }
}
