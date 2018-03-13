package com.seong.scheduler;

import android.content.Intent;
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

        if(checkedUser == true){
            Log.d(TAG, "User already registered");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Log.d(TAG, "User not exist in DB");
            // Register User to DB

        }

    }

    private boolean checkUser(String token){
        boolean checked = true;

        return checked;
    }
}
