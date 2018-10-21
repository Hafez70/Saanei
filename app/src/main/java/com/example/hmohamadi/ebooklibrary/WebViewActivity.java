package com.example.hmohamadi.ebooklibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

import android.webkit.WebView;
public class WebViewActivity extends AppCompatActivity {


            private WebView webView;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wev_view);

            webView = (WebView) findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://www.saanei.xyz/?view=01,00,00,00,0");

        }

}
