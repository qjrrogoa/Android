package com.kosmo.jsonparse34_3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NaverShoppingService {

    @Headers({"X-Naver-Client-Id: oND3hWoBb9TRRqYJ1H3Y","X-Naver-Client-Secret: MqMeCzzA85"})
    @GET("/v1/search/shop.json")
    Call<NaverShoppingItem> getProducts(@Query("query") String query);

}////PhotoService