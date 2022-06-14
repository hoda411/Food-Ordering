package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity2_details extends AppCompatActivity {
    private  SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session= new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_main_activity2_details);
    }
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
}