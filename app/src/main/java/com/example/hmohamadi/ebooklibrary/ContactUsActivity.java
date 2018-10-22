package com.example.hmohamadi.ebooklibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.hmohamadi.ebooklibrary.Adapters.ExpandableRecyclerAdapter;
import com.example.hmohamadi.ebooklibrary.Helpers.DividerItemDecoration;
import com.example.hmohamadi.ebooklibrary.Models.ExpandableItemModel;

import java.util.ArrayList;
import java.util.List;
import com.github.aakira.expandablelayout.Utils;


public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_ContactUs);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<ExpandableItemModel> data = new ArrayList<>();
        data.add(new ExpandableItemModel(
                getResources().getString(R.string.contactus_qom_title),
                getResources().getString(R.string.contactus_qom_description) ,
                Utils.createInterpolator(Utils.FAST_OUT_SLOW_IN_INTERPOLATOR)));
        data.add(new ExpandableItemModel(
                getResources().getString(R.string.contactus_tehran_title),
                getResources().getString(R.string.contactus_tehran_description),
                Utils.createInterpolator(Utils.FAST_OUT_SLOW_IN_INTERPOLATOR)));

        recyclerView.setAdapter(new ExpandableRecyclerAdapter(data));
    }
}
