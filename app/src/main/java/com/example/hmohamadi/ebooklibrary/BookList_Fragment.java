package com.example.hmohamadi.ebooklibrary;



import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.hmohamadi.ebooklibrary.Adapters.BookList_Adapter;
import com.example.hmohamadi.ebooklibrary.Models.Book_Model;
import com.folioreader.FolioReader;

import java.util.ArrayList;
import java.util.List;



        /**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookList_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookList_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookList_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY_ID = "param_category_id";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String _param_category_id;

    GridView grd_booklist;
    List<Book_Model> _lstBooks = new ArrayList<Book_Model>();

    private OnFragmentInteractionListener mListener;

    public BookList_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param_category_id Parameter 1.
     *
     * @return A new instance of fragment BookList_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookList_Fragment newInstance(String param_category_id) {
        BookList_Fragment fragment = new BookList_Fragment();
        if(param_category_id.length() != 0)
        {
            Bundle args = new Bundle();
            args.putString(ARG_CATEGORY_ID, param_category_id);
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _param_category_id = getArguments().getString(ARG_CATEGORY_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_booklist, container, false);
        // Inflate the layout for this fragment
        _lstBooks.add(new Book_Model(1,"رساله توضیح المسائل","1352","آیت الله صانعی",""));
        _lstBooks.add(new Book_Model(2,"کتاب نمونه 1","1354","آیت الله صانعی",""));
        _lstBooks.add(new Book_Model(3,"کتاب نمونه 2","1356","آیت الله صانعی",""));
        _lstBooks.add(new Book_Model(4,"کتاب نمونه 3","1380","آیت الله صانعی",""));
        _lstBooks.add(new Book_Model(5,"کتاب نمونه 4","1382","آیت الله صانعی",""));
        _lstBooks.add(new Book_Model(6,"کتاب نمونه 5","1383","آیت الله صانعی",""));

        grd_booklist = (GridView)rootView.findViewById(R.id.grdBookList);
        grd_booklist.setAdapter(new BookList_Adapter(getActivity(),_lstBooks));
        grd_booklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book_Model _book = _lstBooks.get(position);
                FolioReader folioReader = FolioReader.get();
                folioReader.openBook(R.raw.resal);

            }
        });

        return rootView;
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
