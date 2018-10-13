package com.example.hmohamadi.ebooklibrary;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.hmohamadi.ebooklibrary.Helpers.ChangeActivityHelper;

public class SplashScreen extends AppCompatActivity {


    AppCompatTextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Activity a = this;
        //Typeface tf=  Typeface.createFromAsset(getAssets(),"fonts/b_davat.ttf");
        //txt =  (AppCompatTextView) findViewById(R.id.txtSplashTtile);
        //txt.setTypeface(tf);
        YoYo.with(Techniques.FadeIn).duration(500).repeat(0).playOn(findViewById(R.id.layoutMain));
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(findViewById(R.id.txtSplashTtile));
        YoYo.with(Techniques.FadeIn).duration(1500).repeat(0).onEnd(new YoYo.AnimatorCallback() {
            @Override
            public void call(Animator animator) {
                ChangeActivityHelper.changeActivity(a,Main_Activity.class,true);
            }
        }).playOn(findViewById(R.id.imgSplash_logo));
    }
}
