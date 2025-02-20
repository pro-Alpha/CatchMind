package com.catchmind.catchmind;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sonsch94 on 2017-07-19.
 */

public class ChatRoomPagerAdapter extends FragmentPagerAdapter {

    public Fragment mf;
    public Fragment df;
    public SharedPreferences mPref;
    public Bundle bundle;

    public ChatRoomPagerAdapter(FragmentManager fm,Fragment mf, Fragment df, SharedPreferences SP, String friendId, int no) {
        super(fm);
        this.mf = mf;
        this.df = df;
        mPref = SP;
        this.bundle = new Bundle();
        this.bundle.putString("userId",mPref.getString("userId","아이디없음"));
        this.bundle.putString("friendId",friendId);
        this.bundle.putInt("no",no);
        mf.setArguments(bundle);
        df.setArguments(bundle);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mf;
            case 1:
                return df;
            default:
                return mf;

        }
    }
}