package com.ahmedbassiouny.test.model.repositories;

import com.ahmedbassiouny.test.model.interfaces.BaseResponse;
import com.ahmedbassiouny.test.model.model.User;

public interface IUserRepo {

    void getUser(long id,BaseResponse<User> baseResponse);
    void insertUser(User user,BaseResponse<Long> baseResponse);
}
