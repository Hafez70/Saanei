package com.example.hmohamadi.ebooklibrary;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.TextView;

import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.ContactUsModel;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.util.AppUtil;

import java.util.List;


public class ContactUsActivity extends AppCompatActivity {

    public TextView txtTitle;
    public TextView txtDescription;
    public RelativeLayout buttonLayout;

    public ConstraintLayout container;
    Config _conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        LinearLayoutCompat viewContainer =(LinearLayoutCompat)findViewById(R.id.container_contactus);
        viewContainer.removeAllViewsInLayout();
        viewContainer.setOrientation(LinearLayout.VERTICAL);

        _conf = AppUtil.getSavedConfig(this);
        List<ContactUsModel> lstModel = GetData(_conf.getLanguage());

        for (ContactUsModel model :
                lstModel) {
            viewContainer.addView(MakeView(
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
    }


    private View MakeView(String _title,String _description)
    {
        LayoutInflater factory = LayoutInflater.from(this);

        final View MyView = factory.inflate(R.layout.contactus_item, null);

        //region init components
        TextView lblTitle = (TextView)MyView.findViewById(R.id.lbl_title);
        TextView lbldescription = (TextView)MyView.findViewById(R.id.lbl_description);
        lbldescription.setTextDirection(View.TEXT_DIRECTION_RTL);

        if(_conf.getLanguage() == Constants.LANG_EN) {
            lbldescription.setTextDirection(View.TEXT_DIRECTION_LTR);
            lbldescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }


        //endregion init components
        lblTitle.setText(_title);
        lbldescription.setText(_description);
        return MyView;
    }

    private List<ContactUsModel> GetData(String Lang_name)
    {
        DBHelper db = new DBHelper(this);
        return db.getAll_ContactUs_With_LanguageID(db.get_LanguageID((Lang_name.length() == 0 ? "fa":  Lang_name)).get_id());
    }

}
