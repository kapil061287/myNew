package com.depex.eatasmuch.user.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NetworkReceiver extends BroadcastReceiver {

    View view;
    public NetworkReceiver(View view){
        this.view=view;
    }

    public NetworkReceiver(){super();}


    @Override
    public void onReceive(Context context, Intent intent) {


        if(this.view instanceof TextView){
            setOnlineOrOffline(context, (TextView) this.view);
        }


    }

    void setOnlineOrOffline(Context context, TextView view){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if(info!=null){
            if(info.isConnected()){
                Log.i("responseData", "Network Receiver"+context.getClass().getName());
            }
        }
    }
}