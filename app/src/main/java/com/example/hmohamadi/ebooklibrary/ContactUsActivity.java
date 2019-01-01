package com.example.hmohamadi.ebooklibrary;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.ContactUsModel;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.util.AppUtil;
import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.util.List;


public class ContactUsActivity extends AppCompatActivity {

    public TextView txtTitle;
    public TextView txtDescription;
    public RelativeLayout buttonLayout;

    public ConstraintLayout container;
    ScrollView _scrv;
    Config _conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        _conf = AppUtil.getSavedConfig(this);
        AppUtil.ChangeLocale(this,_conf);


        _scrv = (ScrollView)findViewById(R.id.scrollContainer);

        LinearLayoutCompat viewContainer =(LinearLayoutCompat)findViewById(R.id.container_contactus);
        viewContainer.removeAllViewsInLayout();
        viewContainer.setOrientation(LinearLayout.VERTICAL);


        List<ContactUsModel> lstModel = GetData(_conf.getLanguage());

        for (ContactUsModel model :
                lstModel) {
            viewContainer.addView(MakeView(model.get_id(),
                                            model.getTitle(),
                                            model.getContactUsDescriptionAsString(
                                                                                    getString(R.string.contactUs_Address),
                                                                                    getString(R.string.contactUs_PostCode),
                                                                                    getString(R.string.contactUs_MailBox),
                                                                                    getString(R.string.contactUs_PhoneNumbers),
                                                                                    getString(R.string.contactUs_Fax)
                                            )
                                    )
                            );
        }

        ImageButton btnTElegram = (ImageButton) findViewById(R.id.btn_telegram);
        btnTElegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/saaneioffice"));
                startActivity(telegram);
            }
        });

        ImageButton btnInstagram = (ImageButton) findViewById(R.id.btn_instagram);
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(newInstagramProfileIntent(getPackageManager(),"https://www.instagram.com/ayatullah.saanei"));
            }
        });

        ImageButton btnChannel = (ImageButton) findViewById(R.id.btn_channel);
        btnChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webSite = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/saanei_office"));
                startActivity(webSite);
            }
        });

        ImageButton btnSms = (ImageButton) findViewById(R.id.btn_sms);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:30007960"));  // This ensures only SMS apps respond
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });



    }


    public static Intent newInstagramProfileIntent(PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (PackageManager.NameNotFoundException ignored) {

        }
        intent.setData(Uri.parse(url));
        return intent;
    }

    private View MakeView(int _id,String _title,String _description)
    {
        LayoutInflater factory = LayoutInflater.from(this);

        final View MyView = factory.inflate(R.layout.contactus_item, null);
        Log.w("MakeView >>>>","model ID >>>>>> "+ _id);
        //region init components
        TextView lblTitle = (TextView)MyView.findViewById(R.id.lbl_title);
        TextView lbldescription = (TextView)MyView.findViewById(R.id.lbl_description);
        ImageButton btnCall = (ImageButton)MyView.findViewById(R.id.btn_contactus_call);
        btnCall.setTag(_id);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _ID = Integer.valueOf(v.getTag().toString());
                final List<String> lst_tells = ContactUsModel.Get_Tells_list(_ID,getApplicationContext());
                Log.w("onClick > ","Get_Tells_list  size :" + lst_tells.size());

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ContactUsActivity.this,R.style.ThemeCustomAlertDialog);
                Log.w("onClick > ","dialogBuilder  pass");
                LayoutInflater inflater = getLayoutInflater();
                if(inflater == null)
                {
                    Log.w("onClick > ","inflater == null");
                }
                final View dialogView = inflater.inflate(R.layout.tell_dialog_layout, null);
                if(dialogView == null)
                {
                    Log.w("onClick > ","dialogView == null");
                }
                dialogBuilder.setView(dialogView);

                final ListView lstv = (ListView) dialogView.findViewById(R.id.lstTells);
                ArrayAdapter<String> myadapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, lst_tells)
                {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        text1.setTextColor(getResources().getColor(R.color.textColorPrimary));
                        return view;
                    }
                };


                lstv.setAdapter(myadapter);

                lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (ContextCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ContactUsActivity.this, Constants.gePhonecallPerms(), Constants.PHONE_CALL_PERMITIONN);

                        }
                        else {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + lst_tells.get(position)));
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    }
                });

                dialogBuilder.setTitle(R.string.tells_list_dialog_text);

                dialogBuilder.setNegativeButton(R.string.tells_list_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //pass
                    }
                });
                dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });
        lbldescription.setTextDirection(View.TEXT_DIRECTION_RTL);

        if(_conf.getLanguage() == Constants.LANG_EN) {
            lbldescription.setTextDirection(View.TEXT_DIRECTION_LTR);
            lbldescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }


        //endregion init components
        lblTitle.setText(_title);
        lbldescription.setText(_description);

        ExpansionLayout expansionLayout = (ExpansionLayout)MyView.findViewById(R.id.expansionLayout);
        expansionLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(final ExpansionLayout expansionLayout, boolean expanded) {
                    if(expanded)
                    {
                        expansionLayout.requestFocus();
//                        _scrv.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                _scrv.smoothScrollTo(0, expansionLayout.getBottom());
//                            }
//                        });
                    }
            }
        });

        return MyView;
    }

    private final void focusOnView(final TextView txt){

        _scrv.post(new Runnable() {
            @Override
            public void run() {
                _scrv.smoothScrollTo(0, txt.getTop());
            }
        });
    }

    private List<ContactUsModel> GetData(String Lang_name)
    {
        DBHelper db = new DBHelper(this);
        return db.getAll_ContactUs_With_LanguageID(db.get_LanguageID((Lang_name.length() == 0 ? "fa":  Lang_name)).get_id());
    }

}
