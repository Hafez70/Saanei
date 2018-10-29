package com.example.hmohamadi.ebooklibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.ui.folio.activity.FolioActivity;
import com.folioreader.util.AppUtil;
import com.folioreader.util.FileUtil;

import java.io.InputStream;
import java.util.Locale;


public class Main_Activity extends AppCompatActivity
        implements
        Menu_Fragment.OnFragmentInteractionListener,
        Setting_Fragment.OnFragmentInteractionListener,
        BookList_Fragment.OnFragmentInteractionListener
{
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Menu_Fragment fragment = new Menu_Fragment();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                case R.id.navigation_Setting: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Setting_Fragment fragment = new Setting_Fragment();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                case R.id.navigation_BookList: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    BookList_Fragment fragment = new BookList_Fragment();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
            }
            return true;
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constants.WRITE_EXTERNAL_STORAGE_REQUEST : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    navigation.setSelectedItemId(R.id.navigation_BookList);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config config = AppUtil.getSavedConfig(getBaseContext());
        chngeApplicationLanguage(config.getLanguage());
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(Main_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main_Activity.this, Constants.getWriteExternalStoragePerms(), Constants.WRITE_EXTERNAL_STORAGE_REQUEST);

        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_BookList);

        InputStream epubInputStream;
        epubInputStream = this.getResources().openRawResource(R.raw.resal);
        FileUtil.saveTempEpubFile(FileUtil.getFolioEpubFilePath(FolioActivity.EpubSourceType.RAW, "", "resal"), "resal", epubInputStream);

        InputStream epubInputStream_cover;
        epubInputStream_cover = this.getResources().openRawResource(R.raw.resal_cover);
        String coverFIlePath = FileUtil.getFolioCoverFilePath(FolioActivity.EpubSourceType.RAW, "", "resal_cover",".jpeg");
        Log.w("onCreate >>> ","coverFIlePath :  >>" +  coverFIlePath);
        FileUtil.saveTempEpubFile(coverFIlePath, "resal_cover", epubInputStream_cover);
    }
    private void chngeApplicationLanguage(String selectedLang )
    {
        String languageToLoad  = selectedLang; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
    @Override
    public void onFragmentInteraction(String data) {

        if(data.contains("call_setting"))
        {
            navigation.setSelectedItemId(R.id.navigation_Setting);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        Log.w("onConfigurationChanged","<<<<<<<<<<<<<<<<<<< call");
        navigation.setSelectedItemId(R.id.navigation_Setting);
        super.onConfigurationChanged(newConfig);
    }
}
