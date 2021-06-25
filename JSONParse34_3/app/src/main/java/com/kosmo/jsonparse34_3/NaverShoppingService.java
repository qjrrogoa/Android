package com.kosmo.jsonparse34_3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NaverShoppingService {
    @Headers({
            "X-Naver-Client-Id: bw3v2DjodEnbgwnFB9_p",
            "X-Naver-Client-Secret: 6oDYO78Jmy"
    })
    @GET("/v1/search/shop.json")
    Call<String> getProducts(@Query("query") String query);
}
