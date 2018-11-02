package com.example.hmohamadi.ebooklibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.Language_model;
import com.example.hmohamadi.ebooklibrary.Models.Setting_model;
import com.folioreader.Config;
import com.folioreader.util.AppUtil;

public class PaymentsWebviewActivity extends AppCompatActivity {


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

            Language_model lng = db.get_LanguageID((_conf.getLanguage().length() == 0 ? "fa":  _conf.getLanguage()));
            Setting_model model = db.get_Setting_byLang(lng.get_id());

            webView.loadUrl(model.getVojoohatSiteUrl());

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

}
