package com.seong.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seong.scheduler.models.Token;
import com.seong.scheduler.models.User;
import com.seong.scheduler.models.UserList;
import com.seong.scheduler.network.GetUserDataService;
import com.seong.scheduler.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";

    private EditText edtStudentID, edtName;
    private Button btnSubmit;

    private User currentUser;

    private String token;
    private GetUserDataService service;

    private View.OnClickListener submitInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = edtName.getText().toString();
            int studentId = Integer.parseInt(edtStudentID.getText().toString());

            Token androidToken = new Token(token);
            User user = new User(studentId, name, androidToken);
            registerUser(user);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        edtName = findViewById(R.id.edtName);
        edtStudentID = findViewById(R.id.edtID);
        btnSubmit = findViewById(R.id.btnSubmit);

        token = FirebaseInstanceId.getInstance().getToken();

        service = RetrofitInstance.getRetrofitInstace().create(GetUserDataService.class);

        btnSubmit.setOnClickListener(submitInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean checkedUser = checkUser(token);

        if(checkedUser == true){
            Log.d(TAG, "User already registered");
            sendToMain();
        }else{
            Log.d(TAG, "User not exist in DB");
        }
    }

    private boolean checkUser(String token){
        final boolean[] checked = {false};

        Call<UserList> call = service.getUser(token);

        Log.d(TAG, "URL Called: " + call.request().url());

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.d(TAG, "On Response Called " + response.code());
                if(response.body() == null){
                    Log.d(TAG, "Response is null!");
                }else{
                    Log.d(TAG, response.body().getUsers() + "");
                    List<User> users = response.body().getUsers();
                    if(users != null) {
                        for (User user : users) {
                            if (user == null) {
                                Toast.makeText(LaunchActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LaunchActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                checked[0] = true;
                                currentUser = user;
                            }
                        }
                    }else{
                        Log.d(TAG, users + "");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d(TAG, "On Failure Called");
            }
        });

        return checked[0];
    }

    private void sendToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", currentUser);
        startActivity(intent);
    }

    private void registerUser(User user){
        Call<User> call = service.saveUser(user.getStudentId(), user.getName(), user.getToken());
        
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "Register User on Response Call: " + response.code());
                sendToMain();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Register User on Failure Called");
                Toast.makeText(LaunchActivity.this, "Failed to save user", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
