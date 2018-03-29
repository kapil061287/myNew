package com.depex.eatasmuch.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.adapter.MarchantListPickupDeliveryAdapter;
import com.depex.eatasmuch.user.listener.OnMarchantItemClickListener;
import com.depex.eatasmuch.user.model.Marchant;
import com.depex.eatasmuch.user.utils.UtilMethods;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarchantPickupFragment extends Fragment implements OnMarchantItemClickListener {


    @BindView(R.id.marchant_pickup_recycler)
    RecyclerView marchantPickupRecycler;

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_marchant_pickup_fragment, container, false);
        ButterKnife.bind(this, view);
        String json=getArguments().getString("json");
        Gson gson=new Gson();
        Marchant[] marchants=gson.fromJson(json, Marchant[].class);
        List<Marchant> marchants1=new ArrayList<>(Arrays.asList(marchants));
        List<Marchant> marchantsListForPickup= UtilMethods.getListForPickup(marchants1);
        List<Marchant> marchantsListForBoth=UtilMethods.getListForPickupAndDeliveryBoth(marchants1);
        //Convert to set for unique items
        Set<Marchant> tempSet=new HashSet<>(marchantsListForPickup);
        tempSet.addAll(marchantsListForBoth);

        //reverse process to convert in list
        List<Marchant> marchants2=new ArrayList<>(tempSet);

        Log.i("marchantList", marchants2.toString());
        MarchantListPickupDeliveryAdapter adapter=new MarchantListPickupDeliveryAdapter(marchants2, context, this);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(context, manager.getOrientation());
        marchantPickupRecycler.setLayoutManager(manager);
        marchantPickupRecycler.addItemDecoration(dividerItemDecoration);
        marchantPickupRecycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onMarchantItemClick(Marchant marchant) {
          UtilMethods.startFoodItemFragment(getParentFragment(), marchant);
    }
}