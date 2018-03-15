package com.seong.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;
import com.seong.scheduler.models.User;
import com.seong.scheduler.models.UserList;
import com.seong.scheduler.network.GetUserDataService;
import com.seong.scheduler.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";

    private EditText studentID, name;
    private Button submit;

    private String token;
    private GetUserDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        name = findViewById(R.id.edtName);
        studentID = findViewById(R.id.edtID);
        submit = findViewById(R.id.btnSubmit);

        token = FirebaseInstanceId.getInstance().getToken();

        service = RetrofitInstance.getRetrofitInstace().create(GetUserDataService.class);

        boolean checkedUser = checkUser(token);

        if(checkedUser == true){
            Log.d(TAG, "User already registered");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Log.d(TAG, "User not exist in DB");
        }



    }

    private boolean checkUser(String token){
        boolean checked = false;

        Call<UserList> call = service.getUser();

        Log.d(TAG, "URL Called: " + call.request().url());

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.d(TAG, "On Response Called");
                if(response.body().getUserList() == null){
                    Log.d(TAG, "Response 0");
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d(TAG, "On Failur Called");
            }
        });

        return checked;
    }
}
