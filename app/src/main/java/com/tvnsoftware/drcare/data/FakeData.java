package com.tvnsoftware.drcare.data;

import com.tvnsoftware.drcare.model.History;
import com.tvnsoftware.drcare.model.MedicineRecord;
import com.tvnsoftware.drcare.model.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thieusike on 7/28/2017.
 */

public class FakeData {
    public static List<MedicineRecord> getMedicinRecords(){
        List<MedicineRecord> res = new ArrayList<>();
        return res;
    }
    public static List<History> getHistorys(){
        List<History> res = new ArrayList<>();
        for(int i= 0; i< 10; i++){
            History history = new History();
            history.setDate("2017/01/20");
            history.setDecription("This is a decsription "+ i);
            history.setDoctorName("Doctor "+ i);
            history.setDiagnose("Diagnose "+ i);
            res.add(history);
        }
        return res;
    }
    public static List<User> getUsers(){
        List<User> res = new ArrayList<>();
        for(int i= 0; i< 10; i++){
            User user = new User();
            user.setUserCode("HTH000"+i);
            user.setRoleCode(1);
            user.setUserName("Doctor "+i);
            res.add(user);
        }
        for(int i= 10; i< 20; i++){
            User user = new User();
            user.setUserCode("HTH000"+i);
            user.setRoleCode(2);
            user.setUserName("Patient "+i);
            res.add(user);
        }
        return res;
    }
}
