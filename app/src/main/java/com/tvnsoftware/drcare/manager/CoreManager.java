package com.tvnsoftware.drcare.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.tvnsoftware.drcare.model.users.User;

/**
 * Created by Thieusike on 7/24/2017.
 */

public class CoreManager {
    private static CoreManager sDataManager;
    private boolean isLogedIn;
    private User userData;
    private String token;
    public void setUserData(Context context, User userData) {
        this.userData = userData;
        Gson gson = new Gson();
//        PreferenceUtils.saveStringPref(context, AppConstant.PREF_USER_DATA, gson.toJson(userData));
    }
}
