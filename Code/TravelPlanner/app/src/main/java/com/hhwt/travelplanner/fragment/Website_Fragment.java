package com.hhwt.travelplanner.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.Utills.InternetAccessCheck;
import com.hhwt.travelplanner.activity.NewUiView_itemInfo_Fragment;

import java.util.ArrayList;

public class Website_Fragment extends Fragment {
    WebView _Wv;
    ArrayList<String> aa = new ArrayList<String>();
    private WebViewClient client;
    public static Activity c;
    ArrayList<String> URL = new ArrayList<String>();
    WebSettings w;
    ImageView back;
    String url;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_webview, container, false);
        c = getActivity();
        _Wv = (WebView) v.findViewById(R.id._webview);
        Bundle bundle = this.getArguments();

        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));
   //     url = bundle.getString(View_itemInfo_Fragment.EXTRAlinkINFO);

        url = bundle.getString(NewUiView_itemInfo_Fragment.EXTRAlinkINFO);


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

        _Wv.loadUrl(url);
        client = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        };
        return v;
    }

    public Website_Fragment() {
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
