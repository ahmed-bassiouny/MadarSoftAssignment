package com.ahmedbassiouny.test.utils;

import android.app.Application;

import androidx.room.Room;

import com.ahmedbassiouny.test.model.MyDataBase;

public class MyApplication extends Application {

    public static MyDataBase myDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        myDataBase = Room.databaseBuilder(this, MyDataBase.class, "my_data_base").build();
    }


}
