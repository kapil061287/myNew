package com.depex.eatasmuch.user.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RadioButton;

import com.depex.eatasmuch.user.R;


public class EatAsMuchRadioButton extends ViewRender<String> {
    public EatAsMuchRadioButton(Context context) {
        super(context, R.layout.eat_as_much_radio_btn);
    }

    @Override
    public void bindView(View v, String data) {
        RadioButton radioButton= (RadioButton) v;

        radioButton.setText(data);
    }
}
