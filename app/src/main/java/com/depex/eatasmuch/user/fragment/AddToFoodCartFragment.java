package com.depex.eatasmuch.user.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depex.eatasmuch.user.R;

/**
 * Created by we on 3/30/2018.
 */

public class AddToFoodCartFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.food_cart_dialog, container, false);
        return view;
    }
}
