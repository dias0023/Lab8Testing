package com.example.nisin.lab8_testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    protected static final String LoginActivity = "StartActivity";

    static  final int REQUEST_IMAGE_CAPTURE =1;
    protected Button loginButton;
    public static String pref_Email="email@gmail.com";

    public static SharedPreferences sharedPref ;
    static SharedPreferences.Editor editor;
    EditText etext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(LoginActivity, "In onCreate");
        etext= (EditText)findViewById(R.id.userinput);

        sharedPref= getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPref.edit();
        editor.putString("name", etext.getText().toString());
        editor.commit();





        loginButton = (Button)findViewById(R.id.button);
        sharedPref.getString("name",pref_Email);

        editor.putString("LoginName", etext.getText().toString());
        loginButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String inputEmail=etext.getEditableText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor= sharedPref.edit();
                etext= (EditText)findViewById(R.id.userinput);
                editor.putString("name", inputEmail);
                editor.commit();
                Intent itent=new Intent(LoginActivity.this, StartActivity.class);
                startActivity(itent);
            }});

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LoginActivity, "In onStart()");
        SharedPreferences sharedPref= getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name =sharedPref.getString("name",pref_Email);
        etext.setText(name);
        ;
    }
    protected void onResume(){
        super.onResume();
        Log.i(LoginActivity, "In onResume");
    }



    protected void onPause() {
        super.onPause();
        Log.i(LoginActivity, "In onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LoginActivity, "In onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LoginActivity, "In onDestroy()");
    }
}