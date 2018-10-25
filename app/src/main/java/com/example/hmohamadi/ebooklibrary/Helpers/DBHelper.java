package com.example.hmohamadi.ebooklibrary.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hmohamadi.ebooklibrary.Models.Book_Model;
import com.example.hmohamadi.ebooklibrary.Models.ReadPositionsModel;
import com.folioreader.util.FileUtil;

import java.security.Key;
import java.util.ArrayList;

/**
 * Created by h.mohamadi on 12/16/2017.
 */

public class DBHelper {

    private SQLiteOpenHelper _openHelper;
    private String DBName = "Saanei.db";

    // Book Table
    private String tblBook = "tblColors";
    //columns
    public String ID_tblBook = "_id";
    public String Name_tblBook = "Name";
    public String Title_tblBook = "Title";
    public String Cat_ID_tblBook = "Cat_ID";
    public String AutorName_tblBook = "AutorName";
    public String Year_tblBook = "Year";
    public String ISBN_tblBook = "ISBN";
    public String Url_path_tblBook = "Url_path";
    public String Url_Image_tblBook = "Url_Image";
    public String Keywords_tblBook = "Keywords";
    public String JsonText_tblBook = "JsonText";
    public String Version_tblBook = "version";
    // Book Table

    // Category Table
    private String tblCategory = "tblCategory";
    //columns
    public String ID_tblCategory = "_id";
    public String Desc_tblCategory = "Description";
    public String Fav_tblCategory= "Favorite";
    public String Lang_ID_tblCategory = "Lang_ID";
    // Category Table

    // Languages Table
    private String tblLanguage = "tblLanguage";
    //columns
    public String ID_tblLanguage = "_id";
    public String Name_tblLanguage = "name";
    // Languages Table

    //region// ContactUs Table
    private String tblContactUs = "tblContactUs";
    //columns
    public String ID_tblContactUs = "_id";
    public String Lang_ID_tblContactUs = "Lang_ID";
    public String Version_tblContactUs = "Version";
    public String Address_tblContactUs = "Address";
    public String PostCode_tblContactUs = "PostCode";
    public String PreTel1_tblContactUs = "PreTel1";
    public String Tel1_tblContactUs = "Tel1";
    public String PreTel2_tblContactUs = "PreTel2";
    public String Tel2_tblContactUs = "Tel2";
    public String PreTel3_tblContactUs = "PreTel3";
    public String Tel3_tblContactUs = "Tel3";
    public String PreTel4_tblContactUs = "PreTel4";
    public String Tel4_tblContactUs = "Tel4";
    public String PreTel5_tblContactUs = "PreTel5";
    public String Tel5_tblContactUs = "Tel5";
    public String PreFax_tblContactUs = "PreFax";
    public String Fax_tblContactUs = "Fax";
    //endregion// ContactUs Table

    // Settings Table
//    private String tblSettings = "tblSettings";
//    //columns
//    public String ID_tblCategory = "_id";
//    public String WebSiteUrl_tblCategory = "WebSiteUrl";
//    public String Fav_tblCategory= "SenderEmailAddress";
//    public String Lang_ID_tblCategory = "SenderPassword";
//    public String Lang_ID_tblCategory = "RecieverEmailAddress";
//    public String Lang_ID_tblCategory = "SMTPHost";
//    public String Lang_ID_tblCategory = "SMTPSocketFactoryPort";
//    public String Lang_ID_tblCategory = "SMTPSocketFactoryClass";
//    public String Lang_ID_tblCategory = "SMTPAuth";
//    public String Lang_ID_tblCategory = "SMTPPort";
//    public String Lang_ID_tblCategory = "vojoohatSiteUrl";
//    public String Lang_ID_tblCategory = "Version";
    // Category Table
    public DBHelper(Context context) {
        _openHelper = new DBOpenHelper (context);
    }

    class DBOpenHelper extends SQLiteOpenHelper {
        DBOpenHelper (Context context) {
            super(context, DBName , null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+ tblBook  +" ("+
                    ID_tblBook+" integer primary key autoincrement, "+
                    Name_tblBook +" text, "+
                    Title_tblBook +" text, "+
                    Cat_ID_tblBook+" integer, "+
                    AutorName_tblBook+" text, "+
                    Year_tblBook+" text, "+
                    ISBN_tblBook+" text, "+
                    Url_path_tblBook+" text, "+
                    Url_Image_tblBook+" text, "+
                    JsonText_tblBook+" text, "+
                    Version_tblBook+" text, "+
                    Keywords_tblBook+" text)");

            db.execSQL("create table "+ tblCategory  +" ("+
                    ID_tblCategory+" integer primary key autoincrement, "+
                    Desc_tblCategory+" text, "+
                    Fav_tblCategory+" text, "+
                    Lang_ID_tblCategory+" integer)");

            db.execSQL("create table "+ tblLanguage  +" ("+
                    ID_tblLanguage+ " integer primary key autoincrement, "+
                    Name_tblLanguage + " text)");




            ContentValues row = new ContentValues();


            row.put(Name_tblBook,"رساله توضیح المسائل");
            row.put(Title_tblBook,"resal");
            //row.put(Cat_ID_tblBook,bk.getCategory_ID());
            row.put(AutorName_tblBook,"آیت الله صانعی");
            row.put(Year_tblBook,"1392");
            row.put(ISBN_tblBook,"964-91554-0-6");
            row.put(Url_path_tblBook,FileUtil.getFolioEpubFolderPath("resal") + "/resal.epub");
            row.put(Url_Image_tblBook,FileUtil.getFolioEpubFolderPath("resal_cover") +"/resal_cover.jpeg");
            row.put(JsonText_tblBook,"");
            row.put(Version_tblBook,"1");
            row.put(Keywords_tblBook,"");

            db.insert(tblBook, null, row);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        }
    }

