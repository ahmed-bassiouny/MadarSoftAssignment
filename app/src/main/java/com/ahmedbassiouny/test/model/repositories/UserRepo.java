package com.ahmedbassiouny.test.model.repositories;

import com.ahmedbassiouny.test.model.interfaces.BaseResponse;
import com.ahmedbassiouny.test.model.model.User;
import com.ahmedbassiouny.test.utils.MyApplication;

public class UserRepo implements IUserRepo {


    @Override
    public void getUser(final long id, final BaseResponse<User> baseResponse) {
     new Thread(new Runnable() {
         @Override
         public void run() {
             User user = MyApplication.myDataBase.userDao().getUser(id);
             if (user != null){
                 baseResponse.Success(user);
             }else{
                 baseResponse.Failure("User not found");
             }
         }
     }).start();
    }

    @Override
    public void insertUser(final User user, final BaseResponse<Long> baseResponse) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                long id = MyApplication.myDataBase.userDao().insert(user);
                if (id > 0){
                    baseResponse.Success(id);
                }else {
                    baseResponse.Failure("Error");
                }
            }
        }).start();

    }
}
