package com.tvnsoftware.drcare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.Utils.CommonConvert;
import com.tvnsoftware.drcare.api.CommonInterface;
import com.tvnsoftware.drcare.api.restservice.LoginService;
import com.tvnsoftware.drcare.api.restservice.UserService;
import com.tvnsoftware.drcare.model.Login.LoginResponse;
import com.tvnsoftware.drcare.model.users.UsersResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    @BindView(R.id.edt_login_id)
    EditText mEdtLoginId;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_qr_code)
    Button mBtnQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //ToDo something
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(i);
                callAPI();
            }
        });
        mBtnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ZxingOrient(LoginActivity.this).initiateScan();
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
                Toast.makeText(this, "Scanned: " + scanResult.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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

    private void login() {
        LoginService loginService = new LoginService();
        loginService.setRequest(CommonConvert.stringToInterge(mEdtLoginId.getText().toString()));
        loginService.request(this, new CommonInterface.ModelResponse<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse result) {
                if (result.isStatus()) {
                    //Todo: login success and save data into userLogin
                    transferToPage(result.getUsers().get(0).getRoleCode());

                } else {
                    //Todo: handle error
                }
            }

            @Override
            public void onFail() {
                //Todo: handle error
            }
        });
    }

    private void transferToPage(int userRole) {
        //1: Doctor page
        if (1 == userRole) {
            transferToDoctor();
        } else {
            transferToPatientPage();
        }
    }

    private void transferToPatientPage() {
        //Patient activity
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void transferToDoctor() {
        //Doctor activity
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}
