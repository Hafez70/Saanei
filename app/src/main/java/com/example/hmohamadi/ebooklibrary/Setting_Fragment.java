package com.example.hmohamadi.ebooklibrary;


import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.FolioReader;
import com.folioreader.util.AppUtil;
import com.folioreader.util.UiUtil;
import com.folioreader.view.StyleableTextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Setting_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Setting_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private OnFragmentInteractionListener mListener;
    private Config config = null;
    boolean  isNightMode = false;
    public Setting_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Setting_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting_Fragment newInstance() {
        Setting_Fragment fragment = new Setting_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config = AppUtil.getSavedConfig(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_setting, container, false);

        initViews(rootview);

        return rootview;
    }

    private void initViews(View v) {
        inflateView(v);
        configFonts(v);
        configLang(v);
        SeekBar seek = v.findViewById(R.id.view_setting_font_size_seek_bar);
        Config _config = new Config()
                .setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL)
                .setDirection(Config.Direction.VERTICAL)
                .setFont(Constants.FONT_NAZANIN)
                .setFontSize(2)
                .setNightMode(false)
                .setShowTts(true)
                .setLanguage("");
        if (config==null) {
        config=_config;
        }

        seek.setProgress(config.getFontSize());
        configSeekBar(seek);
        selectFont(v,config.getFont());
        selectLang(v,config.getLanguage());
        isNightMode = config.isNightMode();

        ImageButton btnDay = (ImageButton)v.findViewById(R.id.view_setting_ib_day_mode);
        ImageButton btnnight= (ImageButton)v.findViewById(R.id.view_setting_ib_night_mode);
        int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
        if(isNightMode)
        {
            btnnight.setSelected(true);
            btnDay.setSelected(false);
            UiUtil.setColorIntToDrawable(_color, btnnight.getDrawable());
            UiUtil.setColorResToDrawable(R.color.app_gray, btnDay.getDrawable());
        }
        else
        {
            btnDay.setSelected(true);
            btnnight.setSelected(false);
            UiUtil.setColorIntToDrawable(_color, btnDay.getDrawable());
            UiUtil.setColorResToDrawable(R.color.app_gray, btnnight.getDrawable());
        }
       //////////////////////change theme
    }

    private void inflateView(View _v) {
        final ImageButton btnDay = _v.findViewById(R.id.view_setting_ib_day_mode);
        final ImageButton btnnight= _v.findViewById(R.id.view_setting_ib_night_mode);
        final int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNightMode = false;
                btnDay.setSelected(!isNightMode);
                btnnight.setSelected(isNightMode);
                config.setNightMode(isNightMode);
                UiUtil.setColorIntToDrawable(_color, btnDay.getDrawable());
                UiUtil.setColorResToDrawable(R.color.app_gray, btnnight.getDrawable());
                AppUtil.saveConfig(getContext(), config);
            }
        });
        btnnight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNightMode = true;
                btnDay.setSelected(!isNightMode);
                btnnight.setSelected(isNightMode);
                config.setNightMode(isNightMode);
                UiUtil.setColorIntToDrawable(_color, btnnight.getDrawable());
                UiUtil.setColorResToDrawable(R.color.app_gray, btnDay.getDrawable());
                AppUtil.saveConfig(getContext(), config);
            }
        });
    }

    private void configFonts(final View _v) {
        final int _color_grey = ContextCompat.getColor(getContext(),R.color.grey_color);
        final StyleableTextView btnnazanin = _v.findViewById(R.id.view_setting_font_nazanin);
        btnnazanin.setTextColor(_color_grey);
        final StyleableTextView btnlotus = _v.findViewById(R.id.view_setting_font_lotus);
        btnlotus.setTextColor(_color_grey);
        final StyleableTextView btnkoodak = _v.findViewById(R.id.view_setting_font_koodak);
        btnkoodak.setTextColor(_color_grey);
        final StyleableTextView btnyekan = _v.findViewById(R.id.view_setting_font_yekan);
        btnyekan.setTextColor(_color_grey);

        btnnazanin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnnazanin.setTextColor(_color);
                btnlotus.setTextColor(_color_grey);
                btnkoodak.setTextColor(_color_grey);
                btnyekan.setTextColor(_color_grey);
                selectFont(_v,Constants.FONT_NAZANIN);

            }
        });

        btnlotus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFont(_v,Constants.FONT_LOTUS);
                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnnazanin.setTextColor(_color_grey);
                btnlotus.setTextColor(_color);
                btnkoodak.setTextColor(_color_grey);
                btnyekan.setTextColor(_color_grey);
            }
        });
        btnkoodak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectFont(_v,Constants.FONT_KOODAK);
                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnnazanin.setTextColor(_color_grey);
                btnlotus.setTextColor(_color_grey);
                btnkoodak.setTextColor(_color);
                btnyekan.setTextColor(_color_grey);
            }
        });
        btnyekan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFont(_v,Constants.FONT_YEKAN);
                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnnazanin.setTextColor(_color_grey);
                btnlotus.setTextColor(_color_grey);
                btnkoodak.setTextColor(_color_grey);
                btnyekan.setTextColor(_color);
            }
        });
    }
    private void configLang(final View _v) {
        final int _color_grey = ContextCompat.getColor(getContext(),R.color.grey_color);
        final StyleableTextView btnFa = _v.findViewById(R.id.view_setting_lang_fa);
        btnFa.setTextColor(_color_grey);
        final StyleableTextView btnAr = _v.findViewById(R.id.view_setting_lang_Ar);
        btnAr.setTextColor(_color_grey);
        final StyleableTextView btnEn = _v.findViewById(R.id.view_setting_lang_en);
        btnEn.setTextColor(_color_grey);

        btnFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnFa.setTextColor(_color);
                btnAr.setTextColor(_color_grey);
                btnEn.setTextColor(_color_grey);
                selectLang(_v,Constants.LANG_FA);

            }
        });

        btnAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLang(_v,Constants.LANG_AR);
                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnFa.setTextColor(_color_grey);
                btnAr.setTextColor(_color);
                btnEn.setTextColor(_color_grey);

            }
        });
        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectLang(_v,Constants.LANG_EN);
                int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
                btnFa.setTextColor(_color_grey);
                btnAr.setTextColor(_color_grey);
                btnEn.setTextColor(_color);

            }
        });

    }
    private void selectLang(View _v,String selectedLang ) {
        final int _color_grey = ContextCompat.getColor(getContext(),R.color.grey_color);
        int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
        final StyleableTextView btnFa = _v.findViewById(R.id.view_setting_lang_fa);
        btnFa.setTextColor(_color_grey);
        final StyleableTextView btnAr = _v.findViewById(R.id.view_setting_lang_Ar);
        btnAr.setTextColor(_color_grey);
        final StyleableTextView btnEn = _v.findViewById(R.id.view_setting_lang_en);
        btnEn.setTextColor(_color_grey);
        switch (selectedLang) {
            case Constants.LANG_FA : {


                btnFa.setTextColor(_color);
                btnFa.setSelected(true);

                btnEn.setTextColor(_color_grey);
                btnEn.setSelected(false);

                btnAr.setTextColor(_color_grey);
                btnAr.setSelected(false);

                break;
            }
            case
                    Constants.LANG_EN :{

                btnFa.setTextColor(_color_grey);
                btnFa.setSelected(false);

                btnEn.setTextColor(_color);
                btnEn.setSelected(true);

                btnAr.setTextColor(_color_grey);
                btnAr.setSelected(false);
                break;
            }
            case
                    Constants.LANG_AR :
            {
                btnFa.setTextColor(_color_grey);
                btnFa.setSelected(false);

                btnEn.setTextColor(_color_grey);
                btnEn.setSelected(false);

                btnAr.setTextColor(_color);
                btnAr.setSelected(true);
                break;
            }

        }
        chngeApplicationLanguage(selectedLang);
        config.setLanguage(selectedLang);

        AppUtil.saveConfig(getActivity(), config);
    }

    private void chngeApplicationLanguage(String selectedLang )
    {
        String languageToLoad  = selectedLang; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,
        getActivity().getBaseContext().getResources().getDisplayMetrics());
    }

    private void selectFont(View _v,int selectedFont ) {
        int _color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
        switch (selectedFont) {
            case Constants.FONT_NAZANIN : {

                final StyleableTextView btnnazanin = _v.findViewById(R.id.view_setting_font_nazanin);
                btnnazanin.setTextColor(_color);
                setSelectedFont(_v, true, false, false, false);

                break;
            }
            case
                    Constants.FONT_LOTUS :{
                final StyleableTextView btnltus = _v.findViewById(R.id.view_setting_font_lotus);
                btnltus.setTextColor(_color);
                setSelectedFont(_v,false, true, false, false); break;
            }
            case
                    Constants.FONT_KOODAK :
            {
                final StyleableTextView btnkoodak = _v.findViewById(R.id.view_setting_font_koodak);
                btnkoodak.setTextColor(_color);
                setSelectedFont(_v,false, false, true, false);
                break;
            }
            case
                    Constants.FONT_YEKAN :
            {
                final StyleableTextView btnyekan = _v.findViewById(R.id.view_setting_font_yekan);
                btnyekan.setTextColor(_color);
                setSelectedFont(_v,false, false, false, true);
                break;
            }
         }
        config.setFont(selectedFont);

        AppUtil.saveConfig(getActivity(), config);
    }

    private void setSelectedFont(View v, Boolean nazanin,Boolean lotus,Boolean koodak,Boolean yekan) {

        StyleableTextView btnnazanin = v.findViewById(R.id.view_setting_font_nazanin);
        btnnazanin.setSelected(nazanin);
        Log.w("My error >>>>", " setSelectedFont >>>>>>>>>>>>>" + String.valueOf(nazanin));
        StyleableTextView btnlotus = v.findViewById(R.id.view_setting_font_lotus);
        btnlotus.setSelected(lotus);
        StyleableTextView btnkoodak = v.findViewById(R.id.view_setting_font_koodak);
        btnkoodak.setSelected(koodak);
        StyleableTextView btnyekan = v.findViewById(R.id.view_setting_font_yekan);
        btnyekan.setSelected(yekan);
    }

    private void configSeekBar(SeekBar _seek) {

        _seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                config.setFontSize(progress);
                AppUtil.saveConfig(getActivity(), config);
               // EventBus.getDefault().post(ReloadDataEvent())
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
