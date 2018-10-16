package com.example.hmohamadi.ebooklibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ContactUsStaticTextActivity extends AppCompatActivity {
private TextView txtContactUsStatic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_static_text);
        txtContactUsStatic= findViewById(R.id.contactUsStatictxt);
        txtContactUsStatic.setMovementMethod(new ScrollingMovementMethod());
        String content="امور ارتباطات و ملاقات \n" +
                "حجت الاسلام و المسلمين سعيدى – 37748491\n" +
                "لطفا پيشنهادات و نظرات خود را براي بهتر شدن ارائه خدمات به مديريت سايت ارسال نماييد.\n" +
                "info[@]saanei.org";
        txtContactUsStatic.setText(content);
    }
}
