package com.ahmedbassiouny.test.view_model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedbassiouny.test.model.interfaces.BaseResponse;
import com.ahmedbassiouny.test.model.model.User;
import com.ahmedbassiouny.test.model.repositories.IUserRepo;
import com.ahmedbassiouny.test.model.repositories.UserRepo;

public class RegisterViewModel extends ViewModel {

    public MutableLiveData<String> error = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Long> success = new MutableLiveData<>();

    private IUserRepo iUserRepo;

    public RegisterViewModel() {
        iUserRepo = new UserRepo();
    }



    public void setUser(String name,String jobTitle,String age,int gender){
        if (name.trim().isEmpty()){
            error.setValue("Name Required");
        }else if (jobTitle.trim().isEmpty()){
            error.setValue("Job title Required");
        }else if(age.trim().isEmpty()){
            error.setValue("Age Required");
        }else {
            loading.setValue(true);
            User user = new User(name,Integer.parseInt(age),jobTitle,gender);
            iUserRepo.insertUser(user, new BaseResponse<Long>() {
                @Override
                public void Success(Long aLong) {
                    loading.postValue(false);
                    success.postValue(aLong);
                }

                @Override
                public void Failure(String msg) {
                    error.setValue(msg);
                    loading.postValue(false);
                }
            });
        }
    }
}
