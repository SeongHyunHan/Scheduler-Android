package com.seong.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.seong.scheduler.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        User user = bundle.getParcelable("user");


    }
}
