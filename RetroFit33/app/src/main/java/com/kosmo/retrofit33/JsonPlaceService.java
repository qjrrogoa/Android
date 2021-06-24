package com.kosmo.retrofit33;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceService {
    @GET("posts/") //baseUrl을 제외한 EndPoint
    Call<List<JsonPlaceDTO>> getPosts();
}
