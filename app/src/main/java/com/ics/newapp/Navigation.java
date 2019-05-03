package com.ics.newapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ics.newapp.fregment.Create_Activity;
import com.ics.newapp.fregment.Event_Host_User_Profile;
import com.ics.newapp.fregment.Favorite_list;
import com.ics.newapp.fregment.Guest_Event_Screen;
import com.ics.newapp.fregment.Host_Event_Screen;
import com.ics.newapp.fregment.ListFragment;
import com.ics.newapp.fregment.MapFregment;
import com.ics.newapp.fregment.MyActivity_fragment;
import com.ics.newapp.fregment.ReviewAndRating;
import com.ics.newapp.fregment.Selection_Screen;
import com.ics.newapp.fregment.bookmark_fragment;
import com.ics.newapp.fregment.profile_fragment;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout ll_mlist,ll_create_act,share_id;

    LinearLayout fav_list,add_event,view_profile;
    Button btn_M_view,btn_L_view,view_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new MapFregment());
        tx.commit();

//************************************************************************************************
        ll_mlist=findViewById(R.id.mlist);
        ll_create_act=findViewById(R.id.create_act);
        share_id=findViewById(R.id.share_id);

        ll_mlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new ListFragment());
                ft.commit();
            }
        });

        ll_create_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Host_Event_Screen());
                ft.commit();
            }
        });
        share_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Selection_Screen());
                ft.commit();
            }
        });
//***************************************************************************************
        fav_list=(LinearLayout)findViewById(R.id.fav_list);
        add_event=(LinearLayout)findViewById(R.id.add_event1);
        view_profile=(LinearLayout)findViewById(R.id.provile_view1);
        btn_L_view=findViewById(R.id.l_view);
        btn_M_view=findViewById(R.id.m_view);


        fav_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Favorite_list());
                ft.commit();
            }
        });

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Create_Activity());
                ft.commit();
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new profile_fragment());
                ft.commit();
            }
        });

        btn_L_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new ListFragment());
                ft.commit();
            }
        });

        btn_M_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new MapFregment());
                ft.commit();
            }
        });
        //****************************************************************************************
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }
        if (id == R.id.action_share) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_homePage:
                fragment = new MapFregment();
                break;
            case R.id.nav_profile:
                fragment = new profile_fragment();
                break;
            case R.id.nav_fav:
                fragment = new Favorite_list();
                break;
            case R.id.nav_my_acti:
                fragment = new MyActivity_fragment();

                break;

            case R.id.nav_bookmark:
                fragment = new bookmark_fragment();
                break;

            case R.id.nav_contact:
               //te fragment = new Create_Activity();
                break;

            case R.id.nav_share:
               // fragment = new Event_Host_User_Profile();
                break;
            case R.id.nav_logout:
                //fragment = new Guest_Event_Screen();
                break;
//            case R.id.nav_logout:
//                fragment = new Host_Event_Screen();
//                break;
        }

        //replacing the fragment
        if (fragment != null) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

}
