package com.catchmind.catchmind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sonsch94 on 2017-07-19.
 */

public class MessageRoomFragment extends Fragment implements ChatRoomActivity.FragmentCommunicator{

    ChatMessageAdapter chatListAdapter;
    ArrayList<ChatMessageItem> ListData;
    SharedPreferences mPref;
    SharedPreferences.Editor editor;

    String userId;
    String friendId;
    int no;
    public MyDatabaseOpenHelper db;
    ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.message_room_fragment, container, false);

        userId = getArguments().getString("userId");
        friendId = getArguments().getString("friendId");
        no = getArguments().getInt("no");

        Log.d("한번확인",no+"");

        ListData = new ArrayList<ChatMessageItem>();

//        ChatMessageItem defaultItem = new ChatMessageItem(0,"무쓸모ID","무쓸모닉", "무쓸모프로필","2017년 8월 27일 일요일",0);
//        ListData.add(defaultItem);

        db = new MyDatabaseOpenHelper(getContext(),"catchMind",null,1);
        Cursor cursor = db.getMessageListJoinChatFriendList(userId,friendId,no);

//        SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");

        while(cursor.moveToNext()) {

//            Date recvTime = new Date(cursor.getLong(4));
//            String time = sdfNow.format(recvTime);


            ChatMessageItem addItem = new ChatMessageItem(cursor.getInt(5),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(3),cursor.getLong(4));
            ListData.add(addItem);

            Log.d("커서야ChatMessageItem",cursor.getString(0)+"#####"+cursor.getString(1)+"#####"+cursor.getString(2)+"#####"+cursor.getString(3)+"#####"+cursor.getString(4)+"#####"+cursor.getString(5)+"#####"+cursor.getString(6)+"#####"+cursor.getString(7)+"#####"+cursor.getString(8));


        }

        lv = (ListView) rootView.findViewById(R.id.messageList);

        chatListAdapter = new ChatMessageAdapter(getActivity(),ListData,userId,no,friendId);

        lv.setAdapter(chatListAdapter);

        lv.setSelection(ListData.size()-1);

        return rootView;

    }



    @Override
    public void passData(String friendId,String nickname, String profile,String content,long now,int type) {
//        Toast.makeText(getActivity(),passdata+" 프래그먼트",Toast.LENGTH_SHORT).show();

//        SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
//        Date recvTime = new Date(now);
//        String time = sdfNow.format(recvTime);

        if(type == 1) {
            ChatMessageItem addItem = new ChatMessageItem(1, friendId, nickname, profile, content, now);
            ListData.add(addItem);
            chatListAdapter.notifyDataSetChanged();
            lv.setSelection(ListData.size()-1);

        }else if(type ==2){
            ChatMessageItem addItem = new ChatMessageItem(2, friendId, nickname, profile, content, now);
            ListData.add(addItem);
            chatListAdapter.notifyDataSetChanged();
            lv.setSelection(ListData.size()-1);
        }else if(type == 3){
            ChatMessageItem addItem = new ChatMessageItem(3, friendId, nickname, profile, content, now);
            ListData.add(addItem);
            chatListAdapter.notifyDataSetChanged();

        }else if(type == 51){
            ChatMessageItem addItem = new ChatMessageItem(51, friendId, nickname, profile, content, now);
            ListData.add(addItem);
            chatListAdapter.notifyDataSetChanged();
            lv.setSelection(ListData.size()-1);

        }else if(type == 52){
            ChatMessageItem addItem = new ChatMessageItem(52, friendId, nickname, profile, content, now);
            ListData.add(addItem);
            chatListAdapter.notifyDataSetChanged();
            lv.setSelection(ListData.size()-1);

        }



    }




    @Override
    public void alertChange() {
        Log.d("MessageRoomF","alertChange");
        if(chatListAdapter != null) {
            chatListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void changeNo(int sNo) {
        this.no = sNo ;
        chatListAdapter.no = sNo;
    }




    @Override
    public void deleteMessage(int position) {
        chatListAdapter.deleteMessage(position);
        chatListAdapter.notifyDataSetChanged();
    }


    @Override
    public void bottomSelect() {

        lv.setSelection(ListData.size()-1);

    }


}
