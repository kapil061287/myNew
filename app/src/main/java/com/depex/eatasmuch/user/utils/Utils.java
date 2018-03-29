package com.depex.eatasmuch.user.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Utils {
    public static final String SITE_URL="http://dialupagengy.biz/eat_as_much/api/";
    public static final String SITE_PREF="eat_as_much_pref";

    public static String confirtStringProperFormat(String itemName) {
        StringBuilder sb=new StringBuilder();

        itemName=itemName.toLowerCase().trim();
        sb.append(itemName);
        int index=0;
        char ch1=sb.charAt(0);
        ch1= (char) (ch1-32);
        sb.setCharAt(0, ch1);
        while (index!=-1) {
            index = sb.indexOf(" ", index+1);
            if(index==-1){
                break;
            }
            char ch = itemName.charAt(index + 1);
            ch = (char) (ch - 32);
            sb.setCharAt(index+1, ch);
        }
        return sb.toString();
    }
}
