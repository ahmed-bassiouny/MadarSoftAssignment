package com.ahmedbassiouny.test.model.interfaces;

public interface BaseResponse<T> {

    void Success(T t);
    void Failure(String msg);
}
