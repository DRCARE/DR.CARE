package com.tvnsoftware.drcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tvnsoftware.drcare.Application;
import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.api.CommonInterface;
import com.tvnsoftware.drcare.api.restservice.UserService;
import com.tvnsoftware.drcare.model.users.User;
import com.tvnsoftware.drcare.model.users.UsersResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    public static final String EXTRA_ROLE = "EXTRA_ROLE";

    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_qr_code)
    Button mBtnQrCode;
    @BindView(R.id.edt_login_id)
    EditText edtLoginId;

    @BindView(R.id.btn_alarm)
    Button mBtnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //ToDo something
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        mBtnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ZxingOrient(LoginActivity.this).initiateScan();
            }

        });
//        if(CoreManager.getInstance().getUserData() != null){
//            transferToPage(CoreManager.getInstance().getUserData().getRoleCode());
//        }
        mBtnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showAlarm();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ZxingOrientResult scanResult =
                ZxingOrient.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            if (scanResult.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(this, "Scanned: " + scanResult.getContents(), Toast.LENGTH_LONG).show();
                //loginQRCode(scanResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(){
        String inputCode = edtLoginId.getText().toString();

        List<User> userList = Application.users;
        if(inputCode.isEmpty() || inputCode.length() == 0 || inputCode.equals("") || inputCode == null)
            Toast.makeText(this, "Please enter UserID to login", Toast.LENGTH_SHORT).show();
        else {
            boolean isUser = false;
            int roleID = 1;
            for (User user : userList){
                if(user.getUserCode().equalsIgnoreCase(inputCode)){
                    //check role
                    roleID = user.getRoleID();
                    isUser = true;
                    break;
                }
            }
            //if(User.checkIsUser(inputCode.toLowerCase(), userList)){
            if(isUser){
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                int getRoleID ; //= User.checkRole(inputCode.toLowerCase(), userList);
                //getRoleID = 2;
                Log.d(TAG, "TEST: role ID = " + roleID);
                i.putExtra(EXTRA_ROLE, roleID);

                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(this, "Wrong User ID!", Toast.LENGTH_SHORT).show();
                edtLoginId.setText("");
            }
        }
    }

    private void callAPI() {
        UserService userService = new UserService();
        userService.request(this, new CommonInterface.ModelResponse<UsersResponse>() {
            @Override
            public void onSuccess(UsersResponse result) {
                Log.d(TAG, result.toString());
            }

            @Override
            public void onFail() {

            }
        });
    }

//    private void loginQRCode(String userCode) {
//        User data = null;
//        for (User u : FakeData.getUsers()) {
//            if (u.getUserCode().equals(userCode.toUpperCase())) {
//                data = u;
//            }
//        }
//        if (data != null) {
//            transferToPage(data.getRoleCode());
//        } else {
//            Toast.makeText(this, "Invalid UserCode", Toast.LENGTH_LONG).show();
//        }
//    }


    private void transferToPage(int userRole) {
        //1: Doctor page
        if (1 == userRole) {
            transferToDoctor();
        } else {
            transferToPatientPage();
        }
    }

    private void transferToPatientPage() {
        //Patient activity hello
        Intent i = new Intent(LoginActivity.this, HistoryActivity.class);
        startActivity(i);
    }

    private void transferToDoctor() {
        //Doctor activity
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

}
