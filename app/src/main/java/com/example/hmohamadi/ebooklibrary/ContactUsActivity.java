package com.example.hmohamadi.ebooklibrary;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;




public class ContactUsActivity extends AppCompatActivity {

    public TextView txtTitle;
    public TextView txtDescription;
    public RelativeLayout buttonLayout;

    public ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


    }
}
