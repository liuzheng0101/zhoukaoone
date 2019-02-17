package com.example.day3.presenter;

import com.example.day3.bean.UserInfo;
import com.example.day3.contract.UserContract;
import com.example.day3.model.UserModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class UserPresenter extends UserContract.UserPresenter {
    private UserContract.IUserView view;
    private UserModel model;
    public UserPresenter(UserContract.IUserView view) {
        this.view = view;
        this.model=new UserModel();
    }
    @Override
    public void getLists(HashMap<String, String> params) {
        model.getLists(params, new UserModel.ModelCallback() {
            @Override
            public void Success(String result) {
                if (view!=null){
                    UserInfo info=new Gson().fromJson(result,UserInfo.class);
                    view.success(info.data);
                }
            }

            @Override
            public void failure(String error) {
                if (view!=null){
                    view.failure(error);
                }
            }
        });
    }
}
