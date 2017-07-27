package com.tvnsoftware.drcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.api.CommonInterface;
import com.tvnsoftware.drcare.api.restservice.UserService;
import com.tvnsoftware.drcare.model.users.UsersResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    @BindView(R.id.edt_login_id)
    EditText mEdtLoginId;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //ToDo something
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                //callAPI();
            }
        });
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, AuthenActivity.class);
                startActivity(i);
            }

        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        ZxingOrientResult scanResult =
//                ZxingOrient.parseActivityResult(requestCode, resultCode, data);
//        if (scanResult != null) {
//            if (scanResult.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Scanned: " + scanResult.getContents(), Toast.LENGTH_LONG).show();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
    private void callAPI(){
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
}
