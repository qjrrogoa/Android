package com.kosmo.retrofit33_2;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SpringApiService {
    //Call<T> T는 서버로부터 받은 데이타를 저장할 데이타 타입
    @GET("springapp/Ajax/AjaxText.do")
    //Call<String> getMemberText(@Query("id") String id,@Query("pwd") String pwd);
    //파라미터가 여러개일때:@QueryMap 사용
    Call<String> getMemberText(@QueryMap HashMap<String,String> map);

    @GET("springapp/Ajax/AjaxJson.do")
    Call<HashMap<String,String>> getMemberJson(@Query("id") String id, @Query("pwd") String pwd);

    @GET("springapp/Ajax/AjaxJsonArray.do")
    Call<List<BBSDto>> getMembers();

}
