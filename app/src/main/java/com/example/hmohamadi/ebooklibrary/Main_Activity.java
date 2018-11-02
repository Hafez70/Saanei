package com.example.hmohamadi.ebooklibrary;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.ui.folio.activity.FolioActivity;
import com.folioreader.util.AppUtil;
import com.folioreader.util.FileUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;


public class Main_Activity extends AppCompatActivity
        implements
        Menu_Fragment.OnFragmentInteractionListener,
        Setting_Fragment.OnFragmentInteractionListener,
        BookList_Fragment.OnFragmentInteractionListener
{
    BottomNavigationView navigation;
    List<Fragment> lstPages = new ArrayList<Fragment>();
    Fragment active_frg;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu: {
                    getSupportFragmentManager().beginTransaction().hide(active_frg).show(lstPages.get(1)).commit();
                    active_frg = lstPages.get(1);
                    return true;
                }
                case R.id.navigation_Setting: {
                    getSupportFragmentManager().beginTransaction().hide(active_frg).show(lstPages.get(2)).commit();
                    active_frg = lstPages.get(2);
                    return true;

                }
                case R.id.navigation_BookList: {

                    getSupportFragmentManager().beginTransaction().hide(active_frg).show(lstPages.get(0)).commit();
                    active_frg = lstPages.get(0);
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


        if (config==null) {
            Config _config = new Config()
                    .setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL)
                    .setDirection(Config.Direction.VERTICAL)
                    .setFont(Constants.FONT_NAZANIN)
                    .setFontSize(2)
                    .setNightMode(false)
                    .setShowTts(true)
                    .setLanguage(Constants.LANG_FA);

            config=_config;
            AppUtil.saveConfig(this,_config);
        }


        chngeApplicationLanguage(config.getLanguage());
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(Main_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main_Activity.this, Constants.getWriteExternalStoragePerms(), Constants.WRITE_EXTERNAL_STORAGE_REQUEST);

        }

        lstPages.add(new BookList_Fragment());
        lstPages.add(new Menu_Fragment());
        lstPages.add(new Setting_Fragment());

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(0), "navigation_BookList").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(1), "navigation_menu").hide(lstPages.get(1)).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(2), "navigation_Setting").hide(lstPages.get(2)).commit();
        active_frg = lstPages.get(0);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_BookList);

        InputStream epubInputStream;
        epubInputStream = this.getResources().openRawResource(R.raw.resal);
        FileUtil.saveTempEpubFile(FileUtil.getFolioEpubFilePath(FolioActivity.EpubSourceType.RAW, "", "resal"), "resal", epubInputStream);

        InputStream epubInputStream_cover;
        epubInputStream_cover = this.getResources().openRawResource(R.raw.resal_cover);
        String coverFIlePath = FileUtil.getFolioCoverFilePath(FolioActivity.EpubSourceType.RAW, "", "resal_cover",".jpeg");

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
    public void onBackPressed() {

        Log.w("onBackPressed >>> ","<<<< BackStackEntryCount : " + getSupportFragmentManager().getBackStackEntryCount());
        if ( active_frg == lstPages.get(0)) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle(R.string.txt_ask_onexit_title);
            dialogBuilder.setMessage(R.string.txt_ask_onexit);
            dialogBuilder.setPositiveButton(R.string.txt_ask_onexit_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                  finish();

                }
            });
            dialogBuilder.setNegativeButton(R.string.txt_ask_onexit_no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });

            AlertDialog b = dialogBuilder.create();
            b.show();

        } else {
            navigation.setSelectedItemId(R.id.navigation_BookList);
        }
    }

    @Override
    public void onFragmentInteraction(String data) {

        if(data.contains("call_setting"))
        {
            navigation.setSelectedItemId(R.id.navigation_Setting);
        }
        if(data.contains(Config.LANGUAGE))
        {
            getSupportFragmentManager().beginTransaction().remove(lstPages.get(0)).commit();
            getSupportFragmentManager().beginTransaction().remove(lstPages.get(1)).commit();
            getSupportFragmentManager().beginTransaction().remove(lstPages.get(2)).commit();

            getSupportFragmentManager().executePendingTransactions();
            Log.w("remove >>> ","<<<< remove :  setting <<<<<<<<<<<<<<<<<<<");
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(0), "navigation_BookList").hide(lstPages.get(0)).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(1), "navigation_menu").hide(lstPages.get(1)).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(2), "navigation_Setting").commit();

            getSupportFragmentManager().executePendingTransactions();
            Log.w("add >>> ","<<<< add :  setting <<<<<<<<<<<<<<<<<<<");
            active_frg = lstPages.get(2);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
