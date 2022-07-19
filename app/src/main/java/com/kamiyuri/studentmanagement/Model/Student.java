package com.kamiyuri.studentmanagement.Model;

import android.util.Log;

import java.util.Date;

public class Student {
    private String mssv, name, email;
    private String birthday;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Student(String mssv, String name, String email, String birthday) {
        this.mssv = mssv;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public void printData() {
        Log.v("TAG", mssv + " --- " + name + " --- " + email + "---" + birthday);
    }
}
