package com.example.ecommercedemoapp.common;

import android.app.IntentService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utilities {

    public static boolean isInternetAvailable(Context context) {
        try {
            if (context == null) {
                return false;
            }

            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mgr.getActiveNetworkInfo();

            if (netInfo != null) {
                if (netInfo.isConnected()) {
                    // Internet Available
                    return true;
                } else {

                    if (context instanceof IntentService) {
                        return false;
                    }

                    //No internet
                    Toast.makeText(context, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {

                if (context instanceof IntentService) {
                    return false;
                }
                //No internet
                Toast.makeText(context, "Please connect to internet!", Toast.LENGTH_SHORT).show();

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
