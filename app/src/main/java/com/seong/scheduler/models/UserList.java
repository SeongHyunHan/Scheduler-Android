package com.seong.scheduler.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hans on 2018-03-15.
 */

public class UserList {
    @SerializedName("userList")
    private ArrayList<User> userList;

    public ArrayList<User> getUserList(){
        return userList;
    }

    public void setUserList(ArrayList<User> userList){
        this.userList = userList;
    }
}
