package com.example.hmohamadi.ebooklibrary.Models;

public class ReadPositionsModel {

    private int id =0;
    private String jsontext="";
    private String bookid = "";

    private ReadPositionsModel() {
    }

    public static ReadPositionsModel createReadPositionsModel() {
        return new ReadPositionsModel();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsontext() {
        return jsontext;
    }

    public void setJsontext(String jsontext) {
        this.jsontext = jsontext;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }
}
