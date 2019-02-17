package com.example.day3.contract;

import com.example.day3.bean.UserInfo;
import com.example.day3.model.UserModel;

import java.util.HashMap;
import java.util.List;

public interface UserContract {
    public abstract class UserPresenter{
        public abstract void getLists(HashMap<String,String> params);
    }

    public interface IUserModel{
        void getLists(HashMap<String,String> params, UserModel.ModelCallback callback);
    }

    public interface IUserView{
        void success(List<UserInfo.Databean> list);
        void failure(String error);
    }
}
