package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sourcey.materiallogindemo.FruitAdapter.OnItemClickLitener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class History_activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fruit[] fruits={new Fruit("马思纯",R.drawable.ic_ma1),new Fruit("马思纯",R.drawable.ic_ma2)
            ,new Fruit("马思纯",R.drawable.ic_ma3),new Fruit("马思纯",R.drawable.ic_ma4)
            ,new Fruit("马思纯",R.drawable.ic_ma5),new Fruit("马思纯",R.drawable.ic_ma6)
            ,new Fruit("马思纯",R.drawable.ic_ma1),new Fruit("马思纯",R.drawable.ic_ma3)
            ,new Fruit("马思纯",R.drawable.ic_ma2),new Fruit("马思纯",R.drawable.ic_ma4)};

    private List<Fruit> fruitList=new ArrayList<>();

    private FruitAdapter adapter;

    ActionBar actionBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);

        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        actionBar2=getSupportActionBar();
        if(actionBar2 !=null) {
            actionBar2.setDisplayHomeAsUpEnabled(true);
            actionBar2.setHomeAsUpIndicator(R.drawable.ic_menu_18pt);
        }
        adapter.setOnItemClickLitener(new OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                final ProgressDialog progressDialog = new ProgressDialog(History_activity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("加载ing……");
                progressDialog.show();
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed


                                progressDialog.dismiss();
                            }
                        }, 5000);
                Intent intent12 = new Intent(History_activity.this, HomeActivity.class);

                startActivity(intent12);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(History_activity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                //adapter.removeData(position);
            }
        });
    }


    private void initFruits(){
        fruitList.clear();
        for (int i = 0; i <10 ; i++) {
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    public void addFragment(Fragment ft) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftraTransaction = fm.beginTransaction();
        ftraTransaction.addToBackStack(null);

        ftraTransaction.commit();
    }


}
