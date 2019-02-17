package com.example.zhoukao1.retrofit;

import com.example.zhoukao1.api.UserApi;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils instance;
    private final Retrofit retrofit;
    //构建者模式
    private RetrofitUtils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient ok=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(UserApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(ok)
                .build();
    }
    //单例链式
    public static RetrofitUtils getInstance() {
        if (instance==null){
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    /**
     * post 请求
     * @param url
     * @param params
     * @param callback
     */
    public void doPost(String url, HashMap<String,String> params, final RetrofitCallback callback){
        RetrofitService service=retrofit.create(RetrofitService.class);
        service.postReg(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200) {
                        String result = response.body().string();
                        if (callback!=null){
                            callback.Success(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (callback!=null){
                    callback.Failure("网络异常");
                }
            }
        });
    }
    /**
     * get请求
     */
    public void doGet(String url, final RetrofitCallback callback){
        RetrofitService service=retrofit.create(RetrofitService.class);
        service.getReg(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200) {
                        String result = response.body().string();
                        if (callback!=null){
                            callback.Success(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (callback!=null){
                    callback.Failure("网络异常");
                }
            }
        });
    }

    public void doUpdateNickname(String userId, String sessionId, final RetrofitCallback callback){
        RetrofitService service=retrofit.create(RetrofitService.class);
        service.updateNickname(userId,sessionId,"").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200) {
                        String result = response.body().string();
                        if (callback!=null){
                            callback.Success(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (callback!=null){
                    callback.Failure("网路异常");
                }
            }
        });
    }
}
