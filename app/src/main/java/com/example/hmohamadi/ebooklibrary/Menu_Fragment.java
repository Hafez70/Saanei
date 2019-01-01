package com.example.hmohamadi.ebooklibrary;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout;

import com.example.hmohamadi.ebooklibrary.Helpers.ChangeActivityHelper;
import com.example.hmohamadi.ebooklibrary.Helpers.DBHelper;
import com.example.hmohamadi.ebooklibrary.Models.Language_model;
import com.example.hmohamadi.ebooklibrary.Models.Setting_model;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.util.AppUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Menu_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Menu_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private OnFragmentInteractionListener mListener;

    public Menu_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Menu_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu_Fragment newInstance() {
        Menu_Fragment fragment = new Menu_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_menu, container, false);


        //---------- contact us --------------------//
        LinearLayout _btnSendEmail =  v.findViewById(R.id.btnSendEmail);
        _btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),SendMailActivity.class,false);
            }
        });
        //---------- contact us --------------------//

        //---------- About us --------------------//
        LinearLayout _btnAboutUs = v.findViewById(R.id.btnAboutUs);
        _btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),AboutUsActivity.class,false);
            }
        });
        //---------- About us --------------------//

        //---------- Contact us --------------------//
        LinearLayout _btnContactUS = v.findViewById(R.id.btnContactUs);
        
        _btnContactUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),ContactUsActivity.class,false);
            }
        });
        //---------- Contact us--------------------//

        //---------- WebView --------------------//
        LinearLayout _btnEnterWebSIte = (LinearLayout) v.findViewById(R.id.btnEnterWebSIte);
        _btnEnterWebSIte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getWebSiteUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });

        LinearLayout _btnPDF = (LinearLayout) v.findViewById(R.id.btnPDF);
        _btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getLibraryUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });


        LinearLayout _btnNews = (LinearLayout) v.findViewById(R.id.btnNews);
        _btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getNewsUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });


        LinearLayout _btnEstekhare  = (LinearLayout) v.findViewById(R.id.btnEstekhare);
        _btnEstekhare .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getEstekhareUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });


        LinearLayout _btnNashrie = (LinearLayout) v.findViewById(R.id.btnNashriehUrl);

        _btnNashrie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getNashriehUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });

        LinearLayout _btnfeghhi = (LinearLayout) v.findViewById(R.id.btnFeghhiUrl);
        _btnfeghhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getFeghhiUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams();
        //


        Config _config = AppUtil.getSavedConfig(getActivity());
        if(_config.getLanguage().length() > 0)
        {
            _btnNashrie.setVisibility(View.GONE);
            _btnfeghhi.setVisibility(View.GONE);
        }
        else
        {
            _btnNashrie.setVisibility(View.VISIBLE);
            _btnfeghhi.setVisibility(View.VISIBLE);
        }
        //---------- WebView--------------------//
        //---------- Setting --------------------//
        LinearLayout _btnbooklist =  v.findViewById(R.id.btnBookList);
        _btnbooklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("call_booklist");
            }
        });
        //---------- Setting--------------------//
        //---------- WebView --------------------//
        LinearLayout _btnPayments = (LinearLayout) v.findViewById(R.id.btnEnterPayments);
        _btnPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString(Constants.WEBVIEW_URL_KEY, GetSettingConfig().getVojoohatSiteUrl());
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false,b);
            }
        });
        //---------- WebView--------------------//
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

        }

    }

    private Setting_model GetSettingConfig()
    {
        DBHelper db = new DBHelper(getActivity());
        Config _conf = AppUtil.getSavedConfig(getActivity());
        Language_model lng = db.get_LanguageID((_conf.getLanguage().length() == 0 ? "fa":  _conf.getLanguage()));

        return db.get_Setting_byLang(lng.get_id());
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
        void onFragmentInteraction(String _data);
    }

}
