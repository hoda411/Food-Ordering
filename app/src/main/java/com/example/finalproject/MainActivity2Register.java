package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2Register extends AppCompatActivity {

    EditText Email, Password, Name ;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    DBHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_register);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Register = (Button)findViewById(R.id.buttonRegister);
        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);
        Name = (EditText)findViewById(R.id.editName);

        sqLiteHelper = new DBHelper(this);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createdatabase();
                createtable();
                notempty();
                ifmailexists();
                cleartextfields();
            }
        });

    }
    public void createdatabase(){
        sqLiteDatabaseObj = openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void createtable() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + DBHelper.TABLE_NAME + "(" + DBHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + DBHelper.Table_Column_1_Name + " VARCHAR, " + DBHelper.Table_Column_2_Email + " VARCHAR, " + DBHelper.Table_Column_3_Password + " VARCHAR);");
    }

    public void InsertDataIntoSQLiteDatabase(){
        if(EditTextEmptyHolder == true) {
            SQLiteDataBaseQueryHolder = "INSERT INTO "+DBHelper.TABLE_NAME+" (name,email,password) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(MainActivity2Register.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(MainActivity2Register.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }
    public void cleartextfields(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();

    }

    public void notempty(){

        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    public void ifmailexists(){
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                F_Result = "Email Found";
                cursor.close();
            }
        }
        CheckFinalResult();

    }
    public void CheckFinalResult(){

        if(F_Result.equalsIgnoreCase("UserName is Founded")) {
            Toast.makeText(MainActivity2Register.this,"This Email IS Already Existed , Please Try Another Mail",Toast.LENGTH_LONG).show();

        }
        else {
            InsertDataIntoSQLiteDatabase();
        }

        F_Result = "Not_Found" ;
    }

    public void logpage(View view){
        
        startActivity(new Intent("MainActivity2Login.java"));
    }
}