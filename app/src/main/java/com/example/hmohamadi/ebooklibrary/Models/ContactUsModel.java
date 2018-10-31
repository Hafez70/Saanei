package com.example.hmohamadi.ebooklibrary.Models;

import android.support.v7.app.AppCompatActivity;

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

    public String getContactUsDescriptionAsString(ContactUsModel _contactUS, String AddressLabel,
                                                  String PostCodeLabel, String MailBoxLabel, String PhoneNumbersLabel, String FaxLabel) {
        String result = "";
        if (_contactUS.getAddress() != null) {
            result += AddressLabel + ": " + _contactUS.getAddress() + "\n";
        }
        if (_contactUS.getPostCode() != null) {
            result += PostCodeLabel + ": " + _contactUS.getPostCode() + "\n";
        }
        if (_contactUS.getMailBox() != null) {
            result += MailBoxLabel + ": " + _contactUS.getMailBox() + "\n";
        }
        if (_contactUS.getPreTel1() != null || _contactUS.getTel1() != null) {
            if (_contactUS.getPreTel1() != null)
                result += PhoneNumbersLabel + ": (" + _contactUS.getPreTel1() + ") ";

            if (_contactUS.getPreTel1() != null)
                result += _contactUS.getTel1() + "\n";
        }
        if (_contactUS.getPreTel2() != null || _contactUS.getTel2() != null) {
            result += "(" + _contactUS.getPreTel2() + ") " + _contactUS.getTel2() + "\n";
        }
        if (_contactUS.getPreTel3() != null || _contactUS.getTel3() != null) {

            if (_contactUS.getPreTel3() != null)
                result += "(" + _contactUS.getPreTel3() + ") ";

            if (_contactUS.getTel3() != null)
                result += _contactUS.getTel3() + "\n";
        }
        if (_contactUS.getPreTel4() != null || _contactUS.getTel4() != null) {
            if (_contactUS.getPreTel4() != null)
                result += "(" + _contactUS.getPreTel4() + ") ";

            if (_contactUS.getTel4() != null)
                result += _contactUS.getTel4() + "\n";
        }
        if (_contactUS.getPreTel5() != null || _contactUS.getTel5() != null) {

            if (_contactUS.getPreTel5() != null)
                result += "(" + _contactUS.getPreTel5() + ") ";

            if (_contactUS.getTel5() != null)
                result += _contactUS.getTel5() + "\n";
        }

        if (_contactUS.getPreFax() != null || _contactUS.getFax() != null) {

            result += FaxLabel + ": ";
            if (_contactUS.getPreFax() != null)
                result += "(" + _contactUS.getPreTel2() + ") ";
            if (_contactUS.getFax() != null)
                result += _contactUS.getTel2() + "\n";
        }
        return result;
    }

    public String getMailBox() {
        return MailBox;
    }

    public void setMailBox(String mailBox) {
        MailBox = mailBox;
    }
}
