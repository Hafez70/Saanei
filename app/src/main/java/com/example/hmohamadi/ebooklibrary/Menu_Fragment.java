package com.example.hmohamadi.ebooklibrary;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hmohamadi.ebooklibrary.Helpers.ChangeActivityHelper;


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
        Button _btnSendEmail =  v.findViewById(R.id.btnSendEmail);
        _btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),SendMailActivity.class,false);
            }
        });
        //---------- contact us --------------------//

        //---------- About us --------------------//
        Button _btnAboutUs = v.findViewById(R.id.btnAboutUs);
        _btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),AboutUsActivity.class,false);
            }
        });
        //---------- About us --------------------//

        //---------- Contact us --------------------//
        Button _btnContactUS = v.findViewById(R.id.btnContactUs);
        _btnContactUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),ContactUsActivity.class,false);
            }
        });
        //---------- Contact us--------------------//

        //---------- WebView --------------------//
        Button _btnEnterWebSIte = (Button) v.findViewById(R.id.btnEnterWebSIte);
        _btnEnterWebSIte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),WebViewActivity.class,false);
            }
        });
        //---------- WebView--------------------//
        //---------- Setting --------------------//
        Button _btnSetting =  v.findViewById(R.id.btnSetting);
        _btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("call_setting");
            }
        });
        //---------- Setting--------------------//
        //---------- WebView --------------------//
        Button _btnPayments = (Button) v.findViewById(R.id.btnEnterPayments);
        _btnPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityHelper.changeActivity(getActivity(),PaymentsWebviewActivity.class,false);
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
