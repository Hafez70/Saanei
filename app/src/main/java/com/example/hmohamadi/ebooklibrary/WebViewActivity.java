package com.example.hmohamadi.ebooklibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.Language_model;
import com.example.hmohamadi.ebooklibrary.Models.Setting_model;
import com.folioreader.Config;
import com.folioreader.util.AppUtil;

public class WebViewActivity extends AppCompatActivity {


            private WebView webView;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wev_view);


            webView = (WebView) findViewById(R.id.webView1);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setSupportMultipleWindows(true);
            settings.setJavaScriptEnabled(true);
            settings.setAppCacheEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setGeolocationEnabled(true);
            settings.setSaveFormData(false);
            settings.setSavePassword(false);
            settings.setSupportZoom(true);

            DBHelper db = new DBHelper(this);

            Config _conf = AppUtil.getSavedConfig(this);

            Log.w("WebViewActivity >>> ","getlang  :  >>" +  (_conf.getLanguage().length() == 0 ? "fa":  _conf.getLanguage()));
            Language_model lng = db.get_LanguageID((_conf.getLanguage().length() == 0 ? "fa":  _conf.getLanguage()));
            Log.w("WebViewActivity >>> ","lang ID   :  >>" +  lng.get_id());
            Setting_model model = db.get_Setting_byLang(lng.get_id());
            Log.w("WebViewActivity >>> "," url   :  >>" +  model.getWebSiteUrl());
            webView.loadUrl(model.getWebSiteUrl());

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

}
