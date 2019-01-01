package com.example.hmohamadi.ebooklibrary.Models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactUsModel {
    private int _id = 0;
    private int Lang_ID = 0;
    private int RowNumber = 0;
    private String Version;
    private String Address;
    private String Title;
    private String PostCode;
    private String MailBox;
    private String PreTel1;
    private String Tel1;
    private String PreTel2;
    private String Tel2;
    private String PreTel3;
    private String Tel3;
    private String PreTel4;
    private String Tel4;
    private String PreTel5;
    private String Tel5;
    private String PreFax;
    private String Fax;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getLang_ID() {
        return Lang_ID;
    }

    public void setLang_ID(int lang_ID) {
        Lang_ID = lang_ID;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getPreTel1() {
        return PreTel1;
    }

    public void setPreTel1(String preTel1) {
        PreTel1 = preTel1;
    }

    public String getTel1() {
        return Tel1;
    }

    public void setTel1(String tel1) {
        Tel1 = tel1;
    }

    public String getPreTel2() {
        return PreTel2;
    }

    public void setPreTel2(String preTel2) {
        PreTel2 = preTel2;
    }

    public String getTel2() {
        return Tel2;
    }

    public void setTel2(String tel2) {
        Tel2 = tel2;
    }

    public String getPreTel3() {
        return PreTel3;
    }

    public void setPreTel3(String preTel3) {
        PreTel3 = preTel3;
    }

    public String getTel3() {
        return Tel3;
    }

    public void setTel3(String tel3) {
        Tel3 = tel3;
    }

    public String getPreTel4() {
        return PreTel4;
    }

    public void setPreTel4(String preTel4) {
        PreTel4 = preTel4;
    }

    public String getTel4() {
        return Tel4;
    }

    public void setTel4(String tel4) {
        Tel4 = tel4;
    }

    public String getPreTel5() {
        return PreTel5;
    }

    public void setPreTel5(String preTel5) {
        PreTel5 = preTel5;
    }

    public String getTel5() {
        return Tel5;
    }

    public void setTel5(String tel5) {
        Tel5 = tel5;
    }

    public String getPreFax() {
        return PreFax;
    }

    public void setPreFax(String preFax) {
        PreFax = preFax;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(int rowNumber) {
        RowNumber = rowNumber;
    }

    public String getContactUsDescriptionAsString(String AddressLabel,
                                                  String PostCodeLabel, String MailBoxLabel, String PhoneNumbersLabel, String FaxLabel) {
        String result = "";
        if (this.getAddress() != null) {
            result += AddressLabel + ": " + this.getAddress() + "\n";
        }
        if (this.getPostCode() != null) {
            result += PostCodeLabel + ": " + this.getPostCode() + "\n";
        }
        if (this.getMailBox() != null) {
            result += MailBoxLabel + ": " + this.getMailBox() + "\n";
        }
        if (this.getPreTel1() != null || this.getTel1() != null) {
            if (this.getPreTel1() != null)
                result += PhoneNumbersLabel + ": (" + this.getPreTel1() + ") ";

            if (this.getPreTel1() != null)
                result += this.getTel1() + "\n";
        }
        if (this.getPreTel2() != null || this.getTel2() != null) {
            if (this.getPreTel2() != null)
                result += "(" + this.getPreTel2() + ") ";

            if (this.getTel2() != null)
                result += this.getTel2() + "\n";
        }
        if (this.getPreTel3() != null || this.getTel3() != null) {

            if (this.getPreTel3() != null)
                result += "(" + this.getPreTel3() + ") ";

            if (this.getTel3() != null)
                result += this.getTel3() + "\n";
        }
        if (this.getPreTel4() != null || this.getTel4() != null) {
            if (this.getPreTel4() != null)
                result += "(" + this.getPreTel4() + ") ";

            if (this.getTel4() != null)
                result += this.getTel4() + "\n";
        }
        if (this.getPreTel5() != null || this.getTel5() != null) {

            if (this.getPreTel5() != null)
                result += "(" + this.getPreTel5() + ") ";

            if (this.getTel5() != null)
                result += this.getTel5() + "\n";
        }

        if (this.getPreFax() != null || this.getFax() != null) {

            result += FaxLabel + ": ";
            if (this.getPreFax() != null)
                result += "(" + this.getPreFax() + ") ";

            if (this.getFax() != null)
                result += this.getFax() + "\n";
        }
        return result;
    }

    public static List<String> Get_Tells_list(int ID, Context mycontext)
    {
        DBHelper db = new DBHelper(mycontext);
        ContactUsModel newmodel = db.get_ContactUs_With_ID(ID); 
        
        
        List<String> lst= new ArrayList<>(); 
        if (newmodel.getPreTel1() != null || newmodel.getTel1() != null) {
            String tell = "";
            if (newmodel.getPreTel1() != null)
            {
                tell = newmodel.getPreTel1();
            }  

            if (newmodel.getTel1() != null)
                tell += newmodel.getTel1() ;  
            
            lst.add(tell) ;
        }
        if (newmodel.getPreTel2() != null || newmodel.getTel2() != null) {
            String tell = "";
            if (newmodel.getPreTel2() != null)
                tell += newmodel.getPreTel2();

            if (newmodel.getTel2() != null)
                tell += newmodel.getTel2() ;

            lst.add(tell) ;
        }
        if (newmodel.getPreTel3() != null || newmodel.getTel3() != null) {
            String tell = "";
            if (newmodel.getPreTel3() != null)
                tell += newmodel.getPreTel3();

            if (newmodel.getTel3() != null)
                tell += newmodel.getTel3() ;

            lst.add(tell) ;
        }
        if (newmodel.getPreTel4() != null || newmodel.getTel4() != null) {
            String tell = "";
            if (newmodel.getPreTel4() != null)
                tell += newmodel.getPreTel4();

            if (newmodel.getTel4() != null)
                tell += newmodel.getTel4() ;

            lst.add(tell) ;
        }
        if (newmodel.getPreTel5() != null || newmodel.getTel5() != null) {
            String tell = "";
            if (newmodel.getPreTel5() != null)
                tell += newmodel.getPreTel5();

            if (newmodel.getTel5() != null)
                tell += newmodel.getTel5() ;

            lst.add(tell) ;
        }
        
        return lst;
    }
    
    public String getMailBox() {
        return MailBox;
    }

    public void setMailBox(String mailBox) {
        MailBox = mailBox;
    }
}
