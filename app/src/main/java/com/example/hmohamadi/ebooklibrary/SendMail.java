package com.example.hmohamadi.ebooklibrary;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.folioreader.Config;
import android.app.ProgressDialog;

import java.util.Properties;

public class SendMail extends AsyncTask<Void,Void,Boolean> {
    private Context context;
    private Session session;

    private String email;
    private String subject;
    private String message;
private boolean _do = true;
    private ProgressDialog progressDialog;

    public SendMail(Context context,String email,String subject,String message)
    {
        this.context= context;

        this.email=email;
        this.subject=subject;
        this.message=message;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ConnectivityManager conMgr =  (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            _do = false;
            Toast.makeText(context,"اتصال به اینترنت را چک کنید", Toast.LENGTH_LONG).show();
        }
        else
        {
            progressDialog = ProgressDialog.show(context,"در حال ","لطفا صبر کنید...",false,false);
        }

        //Showing progress dialog while sending email

    }

    @Override
    protected void onPostExecute(Boolean resault) {
        super.onPostExecute(resault);
        if(resault)
        {
            Toast.makeText(context,"ایمیل ارسال شد", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"ایرادی در ارسال رخ داده است", Toast.LENGTH_LONG).show();
        }
        //Dismissing the progress dialog
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
        //Showing a success message

    }
    @Override
    protected Boolean doInBackground(Void... params) {
        //Creating properties
        // Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        // props.put("mail.smtp.host", "Custom SMTP");
        //props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "25");
        if (_do) {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            String MAIL_SMTP_CONNECTIONTIMEOUT ="mail.smtp.connectiontimeout";
            String MAIL_SMTP_TIMEOUT = "mail.smtp.timeout";
            String MAIL_SMTP_WRITETIMEOUT = "mail.smtp.writetimeout";
            String MAIL_SOCKET_TIMEOUT = "5000";
            props.put(MAIL_SMTP_CONNECTIONTIMEOUT, MAIL_SOCKET_TIMEOUT);
            props.put(MAIL_SMTP_TIMEOUT, MAIL_SOCKET_TIMEOUT);
            props.put(MAIL_SMTP_WRITETIMEOUT, MAIL_SOCKET_TIMEOUT);

            //Creating a new session
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        //Authenticating the password
                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication(Config.SOURCE_EMAIL, Config.PASSWORD);
                        }
                    });

            try {
                //Creating MimeMessage object
                MimeMessage mm = new MimeMessage(session);


                //Setting sender address
                mm.setFrom(new InternetAddress(Config.SOURCE_EMAIL));
                //Adding receiver
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                //Adding subject
                mm.setSubject(subject);
                //Adding message
                mm.setText(message, "utf-8", "html");

                //Sending email

                Transport.send(mm);

            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


}
