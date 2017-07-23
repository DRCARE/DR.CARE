package com.tvnsoftware.drcare.model.Login;

import com.google.gson.annotations.SerializedName;
import com.tvnsoftware.drcare.model.users.User;

import java.util.List;

/**
 * Created by Thieusike on 7/23/2017.
 */

public class LoginResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("response")
    private List<User> users;

    public LoginResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
