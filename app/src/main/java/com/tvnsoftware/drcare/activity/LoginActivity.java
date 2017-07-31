package com.tvnsoftware.drcare.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.AlarmClock;
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
import com.tvnsoftware.drcare.data.FakeData;
import com.tvnsoftware.drcare.manager.CoreManager;
import com.tvnsoftware.drcare.model.Login.LoginResponse;
import com.tvnsoftware.drcare.model.users.User;
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
                login();
            }
        });
        mBtnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ZxingOrient(LoginActivity.this).initiateScan();
            }

        });
        if(CoreManager.getInstance().getUserData() != null){
            transferToPage(CoreManager.getInstance().getUserData().getRoleCode());
        }
        mBtnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarm();
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
                loginQRCode(scanResult.getContents());
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

    private void loginQRCode(String userCode) {
        User data = null;
        for (User u : FakeData.getUsers()) {
            if (u.getUserCode().equals(userCode.toUpperCase())) {
                data = u;
            }
        }
        if (data != null) {
            transferToPage(data.getRoleCode());
        } else {
            Toast.makeText(this, "Invalid UserCode", Toast.LENGTH_LONG).show();
        }
    }

    private void login() {
        User data = null;
        for (User u : FakeData.getUsers()) {
            if (u.getUserCode().equals(mEdtLoginId.getText().toString().toUpperCase())) {
                data = u;
            }
        }
        if (data != null) {
            CoreManager.getInstance().setUserData(this, data);
            transferToPage(data.getRoleCode());
        } else {
            Toast.makeText(this, "Invalid UserCode", Toast.LENGTH_LONG).show();
        }
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
        //Patient activity hello
        Intent i = new Intent(LoginActivity.this, HistoryActivity.class);
        startActivity(i);
    }

    private void transferToDoctor() {
        //Doctor activity
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
    private void showAlarm(){
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
//        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
//        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
//        this.startActivityForResult(intent, 5);
    }
}
