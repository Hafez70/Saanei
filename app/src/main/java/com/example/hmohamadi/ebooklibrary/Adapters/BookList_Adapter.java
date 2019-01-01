package com.example.hmohamadi.ebooklibrary.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.codesgood.views.JustifiedTextView;
import com.example.hmohamadi.ebooklibrary.Models.Book_Model;
import com.example.hmohamadi.ebooklibrary.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BookList_Adapter extends ArrayAdapter<Book_Model> {

    private Context mycontext;
    private List<Book_Model> _lstbooks;
    public BookList_Adapter(@NonNull Context context, List<Book_Model> books) {
        super(context, R.layout.book_list_layout,books);
        this.mycontext = context;
        this._lstbooks = books;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater lf = (LayoutInflater ) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = lf.inflate(R.layout.book_list_layout,parent,false);
        Book_Model book = _lstbooks.get(position);

        AppCompatImageView imagebookPhoto = (AppCompatImageView)v.findViewById(R.id.img_bookImage_gridItem);
        if(book.getUrl_image().length() != 0) {

            Bitmap myBitmap = getBitmapFromFielPath(book.getUrl_image());
            if(myBitmap != null) {
                myBitmap = getRoundedCornerBitmap(myBitmap,10);
                imagebookPhoto.setImageBitmap(myBitmap);
            }
        }

        AppCompatTextView txt_name = (AppCompatTextView)v.findViewById(R.id.txt_name_gridItem);
        txt_name.setText(book.getName());

        JustifiedTextView txt_keyword = (JustifiedTextView)v.findViewById(R.id.txt_Keyword_gridItem);
        txt_keyword.setText(book.getKeywords());

//
//        AppCompatTextView txt_autor = (AppCompatTextView)v.findViewById(R.id.txt_AutorName_gridItem);
//        txt_autor.setText(book.getAutorname());
//
//        AppCompatTextView txt_Year = (AppCompatTextView)v.findViewById(R.id.txt_Year_gridItem);
//        txt_Year.setText(book.getYear());

        return v;
    }

    private Bitmap getBitmapFromAssets(String fileName){

        AssetManager am = mycontext.getAssets();
        InputStream is = null;
        try{

            is = am.open(fileName);
        }catch(IOException e){
            e.printStackTrace();
        }


        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
    private Bitmap getBitmapFromFielPath(String fileName){


        File imgFile = new File(fileName);

        if(imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            return myBitmap;
        }
        return null;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
