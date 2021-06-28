package com.kosmo.jsonparse34_3;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface KakaoVisionService {

    @Headers("Authorization: KakaoAK d9d322e1504db133ed5d8f55ead96eb9")
    @FormUrlEncoded
    @POST("/v2/vision/product/detect")
    Call<KakaoVisionItem> getVisionProducts(@Field("image_url") String image_url);

}////PhotoService