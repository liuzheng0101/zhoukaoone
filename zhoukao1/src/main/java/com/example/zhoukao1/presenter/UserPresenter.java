package com.example.zhoukao1.presenter;

import com.example.zhoukao1.bean.UserInfo;
import com.example.zhoukao1.contract.UserContract;
import com.example.zhoukao1.model.UserModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class UserPresenter extends UserContract.UserPresenter {
    private UserContract.IUserView view;
    private WeakReference<UserContract.IUserView> weak;
    private UserModel model;
    public UserPresenter(UserContract.IUserView view) {
        weak=new WeakReference<>(view);
        this.view = view;
        model=new UserModel();
    }
    @Override
    public void getShop(HashMap<String, String> params) {
        model.getShop(params, new UserModel.ModelCallback() {
            @Override
            public void success(String result) {
                if (view!=null){
                    UserInfo userInfo=new Gson().fromJson(result,UserInfo.class);
                    view.Success(userInfo);
                }
            }
            @Override
            public void failure(String error) {
                if (view!=null){
                    view.Failure(error);
                }
            }
        });
    }
    public void uBindView(){
        if (view!=null){
            weak.clear();
            weak=null;
            view=null;
        }
    }
}
