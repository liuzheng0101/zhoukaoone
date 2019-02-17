package com.example.zhoukao1.contract;

import com.example.zhoukao1.bean.UserInfo;
import com.example.zhoukao1.model.UserModel;

import java.util.HashMap;

public interface UserContract {
    public abstract class UserPresenter{
        public abstract void getShop(HashMap<String,String> params);
    }
    public interface IUserModel{
        void getShop(HashMap<String,String> parmas, UserModel.ModelCallback callback);
    }
    public interface IUserView{
        void Success(UserInfo userInfo);
        void Failure(String error);
    }
}
