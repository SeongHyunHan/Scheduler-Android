package com.seong.scheduler.network;

import com.seong.scheduler.models.Token;
import com.seong.scheduler.models.User;
import com.seong.scheduler.models.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hans on 2018-03-15.
 */

public interface GetUserDataService {
    @GET("user/android/{token}")
    Call<UserList> getUser(@Path("token")String token);

    @POST("user/")
    @FormUrlEncoded
    Call<User> saveUser(@Field("studentId") int studentId,
                        @Field("name") String name,
                        @Field("token") Token token);
}
