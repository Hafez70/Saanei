package com.example.hmohamadi.ebooklibrary;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.Language_model;
import com.example.hmohamadi.ebooklibrary.Models.Setting_model;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.util.AppUtil;

public class WebViewActivity extends AppCompatActivity {


            private WebView webView;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Config _conf = AppUtil.getSavedConfig(this);
            AppUtil.ChangeLocale(this,_conf);

            new WebView(this).destroy();

            ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
            if (netInfo == null) {

                Toast.makeText(this, getResources().getString(R.string.check_internet_connection), Toast.LENGTH_LONG).show();
            } else {
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


                Bundle b = getIntent().getExtras();

                String _Url = b.getString(Constants.WEBVIEW_URL_KEY);
                Log.w("WebViewActivity >>>>", " URL >>>>>>>> : " + _Url);
                webView.loadUrl(_Url);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

                webView.setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Config _conf = AppUtil.getSavedConfig(getBaseContext());
                        AppUtil.ChangeLocale(getBaseContext(),_conf);

                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                        request.setMimeType(mimetype);
                        //------------------------COOKIE!!------------------------
                        String cookies = CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie", cookies);
                        //------------------------COOKIE!!------------------------
                        request.addRequestHeader("User-Agent", userAgent);
                        request.setDescription(getString(R.string.Downlading));
                        request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                        Toast.makeText(getApplicationContext(), getString(R.string.Downlading), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
