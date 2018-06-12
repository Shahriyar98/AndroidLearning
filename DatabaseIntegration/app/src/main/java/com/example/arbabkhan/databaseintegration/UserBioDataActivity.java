package com.example.arbabkhan.databaseintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;

import Adaptors.PersonInfoAdaptor;

public class UserBioDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List list_RecyclerResource;
    PersonInfoAdaptor personAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bio_data);

        this.list_RecyclerResource = UserAuthenticationActivity.arr_StudentInfo;
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.personAdaptor = new PersonInfoAdaptor(this.list_RecyclerResource, this);
        this.recyclerView.setAdapter(this.personAdaptor);
    }
}
