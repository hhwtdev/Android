package com.hhwt.travelplanner.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;

import java.util.ArrayList;
import java.util.Hashtable;

public class Guide_Fragment extends Fragment {
    WebView _Wv;
    ArrayList<String> aa = new ArrayList<String>();
    private WebViewClient client;
    public static Activity c;
    ArrayList<String> URL = new ArrayList<String>();
    WebSettings w;
    ImageView back;
    String url;
    Hashtable connectFlags;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_webview, container, false);
       // Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));

       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
       // Tapjoy.setDebugEnabled(true);
        c = getActivity();
        _Wv = (WebView) v.findViewById(R.id._webview);
        url = "http://everestitservices.com/govind.html";
        _Wv.setWebChromeClient(new WebChromeClient());
        _Wv.setWebViewClient(client);
        w = _Wv.getSettings();
        _Wv.setWebChromeClient(new WebChromeClient());
        client = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                InternetAccessCheck ic = new InternetAccessCheck();
                Boolean check = ic.isNetworkConnected(c);
                if (check)
                    URL.add(url);

                return false;
            }
        };
        _Wv.setWebViewClient(client);
        w = _Wv.getSettings();
        w.setJavaScriptEnabled(true);
   /*     _Wv.clearCache(true);
        _Wv.clearHistory();*/
        w.setSaveFormData(true);
        w.getDomStorageEnabled();
        _Wv.setFocusable(true);
        _Wv.setFocusableInTouchMode(true);
        w.setJavaScriptEnabled(true);
        w.setRenderPriority(WebSettings.RenderPriority.HIGH);
        w.setCacheMode(WebSettings.LOAD_NO_CACHE);
        w.setDomStorageEnabled(true);
        _Wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            w.setAllowFileAccessFromFileURLs(true);

        }

        _Wv.loadUrl(url.trim());
        client = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        };

        // Show a datepicker when the dateButton is clickedow a datepicker when the dateButton is clicked
        // Inflate the layout for this fragment
        return v;
    }


    // called when Tapjoy connect call succeed

    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    // called when Tapjoy connect call failed

    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }
    //session start
    @Override
    public void onStart() {
        super.onStart();
       // Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
        //Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }




    public Guide_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
