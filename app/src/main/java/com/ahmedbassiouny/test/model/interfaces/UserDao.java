package com.ahmedbassiouny.test.model.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ahmedbassiouny.test.model.model.User;

@Dao
public interface UserDao {

    @Insert()
    long insert(User users);

    @Query("SELECT * FROM user where id = :id")
    User getUser(long id);
}
