package com.example.hmohamadi.ebooklibrary.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.hmohamadi.ebooklibrary.Models.Book_Model;
import com.example.hmohamadi.ebooklibrary.R;

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
            imagebookPhoto.setImageBitmap(getBitmapFromAssets(book.getUrl_image()));
        }

        AppCompatTextView txt_name = (AppCompatTextView)v.findViewById(R.id.txt_name_gridItem);
        txt_name.setText(book.getName());

        AppCompatTextView txt_autor = (AppCompatTextView)v.findViewById(R.id.txt_AutorName_gridItem);
        txt_autor.setText(book.getAutorname());

        AppCompatTextView txt_Year = (AppCompatTextView)v.findViewById(R.id.txt_Year_gridItem);
        txt_Year.setText(book.getYear());
        return v;
    }

    private Bitmap getBitmapFromAssets(String fileName){
        /*
            AssetManager
                Provides access to an application's raw asset files.
        */

        /*
            public final AssetManager getAssets ()
                Retrieve underlying AssetManager storage for these resources.
        */
        AssetManager am = mycontext.getAssets();
        InputStream is = null;
        try{
            /*
                public final InputStream open (String fileName)
                    Open an asset using ACCESS_STREAMING mode. This provides access to files that
                    have been bundled with an application as assets -- that is,
                    files placed in to the "assets" directory.

                    Parameters
                        fileName : The name of the asset to open. This name can be hierarchical.
                    Throws
                        IOException
            */
            is = am.open(fileName);
        }catch(IOException e){
            e.printStackTrace();
        }

        /*
            BitmapFactory
                Creates Bitmap objects from various sources, including files, streams, and byte-arrays.
        */

        /*
            public static Bitmap decodeStream (InputStream is)
                Decode an input stream into a bitmap. If the input stream is null, or cannot
                be used to decode a bitmap, the function returns null. The stream's
                position will be where ever it was after the encoded data was read.

                Parameters
                    is : The input stream that holds the raw data to be decoded into a bitmap.
                Returns
                    The decoded bitmap, or null if the image data could not be decoded.
        */
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

}
