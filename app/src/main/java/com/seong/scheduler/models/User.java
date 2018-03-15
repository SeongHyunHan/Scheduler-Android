package com.seong.scheduler.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hans on 2018-03-13.
 */

public class User {
    @SerializedName("studentId")
    private int studentId;
    @SerializedName("name")
    private String name;
    @SerializedName("token")
    private String token;

    public User(int studentId, String name, String token) {
        this.studentId = studentId;
        this.name = name;
        this.token = token;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
