package com.example.nisin.lab8_testing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolBar extends AppCompatActivity {

    private String newMessage = "Item 1 is Selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tool_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_one:
                Log.d("Toolbar", "Option 1 is selected");
                Snackbar.make(findViewById(R.id.action_one),
                        newMessage, Snackbar.LENGTH_LONG)
                        .setAction("Option 1", null).show();
                break;
            case R.id.action_two:
                Log.d("Toolbar", "Option 2 is selected");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Do you want to go back?");
                //Add the button
                builder1.setPositiveButton("OK", (dialog, id) ->{
                    finish();
                Intent start = new Intent(TestToolBar.this, StartActivity.class);
                startActivity(start);
            });
            builder1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                }
            });
            //Create the AlertDialog
            AlertDialog dialog1 = builder1.create();
            dialog1.show();
            break;
        //Start an activity...
            case R.id.action_three:
                Log.d("Toolbar", "Choice 3 is selected");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Set new message");
                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                builder2.setView(dialogView);
                //Add buttons
                builder2.setPositiveButton("OK", (dialog, id) ->{
                    EditText text = (EditText) dialogView.findViewById(R.id.newMessage);
                    newMessage = text.getText().toString();
                });
                builder2.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                //Create the AlertDialog
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                break;

            case R.id.about:
                Context context = getApplicationContext();
                CharSequence text = "Version 1.0 by " + " Nisini Dias";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
        }
        return true;

    }
}
