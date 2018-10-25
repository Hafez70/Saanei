package com.example.hmohamadi.ebooklibrary.Models;

import java.io.Serializable;

public class Book_Model implements Serializable {

    public Book_Model()
    {

    }

    public Book_Model(int id, String name, int category_ID, String autorname, String year, int pagecount, String isbn, String url_path, String url_image, String keywords)
    {
        this.id = id;
        this.name = name;
        this.category_ID = category_ID;
        this.autorname = autorname;
        this.year = year;
        this.pagecount = pagecount;
        this.isbn = isbn;
        this.url_path = url_path;
        this.url_image = url_image;
        this.keywords = keywords;
    }

    public Book_Model(int id, String name, String year, String autorname, String url_image) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.autorname = autorname;
        this.url_image = url_image;
    }

    private int id;
        private String name;
        private String title;
        private int category_ID;
        private String category_name;
        private String autorname;
        private String year;
        private int pagecount;
        private String isbn;
        private String url_path;
        private String url_image;
        private String jsonText;
        private String version;
        private String keywords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(int category_ID) {
        this.category_ID = category_ID;
    }

    public String getAutorname() {
        return autorname;
    }

    public void setAutorname(String autorname) {
        this.autorname = autorname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUrl_path() {
        return url_path;
    }

    public void setUrl_path(String url_path) {
        this.url_path = url_path;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
