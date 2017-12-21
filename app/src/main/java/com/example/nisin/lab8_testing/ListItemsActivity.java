package com.example.nisin.lab8_testing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

public class ListItemsActivity extends Activity {
    protected static final String Activity_NAME = "StartActivity";
    private ImageButton iButton;
    android.widget.Switch Switch;
    boolean isChecked = false;
    CheckBox cbox;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    ////////////************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        iButton = (ImageButton) findViewById(R.id.Image);
        iButton.setOnClickListener((v) -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        Switch = (android.widget.Switch) findViewById(R.id.Switch1);
        Switch.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (!isChecked) {
                CharSequence text = "Switch is off";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            } else {
                CharSequence text = "Switch is on";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        });

        cbox = (CheckBox) findViewById(R.id.Checkbox);
        cbox.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton Checkbox, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    builder.setMessage(R.string.dialog_message)
                            .setTitle(R.string.dialog_title)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("Response", "My information to share");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton("Cancel", (dialog, id) -> {
                            })
                            .show();
                }
            }
        }));

    }

    protected void onStart(){
        Log.i(Activity_NAME, "In onStart()");
        super.onStart();
    }

    protected void onResume() {
        Log.i(Activity_NAME, "In onResume()");
        super.onResume();
    }

    protected void onPause(){
        Log.i(Activity_NAME, "In onPause()");
        super.onPause();
    }
    protected void onStop(){
        Log.i(Activity_NAME, "In onStop()");
        super.onStop();
    }
    protected void onDestroy(){
        Log.i(Activity_NAME, "In onDestroy()");
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iButton.setImageBitmap(imageBitmap);
        }
    }
}
