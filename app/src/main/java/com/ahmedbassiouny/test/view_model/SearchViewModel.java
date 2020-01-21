package com.ahmedbassiouny.test.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedbassiouny.test.model.interfaces.BaseResponse;
import com.ahmedbassiouny.test.model.model.User;
import com.ahmedbassiouny.test.model.repositories.IUserRepo;
import com.ahmedbassiouny.test.model.repositories.UserRepo;

public class SearchViewModel extends ViewModel {

    public MutableLiveData<String> error = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<User> success = new MutableLiveData<>();

    private IUserRepo iUserRepo;

    public SearchViewModel() {
        iUserRepo = new UserRepo();
    }


    public void getUser(String userId) {
        if (userId.trim().isEmpty()) {
            error.setValue("User Id Required");
        } else {
            loading.setValue(true);

            iUserRepo.getUser(Long.parseLong(userId), new BaseResponse<User>() {
                @Override
                public void Success(User user) {
                    loading.postValue(false);
                    success.postValue(user);
                }

                @Override
                public void Failure(String msg) {
                    loading.postValue(false);
                    error.postValue(msg);
                }
            });
        }
    }
}