    //region /////////////////////// BOOK ////////////////////////////////////
    public ArrayList<Book_Model> getAll_Books() {
        ArrayList<Book_Model> resultLst= new ArrayList<Book_Model>();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from "+tblBook, null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Book_Model row = new Book_Model();

                row.setId(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setTitle(cursor.getString(2));
                row.setCategory_ID(cursor.getInt(3));
                row.setAutorname(cursor.getString(4));
                row.setYear(cursor.getString(5));
                row.setIsbn(cursor.getString(6));
                row.setUrl_path(cursor.getString(7));
                row.setUrl_image(cursor.getString(8));
                row.setJsonText(cursor.getString(9));
                row.setVersion(cursor.getString(10));
                row.setKeywords(cursor.getString(11));

                resultLst.add(row);
            }
        }
        cursor.close();
        db.close();
        return resultLst;
    }

    public Book_Model add_Book(Book_Model bk) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return null;
        }
        ContentValues row = new ContentValues();

        row.put(Name_tblBook,bk.getName());
        row.put(Title_tblBook,bk.getTitle());
        row.put(Cat_ID_tblBook,bk.getCategory_ID());
        row.put(AutorName_tblBook,bk.getAutorname());
        row.put(Year_tblBook,bk.getYear());
        row.put(ISBN_tblBook,bk.getIsbn());
        row.put(Url_path_tblBook,bk.getUrl_path());
        row.put(Url_Image_tblBook,bk.getUrl_image());
        row.put(JsonText_tblBook,bk.getJsonText());
        row.put(Version_tblBook,bk.getVersion());
        row.put(Keywords_tblBook,bk.getKeywords());

        Long bookID = db.insert(tblBook, null, row);
        bk.setId(bookID.intValue());
        db.close();
        return bk;
    }

    public void delete_Book(Book_Model bk) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }

        db.delete(tblBook, ID_tblBook +  " = ?", new String[] { String.valueOf(bk.getId())});
        db.close();
    }

    public void update_Book(Book_Model bk) {

        SQLiteDatabase db = _openHelper.getWritableDatabase();

        if (db == null) {
            return;
        }

        ContentValues row = new ContentValues();

        row.put(Name_tblBook,bk.getName());
        row.put(Title_tblBook,bk.getTitle());
        row.put(Cat_ID_tblBook,bk.getCategory_ID());
        row.put(AutorName_tblBook,bk.getAutorname());
        row.put(Year_tblBook,bk.getYear());
        row.put(ISBN_tblBook,bk.getIsbn());
        row.put(Url_path_tblBook,bk.getUrl_path());
        row.put(Url_Image_tblBook,bk.getUrl_image());
        row.put(JsonText_tblBook,bk.getJsonText());
        row.put(Version_tblBook,bk.getVersion());
        row.put(Keywords_tblBook,bk.getKeywords());

        db.update(tblBook, row, ID_tblBook + " = ?", new String[] { String.valueOf(bk.getId()) } );
        db.close();
    }

    public Book_Model get_Book(Integer id) {
        Book_Model row = new Book_Model();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from "+tblBook+" where "+ID_tblBook+" = ?", new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {
            row.setId(cursor.getInt(0));
            row.setName(cursor.getString(1));
            row.setTitle(cursor.getString(2));
            row.setCategory_ID(cursor.getInt(3));
            row.setAutorname(cursor.getString(4));
            row.setYear(cursor.getString(5));
            row.setIsbn(cursor.getString(6));
            row.setUrl_path(cursor.getString(7));
            row.setUrl_image(cursor.getString(8));
            row.setJsonText(cursor.getString(9));
            row.setVersion(cursor.getString(10));
            row.setKeywords(cursor.getString(11));

        }
        cursor.close();
        db.close();

        return row;
    }

    public String get_Book_ReadPosition(Integer id) {
        String _Jsonstring ="";
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select "+JsonText_tblBook+" from "+tblBook+" where "+ID_tblBook+" = ?", new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {

            _Jsonstring = cursor.getString(0);

        }
        cursor.close();
        db.close();

        return _Jsonstring;
    }

    public void update_Book_ReadPosition(Book_Model bk) {

        SQLiteDatabase db = _openHelper.getWritableDatabase();

        if (db == null) {
            return;
        }

        ContentValues row = new ContentValues();


        row.put(JsonText_tblBook,bk.getJsonText());


        db.update(tblBook, row, ID_tblBook + " = ?", new String[] { String.valueOf(bk.getId()) } );
        db.close();
    }
    //endregion///////////////////////////// BOOK end ////////////////////////////////////

    //region /////////////////////// ContactUs ////////////////////////////////////
    public ArrayList<Book_Model> getAll_ContactUs() {
        ArrayList<Book_Model> resultLst= new ArrayList<Book_Model>();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from "+tblBook, null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Book_Model row = new Book_Model();

                row.setId(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setTitle(cursor.getString(2));
                row.setCategory_ID(cursor.getInt(3));
                row.setAutorname(cursor.getString(4));
                row.setYear(cursor.getString(5));
                row.setIsbn(cursor.getString(6));
                row.setUrl_path(cursor.getString(7));
                row.setUrl_image(cursor.getString(8));
                row.setJsonText(cursor.getString(9));
                row.setVersion(cursor.getString(10));
                row.setKeywords(cursor.getString(11));

                resultLst.add(row);
            }
        }
        cursor.close();
        db.close();
        return resultLst;
    }
    //endregion///////////////////////////// ContactUs ////////////////////////////////////
}



