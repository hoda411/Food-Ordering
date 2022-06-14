package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity2Login extends AppCompatActivity {
    Button LogInButton;
    TextView RegisterButton ;
    EditText Email, Password ;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    DBHelper sqLiteHelper;
    Cursor cursor;
    String TempPass = "NOTFOUND" ;
    public static final String UserEmail = "";


private  SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        LogInButton = (Button)findViewById(R.id.buttonLogin);
        RegisterButton = (TextView) findViewById(R.id.buttonRegister);
        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);



        sessionManager= new SessionManager(getApplicationContext());
      if(sessionManager.isLoggedIn()){
          Intent i=new Intent(MainActivity2Login.this,MainActivity2_Category.class);
           startActivity(i);
           finish();
      }

        sqLiteHelper = new DBHelper(this);

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkstatus();
                Tologinfun();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2Login.this, MainActivity2Register.class);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("Range")
    public void Tologinfun(){

        if(EditTextEmptyHolder) {
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    TempPass = cursor.getString(cursor.getColumnIndex(DBHelper.Table_Column_3_Password));
                    cursor.close();
                }
            }
            finalfuntologin();
        }
        else {
            Toast.makeText(MainActivity2Login.this,"Please Enter UserName And Password.", Toast.LENGTH_LONG).show();

        }

    }
    public void checkstatus(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    public void finalfuntologin(){

        if(TempPass.equalsIgnoreCase(PasswordHolder)) {
            Intent intent = new Intent(MainActivity2Login.this, MainActivity2_Category.class);
            intent.putExtra(UserEmail, EmailHolder);
            startActivity(intent);
             sessionManager.setlogin(true);
               finish();
        }
        else {
            Toast.makeText(MainActivity2Login.this,"UserName or Password is not matched, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPass = "NOTFOUND" ;

    }
    public void reg(View view){

        startActivity(new Intent("MainActivity2Register.java"));
    }
}