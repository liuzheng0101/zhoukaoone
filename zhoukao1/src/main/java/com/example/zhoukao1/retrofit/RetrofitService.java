package com.example.zhoukao1.retrofit;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface RetrofitService {
    @POST
    @FormUrlEncoded
    Call<ResponseBody> postReg(@Url String url, @FieldMap HashMap<String,String> params);

    @GET
    Call<ResponseBody> getReg(@Url String URL);

    @PUT
    Call<ResponseBody> updateNickname(@Header("userId") String id,@Header("sessionId") String sid,@Field("nickName") String name);
}
