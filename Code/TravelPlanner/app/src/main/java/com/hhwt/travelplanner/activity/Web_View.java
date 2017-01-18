package com.hhwt.travelplanner.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hhwt.travelplanner.R;

import java.io.File;
import java.util.ArrayList;

public class Web_View extends AppCompatActivity {
    WebView _Wv;
    ArrayList<String> aa = new ArrayList<String>();
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences _s;
    RelativeLayout Topbar;
    private WebViewClient client;
    public static Activity c;
    ArrayList<String> URL = new ArrayList<String>();
    WebSettings w;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webview);

        c = Web_View.this;
        _s = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        _Wv = (WebView) findViewById(R.id.webview);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        _Wv.setWebChromeClient(new WebChromeClient());
        client = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

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
        _Wv.loadUrl("http://www.hhwt.io/privacy");
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


}
