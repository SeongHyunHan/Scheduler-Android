package com.seong.scheduler.models;

/**
 * Created by hans on 2018-03-13.
 */

public class User {
    private int studentId;
    private String name;
    private String token;

    public User(){
        this.studentId = 0;
        this.name = null;
        this.token = null;
    }

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
