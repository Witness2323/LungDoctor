package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class FeedBcakActivity extends AppCompatActivity {
    ActionBar actionBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_bcak);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText editText=(EditText)findViewById(R.id.edit_text);
       // String inputText=editText.getText().toString();获取内容


        actionBar2=getSupportActionBar();
        if(actionBar2 !=null){
            actionBar2.setDisplayHomeAsUpEnabled(true);
            actionBar2.setHomeAsUpIndicator(R.drawable.icon2menu);
        }
    }
}
