package com.example.hmohamadi.ebooklibrary.Models;

public class AboutUs_Model {
    private int _id;
    private int Lang_ID;
    private String Version;
    private String Dsc;

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

    public String getDsc() {
        return Dsc;
    }

    public void setDsc(String dsc) {
        Dsc = dsc;
    }
}
