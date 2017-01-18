/*  * Created by Mathankumar  * Copyright 2015 Google Inc. All rights reserved.  *  * Licensed under the Apache License, Version 2.0 (the "License");  * you may not use this file except in compliance with the License.  * You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */   package com.hhwt.travelplanner.Utills;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;

/**
 * Created by NNandrorid03user on 3/20/2015.
 */
public class InternetAccessCheck {
    Activity _activity;
    public boolean isNetworkConnected(Context context) {
        _activity = (Activity) context;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            AlertBoxes.sLoginAlert(_activity,
                    AlertBoxes.AlertCondition.Internet);
            return false;
        } else{

            isInternetAvailable(_activity);
            return true;
        }

    }

    public boolean isNetworkConnectedforchat(Context context) {
        _activity = (Activity) context;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        } else{

            isInternetAvailablechat(_activity);
            return true;
        }

    }
    public boolean isInternetAvailablechat(Activity _context) {
        try {
            InetAddress ipAddr = InetAddress.getByName("https://google.com"); //You can replace it with your name

            if (ipAddr.equals("")) {

                return false;

            } else {

                return true;

            }

        } catch (Exception e) {
            return false;
        }

    }
    public boolean isInternetAvailable(Activity _context) {
        try {
            InetAddress ipAddr = InetAddress.getByName("https://google.com"); //You can replace it with your name

            if (ipAddr.equals("")) {
                AlertBoxes.sLoginAlert(_context,
                        AlertBoxes.AlertCondition.Internet);
                return false;

            } else {

                return true;

            }

        } catch (Exception e) {
            return false;
        }

    }
}
