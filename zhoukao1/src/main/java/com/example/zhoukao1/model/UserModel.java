package com.example.zhoukao1.model;

import com.example.zhoukao1.api.UserApi;
import com.example.zhoukao1.contract.UserContract;
import com.example.zhoukao1.retrofit.RetrofitCallback;
import com.example.zhoukao1.retrofit.RetrofitUtils;

import java.util.HashMap;

public class UserModel implements UserContract.IUserModel {
    @Override
    public void getShop(HashMap<String, String> params, final ModelCallback callback) {
        String keyword=params.get("keyword");
        String page=params.get("page");
        String count=params.get("count");
        RetrofitUtils.getInstance().doGet(UserApi.selectUrl + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void Success(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }
            @Override
            public void Failure(String error) {
                if (callback!=null){
                    callback.failure(error);
                }
            }
        });
    }
    public interface ModelCallback{
        void success(String result);
        void failure(String error);
    }
}
