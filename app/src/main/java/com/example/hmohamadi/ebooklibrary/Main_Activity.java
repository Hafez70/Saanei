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

                    if(active_frg instanceof BookList_Fragment)
                    {
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right)
                                .hide(active_frg).show(lstPages.get(1)).commit();
                    }
                    else if(active_frg instanceof Setting_Fragment )
                    {
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                                .hide(active_frg).show(lstPages.get(1)).commit();
                    }

                    active_frg = lstPages.get(1);
                    return true;
                }
                case R.id.navigation_Setting: {

                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right)
                            .hide(active_frg)
                            .show(lstPages.get(2))
                            .commit();
                    active_frg = lstPages.get(2);
                    return true;

                }
                case R.id.navigation_BookList: {

                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                            .hide(active_frg).show(lstPages.get(0)).commit();
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
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

//                    getSupportFragmentManager().beginTransaction().remove(lstPages.get(0)).commit();
//                    getSupportFragmentManager().executePendingTransactions();
//                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(0), "navigation_BookList").commit();
//                    navigation.setSelectedItemId(R.id.navigation_menu);
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
            Log.w("onCreate >>>", ">>>>>>>>>>>>> config==null <<<<<<<<<<<<<<<<");
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

        AppUtil.ChangeLocale(this,config);

        //chngeApplicationLanguage(config.getLanguage());
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
        navigation.setSelectedItemId(R.id.navigation_menu);

        SaveBookinExternal("resaleh_org","resaleh_org_cover",R.raw.resaleh_org,R.raw.resaleh_org_cover);
        SaveBookinExternal("majma_1","majma_1_cover",R.raw.majma_1,R.raw.majma_1_cover);
        SaveBookinExternal("majma_2","majma_2_cover",R.raw.majma_2,R.raw.majma_2_cover);
        SaveBookinExternal("majma_3","majma_3_cover",R.raw.majma_3,R.raw.majma_3_cover);
        SaveBookinExternal("esteftaat_ghazayee_1","esteftaat_ghazayee_1_cover",R.raw.esteftaat_ghazayee_1,R.raw.esteftaat_ghazayee_1_cover);
        SaveBookinExternal("esteftaat_ghazayee_2","esteftaat_ghazayee_2_cover",R.raw.esteftaat_ghazayee_2,R.raw.esteftaat_ghazayee_2_cover);

        SaveBookinExternal("islamic_laws","islamic_laws_cover",R.raw.islamic_laws,R.raw.islamic_laws_cover);
        SaveBookinExternal("istiftas_en","istiftas_en_cover",R.raw.istiftas_en,R.raw.istiftas_en_cover);

        SaveBookinExternal("mesbah_al_moghaledin","mesbah_al_moghaledin_cover",R.raw.mesbah_al_moghaledin,R.raw.mesbah_al_moghaledin_cover);
        SaveBookinExternal("tahrir_alvasile_1","tahrir_alvasile_1_cover",R.raw.tahrir_alvasile_1,R.raw.tahrir_alvasile_1_cover);
        SaveBookinExternal("tahrir_alvasile_2","tahrir_alvasile_2_cover",R.raw.tahrir_alvasile_2,R.raw.tahrir_alvasile_2_cover);

    }

    private void SaveBookinExternal(String name,String coverName,int book_res, int cover_res)
    {
        InputStream epubInputStream;
        epubInputStream = this.getResources().openRawResource(book_res);
//        FileUtil.saveTempEpubFile(FileUtil.getFolioEpubFilePath(FolioActivity.EpubSourceType.RAW, "", name), name, epubInputStream);
        FileUtil.saveTempEpubFile_Internal(this,FileUtil.getFolioEpubFilePath_internal(this, "", name), name, epubInputStream);
        InputStream epubInputStream_cover;

        epubInputStream_cover = this.getResources().openRawResource(cover_res);
        String coverFIlePath = FileUtil.getFolioCoverFilePath_internal(this, "", coverName,".jpg");

        FileUtil.saveTempEpubFile_Internal(this,coverFIlePath, coverName, epubInputStream_cover);
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


        if ( active_frg == lstPages.get(1)) {
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
            navigation.setSelectedItemId(R.id.navigation_menu);
        }
    }

    @Override
    public void onFragmentInteraction(String data) {

        if(data.contains("call_booklist"))
        {
            navigation.setSelectedItemId(R.id.navigation_BookList);
        }
        if(data.contains(Config.LANGUAGE))
        {
            getSupportFragmentManager().beginTransaction().remove(lstPages.get(0)).commit();
            getSupportFragmentManager().beginTransaction().remove(lstPages.get(1)).commit();
            getSupportFragmentManager().beginTransaction().remove(lstPages.get(2)).commit();

            getSupportFragmentManager().executePendingTransactions();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(0), "navigation_BookList").hide(lstPages.get(0)).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(1), "navigation_menu").hide(lstPages.get(1)).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, lstPages.get(2), "navigation_Setting").commit();

            getSupportFragmentManager().executePendingTransactions();

            active_frg = lstPages.get(2);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
