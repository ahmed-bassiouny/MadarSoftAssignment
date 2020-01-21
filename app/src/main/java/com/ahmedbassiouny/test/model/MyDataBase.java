package com.ahmedbassiouny.test.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ahmedbassiouny.test.model.interfaces.UserDao;
import com.ahmedbassiouny.test.model.model.User;

@Database(entities = {User.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UserDao userDao();

}
