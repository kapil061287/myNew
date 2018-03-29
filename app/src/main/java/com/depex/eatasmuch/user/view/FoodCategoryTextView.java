package com.depex.eatasmuch.user.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class FoodCategoryTextView extends ViewRender<String> {
    public FoodCategoryTextView(Context context, int layoutRes) {
        super(context, layoutRes);
    }

    @Override
    public void bindView(View v, String data) {
        if(v instanceof TextView) {
            TextView textView = (TextView) v;
            textView.setText(data);
        }
    }
}
