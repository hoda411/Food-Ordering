package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2_Category extends AppCompatActivity {
TextView p;
    String EmailHolder;
    private  SessionManager session;
    public static final String UserEmail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_category);

        Button bt=findViewById(R.id.det);
        registerForContextMenu(bt);
       //  p=(TextView) findViewById(R.id.person);
        session= new SessionManager(getApplicationContext());
        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(MainActivity2Login.UserEmail);

        // Setting up received email to TextView.
        if(EmailHolder!=null)
        Toast.makeText(MainActivity2_Category.this,"Hi "+EmailHolder, Toast.LENGTH_LONG).show();
        else
        if(EmailHolder!=null)
            Toast.makeText(MainActivity2_Category.this,"Hi ", Toast.LENGTH_LONG).show();
     //   p.setText("Hi  "+ p.getText().toString()+ EmailHolder+" !");
    }
    public void logpage(View view){
        startActivity(new Intent("MainActivity2Login.java"));
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
            case R.id.contact:
                getPhoneContacts();
                return  true;
            case R.id.out:
                session.setlogin(false);
                startActivity(new Intent("MainActivity2Login.java"));
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getPhoneContacts(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},0);
        }
        ContentResolver contentResolver=getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor=contentResolver.query(uri,null,null,null,null);
        Log.i("CONTACTInFO","Number Of Contacts : "+Integer.toString(cursor.getCount()));
        if(cursor.getCount()>0){
            while(cursor.moveToNext()) {
                @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i("CONTACTInFO", "Name " + contactName + "  Number " + contactNumber);
            }}
        else
            Log.i("CONTACTInFO","No Contact ");

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menitem) {
                return  onPopupMenuClick(menitem);

            }
        });
        popup.show();
    }
    private  boolean onPopupMenuClick(MenuItem item){
        switch (item.getItemId()) {
            case R.id.back:
                startActivity(new Intent("MainActivity2_CRUD.java"));
                return true;
            default:
                return false;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);  MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent("MainActivity2_details.java"));
                return true;
            default: return super.onContextItemSelected(item);  } }



}