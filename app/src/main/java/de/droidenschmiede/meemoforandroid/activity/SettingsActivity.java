package de.droidenschmiede.meemoforandroid.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.droidenschmiede.meemoforandroid.R;
import de.droidenschmiede.meemoforandroid.helper.Singleton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref = getSharedPreferences("settings",Context.MODE_PRIVATE);
        EditText edtServer = (EditText) findViewById(R.id.edt_settings_server);
        EditText edtUsername = (EditText) findViewById(R.id.edt_settings_username);
        EditText edtPassword = (EditText) findViewById(R.id.edt_settings_password);

        String server = sharedPref.getString("server", "");
        String username = sharedPref.getString("username", "");
        String password = sharedPref.getString("password", "");

        edtServer.setText(server);
        edtUsername.setText(username);
        edtPassword.setText(password);


        Button saveBtn = (Button) findViewById(R.id.btn_settings_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtServer = (EditText) findViewById(R.id.edt_settings_server);
                EditText edtUsername = (EditText) findViewById(R.id.edt_settings_username);
                EditText edtPassword = (EditText) findViewById(R.id.edt_settings_password);

                SharedPreferences sharedPref = getSharedPreferences("settings",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("server", edtServer.getText().toString());
                editor.putString("username", edtUsername.getText().toString());
                editor.putString("password", edtPassword.getText().toString());
                editor.apply();

                Singleton.setServer(edtServer.getText().toString());
                Singleton.setUsername(edtUsername.getText().toString());
                Singleton.setPassword(edtPassword.getText().toString());

                Snackbar.make(findViewById(R.id.lay_settings_main), "Settings Saved" ,Snackbar.LENGTH_SHORT).show();

            }
        });
    }

}
