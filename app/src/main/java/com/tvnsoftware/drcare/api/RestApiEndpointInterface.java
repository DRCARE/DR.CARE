package com.tvnsoftware.drcare.api;

import com.tvnsoftware.drcare.model.Login.LoginResponse;
import com.tvnsoftware.drcare.model.users.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Thieusike on 7/10/2017.
 */

public interface RestApiEndpointInterface {
    @GET("users")
    Call<UsersResponse> getUsers();
    @POST("users/signin")
    Call<LoginResponse> Login( @Field("USER_CODE")Integer loginId);
}
