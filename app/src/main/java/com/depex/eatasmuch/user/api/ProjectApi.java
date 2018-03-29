package com.depex.eatasmuch.user.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface ProjectApi {


    @POST("search_resta.php")
    Call<String> getMarchantList(@Body String body);

    @POST("category_item.php")
    Call<String> getFoodItems(@Body String body);

}
