package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static  String tag =SessionManager.class.getSimpleName();

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE=0;
    private  static final String PREF_NAME="AndroidHideLogin";
    private static  final  String KEY_IS_LOGGEN="isLoggedIn";

Context _context;
    public  SessionManager(Context context){
        this._context=context;
        pref=_context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();


    }

    public  void setlogin(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGEN,isLoggedIn);
        editor.commit();
        Log.d(tag,"Login Session");
    }


    public boolean isLoggedIn(){
        return  pref.getBoolean(KEY_IS_LOGGEN,false);
    }
}
