package com.example.hmohamadi.ebooklibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hmohamadi.ebooklibrary.Helpers.SendMail;
import com.folioreader.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendMailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_sendmail);

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
                your_message.setError("متن ایمیل نباید خالی باشد");
                your_message.requestFocus();
                return;
            }

            SendMail sm = new SendMail(getActivity(), Config.DESTINATION_EMAIL, subject,
                    "<b>Sender: "+ email + "</b><br>" + message);

            //Executing sendmail to send email
            sm.execute();

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

    public Context getActivity() {
        return this;
    }




}

