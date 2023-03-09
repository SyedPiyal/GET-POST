package com.datasoft.postandget.response;

import com.datasoft.postandget.models.Response;
import com.datasoft.postandget.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
    @GET("api/users?page=2")
    Call<Response> getAllData();

    @FormUrlEncoded
    @POST("/api/users")
    Call<User> getUserInformation(@Field("name")String name,@Field("job")String job);


}
