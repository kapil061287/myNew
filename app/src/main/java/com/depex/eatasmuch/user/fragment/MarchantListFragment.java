package com.depex.eatasmuch.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.api.ProjectApi;
import com.depex.eatasmuch.user.factory.StringConvertFactory;
import com.depex.eatasmuch.user.model.Marchant;
import com.depex.eatasmuch.user.utils.Utils;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.apache.commons.beanutils.converters.StringConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MarchantListFragment extends Fragment {
    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.delivery_pickup_viewpager)
    ViewPager deliveryPickupViewPager;

    @BindView(R.id.avl_loading_marchant_list_fragment)
    AVLoadingIndicatorView avLoadingMarchantListFragment;

    @BindView(R.id.overlay_image_marchant_fragment)
    ImageView overLayImage;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_marchant_list_fragment, container, false);
        ButterKnife.bind(this, view);

        deliveryPickupViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(deliveryPickupViewPager));
        Bundle bundle=getArguments();
        if(bundle!=null)
        jsonMarchantData(bundle.getString("pincode"));
        else {

        }
        return view;
    }


    public void jsonMarchantData(String searchKey){
        JSONObject requestData=new JSONObject();
        JSONObject data=new JSONObject();
        try {
            data.put("v_code", getString(R.string.v_code));
            data.put("requestType", "resta_list");
            data.put("apikey", getString(R.string.apikey));
            data.put("search_key",searchKey );
            requestData.put("RequestData", data);
            Log.i("requestData", requestData.toString());

            new Retrofit
                    .Builder()
                    .baseUrl(Utils.SITE_URL)
                    .addConverterFactory(new StringConvertFactory())
                    .build()
                    .create(ProjectApi.class)
                    .getMarchantList(requestData.toString())
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String responseString=response.body();
                            Log.i("responseData", responseString);
                            try {
                                JSONObject res=new JSONObject(responseString);
                                boolean success=res.getBoolean("success");
                                if(success){
                                   JSONObject resObj=res.getJSONObject("response");
                                   JSONArray jsonArray=resObj.getJSONArray("list");
                                    setViewPagerAdapter(jsonArray.toString());
                                    avLoadingMarchantListFragment.hide();
                                    overLayImage.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                avLoadingMarchantListFragment.hide();
                                overLayImage.setVisibility(View.GONE);
                            }
                        }


                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            avLoadingMarchantListFragment.hide();
                            overLayImage.setVisibility(View.GONE);
                            Log.e("responseDataError", t.toString());
                        }
                    });
        } catch (JSONException e) {
            avLoadingMarchantListFragment.hide();
            overLayImage.setVisibility(View.GONE);
            Log.e("requestData", e.toString());
        }
    }

    public void setViewPagerAdapter(String json){
        Bundle bundle=new Bundle();
        bundle.putString("json", json);

        MarchantDelivaryFragment marchantDelivaryFragment=new MarchantDelivaryFragment();
        marchantDelivaryFragment.setArguments(bundle);

        MarchantPickupFragment marchantPickupFragment=new MarchantPickupFragment();
        marchantPickupFragment.setArguments(bundle);

        List<Fragment>  fragments=new ArrayList<>();
        fragments.add(marchantDelivaryFragment);
        fragments.add(marchantPickupFragment);


        DeliveryPickupFragmentPagerAdapter adapter=new DeliveryPickupFragmentPagerAdapter(getChildFragmentManager(), fragments);
        deliveryPickupViewPager.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}