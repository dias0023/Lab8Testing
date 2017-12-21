package com.example.nisin.lab8_testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {
    protected static final String ACTIVITY_NAME = "StartActivity";
    protected final int requestCode = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button b2 = (Button) findViewById(R.id.button);
        b2.setOnClickListener((v) -> {
        Intent i = new Intent(StartActivity.this, ListItemsActivity.class);
        startActivityForResult(i, 10);
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked start chat");
            }
        });

        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, WeatherForecast.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked Weather forecast");
            }
        });
        /*Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener((v) -> {
            Intent i = new Intent(com.example.nisin.lab8_testing.StartActivity.this, WeatherForecast.class);
            startActivityForResult(i, 10);
        });*/
        Button testToolBar = (Button)findViewById(R.id.testToolBar);
        testToolBar.setOnClickListener((v) -> {
            Intent intent = new Intent(StartActivity.this, TestToolBar.class);
            startActivity(intent);
        });
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data) {

        if (requestCode == 10) {
            Log.i(ACTIVITY_NAME, "Returned to com.example.nisin.lab8_testing.StartActivity.onActivityResult");
            if (responseCode == Activity.RESULT_OK) {
                String messagePassed = data.getStringExtra("Response");
                String text = messagePassed;

                Toast toast = Toast.makeText(StartActivity.this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    protected void onResume() {
        Log.i(ACTIVITY_NAME, "In onResume()");
        super.onResume();
    }
    protected void onStart(){
        Log.i(ACTIVITY_NAME, "In onStart()");
        super.onStart();
    }
    protected void onPause(){
        Log.i(ACTIVITY_NAME, "In onPause()");
        super.onPause();
    }
    protected void onStop(){
        Log.i(ACTIVITY_NAME, "In onStop()");
        super.onStop();
    }
    protected void onDestroy(){
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        super.onDestroy();
    }

}
