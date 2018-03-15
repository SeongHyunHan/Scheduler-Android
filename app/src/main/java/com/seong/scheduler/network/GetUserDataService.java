package com.seong.scheduler.network;

import com.seong.scheduler.models.UserList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hans on 2018-03-15.
 */

public interface GetUserDataService {
    @GET("/user")
    Call<UserList> getUser();

}
