package com.tvnsoftware.drcare.model.users;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thieusike on 7/23/2017.
 */

public class User {

    @SerializedName("USER_CODE")
    private int UserID;
    @SerializedName("ROLE_CODE")
    private int RoleID;
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

    /**
     * by Samn 28-Jul-2017
     */


    //// TODO: 28-Jul-17 : nếu có lấy từ API về thì : sửa bỏ UserCode
    private String userCode;
    private static List<User> userList;

    public String getUserCode() {
        return userCode;
    }

    public User(String userCode , String userName, int roleID) {
        RoleID = roleID;
        UserName = userName;
        this.userCode = userCode;
    }

    public User(int userID, String userCode , String userName, int roleID) {
        UserID = userID;
        RoleID = roleID;
        UserName = userName;
        this.userCode = userCode;
    }

    private static void initializeUserList(){
        //cái này lấy từ bên Medical Record (list patient of Doctor) -- UserID = patientCode
        //Patient
        userList = new ArrayList<>();
        userList.add(new User("ABC1234", "John Cena", 1));
        userList.add(new User("SAM6969", "Samn Nguyen", 1));
        userList.add(new User("WSH0933", "Will Smith", 1));
        userList.add(new User("SGZ1065", "Selena Gomez", 1));
        userList.add(new User("HCC8345", "Hoàn Châu Cách Cách", 1));
        userList.add(new User("NTT3087", "Nguyễn Trần Tuyết Nghi", 1));
        userList.add(new User("THB5763", "Trịnh Hoàng Huy Bảo", 1));
        userList.add(new User("TTN9240", "Trần Phan Tố Nguyệt", 1));
        userList.add(new User("PTN8372", "Phạm Kiều Thảo Nguyên", 1));

        //doctor
        userList.add(new User(5, "TBN2374", "Trần Lâm Bảo Nhân", 2));
        userList.add(new User(1, "NHT3249", "Nguyễn Vũ Hoàng Trinh", 2));
        userList.add(new User(2, "LQH4239", "Lưu Quang Huy", 2));
        userList.add(new User(3, "PTT8930", "Phạm Trọng Tội", 2));
        userList.add(new User(4, "NPA6969", "Nightury Michaelis", 2));
    }

    public static List<User> getUserList(){
        initializeUserList();
        return userList;
    }

    public static boolean checkIsUser(String user_code, List<User> userList){
        int isUser = 0;
        for (User user : userList){
            if(user.getUserCode().toLowerCase().equals(user_code)){
                isUser = 0;
                break;
            }
        }
        return (isUser == 1)? true : false;
    }

    public static int checkRole(String user_code, List<User> userList){
        int Role = 1;
        for (User user : userList){
            if(user.getUserCode().toLowerCase().equals(user_code)){
                Role = user.getRoleID();
                break;
            }
        }
        return Role;
    }

    public static String getUserNameByID(int userID) {
        String result = "Default";
        for(User user : userList){
            if (userID == user.getUserID()){
                result = user.getUserName();
            }
        }
        return result;
    }
    /**
     * end Samn ___
     */

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
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
