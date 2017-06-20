package de.droidenschmiede.meemoforandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.ArrayList;

import de.droidenschmiede.meemoforandroid.R;
import de.droidenschmiede.meemoforandroid.helper.MeemoHelper;
import de.droidenschmiede.meemoforandroid.helper.Singleton;
import de.droidenschmiede.meemoforandroid.interfaces.VolleyInterface;
import de.droidenschmiede.meemoforandroid.objects.CustomError;
import de.droidenschmiede.meemoforandroid.objects.Login;
import de.droidenschmiede.meemoforandroid.objects.Thing;
import de.droidenschmiede.meemoforandroid.objects.Things;
import us.feras.mdv.MarkdownView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, VolleyInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Load settings
        SharedPreferences sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        Singleton.setServer(sharedPref.getString("server", ""));
        Singleton.setUsername(sharedPref.getString("username", ""));
        Singleton.setPassword(sharedPref.getString("password", ""));

        //Login User
        MeemoHelper meemoHelper = new MeemoHelper();
        meemoHelper.loginUser(this, this);
    }

    @Override
    public void onResponse(String result, Class clazz) {
        MeemoHelper meemoHelper = new MeemoHelper();

        if (clazz.equals(CustomError.class)) {

            Gson gson = new Gson();
            CustomError error = gson.fromJson(result, CustomError.class);

            Snackbar.make(findViewById(R.id.lay_main_content), error.getStatus() + "\n" + error.getMessage() ,Snackbar.LENGTH_SHORT).show();
        }

        if (clazz.equals(Login.class)) {
            Gson gson = new Gson();
            Login login = gson.fromJson(result, Login.class);

            Singleton.setLogin(login);

            meemoHelper.getUserThings(getApplicationContext(), this);
        }

        if (clazz.equals(Things.class)) {
            try {
                MarkdownView mv = (MarkdownView) findViewById(R.id.mv_main_test);

                Gson gson = new Gson();
                Things things = gson.fromJson(result, Things.class);

                ArrayList<Thing> thingList = things.getThings();

                String markdownString = "";

                for (int i = 0; i < thingList.size(); i++) {
                    markdownString += thingList.get(i).getContent();
                    markdownString += "\n\n---\n\n";
                }

                mv.loadMarkdown(markdownString);
            } catch (Exception e) {
                Log.d("MainActivity", e.getMessage());
            }
        }

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
