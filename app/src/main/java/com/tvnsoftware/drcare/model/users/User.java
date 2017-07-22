package com.tvnsoftware.drcare.model.users;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thieusike on 7/23/2017.
 */

public class User {
    @SerializedName("USER_CODE")
    private int UserCode;
    @SerializedName("ROLE_CODE")
    private int RoleCode;
    @SerializedName("USER_NAME")
    private String UserName;
    @SerializedName("USER_ID_CARD_NO")
    private String UserIdCardNo;
    @SerializedName("USER_PHONE")
    private String UserPhone;
    @SerializedName("USER_ADDRESS")
    private String UserAddress;
    @SerializedName("USER_DOB")
    private String UserDoB;
    @SerializedName("USER_IMAGE")
    private String UserImage;
    @SerializedName("USER_NOTE")
    private String UserNote;
    @SerializedName("USER_HEALTH_INSURANCE")
    private String UserHealthInsurance;
    @SerializedName("USER_DAY_CREATED")
    private String UserDayCreated;

    public User() {
    }

    public int getUserCode() {
        return UserCode;
    }

    public void setUserCode(int userCode) {
        UserCode = userCode;
    }

    public int getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(int roleCode) {
        RoleCode = roleCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserIdCardNo() {
        return UserIdCardNo;
    }

    public void setUserIdCardNo(String userIdCardNo) {
        UserIdCardNo = userIdCardNo;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getUserDoB() {
        return UserDoB;
    }

    public void setUserDoB(String userDoB) {
        UserDoB = userDoB;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getUserNote() {
        return UserNote;
    }

    public void setUserNote(String userNote) {
        UserNote = userNote;
    }

    public String getUserHealthInsurance() {
        return UserHealthInsurance;
    }

    public void setUserHealthInsurance(String userHealthInsurance) {
        UserHealthInsurance = userHealthInsurance;
    }

    public String getUserDayCreated() {
        return UserDayCreated;
    }

    public void setUserDayCreated(String userDayCreated) {
        UserDayCreated = userDayCreated;
    }
}
