package com.kosmo.retrofit33_2;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpringApiService {

    @GET("springapp/Ajax/AjaxText.do")
    Call<String> getMemberText(@Query("id") String id,@Query("pwd") String pwd);

    @GET("springapp/Ajax/AjaxJson.do")
    Call<HashMap<String,String>> getMemberJson(@Query("id") String id, @Query("pwd") String pwd);
}
