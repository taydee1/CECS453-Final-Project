package com.example.android.finalproject.Model;

import android.widget.EditText;

public class User {
    public String fullName, userName, email;

    public User(EditText name, EditText user, EditText email)
    {

    }

    public User(String name, String user)
    {
        this.fullName = name;
        this.userName = user;
    }

    public User(String fullName, String userName, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
    }
}
