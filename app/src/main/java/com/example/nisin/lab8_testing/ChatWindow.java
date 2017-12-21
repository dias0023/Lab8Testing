package com.example.nisin.lab8_testing;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    ListView listV;
    EditText editT;
    Button SendB;

    protected static final String ACTIVITY_NAME = "ChatWindow";
    ChatWindow cW;

    ArrayList<String> aList = new ArrayList <String>();
    ChatDatabaseHelper mydbase;
    protected SQLiteDatabase dbase;

     Cursor csr;
    int deleteId;
    long deleteBDid;
    ChatAdapter messageAdapter;
    ContentValues cv = new ContentValues();

    FrameLayout fragmentFrame;
    boolean frame;

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        Resources resources = getResources();
        Context context = getApplicationContext();

       /* listV = (ListView) findViewById(R.id.commentsList);
        editT = (EditText) findViewById(R.id.comment);
        SendB = (Button) findViewById(R.id.SendB);*/

        //fragmentFrame = (FrameLayout) findViewById(R.id.fragment_frame);
        fragmentFrame = (FrameLayout)findViewById(R.id.fragment_frame);

        //If used on a phone
        if (fragmentFrame == null) {
            Log.i(ACTIVITY_NAME, "frame is not loaded");
            frame = false;
        }
        //If used on a tablet
        else {
            Log.i(ACTIVITY_NAME, "frame is loaded");
            frame = true;
        }

        listV = (ListView) findViewById(R.id.commentsList);
        editT = (EditText) findViewById(R.id.comment);
        SendB = (Button) findViewById(R.id.SendB);

        messageAdapter = new ChatAdapter(this);
        listV.setAdapter(messageAdapter);

        mydbase = new ChatDatabaseHelper(context);
        cv = new ContentValues();
        mydbase.open();
        csr = mydbase.getChatMessages();

        //if (result != null && result.moveToFirst());
        if (csr.moveToFirst()) {
            do {
                String msg = mydbase.getMessageFromCursor(csr);
                Log.i(ACTIVITY_NAME, "SQL Message: " + msg);
                Log.i(ACTIVITY_NAME, "Cursor's column count=" + csr.getColumnCount());
                aList.add(msg);
                csr.moveToNext();
            } while (!csr.isAfterLast());
            messageAdapter.notifyDataSetChanged();

        }

        for (int i = 0; i < csr.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, csr.getColumnName(i));
        }

        SendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("EditText", editT.getText().toString());
                String chatString = editT.getText().toString();
                aList.add(chatString);
                messageAdapter.notifyDataSetChanged();
                editT.setText("");
                cv.put("message", chatString);
               // cv.put("message", chatString);
                mydbase.insert(cv);
                csr = mydbase.getChatMessages();
            }
        });

        cW = this;

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = messageAdapter.getItem(position);
                String str = (String) o; //Default String Adapter

                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();


                if (frame) {
                    // if the app is running on a tablet
                    MessageFragment fragment = new MessageFragment();
                    fragment.setChatWindow(cW);
                    Bundle bundle = new Bundle();
                    bundle.putString("chatMsg", str);
                    bundle.putInt("Id", position);
                    //bundle.putLong("dbId",id);
                    fragment.setArguments(bundle);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                /* sending the activity to the newly created MessageDetails class */
                else {
                    Intent intent = new Intent(getApplicationContext(), MessageDetails.class);
                    intent.putExtra("chatMsg", str);
                    intent.putExtra("Id", position);
                    //intent.putExtra("dbId",id);
                    startActivityForResult(intent, 10);
                }
            }
        });

    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 10  && responseCode == 10) {
            // received data from fragment to delete the message
            Bundle bundle = data.getExtras();
            deleteId = bundle.getInt("deleteMsgId");
            deleteBDid = bundle.getLong("deleteDBMsgId");
            deleteBDid = messageAdapter.getItemId(deleteId);
            mydbase.remove(deleteBDid);
            aList.remove(deleteId);
            csr = mydbase.getChatMessages();
            messageAdapter.notifyDataSetChanged();
            //deleteMessage(deleteId);
            //Log.i(String.valueOf(ChatWindow.this), String.valueOf(chatList.size()));
        }
    }


    public void deleteMessage(int id){
        long deleteDBIdTab = messageAdapter.getItemId(id);
        mydbase.remove(deleteDBIdTab);
        aList.remove(id);
        messageAdapter.notifyDataSetChanged();

    }
    protected void onDestroy(){
        super.onDestroy();
        //dbase.close();
        Log.i("ChatWindow", "In onDestroy()");

    }

    private class ChatAdapter extends ArrayAdapter<String> {

            public ChatAdapter(Context ctx) { super(ctx, 0);}

        public int getCount() {return aList.size();}

        public String getItem(int position) {return aList.get(position);}

        public long getItemId(int position)
        {
            csr.moveToPosition(position);
            int  id = mydbase.getIdFromCursor(csr);
            return id;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null;

            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

                     TextView message = (TextView)result.findViewById(R.id.message_text);
                     // message = (TextView)result.findViewById(R.id.messageText);
                     message.setText(getItem(position));
                     return result;

        }
    }
}