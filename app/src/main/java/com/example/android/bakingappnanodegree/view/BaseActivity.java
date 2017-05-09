package com.example.android.bakingappnanodegree.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.bakingappnanodegree.R;

import java.util.ArrayList;



public abstract class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";
    //instance of the current activity
    public static BaseActivity activity;
    //app bar
    Toolbar toolbar;
    //virtual app bar
    //to make the page comes behind or below the app bar
    LinearLayout virtual_app_bar;

    // to allow sliding menu or not
    private boolean mAllowSideMenu = true;
    // inner layout to be inflated
    private int mActivityLayout;
    //side menu
    private DrawerLayout mDrawerLayout;

    //Constructor
    public BaseActivity(int activityLayout, boolean allowMenu) {
        this.mActivityLayout = activityLayout;
        this.mAllowSideMenu = allowMenu;
    }

    // abstract method to be called in activities that extend this one instead of oncreate().
    protected abstract void doOnCreate(Bundle bundle);

    /**
     * ========================================= activity life cycle =========================================
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = BaseActivity.this;

        if (mAllowSideMenu) {
            setContentView(R.layout.activity_base);
            ViewGroup contentLayout = (ViewGroup) findViewById(R.id.simple_fragment);
            if (mActivityLayout != -1)
                LayoutInflater.from(this).inflate(mActivityLayout, contentLayout, true);



            /*initialize appbar*/
            toolbar = (Toolbar) findViewById(R.id.app_bar);
            virtual_app_bar = (LinearLayout) findViewById(R.id.virtual_app_bar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
            /*initialize sideMenu*/

        } else {
            setContentView(mActivityLayout);
        }

        doOnCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // ar
//            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        }
    }

    /**
     * ========================================= app bar  =========================================
     */

//change the background of the header
    public void setHeaderBackgroundColor(int colorResId) {
        //get resource color for lollipop+ & pre-lollipop
        int bgColor = ContextCompat.getColor(this, colorResId);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(bgColor));
        }
    }

    //change the first line title of the header
    public void setHeaderTitleText(String titleText) {
        if (getSupportActionBar() != null) {
            if (TextUtils.isEmpty(titleText)) {
                getSupportActionBar().setTitle(titleText);
            } else
                getSupportActionBar().setTitle(null);
        }
    }

    //change the second line title of the header
    public void setHeaderSubTitleText(String titleText) {
        if (getSupportActionBar() != null) {
            if (TextUtils.isEmpty(titleText)) {
                getSupportActionBar().setSubtitle(titleText);
            } else {
                getSupportActionBar().setSubtitle(null);
            }
        }

    }

    //control the position of the page
    //isBehind == true  means that the page comes BEHIND the app bar
    //isBehind == false  means that the page comes BELOW the app bar
    public void displayBehindAppBar(boolean isBehind) {
        virtual_app_bar.setVisibility(isBehind ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
//            case R.id.item4:
//                displayToast("item4");
//                break;


            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ========================================= Navigation Drawer=========================================
     */

    //disable user from opening menu by drag from left side
    public void disableSideMenu() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    //enable user from opening menu by drag from left side
    public void enableSideMenu() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void onMenuItemPressed(int itemIndex) {

    }


    /**
     * ========================================= Back Button Pressed =========================================
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && onBackButtonPressed()) {
            return true; //call the default onBackPressed method
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean onBackButtonPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            return true;
        }
       /* else{
            finish();
        }*/
        return false;
    }





}