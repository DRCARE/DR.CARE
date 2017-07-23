package com.tvnsoftware.drcare.api.restservice;

import android.content.Context;

import com.tvnsoftware.drcare.api.CommonInterface;
import com.tvnsoftware.drcare.api.RetrofitManager;
import com.tvnsoftware.drcare.model.Login.LoginResponse;

/**
 * Created by Thieusike on 7/23/2017.
 */

public class LoginService extends BaseService<Integer, LoginResponse> {

    private Integer mLoginId;

    @Override
    public void request(Context context, CommonInterface.ModelResponse<LoginResponse> callBack) {
        RetrofitManager.getInstance().sendApiRequest(RetrofitManager.getInstance()
                .getRestApiEndpointInterface().Login(mLoginId), callBack);
    }

    @Override
    public void setRequest(Integer r) {
        this.mLoginId = r;
    }
}
