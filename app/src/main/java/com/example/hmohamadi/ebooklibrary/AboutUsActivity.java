package com.example.hmohamadi.ebooklibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.codesgood.views.JustifiedTextView;
import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.AboutUs_Model;
import com.folioreader.Config;
import com.folioreader.util.AppUtil;

public class AboutUsActivity extends AppCompatActivity {
private android.support.v7.widget.AppCompatTextView txtViewAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Config _conf = AppUtil.getSavedConfig(this);
        AppUtil.ChangeLocale(this,_conf);


        txtViewAboutUs= (android.support.v7.widget.AppCompatTextView)findViewById(R.id.about_us_txtAboutUs);

        DBHelper db = new DBHelper(this);

        AboutUs_Model model =db.get_AboutUs_byLang(db.get_LanguageID((_conf.getLanguage().length() == 0 ? "fa":  _conf.getLanguage())).get_id());

        txtViewAboutUs.setText(model.getDsc());

    }
}
