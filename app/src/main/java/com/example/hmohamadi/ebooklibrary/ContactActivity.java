package com.example.hmohamadi.ebooklibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hmohamadi.ebooklibrary.Models.GMailSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        final EditText your_name        = (EditText) findViewById(R.id.your_name);
        final EditText your_email       = (EditText) findViewById(R.id.your_email);
        final EditText your_subject     = (EditText) findViewById(R.id.your_subject);
        final EditText your_message     = (EditText) findViewById(R.id.your_message);
        Button email = (Button) findViewById(R.id.post_message);

        email.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            String name = your_name.getText().toString();
            String email = your_email.getText().toString();
            String subject = your_subject.getText().toString();
            String message = your_message.getText().toString();

            if (TextUtils.isEmpty(name)) {
                your_name.setError(getResources().getString(R.string.empty_name));
                your_name.requestFocus();
                return;
            }
            Boolean onError = false;
            if (!isValidEmail(email)) {
                onError = true;
                your_email.setError(getResources().getString(R.string.invalid_email));
                return;
            }

            if (TextUtils.isEmpty(subject)){
                your_subject.setError(getResources().getString(R.string.empty_subject));
                your_subject.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(message)){
                your_message.setError(getResources().getString(R.string.invalid_email));
                your_message.requestFocus();
                return;
            }
            //Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);

            /* Fill it with Data */

            try {
                GMailSender sender = new GMailSender("saaneimobileapp@gmail.com", "Saanei2018");
                sender.sendMail(subject,
                        message,
                        "saaneimobileapp@gmail.com",
                        "mostafa.abdollahi@gmail.com");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }



            //sendEmail.setType("plain/text");
            //sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"saaneimobileapp@gmail.com"});
            //sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
           // sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
               //     "name:"+name+'\n'+"Email ID:"+email+'\n'+"Message:"+'\n'+message);

            /* Send it off to the Activity-Chooser */
            //startActivity(Intent.createChooser(sendEmail, "Send email..."));
        }
        });

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
