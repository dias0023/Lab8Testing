package com.example.nisin.lab8_testing;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MessageFragment extends Fragment {

    private TextView tMessage;
    private TextView messageId;
    private Button deleteB;
    private String myMsg;
    private int myId;
    private long dbID;
    private ChatWindow chatWindow;

    public MessageFragment() {
        // Required empty public constructor
    }

    public void setChatWindow(ChatWindow chatWindow){
        this.chatWindow = chatWindow;
    }


    public static MessageFragment newInstance(){
        MessageFragment newFragment = new MessageFragment();
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_message_fragment);

        if (getArguments() != null) {
            myMsg = getArguments().getString("chatMsg");
            myId = getArguments().getInt("Id");
            dbID = getArguments().getLong("dbId");
            Log.i("MessageFragment", myMsg);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_message_fragment, container, false);
        tMessage = (TextView) view.findViewById(R.id.tMessage);
        tMessage.setText(myMsg);

        messageId = (TextView) view.findViewById(R.id.messageId);
        messageId.setText(Integer.toString(myId));

        deleteB = (Button) view.findViewById(R.id.deleteB);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatWindow != null){
                    //tablet
                    chatWindow.deleteMessage(myId);
                    // once deleted the fragment is disappeared
                    getActivity().getFragmentManager().popBackStack();

                }
                else{
                    Log.i("tag","hello");
                    Intent intent = new Intent();
                    intent.putExtra("deleteMsgId", myId);
                    //intent.putExtra("deleteDBMsgId", dbID);
                    getActivity().setResult(10, intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}
