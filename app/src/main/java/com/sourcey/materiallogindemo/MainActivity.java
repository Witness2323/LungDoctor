package com.sourcey.materiallogindemo;
import  com.sourcey.materiallogindemo.R;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import  com.roughike.bottombar.*;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import java.util.*;
import  android.os.PersistableBundle;
public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private   BottomBar bottomBar;
    FragmentManager fm;
    private String[] tags = new String[]{"HomeFragment", "AddFragment", "HelpFragment"};
    private List<Fragment> fragments = new ArrayList<>();
    Fragment HomeFragment1, AddFragment1, HelpFragment1;
    private Fragment mContent;//当前fragment
    ActionBar actionBar1;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
      actionBar1=getSupportActionBar();
        if(actionBar1 !=null){
            actionBar1.setDisplayHomeAsUpEnabled(true);
            actionBar1.setHomeAsUpIndicator(R.drawable.ic_menu_18pt);
        }
        init();
        navView.setCheckedItem(R.id.profile);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return  true;
            }
        });



        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId) {
                    case R.id.first2page:
                       //switchFragment(HomeFragment1).commit();
                      //  switchFragment(mContent, fragments.get(0),0);
                        if(mContent!=fragments.get(0)){
                       actionBar1.show();
                        switchFragment(mContent, fragments.get(0),0);}
                        break;
                    case R.id.add1:
                      // switchFragment(AddFragment1).commit();
                        actionBar1.show();
                        switchFragment(mContent, fragments.get(1),1);
                        break;
                    case R.id.help:
                      //switchFragment(HomeFragment1).commit();
                        actionBar1.hide();
                        switchFragment(mContent, fragments.get(2),2);


                        break;

                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {


            @Override
            public void onTabReSelected(@IdRes int tabId) {
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddFragment()).commit();
            }
        });






    }
    private void init() {
        fm = getSupportFragmentManager();
        HomeFragment1 = new HomeFragment();
         AddFragment1 = new AddFragment();
         HelpFragment1 = new HelpFragment();
        fragments.add(0, HomeFragment1);
        fragments.add(1, AddFragment1);
        fragments.add(2, HelpFragment1);

        mContent = HomeFragment1 ;
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, mContent);
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /**
     * 当activity非正常销毁时会进入此方法中 保存一些临时性的数据
     *
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        stateCheck(outState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    public void switchFragment(Fragment from, Fragment to, int position) {

        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from)
                        .add(R.id.fragment_container, to, tags[position]).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    /**
     * 状态检测 用于内存不足的时候保证fragment不会重叠
     *
     * @param savedInstanceState
     */
    private void stateCheck(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            fm = getSupportFragmentManager();
            fm = getSupportFragmentManager();
            FragmentTransaction fts = fm.beginTransaction();
            HomeFragment af = new HomeFragment();
            mContent = af;
            fts.add(R.id.fragment_container, af);
            fts.commit();
        } else {
            /**
             * 通过设置的tag来寻找fragment
             */
            HomeFragment home = (HomeFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[0]);
            AddFragment content = (AddFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[1]);
            HelpFragment list = (HelpFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[2]);

            getSupportFragmentManager().beginTransaction().show(home).hide(content).hide(list)
                    .commitAllowingStateLoss();
        }
    }


}
