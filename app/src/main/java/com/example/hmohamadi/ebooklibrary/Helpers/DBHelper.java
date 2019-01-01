package com.example.hmohamadi.ebooklibrary.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hmohamadi.ebooklibrary.Models.AboutUs_Model;
import com.example.hmohamadi.ebooklibrary.Models.Book_Model;
import com.example.hmohamadi.ebooklibrary.Models.ContactUsModel;
import com.example.hmohamadi.ebooklibrary.Models.Language_model;
import com.example.hmohamadi.ebooklibrary.Models.Setting_model;
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
    private String tblBook = "tblBooks";
    //columns
    public String ID_tblBook = "_id";
    public String Name_tblBook = "Name";
    public String Title_tblBook = "Title";
    //public String Cat_ID_tblBook = "Cat_ID";
    public String Lang_ID_tblBook = "Lang_ID";
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
    public String Desc_tblCategory = "Dsc";
    public String Fav_tblCategory = "Favorite";
    public String Lang_ID_tblCategory = "Lang_ID";
    //endregion

    //region Languages Table
    private String tblLanguage = "tblLanguage";
    //columns
    public String ID_tblLanguage = "_id";
    public String Name_tblLanguage = "name";
    public String Title_tblLanguage = "title";
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
    public String MailBox_tblContactUs = "MailBox";
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

    //region ContactUs Table
    private String tblAboutUs = "tblAboutUs";
    //columns
    public String ID_tblAboutUs = "_id";
    public String Lang_ID_tblAboutUs = "Lang_ID";
    public String Version_tblAboutUs = "Version";
    public String Description_tblAboutUs = "Dsc";


    //endregion

    //region Settings Table
    private String tblSettings = "tblSettings";
    //columns
    public String ID_tblSettings = "_id";
    public String Lang_ID_tblSettings = "lang_id";
    public String WebSiteUrl_tblSettings = "WebSiteUrl";

    public String NewsUrl_tblSettings = "NewsUrl";
    public String EstekhareUrl_tblSettings = "EstekhareUrl";
    public String LibraryUrl_tblSettings = "LibraryUrl";
    public String FeghhiUrl_tblSettings = "FeghhiUrl";
    public String NashriehUrl_tblSettings = "NashriehUrl";

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

    Context localContext;

    public DBHelper(Context context) {
        _openHelper = new DBOpenHelper(context);
    }

    class DBOpenHelper extends SQLiteOpenHelper {
        DBOpenHelper(Context context) {
            super(context, DBName, null, 2);
            localContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            //region DeclareTables
            db.execSQL("create table " + tblBook + " (" +
                    ID_tblBook + " integer primary key autoincrement, " +
                    Name_tblBook + " text, " +
                    Title_tblBook + " text, " +
                    Lang_ID_tblBook + " integer, " +
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
                    Title_tblLanguage + " text, " +
                    Name_tblLanguage + " text)");

            db.execSQL("create table " + tblContactUs + " (" +
                    ID_tblContactUs + " integer primary key autoincrement, " +
                    Lang_ID_tblContactUs + " integer," +
                    Version_tblContactUs + " text, " +
                    Title_tblContactUs + " text, " +
                    Address_tblContactUs + " text, " +
                    PostCode_tblContactUs + " text, " +
                    MailBox_tblContactUs + " text, " +
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
                    RowNumber_tblContactUs + " integer) "
                  );

            db.execSQL("create table " + tblAboutUs + " (" +
                    ID_tblAboutUs + " integer primary key autoincrement, " +
                    Lang_ID_tblAboutUs + " integer," +
                    Version_tblAboutUs + " text, " +
                    Description_tblAboutUs + " text) " );


            db.execSQL("create table " + tblSettings + " (" +
                    ID_tblSettings + " integer primary key autoincrement, " +
                    Lang_ID_tblSettings + " integer, " +
                    WebSiteUrl_tblSettings + " text, " +
                    NewsUrl_tblSettings + " text, " +
                    EstekhareUrl_tblSettings + " text, " +
                    LibraryUrl_tblSettings + " text, " +
                    FeghhiUrl_tblSettings + " text, " +
                    NashriehUrl_tblSettings + " text, " +
                    SenderEmailAddress_tblSettings + " text, " +
                    SenderPassword_tblSettings + " text, " +
                    RecieverEmailAddress_tblSettings + " text, " +
                    SMTPHost_tblSettings + " text, " +
                    SMTPSocketFactoryPort_tblSettings + " text, " +
                    SMTPSocketFactoryClass_tblSettings + " text, " +
                    SMTPAuth_tblSettings + " text, " +
                    SMTPPort_tblSettings + " text, " +
                    VojoohatSiteUrl_tblSettings + " text, " +
                    Version_tblSettings + " text) ");

            //endregion

            //region InsertToTables
            //region LanguagesInsert
            ContentValues languageFarsi = new ContentValues();
            ContentValues languageArabic = new ContentValues();
            ContentValues languageEnglish = new ContentValues();

            languageFarsi.put(ID_tblLanguage, 1);
            languageFarsi.put(Name_tblLanguage, "fa");
            languageFarsi.put(Title_tblLanguage, "فارسی");

            languageEnglish.put(ID_tblLanguage, 2);
            languageEnglish.put(Name_tblLanguage, "en");
            languageEnglish.put(Title_tblLanguage, "English");

            languageArabic.put(ID_tblLanguage, 3);
            languageArabic.put(Name_tblLanguage, "ar");
            languageArabic.put(Title_tblLanguage, "العربیه");

            db.insert(tblLanguage, null, languageFarsi);
            db.insert(tblLanguage, null, languageEnglish);
            db.insert(tblLanguage, null, languageArabic);
            //endregion

            //region BooksInsert
            ContentValues row_resaleh_org = new ContentValues();
            row_resaleh_org.put(Name_tblBook, "رساله توضیح المسائل");
            row_resaleh_org.put(Title_tblBook, "resaleh_org");
            row_resaleh_org.put(Lang_ID_tblBook,1);
            row_resaleh_org.put(AutorName_tblBook, "آیت الله صانعی");
            row_resaleh_org.put(Year_tblBook, "");
            row_resaleh_org.put(ISBN_tblBook, "");
            row_resaleh_org.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"resaleh_org") + "/resaleh_org.epub");
            row_resaleh_org.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"resaleh_org_cover") + "/resaleh_org_cover.jpg");
            row_resaleh_org.put(JsonText_tblBook, "");
            row_resaleh_org.put(Version_tblBook, "1");
            row_resaleh_org.put(Keywords_tblBook, "مسائلی در تمامی ابواب فقه؛ اعم از احکام تقلید، احکام طهارت، احکام نماز، احکام روزه، احکام اعتکاف، احکام خمس ...");

            db.insert(tblBook, null, row_resaleh_org);

            ContentValues row_majma_1 = new ContentValues();
            row_majma_1.put(Name_tblBook, "مجمع المسائل جلد اول");
            row_majma_1.put(Title_tblBook, "majma_1");
            row_majma_1.put(Lang_ID_tblBook,1);
            row_majma_1.put(AutorName_tblBook, "آیت الله صانعی");
            row_majma_1.put(Year_tblBook, "");
            row_majma_1.put(ISBN_tblBook, "");
            row_majma_1.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"majma_1") + "/majma_1.epub");
            row_majma_1.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"majma_1_cover") + "/majma_1_cover.jpg");
            row_majma_1.put(JsonText_tblBook, "");
            row_majma_1.put(Version_tblBook, "1");
            row_majma_1.put(Keywords_tblBook, "جمع آوري استفتائات توسط چند تن از شاگردان را بيان کرده و تنظيم پرسش ها و پاسخ ها همراه با تاريخ آن را بيان مي کند.");

            db.insert(tblBook, null, row_majma_1);

            ContentValues row_majma_2 = new ContentValues();
            row_majma_2.put(Name_tblBook, "مجمع المسائل جلد دوم");
            row_majma_2.put(Title_tblBook, "majma_2");
            row_majma_2.put(Lang_ID_tblBook,1);
            row_majma_2.put(AutorName_tblBook, "آیت الله صانعی");
            row_majma_2.put(Year_tblBook, "");
            row_majma_2.put(ISBN_tblBook, "");
            row_majma_2.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"majma_2") + "/majma_2.epub");
            row_majma_2.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"majma_2_cover") + "/majma_2_cover.jpg");
            row_majma_2.put(JsonText_tblBook, "");
            row_majma_2.put(Version_tblBook, "1");
            row_majma_2.put(Keywords_tblBook,  "جمع آوري استفتائات توسط چند تن از شاگردان را بيان کرده و تنظيم پرسش ها و پاسخ ها همراه با تاريخ آن را بيان ميکند.");

            db.insert(tblBook, null, row_majma_2);


            ContentValues row_majma_3 = new ContentValues();
            row_majma_3.put(Name_tblBook, "مجمع المسائل جلد سوم");
            row_majma_3.put(Title_tblBook, "majma_3");
            row_majma_3.put(Lang_ID_tblBook,1);
            row_majma_3.put(AutorName_tblBook, "آیت الله صانعی");
            row_majma_3.put(Year_tblBook, "");
            row_majma_3.put(ISBN_tblBook, "");
            row_majma_3.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"majma_3") + "/majma_3.epub");
            row_majma_3.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"majma_3_cover") + "/majma_3_cover.jpg");
            row_majma_3.put(JsonText_tblBook, "");
            row_majma_3.put(Version_tblBook, "1");
            row_majma_3.put(Keywords_tblBook,  "جمع آوري استفتائات توسط چند تن از شاگردان را بيان کرده و تنظيم پرسش ها و پاسخ ها همراه با تاريخ آن را بيان مي کند.");

            db.insert(tblBook, null, row_majma_3);


            ContentValues row_esteftaat_ghazayee_1 = new ContentValues();
            row_esteftaat_ghazayee_1.put(Name_tblBook, "استفتاآت قضایی جلد اول");
            row_esteftaat_ghazayee_1.put(Title_tblBook, "esteftaat_ghazayee_1");
            row_esteftaat_ghazayee_1.put(Lang_ID_tblBook,1);
            row_esteftaat_ghazayee_1.put(AutorName_tblBook, "آیت الله صانعی");
            row_esteftaat_ghazayee_1.put(Year_tblBook, "");
            row_esteftaat_ghazayee_1.put(ISBN_tblBook, "");
            row_esteftaat_ghazayee_1.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"esteftaat_ghazayee_1") + "/esteftaat_ghazayee_1.epub");
            row_esteftaat_ghazayee_1.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"esteftaat_ghazayee_1_cover") + "/esteftaat_ghazayee_1_cover.jpg");
            row_esteftaat_ghazayee_1.put(JsonText_tblBook, "");
            row_esteftaat_ghazayee_1.put(Version_tblBook, "1");
            row_esteftaat_ghazayee_1.put(Keywords_tblBook, "با عنوان کلیات قضا و راه\u200Cهای اثبات دعوا که شامل دو فصل میباشد. فصل اول: شرایط و وظایف قاضی است و فصل دوم آن راه های اثبات دعوا را بیان میکند.");

            db.insert(tblBook, null, row_esteftaat_ghazayee_1);

            ContentValues row_esteftaat_ghazayee_2 = new ContentValues();
            row_esteftaat_ghazayee_2.put(Name_tblBook, "استفتاآت قضایی جلد دوم");
            row_esteftaat_ghazayee_2.put(Title_tblBook, "esteftaat_ghazayee_2");
            row_esteftaat_ghazayee_2.put(Lang_ID_tblBook,1);
            row_esteftaat_ghazayee_2.put(AutorName_tblBook, "آیت الله صانعی");
            row_esteftaat_ghazayee_2.put(Year_tblBook, "");
            row_esteftaat_ghazayee_2.put(ISBN_tblBook, "");
            row_esteftaat_ghazayee_2.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"esteftaat_ghazayee_2") + "/esteftaat_ghazayee_2.epub");
            row_esteftaat_ghazayee_2.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"esteftaat_ghazayee_2_cover") + "/esteftaat_ghazayee_2_cover.jpg");
            row_esteftaat_ghazayee_2.put(JsonText_tblBook, "");
            row_esteftaat_ghazayee_2.put(Version_tblBook, "1");
            row_esteftaat_ghazayee_2.put(Keywords_tblBook, "با عنوان کلیات قضا و راه\u200Cهای اثبات دعوا که شامل دو فصل میباشد. فصل اول: شرایط و وظایف قاضی است و فصل دوم آن راه های اثبات دعوا را بیان میکند.");

            db.insert(tblBook, null, row_esteftaat_ghazayee_2);

            ContentValues row_islamic_laws = new ContentValues();
           row_islamic_laws.put(Name_tblBook, "A Selection of Islamic Laws");
           row_islamic_laws.put(Title_tblBook, "islamic_laws");
           row_islamic_laws.put(Lang_ID_tblBook,2);
           row_islamic_laws.put(AutorName_tblBook, "Ayatollah Yousof Saanei");
           row_islamic_laws.put(Year_tblBook, "");
           row_islamic_laws.put(ISBN_tblBook, "");
           row_islamic_laws.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"islamic_laws") + "/islamic_laws.epub");
           row_islamic_laws.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"islamic_laws_cover") + "/islamic_laws_cover.jpg");
           row_islamic_laws.put(JsonText_tblBook, "");
           row_islamic_laws.put(Version_tblBook, "1");
           row_islamic_laws.put(Keywords_tblBook, "The practical laws of Islam which determine what Muslims must do or avoid doing");

            db.insert(tblBook, null, row_islamic_laws);

            ContentValues row_istiftas_en = new ContentValues();
            row_istiftas_en.put(Name_tblBook, "Istiftas");
            row_istiftas_en.put(Title_tblBook, "istiftas_en");
            row_istiftas_en.put(Lang_ID_tblBook,2);
            row_istiftas_en.put(AutorName_tblBook, "Ayatollah Yousof Saanei");
            row_istiftas_en.put(Year_tblBook, "");
            row_istiftas_en.put(ISBN_tblBook, "");
            row_istiftas_en.put(Url_path_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"istiftas_en") + "/istiftas_en.epub");
            row_istiftas_en.put(Url_Image_tblBook, FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"istiftas_en_cover") + "/istiftas_en_cover.jpg");
            row_istiftas_en.put(JsonText_tblBook, "");
            row_istiftas_en.put(Version_tblBook, "1");
            row_istiftas_en.put(Keywords_tblBook, "Fatwas for Those Living Out of Iran");

            db.insert(tblBook, null, row_istiftas_en);

            ContentValues row_mesbah_al_moghaledin = new ContentValues();
            row_mesbah_al_moghaledin.put(Name_tblBook, "مصباح المقلّدين");
            row_mesbah_al_moghaledin.put(Title_tblBook, "mesbah_al_moghaledin");
            row_mesbah_al_moghaledin.put(Lang_ID_tblBook,3);
            row_mesbah_al_moghaledin.put(AutorName_tblBook, "آیت الله صانعی");
            row_mesbah_al_moghaledin.put(Year_tblBook, "");
            row_mesbah_al_moghaledin.put(ISBN_tblBook, "");
            row_mesbah_al_moghaledin.put(Url_path_tblBook,
                        FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"mesbah_al_moghaledin") + "/mesbah_al_moghaledin.epub");
            row_mesbah_al_moghaledin.put(Url_Image_tblBook,
                        FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"mesbah_al_moghaledin_cover") + "/mesbah_al_moghaledin_cover.jpg");
            row_mesbah_al_moghaledin.put(JsonText_tblBook, "");
            row_mesbah_al_moghaledin.put(Version_tblBook, "1");
            row_mesbah_al_moghaledin.put(Keywords_tblBook, "مسائل فی ابواب الفقه، اعم من احکام التقلید و الطهارة والصلاة والصوم و الاعتکاف و الخمس و... ");

            db.insert(tblBook, null, row_mesbah_al_moghaledin);

            ContentValues row_tahrir_alvasile_1 = new ContentValues();
            row_tahrir_alvasile_1.put(Name_tblBook, "تحرير الوسيلة ج1");
            row_tahrir_alvasile_1.put(Title_tblBook, "tahrir_alvasile_1");
            row_tahrir_alvasile_1.put(Lang_ID_tblBook,3);
            row_tahrir_alvasile_1.put(AutorName_tblBook, "آیت الله صانعی");
           row_tahrir_alvasile_1.put(Year_tblBook, "");
           row_tahrir_alvasile_1.put(ISBN_tblBook, "");
           row_tahrir_alvasile_1.put(Url_path_tblBook,
                    FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"tahrir_alvasile_1") + "/tahrir_alvasile_1.epub");
            row_tahrir_alvasile_1.put(Url_Image_tblBook,
                    FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"tahrir_alvasile_1_cover") + "/tahrir_alvasile_1_cover.jpg");
            row_tahrir_alvasile_1.put(JsonText_tblBook, "");
            row_tahrir_alvasile_1.put(Version_tblBook, "1");
            row_tahrir_alvasile_1.put(Keywords_tblBook, "إنّ «تحرير الوسيلة» هو خير وسيلة يبتغيها المكلّف في سيره وسلوكه ، وهو أوثقها عُرىً ، وأصلحها منهاجاً ; لِما امتاز به من سداد في تحديد الموقف العمليّ ، وإصابة في تشخيص الوظائف المُلقاة على عاتق المكلّفين ، وذلك على ضوء الدليلين : الاجتهاديّ والفقاهتيّ ، النابعين من الكتاب والسنّة . ناهيك عن جمعه للمسائل العمليّة ، ونأيه عن المسائل ذات الصبغة النظريّة التي لا تمسّ إلى واقعنا المُعاش بصلة .");

            db.insert(tblBook, null, row_tahrir_alvasile_1);

            ContentValues row_tahrir_alvasile_2 = new ContentValues();
            row_tahrir_alvasile_2.put(Name_tblBook, "تحرير الوسيلة ج2");
            row_tahrir_alvasile_2.put(Title_tblBook, "tahrir_alvasile_2");
            row_tahrir_alvasile_2.put(Lang_ID_tblBook,3);
            row_tahrir_alvasile_2.put(AutorName_tblBook, "آیت الله صانعی");
            row_tahrir_alvasile_2.put(Year_tblBook, "");
            row_tahrir_alvasile_2.put(ISBN_tblBook, "");
            row_tahrir_alvasile_2.put(Url_path_tblBook,
                    FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"tahrir_alvasile_2") + "/tahrir_alvasile_2.epub");
            row_tahrir_alvasile_2.put(Url_Image_tblBook,
                    FileUtil.getFolioEpubFolderPath_internalStorage(localContext,"tahrir_alvasile_2_cover") + "/tahrir_alvasile_2_cover.jpg");
            row_tahrir_alvasile_2.put(JsonText_tblBook, "");
            row_tahrir_alvasile_2.put(Version_tblBook, "1");
            row_tahrir_alvasile_2.put(Keywords_tblBook, "التوضیح : جلد دوم");

            db.insert(tblBook, null, row_tahrir_alvasile_2);
            //endregion

            //region SettingsInsert fa
            ContentValues setting_fa = new ContentValues();
            setting_fa.put(Lang_ID_tblSettings,1 );
            setting_fa.put(WebSiteUrl_tblSettings, "http://www.saanei.xyz/?view=01,00,00,00,0");

            setting_fa.put(NewsUrl_tblSettings, "http://saanei.xyz/?view=01,00,04,00,0");
            setting_fa.put(EstekhareUrl_tblSettings, "http://saanei.xyz/?view=01,00,12,00,0");
            setting_fa.put(LibraryUrl_tblSettings, "http://saanei.xyz/?view=01,00,09,00,0");
            setting_fa.put(FeghhiUrl_tblSettings, "http://saanei.xyz/?view=01,00,10,00,0");
            setting_fa.put(NashriehUrl_tblSettings, "http://saanei.xyz/?view=01,00,48,00,0");

            setting_fa.put(SenderEmailAddress_tblSettings, "saaneimobileapp@gmail.com");
            setting_fa.put(SenderPassword_tblSettings, "Saanei2018");
            setting_fa.put(RecieverEmailAddress_tblSettings, "Istifta@saanei.org");
            setting_fa.put(SMTPHost_tblSettings, "smtp.gmail.com");
            setting_fa.put(SMTPSocketFactoryPort_tblSettings, "465");
            setting_fa.put(SMTPSocketFactoryClass_tblSettings, "javax.net.ssl.SSLSocketFactory");
            setting_fa.put(SMTPAuth_tblSettings, "true");
            setting_fa.put(SMTPPort_tblSettings, "465");
            setting_fa.put(VojoohatSiteUrl_tblSettings, "http://saanei.xyz/?view=01,00,53,00,0");
            setting_fa.put(Version_tblSettings, "1");

            db.insert(tblSettings, null, setting_fa);
            //endregion

            //region SettingsInsert en
            ContentValues setting_en = new ContentValues();
            setting_en.put(Lang_ID_tblSettings,2 );
            setting_en.put(WebSiteUrl_tblSettings, "http://www.saanei.xyz/?view=02,00,00,00,0");

            setting_en.put(NewsUrl_tblSettings, "http://saanei.xyz/?view=02,00,04,00,0");
            setting_en.put(EstekhareUrl_tblSettings, "http://saanei.xyz/?view=02,00,12,00,0");
            setting_en.put(LibraryUrl_tblSettings, "http://saanei.xyz/?view=02,00,09,00,0");
            setting_en.put(FeghhiUrl_tblSettings, "");
            setting_en.put(NashriehUrl_tblSettings, "");

            setting_en.put(SenderEmailAddress_tblSettings, "saaneimobileapp@gmail.com");
            setting_en.put(SenderPassword_tblSettings, "Saanei2018");
            setting_en.put(RecieverEmailAddress_tblSettings, "Istifta@saanei.org");
            setting_en.put(SMTPHost_tblSettings, "smtp.gmail.com");
            setting_en.put(SMTPSocketFactoryPort_tblSettings, "465");
            setting_en.put(SMTPSocketFactoryClass_tblSettings, "javax.net.ssl.SSLSocketFactory");
            setting_en.put(SMTPAuth_tblSettings, "true");
            setting_en.put(SMTPPort_tblSettings, "465");
            setting_en.put(VojoohatSiteUrl_tblSettings, "http://www.saanei.xyz/?view=02,00,53,00,0");
            setting_en.put(Version_tblSettings, "1");

            db.insert(tblSettings, null, setting_en);
            //endregion

            //region SettingsInsert ar
            ContentValues setting_ar = new ContentValues();
            setting_ar.put(Lang_ID_tblSettings,3 );
            setting_ar.put(WebSiteUrl_tblSettings, "http://www.saanei.xyz/?view=03,00,00,00,0");

            setting_ar.put(NewsUrl_tblSettings, "http://saanei.xyz/?view=03,00,04,00,0");
            setting_ar.put(EstekhareUrl_tblSettings, "http://saanei.xyz/?view=03,00,12,00,0");
            setting_ar.put(LibraryUrl_tblSettings, "http://saanei.xyz/?view=03,00,09,00,0");
            setting_ar.put(FeghhiUrl_tblSettings, "");
            setting_ar.put(NashriehUrl_tblSettings, "");

            setting_ar.put(SenderEmailAddress_tblSettings, "saaneimobileapp@gmail.com");
            setting_ar.put(SenderPassword_tblSettings, "Saanei2018");
            setting_ar.put(RecieverEmailAddress_tblSettings, "Istifta@saanei.org");
            setting_ar.put(SMTPHost_tblSettings, "smtp.gmail.com");
            setting_ar.put(SMTPSocketFactoryPort_tblSettings, "465");
            setting_ar.put(SMTPSocketFactoryClass_tblSettings, "javax.net.ssl.SSLSocketFactory");
            setting_ar.put(SMTPAuth_tblSettings, "true");
            setting_ar.put(SMTPPort_tblSettings, "465");
            setting_ar.put(VojoohatSiteUrl_tblSettings, "http://saanei.xyz/?view=03,00,53,00,0");
            setting_ar.put(Version_tblSettings, "1");

            db.insert(tblSettings, null, setting_ar);
            //endregion

            //region ContactUSInsert Farsi
            ContentValues contactUs1Farsi = new ContentValues();
            contactUs1Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs1Farsi.put(RowNumber_tblContactUs, 1);
            contactUs1Farsi.put(Version_tblContactUs, "1");
            contactUs1Farsi.put(Title_tblContactUs, "دفتر حضرت آیت الله العظمی صانعی مدظله  (قــم )");
            contactUs1Farsi.put(Address_tblContactUs, "بلوار شهيد محمد منتظري ، کوچه هشتم ، پلاک 4");
            contactUs1Farsi.put(PostCode_tblContactUs, "3713744369");
            contactUs1Farsi.put(PreTel1_tblContactUs, "025");
            contactUs1Farsi.put(Tel1_tblContactUs, "37744009");
            contactUs1Farsi.put(PreTel2_tblContactUs, "025");
            contactUs1Farsi.put(Tel2_tblContactUs, "37744010");
            contactUs1Farsi.put(PreTel3_tblContactUs, "025");
            contactUs1Farsi.put(Tel3_tblContactUs, "37744767");
            contactUs1Farsi.put(PreTel4_tblContactUs, "025");
            contactUs1Farsi.put(Tel4_tblContactUs, "37831660");
            contactUs1Farsi.put(PreTel5_tblContactUs, "025");
            contactUs1Farsi.put(Tel5_tblContactUs, "37831661");
            contactUs1Farsi.put(PreFax_tblContactUs, "025");
            contactUs1Farsi.put(Fax_tblContactUs, "37735080");
            db.insert(tblContactUs, null, contactUs1Farsi);

            ContentValues contactUs2Farsi = new ContentValues();
            contactUs2Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs2Farsi.put(RowNumber_tblContactUs, 2);
            contactUs2Farsi.put(Version_tblContactUs, "1");
            contactUs2Farsi.put(Title_tblContactUs, "دفتر شماره 1 تهران");
            contactUs2Farsi.put(Address_tblContactUs, "كارگرجنوبی، بالاتر از میدان پاستور ( به طرف انقلاب )، خیابان شهید سرخابی ، نبش کوچه شهید شاطرپور، پلاک 1");
            contactUs2Farsi.put(PostCode_tblContactUs, "1316973114");
            contactUs2Farsi.put(PreTel1_tblContactUs, "021");
            contactUs2Farsi.put(Tel1_tblContactUs, "66564001");
            contactUs2Farsi.put(PreTel2_tblContactUs, "021");
            contactUs2Farsi.put(Tel2_tblContactUs, "66564002");
            contactUs2Farsi.put(PreTel3_tblContactUs, "021");
            contactUs2Farsi.put(Tel3_tblContactUs, "66564003");
            contactUs2Farsi.put(PreTel4_tblContactUs, "021");
            contactUs2Farsi.put(Tel4_tblContactUs, "66564004");
            contactUs2Farsi.put(PreTel5_tblContactUs, "021");
            contactUs2Farsi.put(Tel5_tblContactUs, "66564005");
            contactUs2Farsi.put(PreFax_tblContactUs, "021");
            contactUs2Farsi.put(Fax_tblContactUs, "66409278");
            db.insert(tblContactUs, null, contactUs2Farsi);

            ContentValues contactUs3Farsi = new ContentValues();
            contactUs3Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs3Farsi.put(RowNumber_tblContactUs, 3);
            contactUs3Farsi.put(Version_tblContactUs, "1");
            contactUs3Farsi.put(Title_tblContactUs, "دفتر شماره 2 تهران");
            contactUs3Farsi.put(Address_tblContactUs, "تهران  پاسداران ابتدای نگارستان ۲(شهید کاشیها) پ ۳۷");
            contactUs3Farsi.put(PreTel1_tblContactUs, "021");
            contactUs3Farsi.put(Tel1_tblContactUs, "22885300");
            contactUs3Farsi.put(PreTel2_tblContactUs, "021");
            contactUs3Farsi.put(Tel2_tblContactUs, "22884600");
            contactUs3Farsi.put(PreTel3_tblContactUs, "021");
            contactUs3Farsi.put(Tel3_tblContactUs, "22768181");
            contactUs3Farsi.put(PreTel4_tblContactUs, "021");
            contactUs3Farsi.put(Tel4_tblContactUs, "22768080");
            contactUs3Farsi.put(PreFax_tblContactUs, "021");
            contactUs3Farsi.put(Fax_tblContactUs, "22847095");
            db.insert(tblContactUs, null, contactUs3Farsi);

            ContentValues contactUs4Farsi = new ContentValues();
            contactUs4Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs4Farsi.put(RowNumber_tblContactUs, 4);
            contactUs4Farsi.put(Version_tblContactUs, "1");
            contactUs4Farsi.put(Title_tblContactUs, "دفتر شماره 3 تهران");
            contactUs4Farsi.put(Address_tblContactUs, "شهرک غرب ، بلوار دریا ، تقاطع پاک نژاد (نبش چهار راه پاکنژاد به طرف مدیریت ) ، پلاک 178");
            contactUs4Farsi.put(PostCode_tblContactUs, "1466943141");
            contactUs4Farsi.put(PreTel1_tblContactUs, "021");
            contactUs4Farsi.put(Tel1_tblContactUs, "88582697");
            contactUs4Farsi.put(PreTel2_tblContactUs, "021");
            contactUs4Farsi.put(Tel2_tblContactUs, "88583425");
            contactUs4Farsi.put(PreTel3_tblContactUs, "021");
            contactUs4Farsi.put(Tel3_tblContactUs, "88583415");
            contactUs4Farsi.put(PreFax_tblContactUs, "021");
            contactUs4Farsi.put(Fax_tblContactUs, "88580821");
            db.insert(tblContactUs, null, contactUs4Farsi);

            ContentValues contactUs5Farsi = new ContentValues();
            contactUs5Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs5Farsi.put(RowNumber_tblContactUs, 5);
            contactUs5Farsi.put(Version_tblContactUs, "1");
            contactUs5Farsi.put(Title_tblContactUs, "دفتر شماره 1 اصفهان");
            contactUs5Farsi.put(Address_tblContactUs, "خيابان عبدالرزاق ، كوچه قصر ، پلاک 126");
            contactUs5Farsi.put(PostCode_tblContactUs, "8148686613");
            contactUs5Farsi.put(PreTel1_tblContactUs, "031");
            contactUs5Farsi.put(Tel1_tblContactUs, "34497660");
            contactUs5Farsi.put(PreTel2_tblContactUs, "031");
            contactUs5Farsi.put(Tel2_tblContactUs, "34497661");
            contactUs5Farsi.put(PreTel3_tblContactUs, "031");
            contactUs5Farsi.put(Tel3_tblContactUs, "34497662");
            contactUs5Farsi.put(PreFax_tblContactUs, "031");
            contactUs5Farsi.put(Fax_tblContactUs, "34493391");
            db.insert(tblContactUs, null, contactUs5Farsi);

            ContentValues contactUs6Farsi = new ContentValues();
            contactUs6Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs6Farsi.put(RowNumber_tblContactUs, 6);
            contactUs6Farsi.put(Version_tblContactUs, "1");
            contactUs6Farsi.put(Title_tblContactUs, "دفتر شماره 2 اصفهان");
            contactUs6Farsi.put(Address_tblContactUs, "خیابان دانشگاه ، خیابان توحید ، کوچه شماره 2 ، پلاک 4");
            contactUs6Farsi.put(PostCode_tblContactUs, "8173939481");
            contactUs6Farsi.put(PreTel1_tblContactUs, "031");
            contactUs6Farsi.put(Tel1_tblContactUs, "36281917");
            contactUs6Farsi.put(PreTel2_tblContactUs, "031");
            contactUs6Farsi.put(Tel2_tblContactUs, "36271093");
            db.insert(tblContactUs, null, contactUs6Farsi);

            ContentValues contactUs7Farsi = new ContentValues();
            contactUs7Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs7Farsi.put(RowNumber_tblContactUs, 7);
            contactUs7Farsi.put(Version_tblContactUs, "1");
            contactUs7Farsi.put(Title_tblContactUs, "دفتر شماره 3 اصفهان");
            contactUs7Farsi.put(Address_tblContactUs, "نجف آباد - خیابان فردوسی شمالی، خیابان نوبهار، پلاک 21 (جنب فیزیوتراپی)");
            contactUs7Farsi.put(PreTel1_tblContactUs, "031");
            contactUs7Farsi.put(Tel1_tblContactUs, "42616551");
            contactUs7Farsi.put(PreTel1_tblContactUs, "031");
            contactUs7Farsi.put(Tel1_tblContactUs, "42621173");
            db.insert(tblContactUs, null, contactUs7Farsi);

            ContentValues contactUs8Farsi = new ContentValues();
            contactUs8Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs8Farsi.put(RowNumber_tblContactUs, 8);
            contactUs8Farsi.put(Version_tblContactUs, "1");
            contactUs8Farsi.put(Title_tblContactUs, "دفـتر استان خراسان رضوی (مشهـد مقدّس)");
            contactUs8Farsi.put(Address_tblContactUs, "چهارراه شهداء ، خيابان آیت الله بهجت ( آزادى ) ، کوچه بهجت 1 / 9 ( بينادل ) ، پلاك 16");
            contactUs8Farsi.put(PostCode_tblContactUs, "9143786496");
            contactUs8Farsi.put(PreTel1_tblContactUs, "051");
            contactUs8Farsi.put(Tel1_tblContactUs, "32222577");
            contactUs8Farsi.put(PreTel2_tblContactUs, "051");
            contactUs8Farsi.put(Tel2_tblContactUs, "32230323");
            contactUs8Farsi.put(PreTel3_tblContactUs, "051");
            contactUs8Farsi.put(Tel3_tblContactUs, "32243142");
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
            contactUs9Farsi.put(PreTel1_tblContactUs, "071");
            contactUs9Farsi.put(Tel1_tblContactUs, "32243498");
            contactUs9Farsi.put(PreTel2_tblContactUs, "071");
            contactUs9Farsi.put(Tel2_tblContactUs, "32243334");
            contactUs9Farsi.put(PreTel3_tblContactUs, "071");
            contactUs9Farsi.put(Tel3_tblContactUs, "32230400");
            contactUs9Farsi.put(PreTel4_tblContactUs, "071");
            contactUs9Farsi.put(Tel4_tblContactUs, "32230401");
            contactUs9Farsi.put(PreTel5_tblContactUs, "071");
            contactUs9Farsi.put(Tel5_tblContactUs, "32222294");
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
            contactUs10Farsi.put(PreTel1_tblContactUs, "086");
            contactUs10Farsi.put(Tel1_tblContactUs, "33259777");
            contactUs10Farsi.put(PreTel2_tblContactUs, "086");
            contactUs10Farsi.put(Tel2_tblContactUs, "32272300");
            contactUs10Farsi.put(PreTel3_tblContactUs, "086");
            contactUs10Farsi.put(Tel3_tblContactUs, "32272200");
            contactUs10Farsi.put(PreFax_tblContactUs, "086");
            contactUs10Farsi.put(Fax_tblContactUs, "33259777");
            db.insert(tblContactUs, null, contactUs10Farsi);

            ContentValues contactUs11Farsi = new ContentValues();
            contactUs11Farsi.put(Lang_ID_tblContactUs, 1);
            contactUs11Farsi.put(RowNumber_tblContactUs, 11);
            contactUs11Farsi.put(Version_tblContactUs, "1");
            contactUs11Farsi.put(Title_tblContactUs, "دفتر استان آذربایجان شرقی (تبـریز)");
            contactUs11Farsi.put(Address_tblContactUs, "خیابان شهید رجائی، گلشن 2، گذر محله درب نو، منزل آقای قدس علوی");
            contactUs11Farsi.put(PostCode_tblContactUs, "5136975499");
            contactUs11Farsi.put(PreTel1_tblContactUs, "041");
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
            contactUs12Farsi.put(PreTel1_tblContactUs, "017");
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
            contactUs1English.put(PreTel1_tblContactUs, "+9825");
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
            contactUs2English.put(Title_tblContactUs, "Tehran office 1");
            contactUs2English.put(Address_tblContactUs, " No.17, Kaamyab lane, between Azarbayejan and Jomhuri Avenues, south Kargar Ave, Tehran, Iran");
            contactUs2English.put(PreTel1_tblContactUs, "+9821");
            contactUs2English.put(Tel1_tblContactUs, "66564001");
            contactUs2English.put(PreTel2_tblContactUs, "+9821");
            contactUs2English.put(Tel2_tblContactUs, "66564002");
            contactUs2English.put(PreTel3_tblContactUs, "+9821");
            contactUs2English.put(Tel3_tblContactUs, "66564003");
            contactUs2English.put(PreTel4_tblContactUs, "+9821");
            contactUs2English.put(Tel4_tblContactUs, "66564004");
            contactUs2English.put(PreTel5_tblContactUs, "+9821");
            contactUs2English.put(Tel5_tblContactUs, "66564005");
            contactUs2English.put(PreFax_tblContactUs, "+9821");
            contactUs2English.put(Fax_tblContactUs, "66409278");
            db.insert(tblContactUs, null, contactUs2English);

            ContentValues contactUs3English = new ContentValues();
            contactUs3English.put(RowNumber_tblContactUs, 3);
            contactUs3English.put(Version_tblContactUs, "1");
            contactUs3English.put(Title_tblContactUs, "Tehran office 2");
            contactUs3English.put(Address_tblContactUs, "First Floor, Boustan complex, Near 10th Boustan");
            contactUs3English.put(PreTel1_tblContactUs, "+9821");
            contactUs3English.put(Tel1_tblContactUs, "22885300");
            contactUs3English.put(PreTel2_tblContactUs, "+9821");
            contactUs3English.put(Tel2_tblContactUs, "22884600");
            contactUs3English.put(PreTel3_tblContactUs, "+9821");
            contactUs3English.put(Tel3_tblContactUs, "22768181");
            contactUs3English.put(PreTel4_tblContactUs, "+9821");
            contactUs3English.put(Tel4_tblContactUs, "22768080");
            contactUs3English.put(PreFax_tblContactUs, "+9821");
            contactUs3English.put(Fax_tblContactUs, "22847095");
            contactUs3English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs3English);

            ContentValues contactUs32English = new ContentValues();
            contactUs32English.put(Lang_ID_tblContactUs, 2);
            contactUs32English.put(RowNumber_tblContactUs, 4);
            contactUs32English.put(Version_tblContactUs, "1");
            contactUs32English.put(Title_tblContactUs, "Tehran office 3");
            contactUs32English.put(PostCode_tblContactUs, "1466943141");
            contactUs32English.put(PreTel1_tblContactUs, "+9821");
            contactUs32English.put(Tel1_tblContactUs, "88582697");
            contactUs32English.put(PreTel2_tblContactUs, "+9821");
            contactUs32English.put(Tel2_tblContactUs, "88583425");
            contactUs32English.put(PreTel3_tblContactUs, "+9821");
            contactUs32English.put(Tel3_tblContactUs, "88583415");
            contactUs32English.put(PreFax_tblContactUs, "+9821");
            contactUs32English.put(Fax_tblContactUs, "88580821");
            db.insert(tblContactUs, null, contactUs32English);

            ContentValues contactUs4English = new ContentValues();
            contactUs4English.put(RowNumber_tblContactUs, 5);
            contactUs4English.put(Version_tblContactUs, "1");
            contactUs4English.put(Title_tblContactUs, "Mashhad office");
            contactUs4English.put(PostCode_tblContactUs, "9143786496");
            contactUs4English.put(PreTel1_tblContactUs, "9851");
            contactUs4English.put(Tel1_tblContactUs, "32222577");
            contactUs4English.put(PreTel2_tblContactUs, "+9851");
            contactUs4English.put(Tel2_tblContactUs, "32230323");
            contactUs4English.put(PreTel3_tblContactUs, "+9851");
            contactUs4English.put(Tel3_tblContactUs, "32243142");
            contactUs4English.put(PreTel4_tblContactUs, "+9851");
            contactUs4English.put(Tel4_tblContactUs, "32210002");
            contactUs4English.put(PreTel5_tblContactUs, "+9851");
            contactUs4English.put(Tel5_tblContactUs, "32251152");
            contactUs4English.put(PreFax_tblContactUs, "+9851");
            contactUs4English.put(Fax_tblContactUs, "32230324");
            contactUs4English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs4English);



            ContentValues contactUs5English = new ContentValues();
            contactUs5English.put(RowNumber_tblContactUs, 6);
            contactUs5English.put(Version_tblContactUs, "1");
            contactUs5English.put(Title_tblContactUs, "Isfahan office 1");
            contactUs5English.put(PreTel1_tblContactUs, "+9831");
            contactUs5English.put(Tel1_tblContactUs, "34487660");
            contactUs5English.put(PreTel2_tblContactUs, "+9831");
            contactUs5English.put(Tel2_tblContactUs, "34487661");
            contactUs5English.put(PreTel3_tblContactUs, "+9831");
            contactUs5English.put(Tel3_tblContactUs, "34487662");
            contactUs5English.put(PreFax_tblContactUs, "+9831");
            contactUs5English.put(Fax_tblContactUs, "34463391");
            contactUs5English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs5English);

            ContentValues contactUs52English = new ContentValues();
            contactUs52English.put(Lang_ID_tblContactUs, 2);
            contactUs52English.put(RowNumber_tblContactUs, 7);
            contactUs52English.put(Version_tblContactUs, "1");
            contactUs52English.put(Title_tblContactUs, "Isfahan office 2");
            contactUs52English.put(PostCode_tblContactUs, "8173939481");
            contactUs52English.put(PreTel1_tblContactUs, "+9831");
            contactUs52English.put(Tel1_tblContactUs, "36281917");
            contactUs52English.put(PreTel2_tblContactUs, "+9831");
            contactUs52English.put(Tel2_tblContactUs, "36271093");
            db.insert(tblContactUs, null, contactUs52English);

            ContentValues contactUs53English = new ContentValues();
            contactUs53English.put(Lang_ID_tblContactUs, 2);
            contactUs53English.put(RowNumber_tblContactUs, 8);
            contactUs53English.put(Version_tblContactUs, "1");
            contactUs53English.put(Title_tblContactUs, "Isfahan office 3");
            contactUs53English.put(PreTel1_tblContactUs, "+9831");
            contactUs53English.put(Tel1_tblContactUs, "42616551");
            contactUs53English.put(PreTel1_tblContactUs, "+9831");
            contactUs53English.put(Tel1_tblContactUs, "42621173");
            db.insert(tblContactUs, null, contactUs53English);

            ContentValues contactUs6English = new ContentValues();
            contactUs6English.put(RowNumber_tblContactUs, 9);
            contactUs6English.put(Version_tblContactUs, "1");
            contactUs6English.put(Title_tblContactUs, "Shiraz office");
            contactUs6English.put(PreTel1_tblContactUs, "+9871");
            contactUs6English.put(Tel1_tblContactUs, "32243498");
            contactUs6English.put(PreTel2_tblContactUs, "9871");
            contactUs6English.put(Tel2_tblContactUs, "32243334");
            contactUs6English.put(PreTel3_tblContactUs, "9871");
            contactUs6English.put(Tel3_tblContactUs, "32230400");
            contactUs6English.put(PreTel4_tblContactUs, "9871");
            contactUs6English.put(Tel4_tblContactUs, "32230401");
            contactUs6English.put(PreTel5_tblContactUs, "9871");
            contactUs6English.put(Tel5_tblContactUs, "32222294");
            contactUs6English.put(PreFax_tblContactUs, "9871");
            contactUs6English.put(Fax_tblContactUs, "32226700");
            contactUs6English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs6English);

            ContentValues contactUs7English = new ContentValues();
            contactUs7English.put(RowNumber_tblContactUs, 10);
            contactUs7English.put(Version_tblContactUs, "1");
            contactUs7English.put(Title_tblContactUs, "Arak office");
            contactUs7English.put(PreTel1_tblContactUs, "+9886");
            contactUs7English.put(Tel1_tblContactUs, "32272200");
            contactUs7English.put(PreTel2_tblContactUs, "+9886");
            contactUs7English.put(Tel2_tblContactUs, "32272300");
            contactUs7English.put(PreFax_tblContactUs, "+9886");
            contactUs7English.put(Fax_tblContactUs, "33259777");
            contactUs7English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs7English);

            ContentValues contactUs8English = new ContentValues();
            contactUs8English.put(RowNumber_tblContactUs, 11);
            contactUs8English.put(Version_tblContactUs, "1");
            contactUs8English.put(Title_tblContactUs, "Tabriz Office");
            contactUs8English.put(PreTel1_tblContactUs, "+9841");
            contactUs8English.put(Tel1_tblContactUs, "35264626");
            contactUs8English.put(PreTel2_tblContactUs, "9841");
            contactUs8English.put(Tel2_tblContactUs, "3532023");
            contactUs8English.put(PreFax_tblContactUs, "+9841");
            contactUs8English.put(Fax_tblContactUs, "35252774");
            contactUs8English.put(Lang_ID_tblContactUs, 2);
            db.insert(tblContactUs, null, contactUs8English);

            ContentValues contactUs92English = new ContentValues();
            contactUs92English.put(Lang_ID_tblContactUs, 2);
            contactUs92English.put(RowNumber_tblContactUs, 12);
            contactUs92English.put(Version_tblContactUs, "1");
            contactUs92English.put(Title_tblContactUs, "Gorgan Office");
            contactUs92English.put(PostCode_tblContactUs, "4913616583");
            contactUs92English.put(PreTel1_tblContactUs, "+9817");
            contactUs92English.put(Tel1_tblContactUs, "32332270");
            contactUs92English.put(PreTel2_tblContactUs, "9817");
            contactUs92English.put(Tel2_tblContactUs, "32233270");
            db.insert(tblContactUs, null, contactUs92English);
            //endregion ContactUSInsert English

            //region ContactUSInsert Arabic
            ContentValues contactUs1Arabic = new ContentValues();
            contactUs1Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs1Arabic.put(RowNumber_tblContactUs, 1);
            contactUs1Arabic.put(Version_tblContactUs, "1");
            contactUs1Arabic.put(Title_tblContactUs, "مكتب قم");
            contactUs1Arabic.put(Address_tblContactUs, "شارع الشهيد محمد المنتظري ـ الفرع الثامن ـ الرقم 4");
            contactUs1Arabic.put(PostCode_tblContactUs, "3713744369");
            contactUs1Arabic.put(PreTel1_tblContactUs, "+98251");
            contactUs1Arabic.put(Tel1_tblContactUs, "7831660");
            contactUs1Arabic.put(PreTel2_tblContactUs, "+98251");
            contactUs1Arabic.put(Tel2_tblContactUs, "7744767");
            contactUs1Arabic.put(PreTel3_tblContactUs, "+98251");
            contactUs1Arabic.put(Tel3_tblContactUs, "7744010");
            contactUs1Arabic.put(PreTel4_tblContactUs, "+98251");
            contactUs1Arabic.put(Tel4_tblContactUs, "7744009");
            contactUs1Arabic.put(PreFax_tblContactUs, "+98251");
            contactUs1Arabic.put(Fax_tblContactUs, "7735080");
            db.insert(tblContactUs, null, contactUs1Arabic);

            ContentValues contactUs2Arabic = new ContentValues();
            contactUs2Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs2Arabic.put(RowNumber_tblContactUs, 2);
            contactUs2Arabic.put(Version_tblContactUs, "1");
            contactUs2Arabic.put(Title_tblContactUs, "مكتب طهران 1");
            contactUs2Arabic.put(Address_tblContactUs, "شارع كارگـر جنوني ـ بين شارع جمهوري وآذربايجان ـ زقاق كامياب ـ رقم البناية 17");
            contactUs2Arabic.put(PreTel1_tblContactUs, "+9821");
            contactUs2Arabic.put(Tel1_tblContactUs, "66564004");
            contactUs2Arabic.put(PreTel2_tblContactUs, "+9821");
            contactUs2Arabic.put(Tel2_tblContactUs, "66564003");
            contactUs2Arabic.put(PreTel3_tblContactUs, "+9821");
            contactUs2Arabic.put(Tel3_tblContactUs, "66564002");
            contactUs2Arabic.put(PreTel4_tblContactUs, "+9821");
            contactUs2Arabic.put(Tel4_tblContactUs, "66564001");
            contactUs2Arabic.put(PreTel5_tblContactUs, "+9821");
            contactUs2Arabic.put(Tel5_tblContactUs, "66564000");
            contactUs2Arabic.put(PreFax_tblContactUs, "+9821");
            contactUs2Arabic.put(Fax_tblContactUs, "66564005");
            db.insert(tblContactUs, null, contactUs2Arabic);

            ContentValues contactUs21Arabic = new ContentValues();
            contactUs21Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs21Arabic.put(RowNumber_tblContactUs, 3);
            contactUs21Arabic.put(Version_tblContactUs, "1");
            contactUs21Arabic.put(Title_tblContactUs, "مكتب طهران 2");

            contactUs21Arabic.put(PreTel1_tblContactUs, "+9821");
            contactUs21Arabic.put(Tel1_tblContactUs, "22885300");
            contactUs21Arabic.put(PreTel2_tblContactUs, "+9821");
            contactUs21Arabic.put(Tel2_tblContactUs, "22884600");
            contactUs21Arabic.put(PreTel3_tblContactUs, "+9821");
            contactUs21Arabic.put(Tel3_tblContactUs, "22768181");
            contactUs21Arabic.put(PreTel4_tblContactUs, "+9821");
            contactUs21Arabic.put(Tel4_tblContactUs, "22768080");
            contactUs21Arabic.put(PreFax_tblContactUs, "+9821");
            contactUs21Arabic.put(Fax_tblContactUs, "22847095");
            db.insert(tblContactUs, null, contactUs21Arabic);

            ContentValues contactUs22Arabic = new ContentValues();
            contactUs22Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs22Arabic.put(RowNumber_tblContactUs, 4);
            contactUs22Arabic.put(Version_tblContactUs, "1");
            contactUs22Arabic.put(Title_tblContactUs,"مكتب طهران 3");
            contactUs22Arabic.put(PostCode_tblContactUs, "1466943141");
            contactUs22Arabic.put(PreTel1_tblContactUs, "+9821");
            contactUs22Arabic.put(Tel1_tblContactUs, "88582697");
            contactUs22Arabic.put(PreTel2_tblContactUs, "+9821");
            contactUs22Arabic.put(Tel2_tblContactUs, "88583425");
            contactUs22Arabic.put(PreTel3_tblContactUs, "+9821");
            contactUs22Arabic.put(Tel3_tblContactUs, "88583415");
            contactUs22Arabic.put(PreFax_tblContactUs, "+9821");
            contactUs22Arabic.put(Fax_tblContactUs, "88580821");
            db.insert(tblContactUs, null, contactUs22Arabic);

            ContentValues contactUs3Arabic = new ContentValues();
            contactUs3Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs3Arabic.put(RowNumber_tblContactUs, 5);
            contactUs3Arabic.put(Version_tblContactUs, "1");
            contactUs3Arabic.put(Title_tblContactUs, "مكتب مشهد");
            contactUs3Arabic.put(Address_tblContactUs, "شارع آزادي ـ فرع بينادل");
            contactUs3Arabic.put(PreTel1_tblContactUs, "+98511");
            contactUs3Arabic.put(Tel1_tblContactUs, "2251152");
            contactUs3Arabic.put(PreTel2_tblContactUs, "+98511");
            contactUs3Arabic.put(Tel2_tblContactUs, "2222277");
            contactUs3Arabic.put(PreTel3_tblContactUs, "+98511");
            contactUs3Arabic.put(Tel3_tblContactUs, "2210002");
            contactUs3Arabic.put(PreFax_tblContactUs, "+98511");
            contactUs3Arabic.put(Fax_tblContactUs, "2222577");
            db.insert(tblContactUs, null, contactUs3Arabic);

            ContentValues contactUs4Arabic = new ContentValues();
            contactUs4Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs4Arabic.put(RowNumber_tblContactUs, 6);
            contactUs4Arabic.put(Version_tblContactUs, "1");
            contactUs4Arabic.put(Title_tblContactUs, "مكتب شيراز");
            contactUs4Arabic.put(Address_tblContactUs, "شاه چـراغ (ع) - شارع الشهيد آية الله الدستغيب - فرع مسجد فتح");
            contactUs4Arabic.put(PreTel1_tblContactUs, "+98711");
            contactUs4Arabic.put(Tel1_tblContactUs, "2222294");
            contactUs4Arabic.put(PreTel2_tblContactUs, "+98711");
            contactUs4Arabic.put(Tel2_tblContactUs, "2243498");
            contactUs4Arabic.put(PreTel3_tblContactUs, "+98711");
            contactUs4Arabic.put(Tel3_tblContactUs, "2243334");
            contactUs4Arabic.put(PreFax_tblContactUs, "+98711");
            contactUs4Arabic.put(Fax_tblContactUs, "2226700");
            contactUs4Arabic.put(PostCode_tblContactUs, "63985 ـ 71398");
            db.insert(tblContactUs, null, contactUs4Arabic);

            ContentValues contactUs5Arabic = new ContentValues();
            contactUs5Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs5Arabic.put(RowNumber_tblContactUs, 7);
            contactUs5Arabic.put(Version_tblContactUs, "1");
            contactUs5Arabic.put(Title_tblContactUs, "مكتب اراك");
            contactUs5Arabic.put(Address_tblContactUs, "شارع ادب جو ـ جنب مدرسة آية الله طالقاني - الرقم 8877");
            contactUs5Arabic.put(PreTel1_tblContactUs, "+98861");
            contactUs5Arabic.put(Tel1_tblContactUs, "2272300");
            contactUs5Arabic.put(PreTel2_tblContactUs, "+98861");
            contactUs5Arabic.put(Tel2_tblContactUs, "2272200");
            db.insert(tblContactUs, null, contactUs5Arabic);

            ContentValues contactUs6Arabic = new ContentValues();
            contactUs6Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs6Arabic.put(RowNumber_tblContactUs, 8);
            contactUs6Arabic.put(Version_tblContactUs, "1");
            contactUs6Arabic.put(Title_tblContactUs, "مكتب اصفهان");
            contactUs6Arabic.put(Address_tblContactUs, "شارع عبد الرزاق - زقاق الشهيد بني لوحي (قصر) زقاق الشهيد بور عبائيان - رقم الدار 35");
            contactUs6Arabic.put(PreTel1_tblContactUs, "031");
            contactUs6Arabic.put(Tel1_tblContactUs, "34497660");
            contactUs6Arabic.put(PreTel2_tblContactUs, "+9831");
            contactUs6Arabic.put(Tel2_tblContactUs, "34497661");
            contactUs6Arabic.put(PreTel3_tblContactUs, "+9831");
            contactUs6Arabic.put(Tel3_tblContactUs, "34497662");
            contactUs6Arabic.put(PreFax_tblContactUs, "+9831");
            contactUs6Arabic.put(Fax_tblContactUs, "34493391");
            db.insert(tblContactUs, null, contactUs6Arabic);

            ContentValues contactUs61Arabic = new ContentValues();
            contactUs61Arabic.put(Lang_ID_tblContactUs,3);
            contactUs61Arabic.put(RowNumber_tblContactUs, 9);
            contactUs61Arabic.put(Version_tblContactUs, "1");
            contactUs61Arabic.put(Title_tblContactUs, "مكتب اصفهان 2");
            contactUs61Arabic.put(PostCode_tblContactUs, "8173939481");
            contactUs61Arabic.put(PreTel1_tblContactUs, "+9831");
            contactUs61Arabic.put(Tel1_tblContactUs, "36281917");
            contactUs61Arabic.put(PreTel2_tblContactUs, "+9831");
            contactUs61Arabic.put(Tel2_tblContactUs, "36271093");
            db.insert(tblContactUs, null, contactUs61Arabic);

            ContentValues contactUs62Arabic = new ContentValues();
           contactUs62Arabic.put(Lang_ID_tblContactUs, 3);
           contactUs62Arabic.put(RowNumber_tblContactUs, 10);
           contactUs62Arabic.put(Version_tblContactUs, "1");
           contactUs62Arabic.put(Title_tblContactUs,"مكتب اصفهان 3");
           contactUs62Arabic.put(PreTel1_tblContactUs, "+9831");
           contactUs62Arabic.put(Tel1_tblContactUs, "42616551");
           contactUs62Arabic.put(PreTel1_tblContactUs, "+9831");
           contactUs62Arabic.put(Tel1_tblContactUs, "42621173");
            db.insert(tblContactUs, null, contactUs62Arabic);

            ContentValues contactUs7Arabic = new ContentValues();
            contactUs7Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs7Arabic.put(RowNumber_tblContactUs, 11);
            contactUs7Arabic.put(Version_tblContactUs, "1");
            contactUs7Arabic.put(Title_tblContactUs, "مكتب كرمان");
            contactUs7Arabic.put(Address_tblContactUs, "ميدان مشتاقية ـ قبال بازار شهردارى كرمان");
            contactUs7Arabic.put(PreTel1_tblContactUs, "+98341");
            contactUs7Arabic.put(Tel1_tblContactUs, "2232356");
            contactUs7Arabic.put(PreTel2_tblContactUs, "+98341");
            contactUs7Arabic.put(Tel2_tblContactUs, "2232357");
            db.insert(tblContactUs, null, contactUs7Arabic);

            ContentValues contactUs8Arabic = new ContentValues();
            contactUs8Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs8Arabic.put(RowNumber_tblContactUs, 12);
            contactUs8Arabic.put(Version_tblContactUs, "1");
            contactUs8Arabic.put(Title_tblContactUs, "مكتب تبـریز");
            contactUs8Arabic.put(PostCode_tblContactUs, "5136975499");
            contactUs8Arabic.put(PreTel1_tblContactUs, "+9841");
            contactUs8Arabic.put(Tel1_tblContactUs, "3532023");
            contactUs8Arabic.put(PreTel2_tblContactUs, "+9841");
            contactUs8Arabic.put(Tel2_tblContactUs, "35264626");
            contactUs8Arabic.put(PreFax_tblContactUs, "+9841");
            contactUs8Arabic.put(Fax_tblContactUs, "35252774");
            db.insert(tblContactUs, null, contactUs8Arabic);

            ContentValues contactUs9Arabic = new ContentValues();
            contactUs9Arabic.put(Lang_ID_tblContactUs, 3);
            contactUs9Arabic.put(RowNumber_tblContactUs, 13);
            contactUs9Arabic.put(Version_tblContactUs, "1");
            contactUs9Arabic.put(Title_tblContactUs, "مكتب گـرگان");
            contactUs9Arabic.put(PostCode_tblContactUs, "4913616583");
            contactUs9Arabic.put(PreTel1_tblContactUs, "+9817");
            contactUs9Arabic.put(Tel1_tblContactUs, "32332270");
            contactUs9Arabic.put(PreTel2_tblContactUs, "+9817");
            contactUs9Arabic.put(Tel2_tblContactUs, "32233270");
            db.insert(tblContactUs, null, contactUs9Arabic);
            //endregion

            //endregion

            //region insert About us

            //region SettingsInsert fa
            ContentValues about_fa = new ContentValues();
            about_fa .put(Lang_ID_tblAboutUs,1 );
            about_fa .put(Version_tblAboutUs,"1");

            about_fa .put(Description_tblAboutUs, "در باره ما\n" +
                    "این  نرم افزار مجموعه ای است از آثار و تألیفات مرجع نواندیش حضرت آیت الله العظمی صانعی(مدظله العالی) که در قالب زبانهای فارسی و عربی و انگلیسی به صورت متن و pdf عرضه شده است، این نرم افزار به درخواست مقلدین معظم له، پژوهشگران و محققان، برای دسترسی آسان به فتاوا و نظریات فقهی و پژوهشی مرجع عالیقدر برنامه نویسی شده است.\n" +
                    "\n" +
                    "از نشانه\u200Cهاي يك فقيه و عالم پژوهش\u200Cگر، آثار، تأليفات و نوشته\u200Cهای اوست. اين آثار از فكر و انديشه\u200Cهاي عالمانه\u200C تراوش كرده و بر صفحات كاغذ نقش مي\u200Cبندد تا حلقه\u200Cهای دانش و تفکّرات نويسنده را به ذهن و باور مخاطب منتقل نمايد.\n" +
                    "شايد بهترين شيوه\u200Cی اطلاع از وسعت و عمق آرا و نظريات صاحب هر انديشه\u200Cای\u200C، کرسی تعليمی و تأليفی او باشد. روشن است هر دو جهت در جای خود ثمرات و برکاتی دارند که نفوذ و توانايي\u200Cهای آن دو مسير در تغيير، اصلاح، رشد و ترقّی فرهنگ و ساختار جامعه می\u200Cتواند نمايان شود. ولی در عين حال تفاوت و امتياز اثر نوشتاری بر آثار بيانی و شفاهی، بر کسی پوشيده نيست. محتوای اثر استادی و تعليمی در دراز مدت، به آفت نسيان و تحريف و اندراس منجر خواهد شد، ليکن آثار تأليفی، امروزه و با امکانات و تکنولوژی روز بشر، کمتر دچار آسيب می\u200Cشود. علاوه اينکه ژرف نگری و امعان نظر در محتوای اثر قلمی نيز بيشتر از نطق و بيان شفاهی خواهد بود.\n" +
                    "بدين جهت، شخصيت\u200Cهای که در پی طرح دقيقِ مبانی فکری و علمیِ خود برای نسل\u200Cها در دوران مختلف و اعصار بعدی هستند، سعی دارند تا اين جمله\u200Cی اميرمؤمنان حضرت علی (ع) را که فرمـوده است\u200C: «قيّدوا العلم بالکتابة»  پيشه\u200Cی راه و هدف خود نمايند و آثار خود را در قالب قلم به رشته تحرير و تأليف درآورند.\n" +
                    "در هر صنفی و علمی، فنون نوشتار محتوايی در تقرير، تحليل و تفسيرِ موضوعات مربوطه، متعدّد و متکثّر است. در علم فقه نيز، که علم حيات و زندگانی است، شناخت آثار بزرگان اين علم، رهيافتی برای درک بيشتر علميّت و توانايی\u200Cهای فکری و اجتهادیِ عالم فقيه محسوب می\u200Cشود.\n" +
                    "به طور معمول شخصيت\u200Cهاي فقهي در آثار خود، معمولاً داراي دوگونه اثر هستند؛ يکي فقه استدلالي و ديگري فقه فتوايي. کم نيستند کساني که تصور مي\u200Cکنند که قدرت علمي فقيه در آثاري که جنبه استدلالي دارد، ظهور و بروز پيدا مي\u200Cکند، ولي اين پنداشت نمي\u200Cتواند پنداشت کاملي باشد؛ چراکه اثر فتوايي گاه داراي ظرافت\u200Cها و دقت نظرهايي است که در يک متن استدلالي ديده نمي\u200Cشود.\n" +
                    "در واقع عصاره و ثمره\u200Cي قدرت و دقت در استدلال و استنباط يک فقيه، در فقه فتوايي او نمود پيدا مي\u200Cکند. از اين منظر، بي\u200Cجهت نيست که بگوييم: مرحوم آيـت الله العظمي بروجردي پيش از آنکه با بحث\u200Cها و استدلالات فقهي\u200Cاش در جامعه\u200Cی علمي شناخته شود، با حاشيه کتاب عروه\u200Cاش شناخته شد و اهل فن وقتي که بر حاشيه عروه ايشان که جنبه فتوايي دارد، اطلاع پيدا کردند، معتقد شدند که آيـت الله بروجردي اعلم است. اين حواشي و فتاوا مي\u200Cتواند بسياري از امتيازات و ويژگي\u200Cهاي يک فقيه را به \u200Cخوبي نشان دهد.\n" +
                    "فقه فتوايي\n" +
                    "حضرت آيـت الله صانعي از جمله فقهايی است که می\u200Cتوان ظرافت\u200Cها و دقت در اجتهاد و استنباطِ فروعات فقهی را از فتاوای ايشان نيز دريافت نمود. فتاوای ايشان از پشتوانه و مبانی محکم و مُتقنی برخوردار است که با مطالعه\u200Cی آنها مي\u200Cتوان به صدق اين ادّعا پي برد. \n" +
                    "اين فتاوا و حواشي و تعليقات فتواييِ آيـت الله العظمي صانعي (مدظله) در چندين اثر به زيور چاپ آراسته گرديد که مهم\u200Cترين آنها:\n" +
                    "«رساله توضيح المسائل» به فارسي، «مجمع المسائل» در سه مجلّد به فارسي، «استفتائات قضايي» در دو مجلّد به فارسي، «مصباح\u200Cالمقلّدين» به عربي،  «تعليقه بر تحرير الوسيلـه» حضرت امام خميني(سلام الله عليه) در دو مجلّد به عربي، «تعليقه بر عروه الوثقي» در چهار مجلّد به عربي. هرکدام از مجموعه که بارها مزيّن به طبع شده\u200Cاند در بين تعليقات و حواشي و فتاواي فقها داراي ويژگی\u200Cهای ممتازی هستند.\n" +
                    "فقه استدلالي\n" +
                    "آثار علمی آيـت الله صانعی، بهترين مدرک برای تشخيص عمق تفکّر فقهی و مراتب علمی ايشان است. نوآوری\u200Cها به همراه نظريه\u200Cپردازی مستدل، فهم معنای روايات که از آن به «درايت الحديث» تعبير می\u200Cشود، در کنار شناخت رُوات احاديث و طبقات آنان (علم الحديث) و دقت در نقل روايت و استفاده از منابع اصلی و معتبر روايي، از جمله شاخصه\u200Cهای مهم نوشتاری ايشان به شمار می\u200Cرود.\n" +
                    "آثار فقه استدلالي حضرت آيـت الله صانعی در سه بخش تأليف، تدوين و تحشيه قابل تقسيم است:\n" +
                    "الف) تأليفات: «کتاب القصاص»، «کتاب الطلاق» و «کتاب الإرث» از آثار عمده و برجسته ايشان در اين بخش است.\n" +
                    "ب) تدوين: در اين بخش کتبي با اشراف حضرت آيـت الله صانعی توسط مؤسسه فرهنگي فقه الثقلين انتشار يافته که اهمّ آنها: «فقه و زندگی» که تا اکنون در 15 مجلد منتشر شده است و به آرا و نظريات متفرّد و غير مشهور معظّم\u200Cله اختصاص دارد.\n" +
                    "ج) تحشيه: حاشيه بر کتاب مجمع الفائده و البرهان، تعليقه بر کتاب عروه الوثقي، تعليقه بر تحريرالوسيلـه، حاشيه بر کتاب نزهـه الناظر فى الجمع بين الأشباه والنظائر، تعليقه بر کتاب هدايـت الناسکين، حاشيه بر کتاب دليل الناسک.\n" +
                    "اينک به مجموع آثار مکتوب حضرت آيـت الله العظمي صانعي در دو بخش فقه فتوايي و فقه استدلالی اشاره می\u200Cکنيم تا خوانندگان محترم بر آن مجموعه اطلاع کامل يابند. آنچه از آن آثار تاكنون به زيور طبع آراسته شده و بيشتر آنها نيز چند بار تجديد چاپ و حتی برخي از آنها بيش از هفتاد بار تجديد چاپ گرديده و منتشر شده\u200Cاند،  بدين قرار است:\n" +
                    "الف) کتب فارسي\n" +
                    "1.\tرساله\u200Cي توضيح المسائل\n" +
                    "2.\tمجمع المسائل (3 مجلّد)\n" +
                    "3.\tاستفتائات قضايي (2 مجلّد)\n" +
                    "4.\tاحکام بانوان\n" +
                    "5.\tاحکام نوجوانان\n" +
                    "6.\tاستفتائات پزشکي\n" +
                    "7.\tمنتخب الاحکام (فارسي)\n" +
                    "8.\tمناسک حج\n" +
                    "9.\tمنتخب مناسک حج\n" +
                    "10.\tاحکام حج ويژه بانوان \n" +
                    "11.\tاحکام عمره مفرده\n" +
                    "12.\t72 مسأله از مسائل عمره مفرده\n" +
                    "13.\tمنتخب اعمال و مناسک حج\n" +
                    "14.\t110 مسأله از مسائل حج\n" +
                    "15.\t40 مسأله در حج\n" +
                    "16.\tاحكام تعليم و تربيت \n" +
                    "17.\tاستفتائات (فتاوای مورد نياز افراد مقيم خارج از کشور)\n" +
                    "18.\tاحکام اعتکاف\n" +
                    "19.\tمنتخب ادعيه و اعمال ماه مبارک رمضان\n" +
                    "20.\tشهادت زن در اسلام (از منظر حضرت آيـت الله العظمی صانعی)؛ تأليف حجـت الاسلام فخرالدين صانعي\n" +
                    "21.\tچکيده انديشه\u200Cها؛ برگرفته از آرا و انديشه\u200Cهاي حضرت آيـت الله العظمي صانعي؛  تدوين مؤسسه فقه الثقلين\n" +
                    "22.\tهمراه با آفتاب (يادمان قله رفيع عشق و فضيلت حضرت امام خميني)؛ تدوين مؤسسه فقه الثقلين\n" +
                    "23.\tعقل و عاطفه در نهضت عاشورا؛ برگرفته از آثار و بيانات حضرت آيـت الله العظمی صانعی؛ تدوين مؤسسه فقه الثقلين\n" +
                    "24.\tپندها و پيام\u200Cها؛ برگرفته از آثار حضرت آيـت   الله العظمی صانعی؛ تدوين مؤسسه فقه الثقلين\n" +
                    "25.\tحديث زندگي (داستان زندگي آقا شيخ محمد علي صانعي)؛ تدوين مؤسسه فقه الثقلين\n" +
                    "26.\tامام خميني و راز احياي اجتهاد در عصر حاضر؛ (\u200Cسخنرانی حضرت آيـت الله العظمی صانعی)\n" +
                    "27.\tامام خميني اسوه مردم و حکومت؛ (\u200Cسخنرانی حضرت آيـت الله العظمی صانعی)\n" +
                    "28.\tانديشه(1) اجتهاد پويا؛ مصاحبه با حضرت آيـت الله العظمي صانعي؛ تدوين مؤسسه فقه الثقلين\n" +
                    "29.\tانديشه(2) حقوق زنان و کودکان؛ مصاحبه با حضرت آيـت الله العظمي صانعي؛ تدوين مؤسسه فقه الثقلين\n" +
                    "30.\tفقيه نوانديش (روايت زندگي علمي،  معنوي و سياسی حضرت آيـت الله العظمي صانعي)؛ جمعي از شاگردان معظّم\u200Cله (همين کتاب)\n" +
                    "31.\t استقلال مرجعيّت شيعه (واکاوی يک واقعه)\u200C؛ تدوين مؤسسه فقه \u200Cالثقلين\n" +
                    "ب) کتب عربي\n" +
                    "32.\tمصباح المقلّدين\n" +
                    "33.\tمنتخب الاحكام؛ تعريب شده\u200Cي کتاب «منتخب الاحکام» \n" +
                    "34.\tکتاب الطلاق؛ با تصحيح و تحقيق مؤسسه فقه الثقلين\n" +
                    "35.\tکتاب القصاص؛ با تصحيح و تحقيق مؤسسه فقه الثقلين\n" +
                    "36.\tکتاب الإرث (2 مجلّد)؛ با تصحيح و تحقيق مؤسسه فقه الثقلين\n" +
                    "37.\tکتاب المکاسب(جلد اوّل)\u200C؛ با تصحيح و تحقيق مؤسسه فقه الثقلين؛ مجلّدات بعدی ادامه دارد.\n" +
                    "38.\tنزهـه الناظر فى الجمع بين الأشباه والنظائر؛ از تأليفات فقيه محقق شيخ نجيب الدين يحيي بن سعيد حلّي؛ با حواشي حضرت آيـت الله العظمي صانعي؛ احيا و تصحيح و تحقيق مؤسسه فقه الثقلين\n" +
                    "39.\tمجمع الفائده و البرهان (جلد اوّل)؛ از تأليفات مقدّس اردبيلي؛ با حواشي حضرت آيـت الله العظمي صانعي؛ احيا و تصحيح و تحقيق مؤسسه فقه\u200Cالثقلين؛ حواشي 8 جلد ديگر آن نيز به اتمام رسيده و در مراحل تحقيق قرار دارد.\n" +
                    "40.\tالتعليقـه علي عروه الوثقي؛ از تأليفات سيد محمد کاظم طباطبايي يزدي(4 مجلّد)\n" +
                    "41.\tالتعليقـه علي تحرير الوسيلـه؛ از تأليفات امام خميني(2 مجلّد)\n" +
                    "42.\tهدايـت  الناسکين؛ از تأليفات شيخ الفقهاء شيخ محمد حسن نجفي (\u200Cصاحب جواهر\u200C)؛ با حواشي حضرت آيـت الله العظمي صانعي؛ احيا و تصحيح و تحقيق مؤسسه فقه الثقلين \n" +
                    "43.\tدليل الناسک؛ از تأليفات آيـت الله العظمي سيّد محسن حکيم؛ با حواشي حضرت آيـت الله العظمي صانعي؛ تصحيح و تحقيق مؤسسه فقه الثقلين\n" +
                    "44.\tمناسک الحج؛ تعريب شده\u200Cي کتاب «\u200Cمناسک حج» \n" +
                    "45.\tمأء مسألـه و عشر من مسائل الحج؛ تعريب شده\u200Cي کتاب «110 مسأله از مسائل حج»\n" +
                    "46.\tشهادت المرأه فى الإسلام؛ تعريب شده\u200Cي کتاب «شهادت زن در اسلام»، تأليف حجـت الاسلام فخرالدين صانعي\n" +
                    "47.\tرسالـه فى الربا\n" +
                    "48.\tلباب الألباب؛ برگرفته از آرا و انديشه\u200Cهاي حضرت آيـت الله العظمي صانعي؛ تعريب شده\u200Cي کتاب «چکيده انديشه\u200Cها».\n" +
                    "ج) کتب انگليسي و ترکی\n" +
                    "49.\tA Selection of Islamic Laws (منتخب الاحكام)\n" +
                    ".50A Brief Biography of the Great Religious Authorit Grand Ayatollah Saanei’s (نگاهی کوتاه به زندگینامه مرجع عالیقدر حضرت آیت الله العظمی صانعی)\n" +
                    "\n" +
                    "50.\tThe Essence Of Thoughts  چکيده انديشه\u200Cها))\n" +
                    "51.\tTAM İLMİHAL رساله عمليه ترکي))\n" +
                    "د) تقريرات\n" +
                    "تقريرات دروسِ اساتيد معظّم\u200Cله که به قلم عربي توسط ايشان نگاشته و به\u200C صورت مخطوط می\u200Cباشد به قرار ذيل است:\n" +
                    "1.\tتقريرات درس خارج فقه آيـت الله العظمی بروجردي\u200C\uF076 بحث «خلل  و سجده\u200Cی سهو»\n" +
                    "2.\tتقريرات درس خارج فقه و اصول امام خميني(سلام الله عليه)\n" +
                    "3.\tرساله در قاعده فراغ و تجاوز\n" +
                    "4.\tرساله در تقيّه\n" +
                    "5.\tرساله در لاضرر\n" +
                    "مؤسسه فرهنگي ـ هنري فقه الثقلين  با آدرس اينترنتي www.feqh.ir و پست الکترونيکي info@feqh.ir  كه وابسته به دفتر معظّم\u200Cله در قم مي\u200Cباشد، كليه\u200Cي آثار ايشان را پس از تصحيح،\u200C تحقيق، تدوين و تنظيم، منتشر و در اختيار همگان قرار مي\u200Cدهد.\n" +
                    "همچنين، مجموعه آرا و نظرات فقهي  متفرّدِ حضرت آيـت الله العظمي صانعي که توسط مؤسسه فرهنگي ـ هنري فقه الثقلين تحت اشراف معظّم\u200Cله تدوين و منتشر شده است عبارتند از:\n" +
                    "الف) کتب فارسي\n" +
                    "1.\tفقه و زندگي(1) رباي توليدي\n" +
                    "2.\tفقه و زندگي(2) برابري قصاص (زن و مرد، مسلمان و غير مسلمان)\n" +
                    "3.\tفقه و زندگي(3) برابري ديه (زن و مرد، مسلمان و غير مسلمان)\n" +
                    "4.\tفقه و زندگي(4) قيمومت مادر\n" +
                    "5.\tفقه و زندگي(5) ارث زن از شوهر (در صورت انحصار)\n" +
                    "6.\tفقه و زندگي(6) قمار، مسابقات و سرگرمي\n" +
                    "7.\tفقه و زندگي(7) بلوغ دختران\n" +
                    "8.\tفقه و زندگي (8) وجوب طلاق خلع بر مرد\n" +
                    "9.\tفقه و زندگي(9) ارث غير مسلمان از مسلمان\n" +
                    "10.\tفقه و زندگي (10) کتاب\u200Cهاي گمراه کننده (کتب ضلال)\n" +
                    "11.\tفقه و زندگي (11) امر به معروف و نهي از منکر\n" +
                    "12.\tفقه و  زندگي (12) غنا و موسيقي\n" +
                    "13.\tفقه و زندگي (13) کفايت اغسال از وضو\n" +
                    "14.\tفقه و زندگی(14) ارث زوجه\n" +
                    "15.\tفقه و زندگی(15) دیه بر عاقله\n" +
                    "ب) کتب عربي (تعريب شده\u200Cي کتاب\u200Cهای فقه و زندگی)\n" +
                    "16.\tسلسلـه الفقه المعاصر (1) الربا الاستثمارى\n" +
                    "17.\tسلسلـه الفقه المعاصر (2) مساواء الرجل والمرأء والمسلم و غيره فـى القصاص\n" +
                    "18.\tسلسلـه الفقه المعاصر (3) مساوات الديـه الرجل والمرأء والمسلم والكافر\n" +
                    "19.\tسلسلـه الفقه المعاصر (4) قيمومـه الاُمّ\n" +
                    "20.\tسلسلـه الفقه المعاصر (5) إرث الـزوجـه من الـرجل فى صورت الانحصار\n" +
                    "21.\tسلسلـه الفقه المعاصر (6) القمار، المسابقات، التسليـ \n" +
                    "22.\tسلسلـه الفقه المعاصر (7) بلوغ البنات\n" +
                    "23.\tسلسلـه الفقه المعاصر (8) وجوب طلاق الخلع علي الرجل\n" +
                    "24.\tسلسلـه الفقه المعاصر (9) إرث غير مسلم من المسلم \n" +
                    "25.\tسلسلـه الفقه المعاصر (10) کتب الضلال\n" +
                    "26.\tسلسلـه الفقه المعاصر (11) الأمر بالمعروف والنهى عن المنکر\n" +
                    "27.\tسلسلـه الفقه المعاصر (12) الغناء والموسيقي\n" +
                    "28.\tسلسلـه الفقه المعاصر(13) کفایة الاغسال من الوضو\n" +
                    "29.\tسلسلـه الفقه المعاصر(14) دیة العاقلة\n" +
                    "30.\tسلسلـه الفقه المعاصر(15) ارث الزوجة\n" +
                    "علاوه بر اين، مؤسسه فرهنگی فقه الثقلين بنا به سفارش و ترغيب حضرت آيـت الله صانعی در امرخدمت\u200Cرسانی به حوزه\u200Cهای علميّه\n" +
                    "و احيای متون فقهای عظام (رضوان الله تعالی عليهم)، اقدام به تصحيح و تحقيق  برخی از اين کتاب\u200Cها  نموده است. نسخه\u200Cهای اين متون به صورت مخطوط بوده و پس از نسخه خوانی و عمليات احيايي به شکل مطلوبی به زيور طبع آراسته گرديده\u200Cاند. از جمله اين کتاب\u200Cها عبارتند از:  \n" +
                    "1.\tمصابيح الأحکام؛ از تأليفات علامه بحرالعلوم\n" +
                    "2.\tجامع الشتات؛ از تأليفات ميرزای قمی\n" +
                    "3.\tمنجّزات المريض؛ از تأليفات آيـت الله العظمی سيّد محمدکاظم يزدی (صاحب عروه) \n" +
                    "4.\tجواهر الفرائض؛ از تأليفات خواجه نصيرالدين طوسی\n" +
                    "5.\tرسالـه فی المعاملـه و الاختلاف بين المتعاقدين؛ از تأليفات آيـت الله شيخ محمدرضا حسين\u200Cآبادی جرقويه\u200Cای\n" +
                    "6.\tرسالـه فی الشبهات المقرونـه بالعلم الاجمالی والقبلـه؛ از تأليفات آيـت الله شيخ محمدرضا حسين\u200Cآبادی جرقويه\u200Cای\n" +
                    "ب) آثار غير مکتوب\n" +
                    "1 ـ دروس حضرت آية الله العظمي صانعي\n" +
                    "در طول تدريس دروس خارج فقه و اصول توسط حضرت آيـت الله صانعي، حدود شش هزار جلسه\u200Cي درس از ايشان به جا مانده و بسياری از اين دروس در دهه\u200Cهای 1350 و 1360 شمسی ضبط نشده است و يا از بين رفته است. اين دروس به جا مانده، به\u200Cعنوان سرمايه و منبع عظيم علمي براي حوزه\u200Cهاي علميّه، به\u200Cخصوص دوستداران و علاقه\u200Cمندان به نظريات و نظامات فقهي و فکري ايشان محسوب مي\u200Cشود. در عين حال، بسياري از دروسي که امکانات ثبت و ضبط آنها در آن زمان وجود نداشته است از آمار اين مجموعه خارج شده است و ما از دسترسی به آنها محروم شده\u200Cايم.\n" +
                    "\n");
            db.insert(tblAboutUs, null, about_fa);

            ContentValues about_En = new ContentValues();
            about_En .put(Lang_ID_tblAboutUs,2 );
            about_En .put(Version_tblAboutUs,"1");

            about_En .put(Description_tblAboutUs, "\n" +
                    "Biographical Note:\n" +
                    "\n" +
                    "His Eminence Grand Ayatollah Saanei was born to a clerical family in Neekabad, Isfahan Province in 1316 Hegira (1937). His father, the late Hojat-ul-Islam Sheikh Mohammad Ali Saanei, acclaimed for his piety and quality of virtue, was a cleric.\n" +
                    "His grandfather, Ayatollah Haj Mulla Yusef had been among the devout and highly respected clerics of his own time. Haj Mulla Yusef was tutored in divine philosophy by the well-known Iranian philosopher, Jahangir Khan, and in jurisprudence, he had been among the pupils of Grand Ayatollah Mirza Habibullah Rashti. Haj Mulla Yusef was a disciple and propagator of the great Mirza-ye-Shirazi, the man who led the “tobacco movement” in opposition to the monopolistic concession, which the government of his time had granted to a British firm. His grandfather, an ardent advocate of Shirazi’s views, was known for his love of freedom and objection to social injustice. He never hesitated to stand up against the way people were treated in the hands of some feudal landlords and other agents of oppression. \n" +
                    "Encouraged by his father to devote his life to the pursuit of seminary studies, the young Saanei entered the Isfahan Seminary in the year 1325 Hegira (1946). While being in Isfahan, he attended the lectures and tutorial sessions held by seminarians there. And upon the completion of the preliminary studies in divinity, he entered the Qom Seminary in the year 1330 Hegira (1951).\n" +
                    "In Qom, his exceptional talent and exemplary diligence that earned him a place among the most successful students, helped to attract the views of the prominent seminarians who held him in high esteem. As a brilliant seminary student, he emerged with flying colors and ranked first in the advanced level examinations in the year 1334 Hegira (1955). He was awarded the commendation of the famous Grand Ayatollah Boroujerdi (May God bless his soul) for his exceptional achievement.\n" +
                    "This ingenuous seminary student, assessing \n" +
                    "the unique qualities of the late Imam \n" +
                    "Khomeini’s teachings, joined the circle of the Imam’s pupils in the same year. Backed by his own notable aptitude and great zeal, he continued to derive great benefit from the Imam’s lessons on Islamic Principles and Jurisprudence and the canonical laws.\n" +
                    "In the year 1342 Hegira (1963), he ranked as one of the outstanding graduates of these classes. \n" +
                    "Sheikh Saanei as an industrious student of canonical principles (Kharij) and a keen learner of the intensive and conclusive research works of Imam Khomeini, actively and successively attended the seminary sessions held by His Eminence for several years, which according to Mr. Saanei himself “led to the perception of the principles rather than mere knowing and learning them.”  \n" +
                    "Apart from Imam Khomeini’s classes, Ayatollah Saanei also attended the courses given by such illustrious seminarians as Grand Ayatollah Boroujerdi, Grand Ayatollah Mohaqeq Damad and Grand Ayatollah Araki (May God give rest to their souls). \n" +
                    "The combination of his inborn God given gift and devotion to learning enabled Sheikh Saanei at the age of 22 to reach to the rank of Ijtihad (the authority to deduce independently the religious laws) in the year 1338 (1959). \n" +
                    "In the year 1354 Hegira (1975), he began his formal career as a lecturer in divinity. He offered courses on the Book of Zakat at the Haghani (the Twain Martyrs) School of Divinity. His lectures, compiled by two of his pupils at the School, are extant as a proof of his command of the subject and the clarity of his teaching. When he began to teach the Kharij (an advance level at seminary school, equivalent to post graduate studies at any given university independent of any text or prescribed text) courses, a number of seminary students and lecturers attended his classes, among whom are a number of the most distinguished seminary researchers and Mujtahids (Islamic Jurisprudents) of our time who are active either at the seminaries or in governmental agencies having important roles in the affairs of the Islamic Republic. \n" +
                    "Imam Khomeini’s Standpoint of Ayatollah-ul-Uzma Saanei and His Administrative Responsibilities\n" +
                    "\n" +
                    "Ayatollah Saanei’s successive and successful attendance at the late Imam’s classes, his zeal for learning and understanding the Principles of the Imam’s doctrine and his research works, accompanied by years of correspondence with his teacher, enabled him to earn Ayatollah Khomeini’s highest regard and special consideration. This fatherly attention was behind the Imam’s decision to bestow on an outstanding student like Ayatollah Saanei an uncommon treasure of knowledge and a wealth of moral rectitude as well as an exceptional clairvoyance on social issues and trends.\n" +
                    "It was such intimate acquaintance and deep bonds of respect that led to the announcement of the Imam's decision on the first of Esfand 1358 Hegira (February, 19th, 1980). Just over a year after the victory of the glorious Islamic revolution, the Imam, noting the manifest qualities of his former student and paying intelligent attention to the needs of the post of the membership of the Guardian Council, named his outstanding pupil and long-time friend and disciple, a member of this important state body. In the Imam’s letter of appointment addressed to Ayatollah Sheikh Yusef Saanei, we read, \n" +
                    "“As stipulated in Article Ninety One of the Constitution of the Islamic Republic intended to safeguard the rules of Islam and the decrees of the Constitution in regard to the legislation (to be passed at any future time) by the Islamic Consultative Assembly, the formation of the Council of the Guardians and the selection of the six members of the Council, experts in Islamic jurisprudence and recognized as just and well-versed in issues of the day, is made incumbent upon me. Therefore, I hereby appoint Your Excellency as one of the six jurisprudent members of the Council of the Guardians. I pray to Almighty God to grant you even more success.”\n" +
                    "The decree to elevate Ayatollah Saanei to a position of state responsibility to function as a member of the Council of Guardians was the first major step taken by His Late Eminence the Imam to engage the Ayatollah in the affairs of the Islamic sovereign state. As the wording of this letter of appointment indicates, even from the first months of the victory of the revolution, the captain of the great vessel of the revolution viewed Ayatollah Saanei as a competent, and just jurisprudent acquainted with the demands of the time and every aspect of the revolution, a person qualified to judge the issues of the day and take care of the needs of the revolutionary society and the Muslim people of Iran. \n" +
                    "Ayatollah-ul-Uzma Saanei applied his unfailing endeavor to this momentous task for three years before resigning the post on the nineteenth of Dei, 1361 Hegira (December, 10th, 1982). However, the Imam’s special regard for, and undiminished confidence in him was clearly expressed in a speech made at a meeting with the Chief of the High Court, the judges of the Supreme Judiciary Council and branches of the High Court on the same day. Referring to the services rendered by the judiciary officials, he said, \n" +
                    "“I would like to thank the current Supreme Judiciary Council. These gentlemen worked hard, bore a heavy burden and diligently served for three years. They had never coveted any office. They are the notables of the clergy. They abandoned their serene mode of life. They came to an environment where hard work was demanded undergoing suffering and shouldering immense responsibility before God. Now, it does not really matter to clerics with an Islamic outlook whether to occupy the position of a chief justice of the High Court or the Prosecutor General or to take on any other work... Now, I would like to put this heavy responsibility on the shoulders of Ayatollah Yusef Saanei and introduce him as the country's Prosecutor General. I have brought up Sheikh Saanei like a son, Sheikh Saanei used to actively attend my seminary sessions for long years .He specifically used to personally exchange views with me for which I derived much pleasure from his vast knowledge. He is a prominent personality among the clerics and a man of learning.” \n" +
                    "Later, when His Eminence resigned the post of the Prosecutor General after a period of sincere and unfailing efforts, Imam Khomeini remarked in a speech on the sixteenth of Mordad, 1364 Hegira (July, 4th, 1985), \n" +
                    "“I feel sad and regret the departure of Sheikh Saanei. I hope he will prove effective wherever he may be in the future. I appreciate and thank him for his efforts and hard work. I have known him for many years. He is a learned man, devoted and diligent.” \n" +
                    "And again, speaking at a gathering of the members of the High Court and the officials of the Judiciary Branch on the eighteenth of Mordad, 1364 Hegira (July, 6th, 1985), the Imam commended Ayatollah Saanei in the following words. \n" +
                    "“... The post of the Prosecutor General, as you all know, is among the difficult and sensitive areas [of responsibility] and Ayatollah Saanei, who is an erudite man of learning, whom I have closely known for years and whom I have known to be a good and active person, has held the post until now and I thank him for his efforts.” \n" +
                    "Apart from the membership of the Guardian Council and the position of the Prosecutor General, Ayatollah-ul-Uzma Saanei has held a number of other responsible posts including His Eminence Imam Khomeini’s representative at the High Council of the Reconstruction of the War Zones and a member of the First Session of the Assembly of the Leadership Experts elected with over two million votes for the Tehran constituency in the elections held on the nineteenth of Azar, 1361 Hegira (November, 29th, 1982). \n" +
                    "\n" +
                    "\n" +
                    "History of Political Activities and Struggles\n" +
                    "\n" +
                    "Generally speaking, the major aspects of Ayatollah Saanei’s political strife and activity, prior to the victory of the Islamic revolution fall into the cultural and propagation area. His effective fights against injustice and oppression included promotional works, speeches and sermons, participation in mass demonstration and protest marches and publication of political manifestoes and statements.\n" +
                    "As noted in the third volume of the Documents of the Islamic Revolution, the name and signature of Ayatollah-ul-Uzma Saanei are to be found at the foot of about thirty political and revolutionary announcements, the first of which was a letter addressed to the great leader of the revolution by the clerics of the Qom Seminary after the Imam was transferred from his place of exile in Turkey to the Holy City of Najaf in Iraq.\n" +
                    " The letter bears the date of the month of Mehr, 1344 Hegira (September, 1965).\n" +
                    " And the last revolutionary document bearing Ayatollah Saanei’s signature next to those of other supporters of the revolution is the statement issued in opposition to Bakhtiar’s administration on the seventeenth of Dei, 1357 Hegira (December, 30th, 1978). Of course, what appear in the Documents of the Islamic Revolution by no means exhaust the list of the statements issued during those turbulent times.\n" +
                    "The most important statement by the Qom Seminary clerics was a strongly worded declaration entitled \"Depose the Shah from ruling Iran\" which was endorsed by a group of the Qom Seminary lecturers and bore the Ayatollah’s name and signature.\n" +
                    " The subject of the declaration, like other political statements of the time, dealt with a highly sensitive and dangerous question to the extent that only the true revolutionaries and the brave disciples of Imam Khomeini had the courage to put their names to it because the Pahlavi government took a grave view of such statements and considered the action of those who endorsed them as an unpardonable offense, deserving of capital punishment.\n" +
                    " Perhaps the consideration of the serious consequences of the declaration calling for the fall of the Shah was the reason why only very few clerics dared put their signatures to it.\n" +
                    " The leaders of the Pahlavi regime, in an attempt to forestall the rising tide of the Islamic revolutionary movement, were hard at work to cast doubt on the question of the Imam Khomeini’s position as a Marja’ (a top religious authority) and in this way, undermine public faith in his academic, political and religious competence.\n" +
                    " In response, a group of the seminarians, including Ayatollah Saanei, put some of their political and academic weight behind efforts to counter this conspiracy in order to establish Imam Khomieni as a proven Marja’. \n" +
                    "\n" +
                    "Unabated Activities at Seminaries\n" +
                    "\n" +
                    "During all these years, while being engaged in a variety of activities, His Eminence Ayatollah-ul-Uzma Saanei reserved an earnest interest in, and devoted considerable time and effort to teaching at the Seminary.\n" +
                    " In the course of eighteen years of regular preaching which took him from Qom to Tehran every week, he managed to hold three classes daily in Qom to teach the Book of Makasib at the Imam Hassan Askari (AS) Mosque, the Kifayet-ul-Ussul and the Kharij of jurisprudence at the Haghani School.\n" +
                    "In his classes and lectures, Ayatollah Saanei gave instruction in most of the texts that constitute the preliminary and Sat’h (level of teaching at Seminary School based on some prescribed texts) curricula.\n" +
                    " Moreover, his own personal interest and proficiency in teaching on the one hand, and persistent requests by seminary students on the other, resulted in his offering several courses in some of these books.\n" +
                    " For instance, he taught fifteen different courses to instruct the lofty and incisive book of the Kifayat-ul-Ussual.\n" +
                    "In the year 1352 Hegira (1973), when he was still teaching some of the texts at the Sat’h courses, he began lecturing in the Kharij course in the Principles at the Haghani Seminary School which he concluded after twenty two years, i.e. in the year 1373 Hegira (1994).\n" +
                    "An intimate familiarity and full command of His Eminence Imam Khomeini’s principles of doctrine and views on jurisprudence - the result of years of regular and devotional participation in Imam’s seminary sessions- gave a special touch to the courses offered by Ayatollah Saanei.\n" +
                    " The core of the content of his Kharij level classes in jurisprudence, taught for almost twenty-five years, is Imam Khomeini’s Tahrir-ul-Wassilah.\n" +
                    " While describing and probing the depth of other principles of jurisprudence, he invariably paid a special attention to the ratiocination of the Imam’s Principles, a feature which is of immense value to the Seminary.\n" +
                    "For hundreds of seminary students and researchers who attend his classes, the lucidity of the argument and the critical and incisive view, deep reverence for his righteous predecessors, respect for the canonical method of the seminaries accompanied by an exceptional profundity of views and principles bestowed on lessons given by this illustrious teacher all of which characterize his teaching approach,  mean that he imparts to his students a wealth of invaluable and potent methods to be used in solving contemporary social problems and brings a clarity of elucidation to the main topics in the canon.\n" +
                    " Furthermore, Ayatollah Saanei’s teaching sessions are characterized with his incisive, logical and applicable views on social questions and problems and awareness to social realities and norms.\n" +
                    " It also encompasses his insight to an essential knowledge of the accepted doctrines in the canon, concern over the methodology of transmitting and using the tradition and special care in appraising the correctness or otherwise of narration reference documents as well as the meticulous attention paid to the opinions of the canonists among the companions of the Holy Prophet and the twelve Imams (PBUT) all of which characterize his teaching performance.\n" +
                    "Apart from giving regular general courses at the seminary, a group of the leading seminary scholars also benefit from the Ayatollah’s presence there.\n" +
                    " For the past few years, the focus of Ayatollah-ul-Uzma Saanei’s teaching has been placed on issues in canonical jurisprudence.\n" +
                    "So far, he has completed courses on the books of Zakat (Charity tax on property or alms), Khoms (Quint Tax), Hajj, canonical judgment, Hodud (religious bounds or sanctions), Diyat (compensation according to Islamic laws), Matrimony, Divorce, Inheritance and Qissass (just retaliation or vengeance) and at present, he holds classes on Kitab-ul-Shahadat (evidence and testimonials).\n" +
                    " Besides, some sections of the books of Waqf (endowment), the Travelers’ Salaat (prayers), Concessions for the Sick, Taqiyah (dissimulation), La Zarar (the Islamic principle of no injury or malicious damage), Incompetence, the Question of Innovation and Accession and a number of other topics have also been included in his lecture courses, and parts of these are being compiled for publication at present.\n" +
                    "This eminent teacher’s special emphasis on the assumption of responsibility for the revolution and the affairs of the state by the seminary graduates and teachers, as well as his emphasis on the canonical, ethical and revolutionary qualities of the seminary have meant that, in different ways, his pupils are not only the recipients of the gift of knowledge in the fields of jurisprudence, fundamentals of religion and biographical studies, they also find themselves compelled to take on the heavy load of social and political responsibilities in the country.\n" +
                    " The humility shown to the canon and the reverence reserved for former canonists in the course of his lessons on the one hand, and his scholarly appraisal of the decisive evidence specially from the Koranic verses and the traditions on the other, has intensified his students’ faith in the unrivaled canon of Shi’ism and its illustrious canonists just as it has instilled in them the courage to probe into the opinions of the past and the belief in the competence of the canon to offer contemporary solutions.\n" +
                    " Familiarity with the ordinary people, close scrutiny of social relations and norms, awareness of the needs and realities of today’s society, a deep understanding of the comprehensive and firm principles of the departed Imam as regards his emphasis on the preservation of the canonical standards and frameworks and reliance on sound religious reasoning, alongside his valuable achievements in issuing Fatwas (decrees), open up fresh horizons before his pupils and strengthen their understanding of how the upright cannon of Shi’ism is placed on clear and unshakeable foundations to retain its vivacity at all times and offer solutions for all problems while his continued support of talented seminary students encourages their potentials to flourish to the full. \n" +
                    "\n" +
                    "\n" +
                    "On Issuing Fatwas (Rules)\n" +
                    "\n" +
                    "The master's exacting standards in research and study and his rigorous approach, specially to the area of science of the tradition, have not led him to apply similarly a rigorous and strict method in issuing Fatwas, because in comprehension of the Koranic verses and traditions and the foundations of deduction, he pays special attention to the principle of facility and always holds to the advice given to sheikh Ansari by the author of the Jawahir who said, “Decrease your cautions, for the creed of Islam is the easy and convenient creed.”\n" +
                    "Not only does Ayatollah Saanei bring up various canonical issues for general discussion by large groups of seminary students and researchers who assemble in his house every day, he also tries to benefit from their exchange of opinions and cooperation by forming a group for the formulation of Fatwas.\n" +
                    " A number of books by His Eminence have so far been published. They include the common book of Religious Laws (Towdhih-ul-Massael), Hajj Rites, Majma-ul-Masael (two volumes), Selected Rulings, Fatwas on Medical Issues, Misbah-ul-Moqaledin, the Rules of Hajj for Ladies, the Book of Qissass, the Book of Divorce, the Rules of the Lesser Pilgrimage (Omra Mufrada), and Religious Rules for Ladies.\n" +
                    " He has also published his annotations to the Tahrir-ul-Wassilah and Orwat-ul-Wothqa in Arabic.\n" +
                    " His other works include notes of Imam Khomeini’s Kharij course, pamphlet on the rule of relief and transgression, pamphlet on Taqiya, pamphlet on the principle of non-malice in canonical rules.\n" +
                    "Another part of Ayatollah-ul-Uzma Saanei’s time is allocated to replying to requests for Fatwas and so far, several thousands of such requests have been handled and returned.\n" +
                    " What is striking in his book Majma-ul-Massael is that his method of replying is similar to that of the great Marja' and jurisprudent, the late Mirza-ye-Qomi.\n" +
                    " In some instances, apart from stating the Fatwa in question, the evidence and supportive documents are also mentioned which can be an invaluable source of learning for seminary researchers and the students of the canon.\n" +
                    " Despite the fact that Ayatollah Saanei always makes a point of respecting the ruling by prominent canonists and the views of the disciples, specially the well-known opinions, and while he always calls on all seminaries, and his own students in particular, to show proper respect to the canonists of the disciples and refers to them with the greatest sign of respect and praise for their ideas, his open-mindedness in canonical matters and the special attention he pays to basing his interpretations on reliable references and solid decisive evidence have led him to issue a large number of innovative and novel Fatwas and opinions for his practicing followers.\n" +
                    " These Fatwas which are invariably upheld within the framework of the principles and standards of the canon have, in many instances, solved the practical problems faced by his followers in the area of private and social action.\n" +
                    " For those who are well acquainted with the principles and proofs, referring to the detailed discussion and the documented evidence underlying these Fatwas can prove a great asset in gaining a better and more precise insight in the interpretation of the evidence and in responding to the canonical needs of society.\n" +
                    " The following are samples of his fatwas:\n" +
                    "1- On Vilayat-ul-Faqih (Supreme Religious Jurisprudence): Any comprehensively qualified Mujtahid is competent to assume the position of Vilayat and as regards the people, and in serving the public interest in cases for which Islam has not decreed a specific ruling, the legitimacy for action derives solely from the people and the view of the majority and mass of the people, where legitimacy depends on the endorsement by the public as a whole or a majority thereof and the duty to apply them rests directly or indirectly with the Valy-ul-Faqih (Supreme Religious Jurisprudent).\n" +
                    "2- On the age of puberty for girls, His Eminence states his preference for the evidence by Ammar Sabati who has specified the age of thirteen lunar years as a condition of religious puberty, and says, “In case other signs of puberty explained by textual support and Fatwas are not realized, they (girls) reach religious maturity at this age.\n" +
                    "3- On women’s sitting in judgment, he has commented: The condition of masculinity has no particular bearing upon judgment and there is no religious reason to support this either, and whoever is vested with authority to appoint judges may appoint them (women) to sit as judges especially in cases relating to women and family rights.\n" +
                    "4- On the question of custody of the underage child and its property in the absence of the father, His Eminence’s opinion is that the mother is the inalienable guardian and the beneficiary of the proofs of benevolence and piety and interests and munificence, and custody of the underage is none but benevolence and munificence; and on the strength of the sacred verse “In custody over the kin some are preferred over some others”, the mother takes precedence over the grandfather, and in the absence of the mother, the grandfather has custody of the child.\n" +
                    "5- As regards the difference in the Diya (blood money) for women and men, he has concluded, “Diya for infliction by mistake or quasi-deliberate infliction is the same for men and women and this ruling is based upon the application of the supportive argument for diya.”\n" +
                    "6- On the question of the curtailed rituals for the oft-traveler, his ruling is, “Persons who travel a distance of four furlongs (farsangs) within a period of ten days (be it on business or otherwise) and do not abide in one place for more than ten days, are oft-travelers and if individuals travel on pilgrimage, too, their prayers are said in full and their fasting is unbroken.”\n" +
                    "7- About the question of music, His Eminence holds that the prohibition of music and lyrics is based on the content and thus, any voice, lyric or music which does not promote laxity and immorality and does not misguide human beings or blemish the visage of Islam, is not forbidden.\n" +
                    "8- On the question of the infidels, he pronounces, “Most apparently, their purity is like the purity of Muslims. Yet, the antagonists among them who fight Muslims because of their adherence to Islam or their belief in Islam (and not for any other reason) are deemed adversaries in religion who, like a few of the infidels, having gained certainty of the validity of Islam continue to deny it, are bound to be unclean.\n" +
                    "9- On Riba (usury), he has ruled, “The forbidding decree on usury as it appears in the Koranic verses and traditions which have been referred to, concerns interest payable on depreciation and not interest resulting from gainful employment of the principal.\n" +
                    "10- On Qissass: Grand Ayatollah Saanei believes in the generality in the all round application of Qissass for the murder of any individual whose life is protected under the Islamic rule, irrespective of the murderer or the victim being Muslim or non-Muslim.\n" +
                    " Qissass is life giving in the eye of those who can think: “And for you, there is life in Qissass, you, possessors of reason” (the Cow, 179). And this ruling is based on the absoluteness and generality of the traditions and Koranic verses related to retaliation (Qissass) and certain traditions on the issue, which provide the necessary conclusive legal proofs.\n" +
                    "11- As regards the status of Vilayat and sovereignty and position of the Marja' and other prerogatives of the Faqih, he does not uphold the condition of masculinity and rules that the criteria is competence in the canon and piety.\n" +
                    " He concludes that just as the Islamic law does not allow any discrimination on the basis of race, nor does it condone discrimination on the grounds of sex and ethnicity. \n" +
                    "His Eminence also holds other views which are representative of his opposition to discrimination and the details of this discussion are presented in his different practical treatises, especially the “Selected rulings.” \n" +
                    "\n" +
                    "\n" +
                    "Some of His Personal and Social Virtues\n" +
                    "\n" +
                    "Ayatollah Saanei’s deep, heartfelt and, at the same time, manifest affection for the Immaculate Family of the Prophet, specially, Hazrat Fatimah (Allah’s Blessings upon them all) has always been noticeable to the lecturers, researchers, students and the members of the public who are received by His Eminence.\n" +
                    "Amenable treatment of the people, which verges on meekness specially when meeting the esteemed members of the clergy and the Islamic thinkers, respect for the seminary students, praise lavished on seminary teachers and researchers and the esteem in which the seminary elders are held all among Ayatollah-ul-Uzma Saanei’s personal marks.\n" +
                    " His behavior is graced with a sincere interest in the lives of the people, affection and care for the underprivileged and those in trouble, humility before the former prisoners of war, the families of war martyrs and the disabled victims of the war, and deep concern for the deprived sections of society whose suffering he feels to the bone, the plainness of his residence and the simple way of his life and vigilance in dispensation of religious funds have combined to create an environment around him in which every seminary student and researcher and any member of the general public finds him directly accessible to answer their questions and solve their problems.\n" +
                    "Yet, his vigilant devotion to religious and academic affairs has not meant the neglect of His Eminence’s responsibilities toward the revolution and the system of the Islamic Republic and the need to emphasize the duty to preserve and promote it, nor has it prevented him from assuming what he considers to be the right stance regarding the state of the Muslim world.\n" +
                    " Indeed, it has helped underline the need to participate in social and political activities and bolster the highest interests of Islam, the revolution and the system alongside cooperating to overcome the difficulties relating to these issues.\n" +
                    " Ayatollah Saanei’s close involvement in canonical, political and social efforts is a reminder of Imam Khomeini’s pronouncement, “The profession of the prophets is politics and religion, the politics which arouses people from this position and leads them to what is in the interest of the nation and the public.”\n" +
                    "To Ayatollah Saanei, the link between the position of a marja' and political involvement emanates from the sprit of the culture and the teachings of the religion and its doctrine.\n" +
                    "We pray to God, the Almighty, the Exalted, to grant health and grace to this venerable canonist. We pray that under the auspices of the system of the Islamic Republic and blessed with the sacred soul of our departed Imam, the Islamic seminaries and our Islamic community will continue to be endowed with such eminent canonists and religious scholars.\n" +
                    "\n");
            db.insert(tblAboutUs, null, about_En);

            ContentValues about_Ar = new ContentValues();
            about_Ar .put(Lang_ID_tblAboutUs,3 );
            about_Ar .put(Version_tblAboutUs,"1");

            about_Ar .put(Description_tblAboutUs, "\n" +
                    "\n" +
                    "نبذة عن حياته\n" +
                    "ولد آية اللّه العظمى الصانعي عام 1356هـ/1937م، في قرية نيك آباد من توابع مدينة اصفهان في أحضان أسرة علمية. جدّه آية اللّه الحاج الملَّى يوسف، كان من العلماء المتقين الورعين في زمانه، ممَّن تتلمذ في الفلسفة على يد جهانگيرخان، وفي الفقه على يد آية اللّه العظمى الميرزا حبيب اللّه الرشتي. وكان من عشّاق الميرزا الشيرازي قائد حركة التنباك، وكان يروّج له، وكان رجلاً حراً أبياً يقف بوجه الخونة والحكام المتسلطين آنذاك.\n" +
                    "والده حجة الاسلام الشيخ محمد علي الصانعي، كان عالماً زاهداً ورعاً، وكان يحثّ ولده باستمرار على الدراسة في الحوزة العلمية، ولهذا دخل الشيخ الحوزة العلمية في اصفهان عام 1365هـ / 1946م، حتى اذا تلقّى فيها الدروس التمهيدية والمقدمات من محضر علماء تلك الحوزة، قصد بلدة قم وحوزتها العلمية في عام 1370هـ / 1951م ليواصل دراسته فيها، فكان ذكاؤه وجديته، منذ أوائل عهده هناك، قد صنّفاه في عداد الطلاب الناجحين والمحترمين لدى شخصيات الحوزة آنذاك.\n" +
                    "لقد نال الشيخ المرتبة الأولى في امتحانات مرحلة السطوح العالية في الحوزة عام 1375هـ / 1955م، فأخذ بتشجيعه المرحوم آية اللّه العظمى البروجردي(قدس سره).\n" +
                    "وفي ذلك العام، أخذ يحضر دروس البحث الخارج للسيد الإمام الخميني(قدس سره) لما رأى فيه من خصوصيات وامتيازات تفرّد بها درسه. واستطاع بذكائه وحزمه أن يواصل دراسة الأصول والفقه لدى الإمام حتى عام 1383هـ / 1963م ليصبح أحد أبرز طلابه.\n" +
                    "كان حضور الشيخ دروس الإمام الخميني(قدس سره) على مدى سنين واستقاؤه من معين بحثه، قد جعله يقف على الآراء الفقهية والأصولية للسيد الإمام(قدس سره) إلى حدّ تجاوز مجرد العلم والمعرفة إلى مستوى اللمس والشعور على حسب تعبير الشيخ نفسه.\n" +
                    "وقد استطاع بحزمه وذكائه وتوفيق اللّه له أن ينال درجة الاجتهاد وهو في الثانية والعشرين. وبالإضافة إلى تلقّيه دروس الإمام، نهل الشيخ من معين أساتذة عظام آخرين، كآية اللّه العظمى البروجردي، وآية اللّه العظمى المحقق الداماد، وآية اللّه العظمى الأراكي(رحمهم اللّه).\n" +
                    "في عام 1395هـ /1975م أخذ الشيخ بتدريس البحث الخارج بشكل رسمي مبتدأً بكتاب الزكاة، وذلك في مدرسة الحقاني (الشهيدين)، ومحاضرات دروسه مكتوبة بقلم اثنين من طلابه. وبابتداء دروس البحث الخارج للشيخ، راح عدد كبير من طلاب الحوزة ينهلون العلم منه، حيث أصبح العديد منهم من الباحثين المرموقين في الحوزة، ونال بعض آخر درجة الاجتهاد ليعملوا في الحوزات العلمية أو في مؤسسات الدولة في الجمهورية الإسلامية.\n" +
                    "مكانته لدى الإمام الخميني(قدس سره)والمناصب التي تولاَّها\n" +
                    "إن مواصلة الشيخ لحضور دروس السيد الإمام(قدس سره)ومثابرته على استيعاب رؤى الإمام(قدس سره) وأفكاره، أدَّى إلى أن يحظى الشيخ بمكانة خاصة لديه. فكان لهذه العناية الأبوية من الإمام أثرها في صقل الشخصية العلمية والأخلاقية وإثراء الرؤية الاجتماعية لطالب متميز كسماحة آية اللّه العظمى الصانعي، حتى أن قائد الثورة الإسلامية الكبير السيد الإمام الخميني(قدس سره)، قام بتاريخ 3 ربيع الثاني 1400هـ ]20/2/1980م[ بعد نحو سنة من انتصار الثورة الإسلامية المباركة، بتعيين تلميذه ونصيره آية اللّه الشيخ يوسف الصانعي عضواً في مجلس صيانة الدستور الذي تعتبر عضويته إحدى أهم المسؤوليات العلمية والتنفيذية في البلاد. وكان ذلك عن إحاطة كاملة من الإمام(قدس سره) بمقتضيات هذا المنصب والكفاءات التي يستلزمها، وقد قال له الإمام(قدس سره) في حكم التعيين:\n" +
                    "يقرر البند الحادي والتسعين من دستور الجمهورية الإسلامية تشكيل مجلس باسم مجلس الصيانة، مهمته حراسة الأحكام الإسلامية والدستور من ناحية عدم مخالفة قررات مجلس الشورى لهما، حيث أُوكل إليّ تعيين ستة من الفقهاء العدول العارفين بمقتضيات العصر في هذا المجلس. من هنا تم تعيين سماحتكم أحد الفقهاء الأعضاء الستة في مجلس صيانة الدستور. أرجو من اللّه لك دوام التوفيق.( )\n" +
                    "إنّ تعيين الشيخ في مجلس الصيانة كان أول سمة رسمية ومسؤولية تنفيذية له من قبل السيد الإمام الخميني(قدس سره). وكما يلاحظ في حكم التعيين، أن قائد الثورة الكبير كان يراه منذ أوائل الثورة فقيهاً كفوءاً عادلاً عارفاً بظروف الثـورة وقضايا العصر ومفيداً للمجتمع والشعب الايراني.\n" +
                    "وبعد نحو من ثلاث سنين من العمل الدؤوب في هذا المنصب الحساس، تنحّى آية اللّه الصانعي من مجلس صيانة الدستور في 24 ربيع الثاني 1403هـ ]9/1/1983م[ ولمّا كان الإمام(قدس سره) يوليه أهمية خاصة، قال(قدس سره) في  اليوم نفسه عند لقائه رئيس الديوان الأعلى وقضاة مجلس القضاء الأعلى وشعب الديوان الأعلى للقضاء كلام قال فيه:\n" +
                    "إني أشكر مجلس القضاء الأعلى السابق، لقد تحمّلوا المتاعب وعانوا سنتين أو ثلاثاً وقدّموا خدمات، ولم يكونوا قد جاءوا لنيل منصب أو مقام. إنهم من أكابر علماء الدين، جاءوا من موضع سكونهم وراحتهم إلى محيط مملوء بالمتاعب والمعاناة والعمل، ومسؤوليته أمام اللّه أكبر. وطبعاً ليس مهماً بالنسبة إلى رجل الدين، الذي يحمل روحية الإسلام، أن يكون رئيس الديوان الأعلى للقضاء أو النيابة العامة أو أي عمل آخر إني أريد الآن أن أُلقي هذا العناء على عاتق الشيخ يوسف الصانعي كنائب عام وأعرّفه للسادة. لقد ربيت الشيخ الصانعي كابن لي، هذا الشيخ الصانعي عندما كان يحضر على مدى سنين طويلة في البحوث التي دارت بيننا، كان ياتي بالخصوص ويتحدث معي وكنت ألتذّ بمعلوماته. إنه رجل بارز بين علماءالدين ورجل عالم وواع.( )\n" +
                    "وبهذا الشكل أخذ الشيخ يواصل جهوده الحثيثة على مـدى دورة طويلة نسبياً، حتى اذا قدّم استقالته، قال الامام الخميني في تاريخ 18 شوال 1405هـ ]7/7/1985م[ :\n" +
                    "إنّى آسف ومتألم لذهاب الشيخ الصانعي. آمل أن يكون رجلاً فعالاً مؤثراً أينما كان، أشكره على الجهود التي بذلها. لقد عرفته على مدى سنين طوال، إنه رجل عالم ملتزم حَرِك.( )\n" +
                    "كما جاء في حديث آخر للإمام(قدس سره) بتاريخ 20 شوال 1405هـ ]9/7/1985م[ وجَّههه إلى أعضاء الديوان الأعلى للقضاء ومسؤولي السلطة القضائية:\n" +
                    "... النيابة العامة ـ كما تعرفون جميعاً ـ من القضايا الصعبة والحساسة جداً، والشيخ الصانعي عالم فاضل عرفته عن قرب على مدى سنين وأعتبره عنصراً فعالاً مفيداً حمل هذا المنصب إلى الآن، وإني أشكر له الجهود التي بذلها.( )\n" +
                    "وقد تولَّى الشيخ مسؤوليات تنفيذية أخرى من قبيل: ممثل الإمام الخميني (قدس سره) في المجلس الأعلى لإعمار مناطق الحرب، عضو الدورة الأولى لمجلس خبراء القيادة كنائب عن مدينة طهران في انتخابات 23 صفر 1403هـ ]10/12/1982م [ بأكثر من مليوني صوت.\n" +
                    "نشاطه وجهاده السياسي\n" +
                    "أبرز جهاد الشيخ السياسي ونشاطه الثوري، قبل انتصار الثورة الاسلامية، تمثّل في الجهود التي بذلها في مجال الثقافة والإعلام. وهذه الجهود تأتّت من خلال التبليغ والخطابة والمشاركة في المسيرات وإصدار البيانات. ففي المجلد الثالث من كتاب (وثائق الثورة الإسلامية) يلاحظ توقيع آية اللّه العظمى الصانعي في ذيل ما يقرب من ثلاثين بياناً سياسياً وثورياً، كان أولها رسالة وجَّهها العلماء في قم إلى قائد الثورة الكبير الإمام الخميني بعد انتقاله من تركيا إلى النجف الأشرف، هذه الرسالة نشرت في جمادى الثانية من عام 1385هـ / 1965م.\n" +
                    "كما كان آخر بيان، يوشحه اسم الشيخ وتوقيعه إلى جانب بقية رفاق الثورة، هو بيان معارضة حكومة بختيار الذي صدر بتاريخ 8 صفر 1399 هـ ]7/1/1979م [وطبعاً لم ترد في  الكتاب المذكور أعلاه جميع البيانات الصادرة آنذاك.\n" +
                    "إنّ أبرز وأقوى بيان لعلماء قم كان حول (خلع الشاه من حكومة ايران) الذي أيدته ثلة من العلماء الأفاضل والأساتذه في الحوزة العلمية لقم، حيث يلاحظ في ذيله اسم وتوقيع آية اللّه الصانعي. محتوى هذا البيان، كغيره من البيانات يومذاك، كان في غاية الحساسية والخطورة، ولم يكن يجرأ على التوقيع عليه أحد سوى الثوريين الحقيقيين والأنصار الشجعان للإمام الخميني. ذلك أنّ بيانات كهذه كانت تعتبرها حكومة الشاه جريمة لاتغتفر، يستحق فاعلها القتل. ولعلَّ هذا هو الذي جعل عدد العلماء الموقعين على بيان (خلع الشاه) يسيراً جداً.\n" +
                    "لقد كان أقطاب الحكم البهلوي يحاولون باستمرار النيل من مرجعية الإمام الخميني للحيلولة دون تعاظم زخم الثورة الإسلامية، فكانوا يحاولون تشكيك الرأي العام في صلاحية الإمام من الناحية العلمية والسياسية والدينية. ولذلك كانت ثلة من العلماء والأفاضل في الحوزة العلمية، ومنهم سماحة آية اللّه الصانعي، توظّف جانباً من طاقاتها الفكرية والسياسية لإحباط هذه المؤامرة وتثبيت مرجعية الإمام الخميني.\n" +
                    "\n" +
                    "في مجال التدريس\n" +
                    "بالإضافة إلى نشاطاته المتنوعة، يولي الشيخ الصانعي أهمية بالغة للبحث والتدريس في الحوزة العلمية منذسنين طويلة، بحيث إنه إلى جانب تردده الاسبوعي إلى طهران للتبليغ مدة ثماني عشرة سنة، كان يلقي يومياً ثلاثة دروس على طلاب الحوزة في قم: درس المكاسب في مسجد الإمام الحسن العسكري(عليه السلام)، ودرسي كفاية الأصول والبحث الخارج في  الفقه في مدرسة الحقاني.\n" +
                    "لقد قام الشيخ بتدريس معظم الكتب المتعارفة في مرحلتي المقدمات والسطوح، كما أن حبّه للتعليم وقدرته على التدريس وتبحّره العلمي من ناحية، ورغبة الطلاب في درسه من ناحية اخرى دفع به إلى أنْ يدرّس بعض الكتب المنهجية في  الحوزة عدة مرات، حتى أنه درّس كفاية الأصول، ذلك الكتاب القيّم الثمين، خمس عشرة مرة.\n" +
                    "وبعد ثماني سنين تقريباً، بينما كان الشيخ لايزال يدرّس بعض كتب مرحلة السطوح، أخذ في عام 1393هـ / 1973م بتدريس أصول الفقه على مستوى البحث الخارج في مدرسة الحقاني، وفرغ منه بعد اثنين وعشرين سنة في عام 1415هـ / 1994م.\n" +
                    "إنّ وقوف الشيخ على الرؤى الأصولية والفقهية للإمام الخميني (قدس سره)، لاستغراقه في الدراسة لدى الإمام على مدى سنوات، قد أضفى على درسه في البحث الخارج سمة خاصة، فمنذ خمس وعشرين سنة من تدريسه للبحث الخارج، جعل محور درسه كتاب الإمام الخميني (تحرير الوسيلة).\n" +
                    "وإلى جانب اهتمامه في البحث بالرؤى الفقهية للعلماء بشكل عام، يولي اهتماماً خاصاً برؤى وآراء الإمام الخميني. وهذا أمرله قيمته في الحوزة العلمية. إنّ رؤية الشيخ الواضحة وأسلوبه النقدي مع احترامه وإجلاله للسلف الصالح والسنة الفقهية في الحوزات، بالإضافة إلى آرائه العلمية الرصينة التي تفتح الآفاق، كل ذلك يشكّل بالنسبة إلى مئات الطلاب الحاضرين أمراً ذا قيمة علمية، ولا سيما في الإسهام بحل قضايا المجتمع وتقديم صورة ناصعة عن الفقه، فدرس الشيخ يمتاز برؤية اجتماعية واضحة ومنطقية، تفتح آفاقاً جديدة، واهتمام بواقع المجتمع والعرف إلى جانب الوقوف على الأصول المسلَّمة في الفقه، ودقة في فقه الحديث وعناية بتقويم الأسانيد ومتابعة لآراء الفقهاء.\n" +
                    "وبالإضافة إلى دروسه العامة، يلتقي الشيخ باستمرار جمعاً من أفاضل الحوزة ممَّن يستنيرون بعلمه. واستطاع إلى الآن تدريس كتاب الزكاة، الخمس، الحج، القضاء، الحدود، الديات، النكاح، الطلاق، الإرث، القصاص، وهو يقوم حالياً بتدريس كتاب الشهادات. هذا بالإضافة إلى أنه تناول بالبحث قسماً من كتاب الوقف، صلاة المسافر، منجّزات المريض، التقية، قاعدة (لاضرر)، الحجر، المسائل المستحـدثة وغيرها، حيث يتم حالياً كتابة هـذه البحـوث.\n" +
                    "إنّ حرص الأستاذ على أن يتولَّى فضلاء الحوزة مهام الثورة والنظام الإسلامي، بالإضافة إلى اهتمامه بالحركة الفقهية الأخلاقية الثورية للحوزة، جعلت طلابه يجدون أنفسهم في مناسبة وأخرى أمام مسؤوليات اجتماعية وسياسية عظمى إلى جانب دراستهم للفقه والأصول والرجال.\n" +
                    "درس الأستاذ الذي ملؤه الإجلال للفقه ولجهود الفقهاء السابقين من ناحية، وتقويمه الدقيق للأدلَّة ولا سيما الآيات والروايات من ناحية أخرى، دعت إلى ترسيخ إيمان الطلاب بعمق الفقه الشيعي، كما قوّت فيهم روح التفحّص وتقويم أقوال السابقين والاعتقاد بانفتاح الفقه وقدرته على حلّ المشاكل.\n" +
                    "احتكاك الشيخ بعامة الناس، ووقوفه على طبيعة العلاقات الاجتماعية والعرفية، واطلاعه على حاجات المجتمع وواقع العصر، وإدراكه العميق للرؤى الرصينة للإمام الراحل(قدس سره) إلى جانب المحافظة على الموازين والأطر الفقهية والاعتناء بالأدلة الشرعية المتينة، كل ذلك، بالإضافة إلى معطياته في  مجال الإفتاء، يفتح آفاقاً جديدة أمام الطلاب، ويعزّز فيهم الشعور بمدى قوة الاُسس التي يقوم عليها الفقه الشيعي، وأنه فقه حي منفتح. كما أنّ تشجيع الأستاذ المتواصل لذوي القابليات من الطلاب يعتبر مدعاة لتفتح قدراتهم ومواهبهم.\n" +
                    "في مجال الإفتاء\n" +
                    "إنّ تتبع الأستاذ الدقيق وعدم تساهله في البحث والتحقيق، ولا سيما في فقه الحديث، لم يمنعه من أن يكون سمحاً على مستوى الإفتاء، ذلك أنه ينظر في فهم الآيات والروايات وأصول الاستنباط إلى مبدأ السهولة في الإسلام، واضعاً بين عينيه باستمرار وصية المرحوم صاحب (الجواهر) للشيخ الأنصاري في أنْ «يقلّل من احتياطاته لأن الإسلام شريعة سهلة».\n" +
                    "والشيخ، إلى جانب طرحه المسائل الفقهية المهمة بمحضر الكثير من الطلاب والفضلاء الذين يجتمعون في داره كل يوم، قام بتشكيل لجنة استفتاء من أجل التعاون العلمي وتبادل الآراء.\n" +
                    "المؤلفات المطبوعة للأستاذ إلى الآن هي: رسالة (توضيح المسائل)، (مناسك الحج)، (مجمع المسائل)، (منتخب الأحكام)، (الاستفتاءات الطبية)، (مصباح المقلدين)، (أحكام النساء)، (فقه الثقلين ـ الطلاق)، (فقه الثقلين ـ القصاص)، كما أنّ له حاشية بالعربية على (تحرير الوسيلة) و(العروة الوثقى). ومن مؤلفاته الأخرى: محاضرات السيد الإمام الخميني في البحث الخارج، (رسالة في قاعدة الفراغ والتجاوز)، (رسالة في التقية)، (رسالة لاضرر) من القواعد الفقهية.\n" +
                    "ونظراً لكثرة المراجعين والأسئلة المطروحة على الأستاذ، فقد أجاب إلى الآن عن آلاف الاستفتاءات.\n" +
                    "والملفت في كتابه (مجمع المسائل)، هو أن أجوبته جاءت على طريقه المرجع الكبير الميرزا القمي(قدس سره)، إذ يشير أحياناً بعد الفتوى إلى شيء من الأدلة، الأمر الذي يقدم فائدة علمية كبيرة للباحثين في الحوزة والمتخصصين في  الفقه.\n" +
                    "وعلى الرغم من كل الإجلال الذي يكنّه الشيخ للفقهاء وآرائهم، ولا سيما المشهور منها، ودعوته المستمرة لطلابه إلى معرفتهم وإجلالهم، وذكره لهم بكامل التقدير والاحترام والثناء الجميل على آرائهم، إلا أن حريته وسعة ثقافته الفقهية ونظره المتميز إلى الأدلة واستناده إلى مبادىء علمية متقنة في الأصول وفي الفقه بشكل خاص، كل ذلك دعاه إلى أن يبتكر فتاوى وآراء جديدة على مستوى عمل المكلفين والمقلدين.\n" +
                    "هذه الفتاوى التي تصدر جميعاً في إطار الاُصول والمعايير الفقهية في الحوزة، استطاعت في كثير من المجالات أن تفتح آفاقاً لحل مشاكل المكلف على المستوى الفردي والاجتماعي. ولعل رجوع المهتمين والمتخصصين إلى تفاصيل هذه الفتاوى ومستنداتها، سيتيح فهماً أفضل وأدق، ويفتح باباً إلى سدّ حاجات المجتمع وحل مشاكله.\n" +
                    "بعض من آرائه الفقهية\n" +
                    "1 ـ يقول سماحته في ولاية الفقيه: أيّ مجتهد جامع للشرائط يعتبر منصوباً للولاية، ومشروعية القيام بما تقتضيه المصالح العامة، التي لم يحدد لها الإسلام حكماً خاصاً، يرجع أمره إلى الناس وأغلبيتهم فقط. والمشروعية تتوقف على آراء الناس ورضاهم بشكل عام أو عن طريق الأكثرية، والتنفيذ العملي لذلك تقوم به ولاية الفقيه بشكل مباشر أو غير مباشر.\n" +
                    "2 ـ بالنسبة إلى بلوغ الفتيات، يرجّح الشيخ موثقة عمار الساباطي التي تعتبر شرط السن هو الثالثة عشرة ويقول: تبلغ الفتيات سن التكليف في الثالثة عشرة اذا لم تحـرز علائم البلوغ الاُخرى المذكورة في النصوص والفتاوى.\n" +
                    "3 ـ بالنسبة إلى عمل النساء بالقضاء يقول: لا خصوصية للذكورة في القضاء، وليس لدينا حجة شرعية على ذلك، وإطلاق أدلة القضاء حجة على العموم والشمول، فكما أن الرجال مجازون من قبل الأئمة المعصومين في التصدي للقضاء، كذلك النساء مجازات من قبلهم، ولا سيما في شؤون المرأة وحقوقها.\n" +
                    "4 ـ في الولاية على الصغير وأمواله، يرى الشيخ أنّ الاُم هي الولي المباشر في حال فقدان الأب، إذ تشملها أدلة الإحسان والمعروف والخير والبر، والولاية على الصغير ليست شيئاً غير البر والإحسان، والآية الشريفة: (وَاُولُوا الأَرْحامِ بَعْضُهُمْ أَوْلى بِبَعْض)( ) تقضي بتقديم الاُم على الجد للأب، وفي حال فقدان الاُم يصبح الجد للأب هو القيم.\n" +
                    "5 ـ في مسألة الاختلاف بين دية الرجل والمرأة يقول: الرجل والمرأة متساويان في دية الخطأ وشبه العمد، وذلك ما يستفاد من إطلاق أدلة الدية مع انعدام الحجة المعتبرة على التقييد والتفصيل.\n" +
                    "6 ـ في مسألة كثيرالسفر يقول: من يداوم على السفر مسافة أربعة فراسخ قبل الإقامة عشرة أيام (سواء لعمل أو لغيره)، ولا يبقى في مكان عشرة أيام، يعتبر كثير السفر، ومـن يسافر للزيارة كذلك، يصلي تماماً ويصح منه الصوم.\n" +
                    "7 ـ ورأيه في الموسيقي هو أن حرمة الموسيقي والغناء لأجل محتواهما، وأيّ صوت وغناء وموسيقى لا تدعو إلى التحلّل والانحراف ولا تسيء إلى الإسلام، فهي ليست حراماً.\n" +
                    "8 ـ وفي خصوص الكفار يقول: الأظهر هو طهارتهم، كطهارة المسلمين. نعم، الحربىّ منهم، الذي يحارب المسلمين بسبب إسلامهم وعقيدتهم (لا لسبب آخر)، يعتبر معانداً لدينهم، وهم القليل من الكفار الذين استيقنوا الحق في  الإسلام وأصـروا على إنكاره، فهولاء محكومون بالنجاسة.\n" +
                    "9 ـ وعن الربا يقول: تحريم الربا الذي جاء في الآيات والروايات، يختص بالربا الاستهلاكي، لا الربا الانتاجي الاستثماري.\n" +
                    "10 ـ يرى شيخنا شمول القصاص لقتل أيّ إنسان محترم الدم يعيش تحت ظل الحكومة الإسلامية، بلا فرق بين المسلم والكافر في القاتل والمقتول. القصاص حياة لأولي الألباب: (وَلَكُمْ فِي الْقِصاصِ حَياةٌ يا اُولِى الاَْلْبابِ)( ) وهذا الرأي يستند إلى إطلاقات وعمومات القصاص وبعض الأخبار في المسألة، الأمر الذي يعتبر حجة في بابه.\n" +
                    "11 ـ لا يشترط الشيخ الذكورة في الولاية والحكم والمرجعية وسائر شؤون الفقيه، والمعيار لديه هو الفقه والتقوى.\n" +
                    "وأخيراً يرى أنّ الحقوق في الإسلام، كما لا تعرف التمييز العنصري بين الأسود والابيض، كذلك لا تعرف التمييز بين جنس وآخر أو جنسية  وأخرى، كما أن لمساحته آراءً أخرى تحكي عن عدم التمييز، تجدون تفاصيلها في كتبه ورسائله العملية، ولا سيما كتابه (منتخب الأحكام).\n" +
                    "بعض سجاياه الأخلاقية والاجتماعية\n" +
                    "يُعرف الشيخ بوجده وحبّه العميق لأهل بيت النبي الأطهار، ولا سيما للسيدة الزهراء (صلوات اللّه عليهم أجمعين) الأمر الذي كان ولا زال يمدُّ الحوزة والزائرين من الأساتذة والطلاب والمراجعين من عامة الناس، بشحنات من الحركة والنشاط العلمي.\n" +
                    "تواضع سماحته للناس، ولا سيما لأهل العلم، وإكرامه للطلاب، وإجلاله للأساتذة والفضلاء، وتأكيده على احترام وجهاء الحوزة، تعدّ من الخصوصيات المشهودة للشيخ. حبّه الصادق للناس وشفقته عليهم، ولا سيما أصحاب المشاكل منهم، وإكرامه وتقديره لعوائل الشهداء ومعوقي الحرب، واهتمامه بالمحرومين من طبقات الشعب وإدراكه العميق لمشكلاتهم، وبساطة عيشه واحتياطه الشديد في استخدام الاموال الشرعية، كل ذلك جعل الكثير من الطلاب والفضلاء وعامة الناس يلتقي سماحته بسهولة ويطرحون عليه مسائلهم.\n" +
                    "وكل هذا وما سبق ذكره لم يشغل سماحته عن الاهتمام المسؤول بالثورة ونظام الجمهورية إلاسلامية وصيانته ودعمه، وعن اتخاذ مواقف تجاه قضايا العالم الإسلامي والتحرّك السياسي والإجتماعي، والاعتناء بمصالح الإسلام والثورة والنظام، والتعاون من أجل تخطّي الثورة والنظام للمشاكل والعقبات، بل وكانت خصاله تلك دافعاً ومحفّزاً لمثل هذه المواقف.\n" +
                    "إنّ تحرّك سماحته الفقهي والسياسي والاجتماعي يعيد إلى الذاكرة ما قاله الإمام الخميني(قدس سره) من أنّ: «الأنبياء عملهم السياسة والديانة، تلك السياسة التي تحرك الجماهير من مواضعها وتهديهم إلى كل ما فيه صلاح الاُمة والناس».\n" +
                    "سماحته يرى أنّ العلاقة بين المرجعية والسياسة تنبعث من روح الثقافة الدينية وتعاليم الشريعة الإسلامية.\n" +
                    "نبتهل إلى المولى الجليل بدوام العز والسلامة لهذا الفقيه الكبير، ونرجو في ظل هذا النظام الإسلامي المبارك والأنفاس الطيبة للإمام الراحل(قدس سره) أن يدوم للحوزة والمجتمع الإسلامي رجال من أمثال سماحة آية اللّه العظمى الصانعي.\n" +
                    "\n" +
                    "والسلام عليكم و رحمة اللّه و بركاته\n");
            db.insert(tblAboutUs, null, about_Ar);
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

        Cursor cursor = db.rawQuery("select * from " + tblBook , null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Book_Model row = new Book_Model();

                row.setId(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setTitle(cursor.getString(2));
                row.setLang_ID(cursor.getInt(3));
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

    public ArrayList<Book_Model> getAll_Books_by_Lang(int lang_id ,String name ) {
        ArrayList<Book_Model> resultLst = new ArrayList<Book_Model>();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        String _whereClouse = "";
        if (db == null) {
            return null;
        }
        if(name.length() > 0)
        {
            _whereClouse = " AND " + Name_tblBook + " like '%"+name+"%'";
        }
        Cursor cursor = db.rawQuery("select * from " + tblBook +" where " + Lang_ID_tblBook + " = " + lang_id + _whereClouse , null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Book_Model row = new Book_Model();

                row.setId(cursor.getInt(0));
                row.setName(cursor.getString(1));
                row.setTitle(cursor.getString(2));
                row.setLang_ID(cursor.getInt(3));
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
        row.put(Lang_ID_tblBook, bk.getLang_ID());
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
        row.put(Lang_ID_tblBook, bk.getLang_ID());
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
            row.setLang_ID(cursor.getInt(3));
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
    public ArrayList<ContactUsModel> getAll_ContactUs_With_LanguageID(int Lang_ID) {
        ArrayList<ContactUsModel> resultLst = new ArrayList<ContactUsModel>();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblContactUs + " where "+Lang_ID_tblContactUs+" = " + Lang_ID, null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                ContactUsModel row = new ContactUsModel();

                row.set_id(cursor.getInt(0));
                row.setLang_ID(cursor.getInt(1));
                row.setVersion(cursor.getString(2));
                row.setTitle(cursor.getString(3));
                row.setAddress(cursor.getString(4));
                row.setPostCode(cursor.getString(5));
                row.setMailBox(cursor.getString(6));
                row.setPreTel1(cursor.getString(7));
                row.setTel1(cursor.getString(8));
                row.setPreTel2(cursor.getString(9));
                row.setTel2(cursor.getString(10));
                row.setPreTel3(cursor.getString(11));
                row.setTel3(cursor.getString(12));
                row.setPreTel4(cursor.getString(13));
                row.setTel4(cursor.getString(14));
                row.setPreTel5(cursor.getString(15));
                row.setTel5(cursor.getString(16));
                row.setPreFax(cursor.getString(17));
                row.setFax(cursor.getString(18));
                row.setRowNumber(cursor.getInt(19));
                resultLst.add(row);
            }
        }
        cursor.close();
        db.close();
        return resultLst;
    }

    public ContactUsModel get_ContactUs_With_ID(int ID) {
        ContactUsModel result = new ContactUsModel();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblContactUs + " where "+ID_tblContactUs+" = " + ID, null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();


               result.set_id(cursor.getInt(0));
               result.setLang_ID(cursor.getInt(1));
               result.setVersion(cursor.getString(2));
               result.setTitle(cursor.getString(3));
               result.setAddress(cursor.getString(4));
               result.setPostCode(cursor.getString(5));
               result.setMailBox(cursor.getString(6));
               result.setPreTel1(cursor.getString(7));
               result.setTel1(cursor.getString(8));
               result.setPreTel2(cursor.getString(9));
               result.setTel2(cursor.getString(10));
               result.setPreTel3(cursor.getString(11));
               result.setTel3(cursor.getString(12));
               result.setPreTel4(cursor.getString(13));
               result.setTel4(cursor.getString(14));
               result.setPreTel5(cursor.getString(15));
               result.setTel5(cursor.getString(16));
               result.setPreFax(cursor.getString(17));
               result.setFax(cursor.getString(18));
               result.setRowNumber(cursor.getInt(19));

            }
        }
        cursor.close();
        db.close();
        return result;
    }
    //endregion///////////////////////////// ContactUs ////////////////////////////////////

    //region///////////////////////////// setting ////////////////////////////////////
    public Setting_model get_Setting_byLang(Integer lang_id) {
        Setting_model row = new Setting_model();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblSettings + " where " + Lang_ID_tblSettings + " = ?", new String[]{String.valueOf(lang_id)});
        if (cursor.moveToNext()) {
            row.set_id(cursor.getInt(0));
            row.setLang_id(cursor.getInt(1));
            row.setWebSiteUrl(cursor.getString(2));

            row.setNewsUrl(cursor.getString(3));
            row.setEstekhareUrl(cursor.getString(4));
            row.setLibraryUrl(cursor.getString(5));
            row.setFeghhiUrl(cursor.getString(6));
            row.setNashriehUrl(cursor.getString(7));

            row.setSenderEmailAddress(cursor.getString(8));
            row.setSenderPassword(cursor.getString(9));
            row.setRecieverEmailAddress(cursor.getString(10));
            row.setSMTPHost(cursor.getString(11));
            row.setSMTPSocketFactoryPort(cursor.getString(12));
            row.setSMTPSocketFactoryClass(cursor.getString(13));
            row.setSMTPAuth(cursor.getString(14));
            row.setSMTPPort(cursor.getString(15));
            row.setVojoohatSiteUrl(cursor.getString(16));
            row.setVersion(cursor.getString(17));

        }
        cursor.close();
        db.close();

        return row;
    }
    //endregion///////////////////////////// setting ////////////////////////////////////

    //region///////////////////////////// language ////////////////////////////////////
    public Language_model get_LanguageID(String _Name) {
        Language_model row = new Language_model();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select "+ ID_tblLanguage +" from "
                + tblLanguage + " where " + Name_tblLanguage + " = ?", new String[]{String.valueOf(_Name)});
        if (cursor.moveToNext()) {
            row.set_id(cursor.getInt(0));
        }
        cursor.close();
        db.close();

        return row;
    }
    //endregion///////////////////////////// language ////////////////////////////////////

    //region //////////////////////////////// about us ///////////////////////////////////
    public AboutUs_Model get_AboutUs_byLang(Integer lang_id) {
        AboutUs_Model  row = new AboutUs_Model ();
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from " + tblAboutUs + " where " + Lang_ID_tblAboutUs + " = ?", new String[]{String.valueOf(lang_id)});
        if (cursor.moveToNext()) {

            row.set_id(cursor.getInt(0));
            row.setLang_ID(cursor.getInt(1));
            row.setVersion(cursor.getString(2));
            row.setDsc(cursor.getString(3));
        }
        cursor.close();
        db.close();

        return row;
    }
    //endregion //////////////////////////////////////////////////////////////////////////
}



