package com.example.nisin.lab8_testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
        getMenuInflater().inflate(R.menu.menu_test_tool_bar, menu);
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
                builder1.setPositiveButton("OK", (dialog, id) {
                    finish();
                Intent start = new Intent(TestToolBar.this, StartActivity.class);
                startActivity(start);
            });
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
