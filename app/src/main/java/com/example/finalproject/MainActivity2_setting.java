package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity2_setting extends AppCompatActivity {
    private Switch myswitch;
    SharedPref sharedpref;
    private  SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);

        //use shared preference to change mode
        if(sharedpref.loadNightModeState()==true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        session= new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_main_activity2_setting);
        myswitch=(Switch)findViewById(R.id.myswitch);



        if (sharedpref.loadNightModeState()==true) {
            myswitch.setChecked(true);
        }

        // onclick to change mode
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }

            }
        });
    }


    // add option menu
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent("MainActivity2_setting.java"));
                return  true;
            case R.id.me:
                startActivity(new Intent("MainActivity2_Category.java"));
                return  true;
            case R.id.out:
                session.setlogin(false);
                startActivity(new Intent("MainActivity2Login.java"));
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // restart activity to change mode
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(),MainActivity2_setting.class);
        startActivity(i);
        finish();
    }
}