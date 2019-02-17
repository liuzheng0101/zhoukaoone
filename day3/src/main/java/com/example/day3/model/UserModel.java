package com.example.day3.model;

import com.example.day3.api.UserApi;
import com.example.day3.contract.OKhttpCallback;
import com.example.day3.contract.UserContract;
import com.example.day3.net.OkhttpUtils;

import java.util.HashMap;

public class UserModel implements UserContract.IUserModel {
    @Override
    public void getLists(HashMap<String, String> params, final ModelCallback callback) {
        OkhttpUtils.getInstance().doPost(UserApi.BASE_API, params, new OKhttpCallback() {
            @Override
            public void success(String result) {
                if (callback!=null){
                    callback.Success(result);
                }
            }
            @Override
            public void failure(String error) {
                if (callback!=null){
                    callback.failure(error);
                }
            }
        });
    }

    public interface ModelCallback{
        void Success(String result);
        void failure(String error);
    }
}
