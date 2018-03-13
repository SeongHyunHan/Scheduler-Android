package com.seong.scheduler;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        token = FirebaseInstanceId.getInstance().getToken();
        boolean checkedUser = checkUser(token);

        Log.d(TAG, "token: " + token);
    }

    private boolean checkUser(String token){
        boolean checked = false;

        return checked;
    }
}
