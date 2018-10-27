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

    //region Book Table
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
    //endregion

    //region Category Table
    private String tblCategory = "tblCategory";
    //columns
    public String ID_tblCategory = "_id";
    public String Desc_tblCategory = "Description";
    public String Fav_tblCategory = "Favorite";
    public String Lang_ID_tblCategory = "Lang_ID";
    //endregion

    //region Languages Table
    private String tblLanguage = "tblLanguage";
    //columns
    public String ID_tblLanguage = "_id";
    public String Name_tblLanguage = "name";
    //endregion Languages Table

    //region ContactUs Table
    private String tblContactUs = "tblContactUs";
    //columns
    public String ID_tblContactUs = "_id";
    public String Lang_ID_tblContactUs = "Lang_ID";
    public String Version_tblContactUs = "Version";
    public String Title_tblContactUs = "Titel";
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
    public String RowNumber_tblContactUs = "RowNumber";

    //endregion

    //region Settings Table
    private String tblSettings = "tblSettings";
    //columns
    public String ID_tblSettings = "_id";
    public String WebSiteUrl_tblSettings = "WebSiteUrl";
    public String SenderEmailAddress_tblSettings = "SenderEmailAddress";
    public String SenderPassword_tblSettings = "SenderPassword";
    public String RecieverEmailAddress_tblSettings = "RecieverEmailAddress";
    public String SMTPHost_tblSettings = "SMTPHost";
    public String SMTPSocketFactoryPort_tblSettings = "SMTPSocketFactoryPort";
    public String SMTPSocketFactoryClass_tblSettings = "SMTPSocketFactoryClass";
    public String SMTPAuth_tblSettings = "SMTPAuth";
    public String SMTPPort_tblSettings = "SMTPPort";
    public String VojoohatSiteUrl_tblSettings = "VojoohatSiteUrl";
    public String Version_tblSettings = "Version";
    //endregion

    public DBHelper(Context context) {
        _openHelper = new DBOpenHelper(context);
    }

    class DBOpenHelper extends SQLiteOpenHelper {
        DBOpenHelper(Context context) {
            super(context, DBName, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            //region DeclareTables
            db.execSQL("create table " + tblBook + " (" +
                    ID_tblBook + " integer primary key autoincrement, " +
                    Name_tblBook + " text, " +
                    Title_tblBook + " text, " +
                    Cat_ID_tblBook + " integer, " +
                    AutorName_tblBook + " text, " +
                    Year_tblBook + " text, " +
                    ISBN_tblBook + " text, " +
                    Url_path_tblBook + " text, " +
                    Url_Image_tblBook + " text, " +
                    JsonText_tblBook + " text, " +
                    Version_tblBook + " text, " +
                    Keywords_tblBook + " text)");

            db.execSQL("create table " + tblCategory + " (" +
                    ID_tblCategory + " integer primary key autoincrement, " +
                    Desc_tblCategory + " text, " +
                    Fav_tblCategory + " text, " +
                    Lang_ID_tblCategory + " integer)");

            db.execSQL("create table " + tblLanguage + " (" +
                    ID_tblLanguage + " integer primary key, " +
                    Name_tblLanguage + " text)");

            db.execSQL("create table " + tblContactUs + " (" +
                    ID_tblContactUs + " integer primary key autoincrement, " +
                    Lang_ID_tblContactUs + " integer," +
                    Version_tblContactUs + " text, " +
                    Title_tblContactUs + " text, " +
                    Address_tblContactUs + " text, " +
                    PostCode_tblContactUs + " text, " +
                    PreTel1_tblContactUs + " text, " +
                    Tel1_tblContactUs + " text, " +
                    PreTel2_tblContactUs + " text, " +
                    Tel2_tblContactUs + " text, " +
                    PreTel3_tblContactUs + " text, " +
                    Tel3_tblContactUs + " text, " +
                    PreTel4_tblContactUs + " text, " +
                    Tel4_tblContactUs + " text, " +
                    PreTel5_tblContactUs + " text, " +
                    Tel5_tblContactUs + " text, " +
                    PreFax_tblContactUs + " text, " +
                    Fax_tblContactUs + " text, " +
                    Tel5_tblContactUs + " text, " +
                    RowNumber_tblContactUs + " integer, " +
                    Fax_tblContactUs + " text)");

            db.execSQL("create table " + tblSettings + " (" +
                    ID_tblSettings + " integer primary key autoincrement, " +
                    WebSiteUrl_tblSettings + " text, " +
                    SenderEmailAddress_tblSettings + " text, " +
                    SenderPassword_tblSettings + " text, " +
                    RecieverEmailAddress_tblSettings + " text, " +
                    SMTPHost_tblSettings + " text, " +
                    SMTPSocketFactoryPort_tblSettings + " text, " +
                    SMTPSocketFactoryClass_tblSettings + " text, " +
                    SMTPAuth_tblSettings + " text, " +
                    SMTPPort_tblSettings + " text, " +
                    VojoohatSiteUrl_tblSettings + " text, " +
                    Version_tblSettings + " text) " );

            //endregion

            //region InsertToTables
            //region LanguagesInsert
            ContentValues languageFarsi = new ContentValues();
            ContentValues languageArabic = new ContentValues();
            ContentValues languageEnglish = new ContentValues();

            languageFarsi.put(ID_tblLanguage, 1);
            languageFarsi.put(Name_tblLanguage, "فارسی");

            languageEnglish.put(ID_tblLanguage, 2);
            languageEnglish.put(Name_tblLanguage, "English");

            languageArabic.put(ID_tblLanguage, 3);
            languageArabic.put(Name_tblLanguage, "العربیه");

            db.insert(tblLanguage, null, languageFarsi);
            db.insert(tblLanguage, null, languageEnglish);
            db.insert(tblLanguage, null, languageArabic);
            //endregion

            //region BooksInsert
            ContentValues row = new ContentValues();
            row.put(Name_tblBook, "رساله توضیح المسائل");
            row.put(Title_tblBook, "resal");
            //row.put(Cat_ID_tblBook,bk.getCategory_ID());
            row.put(AutorName_tblBook, "آیت الله صانعی");
            row.put(Year_tblBook, "1392");
            row.put(ISBN_tblBook, "964-91554-0-6");
            row.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath("resal") + "/resal.epub");
            row.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath("resal_cover") + "/resal_cover.jpeg");
            row.put(JsonText_tblBook, "");
            row.put(Version_tblBook, "1");
            row.put(Keywords_tblBook, "");

            db.insert(tblBook, null, row);
            //endregion

            //region SettingsInsert
            ContentValues setting = new ContentValues();
            setting.put(WebSiteUrl_tblSettings, "http://www.saanei.xyz/?view=01,00,00,00,0");
            setting.put(SenderEmailAddress_tblSettings, "saaneimobileapp@gmail.com");
            setting.put(SenderPassword_tblSettings, "Saanei2018");
            setting.put(RecieverEmailAddress_tblSettings, "mostafa.abdollahi@gmail.com");
            setting.put(SMTPHost_tblSettings,"smtp.gmail.com");
            setting.put(SMTPSocketFactoryPort_tblSettings, "465");
            setting.put(SMTPSocketFactoryClass_tblSettings, "javax.net.ssl.SSLSocketFactory");
            setting.put(SMTPAuth_tblSettings, "true");
            setting.put(SMTPPort_tblSettings, "465");
            setting.put(VojoohatSiteUrl_tblSettings, "http://saanei.xyz/?view=01,00,53,00,0");
            setting.put(Version_tblSettings, "1");

            db.insert(tblSettings, null, setting);
            //endregion

            //region ContactUSInsert Farsi
            ContentValues contactUs1Farsi = new ContentValues();
            contactUs1Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs1Farsi.put(RowNumber_tblContactUs, 1);
            contactUs1Farsi.put(Version_tblContactUs, "1");
            contactUs1Farsi.put(Title_tblContactUs, "دفتر حضرت آیت الله العظمی صانعی مدظله  (قــم )");
            contactUs1Farsi.put(Address_tblContactUs, "بلوار شهيد محمد منتظري ، کوچه هشتم ، پلاک 4");
            contactUs1Farsi.put(PostCode_tblContactUs, "3713744369");
            contactUs1Farsi.put(PreTel1_tblContactUs,"025");
            contactUs1Farsi.put(Tel1_tblContactUs, "40093774");
            contactUs1Farsi.put(PreTel2_tblContactUs, "025");
            contactUs1Farsi.put(Tel2_tblContactUs, "37744010");
            contactUs1Farsi.put(PreTel3_tblContactUs, "025");
            contactUs1Farsi.put(Tel3_tblContactUs, "37744767");
            contactUs1Farsi.put(PreTel4_tblContactUs, "025");
            contactUs1Farsi.put(Tel4_tblContactUs, "37831660-2");
            contactUs1Farsi.put(PreFax_tblContactUs, "025");
            contactUs1Farsi.put(Fax_tblContactUs, "37735080");
            db.insert(tblContactUs, null, contactUs1Farsi);

            ContentValues contactUs2Farsi = new ContentValues();
            contactUs2Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs2Farsi.put(RowNumber_tblContactUs, 2);
            contactUs2Farsi.put(Version_tblContactUs, "1");
            contactUs2Farsi.put(Title_tblContactUs, "دفتر شماره 1 تهران");
            contactUs2Farsi.put(Address_tblContactUs, "كارگرجنوبی، بالاتر از میدان پاستور ( به طرف انقلاب )، خیابان شهید سرخابی ، نبش کوچه شهید شاطرپور، پلاک 1" );
            contactUs2Farsi.put(PostCode_tblContactUs, "1316973114");
            contactUs2Farsi.put(PreTel1_tblContactUs,"021");
            contactUs2Farsi.put(Tel1_tblContactUs, "6656401-5");
            contactUs2Farsi.put(PreFax_tblContactUs, "025");
            contactUs2Farsi.put(Fax_tblContactUs, "37735080");
            db.insert(tblContactUs, null, contactUs2Farsi);

            ContentValues contactUs3Farsi = new ContentValues();
            contactUs3Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs3Farsi.put(RowNumber_tblContactUs, 3);
            contactUs3Farsi.put(Version_tblContactUs, "1");
            contactUs3Farsi.put(Title_tblContactUs, "دفتر شماره 2 تهران");
            contactUs3Farsi.put(Address_tblContactUs, "تهران  پاسداران ابتدای نگارستان ۲(شهید کاشیها) پ ۳۷" );
            contactUs3Farsi.put(PreTel1_tblContactUs,"021");
            contactUs3Farsi.put(Tel1_tblContactUs, "22885300");
            contactUs3Farsi.put(PreTel2_tblContactUs,"021");
            contactUs3Farsi.put(Tel2_tblContactUs, "22884600");
            contactUs3Farsi.put(PreTel3_tblContactUs,"021");
            contactUs3Farsi.put(Tel3_tblContactUs, "22768181");
            contactUs3Farsi.put(PreTel4_tblContactUs,"021");
            contactUs3Farsi.put(Tel4_tblContactUs, "22768080");
            contactUs3Farsi.put(PreFax_tblContactUs, "021");
            contactUs3Farsi.put(Fax_tblContactUs, "22847095");
            db.insert(tblContactUs, null, contactUs3Farsi);

            ContentValues contactUs4Farsi = new ContentValues();
            contactUs4Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs4Farsi.put(RowNumber_tblContactUs, 4);
            contactUs4Farsi.put(Version_tblContactUs, "1");
            contactUs4Farsi.put(Title_tblContactUs, "دفتر شماره 3 تهران");
            contactUs4Farsi.put(Address_tblContactUs, "شهرک غرب ، بلوار دریا ، تقاطع پاک نژاد (نبش چهار راه پاکنژاد به طرف مدیریت ) ، پلاک 178" );
            contactUs1Farsi.put(PostCode_tblContactUs, "1466943141");
            contactUs4Farsi.put(PreTel1_tblContactUs,"021");
            contactUs4Farsi.put(Tel1_tblContactUs, "88582697");
            contactUs4Farsi.put(PreTel2_tblContactUs,"021");
            contactUs4Farsi.put(Tel2_tblContactUs, "88583425");
            contactUs4Farsi.put(PreTel3_tblContactUs,"021");
            contactUs4Farsi.put(Tel3_tblContactUs, "88583415");
            contactUs4Farsi.put(PreFax_tblContactUs, "021");
            contactUs4Farsi.put(Fax_tblContactUs, "88580821");
            db.insert(tblContactUs, null, contactUs4Farsi);

            ContentValues contactUs5Farsi = new ContentValues();
            contactUs4Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs4Farsi.put(RowNumber_tblContactUs, 5);
            contactUs4Farsi.put(Version_tblContactUs, "1");
            contactUs4Farsi.put(Title_tblContactUs, "دفتر شماره 1 اصفهان");
            contactUs4Farsi.put(Address_tblContactUs, "خيابان عبدالرزاق ، كوچه قصر ، پلاک 126" );
            contactUs1Farsi.put(PostCode_tblContactUs, "8148686613");
            contactUs4Farsi.put(PreTel1_tblContactUs,"031");
            contactUs4Farsi.put(Tel1_tblContactUs, "34497660-2");
            contactUs4Farsi.put(PreFax_tblContactUs, "031");
            contactUs4Farsi.put(Fax_tblContactUs, "34493391");
            db.insert(tblContactUs, null, contactUs5Farsi);

            ContentValues contactUs6Farsi = new ContentValues();
            contactUs6Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs6Farsi.put(RowNumber_tblContactUs, 6);
            contactUs6Farsi.put(Version_tblContactUs, "1");
            contactUs6Farsi.put(Title_tblContactUs, "دفتر شماره 2 اصفهان");
            contactUs6Farsi.put(Address_tblContactUs, "خیابان دانشگاه ، خیابان توحید ، کوچه شماره 2 ، پلاک 4" );
            contactUs6Farsi.put(PostCode_tblContactUs, "8173939481");
            contactUs6Farsi.put(PreTel1_tblContactUs,"031");
            contactUs6Farsi.put(Tel1_tblContactUs, "36281917");
            contactUs6Farsi.put(PreTel2_tblContactUs,"031");
            contactUs6Farsi.put(Tel2_tblContactUs, "36271093");

            ContentValues contactUs7Farsi = new ContentValues();
            contactUs7Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs7Farsi.put(RowNumber_tblContactUs, 7);
            contactUs7Farsi.put(Version_tblContactUs, "1");
            contactUs7Farsi.put(Title_tblContactUs, "دفتر شماره 3 اصفهان");
            contactUs7Farsi.put(Address_tblContactUs, "نجف آباد - خیابان فردوسی شمالی، خیابان نوبهار، پلاک 21 (جنب فیزیوتراپی)" );
            contactUs7Farsi.put(PreTel1_tblContactUs,"031");
            contactUs7Farsi.put(Tel1_tblContactUs, "42616551");
            contactUs7Farsi.put(PreTel1_tblContactUs,"031");
            contactUs7Farsi.put(Tel1_tblContactUs, "42621173");
            db.insert(tblContactUs, null, contactUs7Farsi);

            ContentValues contactUs8Farsi = new ContentValues();
            contactUs8Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs8Farsi.put(RowNumber_tblContactUs, 8);
            contactUs8Farsi.put(Version_tblContactUs, "1");
            contactUs8Farsi.put(Title_tblContactUs, "دفـتر استان خراسان رضوی (مشهـد مقدّس)");
            contactUs8Farsi.put(Address_tblContactUs, "چهارراه شهداء ، خيابان آیت الله بهجت ( آزادى ) ، کوچه بهجت 1 / 9 ( بينادل ) ، پلاك 16");
            contactUs8Farsi.put(PostCode_tblContactUs, "9143786496");
            contactUs8Farsi.put(PreTel1_tblContactUs,"051");
            contactUs8Farsi.put(Tel1_tblContactUs, "32222577");
            contactUs8Farsi.put(PreTel2_tblContactUs, "051");
            contactUs8Farsi.put(Tel2_tblContactUs, "32230323");
            contactUs8Farsi.put(PreTel3_tblContactUs, "051");
            contactUs8Farsi.put(Tel3_tblContactUs, "32243142-3");
            contactUs8Farsi.put(PreTel4_tblContactUs, "051");
            contactUs8Farsi.put(Tel4_tblContactUs, "32210002");
            contactUs8Farsi.put(PreTel5_tblContactUs, "051");
            contactUs8Farsi.put(Tel5_tblContactUs, "32251152");
            contactUs8Farsi.put(PreFax_tblContactUs, "051");
            contactUs8Farsi.put(Fax_tblContactUs, "32230324");
            db.insert(tblContactUs, null, contactUs8Farsi);

            ContentValues contactUs9Farsi = new ContentValues();
            contactUs9Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs9Farsi.put(RowNumber_tblContactUs, 9);
            contactUs9Farsi.put(Version_tblContactUs, "1");
            contactUs9Farsi.put(Title_tblContactUs, "دفتر استان فارس (شيـراز)");
            contactUs9Farsi.put(Address_tblContactUs, "شاه چراغ (ع) ، بلوار شهيد آيت الله دستغيب ، روبروی پایانه اتوبوسرانی");
            contactUs9Farsi.put(PostCode_tblContactUs, "7139863985");
            contactUs9Farsi.put(PreTel1_tblContactUs,"071");
            contactUs9Farsi.put(Tel1_tblContactUs, "32243498");
            contactUs9Farsi.put(PreTel2_tblContactUs, "071");
            contactUs9Farsi.put(Tel2_tblContactUs, "32243334");
            contactUs9Farsi.put(PreTel3_tblContactUs, "071");
            contactUs9Farsi.put(Tel3_tblContactUs, "32230400-402");
            contactUs9Farsi.put(PreTel4_tblContactUs, "071");
            contactUs9Farsi.put(Tel4_tblContactUs, "32222294");
            contactUs9Farsi.put(PreFax_tblContactUs, "071");
            contactUs9Farsi.put(Fax_tblContactUs, "32226700");
            db.insert(tblContactUs, null, contactUs9Farsi);

            ContentValues contactUs10Farsi = new ContentValues();
            contactUs10Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs10Farsi.put(RowNumber_tblContactUs, 10);
            contactUs10Farsi.put(Version_tblContactUs, "1");
            contactUs10Farsi.put(Title_tblContactUs, "دفتر استان مرکـزی ( اراک )");
            contactUs10Farsi.put(Address_tblContactUs, "خيابان ادب جو ، مقابل دبیرستان ریحانة النبی ( طالقانی سابق ) ، پلاك 8877");
            contactUs10Farsi.put(PostCode_tblContactUs, "3814698877");
            contactUs10Farsi.put(PreTel1_tblContactUs,"086");
            contactUs10Farsi.put(Tel1_tblContactUs, "33259777");
            contactUs10Farsi.put(PreTel2_tblContactUs, "086");
            contactUs10Farsi.put(Tel2_tblContactUs, "32272300");
            contactUs10Farsi.put(PreTel3_tblContactUs, "086");
            contactUs10Farsi.put(Tel3_tblContactUs, "32272200");
            contactUs10Farsi.put(PreFax_tblContactUs, "086");
            contactUs10Farsi.put(Fax_tblContactUs, "33259777");
            db.insert(tblContactUs, null, contactUs8Farsi);

            ContentValues contactUs11Farsi = new ContentValues();
            contactUs11Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs11Farsi.put(RowNumber_tblContactUs, 11);
            contactUs11Farsi.put(Version_tblContactUs, "1");
            contactUs11Farsi.put(Title_tblContactUs, "دفتر استان آذربایجان شرقی (تبـریز)");
            contactUs11Farsi.put(Address_tblContactUs, "خیابان شهید رجائی، گلشن 2، گذر محله درب نو، منزل آقای قدس علوی");
            contactUs11Farsi.put(PostCode_tblContactUs, "5136975499");
            contactUs11Farsi.put(PreTel1_tblContactUs,"041");
            contactUs11Farsi.put(Tel1_tblContactUs, "3532023");
            contactUs11Farsi.put(PreTel2_tblContactUs, "041");
            contactUs11Farsi.put(Tel2_tblContactUs, "35264626");
            contactUs11Farsi.put(PreFax_tblContactUs, "041");
            contactUs11Farsi.put(Fax_tblContactUs, "35252774");
            db.insert(tblContactUs, null, contactUs11Farsi);

            ContentValues contactUs12Farsi = new ContentValues();
            contactUs12Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs12Farsi.put(RowNumber_tblContactUs, 12);
            contactUs12Farsi.put(Version_tblContactUs, "1");
            contactUs12Farsi.put(Title_tblContactUs, "دفتر استان گلستان (گـرگان)");
            contactUs12Farsi.put(Address_tblContactUs, "خیابان شهید رجائی، گلشن 2 ، گذر محله درب نو، منزل آقای قدس علوی");
            contactUs12Farsi.put(PostCode_tblContactUs, "4913616583");
            contactUs12Farsi.put(PreTel1_tblContactUs,"017");
            contactUs12Farsi.put(Tel1_tblContactUs, "32332270");
            contactUs12Farsi.put(PreTel2_tblContactUs, "017");
            contactUs12Farsi.put(Tel2_tblContactUs, "32233270");
            db.insert(tblContactUs, null, contactUs12Farsi);

            //endregion

            //region ContactUSInsert English
            ContentValues contactUs1English = new ContentValues();
            contactUs1English.put(Lang_ID_tblContactUs, 2);
            contactUs1English.put(RowNumber_tblContactUs, 1);
            contactUs1English.put(Version_tblContactUs, "1");
            contactUs1English.put(Title_tblContactUs, "Qom (Central) office");
            contactUs1English.put(Address_tblContactUs, "No.4, 8th lane, Mohammad Montazeri Ave, Qom, Iran");
            contactUs1English.put(PreTel1_tblContactUs,"+9825");
            contactUs1English.put(Tel1_tblContactUs, "37744767");
            contactUs1English.put(PreTel2_tblContactUs, "+9825");
            contactUs1English.put(Tel2_tblContactUs, "37744009");
            contactUs1English.put(PreTel3_tblContactUs, "+9825");
            contactUs1English.put(Tel3_tblContactUs, "37744010");
            contactUs1English.put(PreTel4_tblContactUs, "+9825");
            contactUs1English.put(Tel4_tblContactUs, "7831660");
            contactUs1English.put(PreTel5_tblContactUs, "+9825");
            contactUs1English.put(Tel5_tblContactUs, "37831661");
            contactUs1English.put(PreFax_tblContactUs, "+9825");
            contactUs1English.put(Fax_tblContactUs, "37735080");
            db.insert(tblContactUs, null, contactUs1English);

            ContentValues contactUs2English = new ContentValues();
            contactUs2English.put(Lang_ID_tblContactUs, 2);
            contactUs2English.put(RowNumber_tblContactUs, 2);
            contactUs2English.put(Version_tblContactUs, "1");
            contactUs2English.put(Title_tblContactUs, "Tehran office (1)");
            contactUs2English.put(Address_tblContactUs, " No.17, Kaamyab lane, between Azarbayejan and Jomhuri Avenues, south Kargar Ave, Tehran, Iran");
            contactUs2English.put(PreTel1_tblContactUs,"+9821");
            contactUs2English.put(Tel1_tblContactUs, "66564000");
            contactUs2English.put(PreTel2_tblContactUs, "+9821");
            contactUs2English.put(Tel2_tblContactUs, "66564001");
            contactUs2English.put(PreTel3_tblContactUs, "+9821");
            contactUs2English.put(Tel3_tblContactUs, "66564002");
            contactUs2English.put(PreTel4_tblContactUs, "+9821");
            contactUs2English.put(Tel4_tblContactUs, "66564003");
            contactUs2English.put(PreTel5_tblContactUs, "+9821");
            contactUs2English.put(Tel5_tblContactUs, "66564004");
            contactUs2English.put(PreFax_tblContactUs, "+9821");
            contactUs2English.put(Fax_tblContactUs, "66564005");
            db.insert(tblContactUs, null, contactUs2English);

            ContentValues contactUs3English = new ContentValues();
            contactUs3English.put(RowNumber_tblContactUs, 3);
            contactUs3English.put(Version_tblContactUs, "1");
            contactUs3English.put(Title_tblContactUs, "Tehran office (2)");
            contactUs3English.put(Address_tblContactUs, "First Floor, Boustan complex, Near 10th Boustan");
            contactUs3English.put(PreTel1_tblContactUs,"+9821");
            contactUs3English.put(Tel1_tblContactUs, "22768181");
            contactUs3English.put(PreTel2_tblContactUs, "+9821");
            contactUs3English.put(Tel2_tblContactUs, "22768080");
            contactUs3English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs3English);

            ContentValues contactUs4English = new ContentValues();
            contactUs3English.put(RowNumber_tblContactUs, 2);
            contactUs3English.put(Version_tblContactUs, "1");
            contactUs3English.put(Title_tblContactUs, "Tehran office (2)");
            contactUs3English.put(Address_tblContactUs, "First Floor, Boustan complex, Near 10th Boustan");
            contactUs3English.put(PreTel1_tblContactUs,"+9821");
            contactUs3English.put(Tel1_tblContactUs, "22768181");
            contactUs3English.put(PreTel2_tblContactUs, "+9821");
            contactUs3English.put(Tel2_tblContactUs, "22768080");
            contactUs3English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs3English);
            //endregion

            //endregion
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    //region /////////////////////// BOOK ////////////////////////////////////
    public ArrayList<Book_Model> getAll_Books() {
        ArrayList<Book_Model> resultLst = new ArrayList<Book_Model>();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblBook, null);

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

        row.put(Name_tblBook, bk.getName());
        row.put(Title_tblBook, bk.getTitle());
        row.put(Cat_ID_tblBook, bk.getCategory_ID());
        row.put(AutorName_tblBook, bk.getAutorname());
        row.put(Year_tblBook, bk.getYear());
        row.put(ISBN_tblBook, bk.getIsbn());
        row.put(Url_path_tblBook, bk.getUrl_path());
        row.put(Url_Image_tblBook, bk.getUrl_image());
        row.put(JsonText_tblBook, bk.getJsonText());
        row.put(Version_tblBook, bk.getVersion());
        row.put(Keywords_tblBook, bk.getKeywords());

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

        db.delete(tblBook, ID_tblBook + " = ?", new String[]{String.valueOf(bk.getId())});
        db.close();
    }

    public void update_Book(Book_Model bk) {

        SQLiteDatabase db = _openHelper.getWritableDatabase();

        if (db == null) {
            return;
        }

        ContentValues row = new ContentValues();

        row.put(Name_tblBook, bk.getName());
        row.put(Title_tblBook, bk.getTitle());
        row.put(Cat_ID_tblBook, bk.getCategory_ID());
        row.put(AutorName_tblBook, bk.getAutorname());
        row.put(Year_tblBook, bk.getYear());
        row.put(ISBN_tblBook, bk.getIsbn());
        row.put(Url_path_tblBook, bk.getUrl_path());
        row.put(Url_Image_tblBook, bk.getUrl_image());
        row.put(JsonText_tblBook, bk.getJsonText());
        row.put(Version_tblBook, bk.getVersion());
        row.put(Keywords_tblBook, bk.getKeywords());

        db.update(tblBook, row, ID_tblBook + " = ?", new String[]{String.valueOf(bk.getId())});
        db.close();
    }

    public Book_Model get_Book(Integer id) {
        Book_Model row = new Book_Model();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblBook + " where " + ID_tblBook + " = ?", new String[]{String.valueOf(id)});
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
        String _Jsonstring = "";
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select " + JsonText_tblBook + " from " + tblBook + " where " + ID_tblBook + " = ?", new String[]{String.valueOf(id)});
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


        row.put(JsonText_tblBook, bk.getJsonText());


        db.update(tblBook, row, ID_tblBook + " = ?", new String[]{String.valueOf(bk.getId())});
        db.close();
    }
    //endregion///////////////////////////// BOOK end ////////////////////////////////////

    //region /////////////////////// ContactUs ////////////////////////////////////
    public ArrayList<Book_Model> getAll_ContactUs() {
        ArrayList<Book_Model> resultLst = new ArrayList<Book_Model>();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblBook, null);

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



