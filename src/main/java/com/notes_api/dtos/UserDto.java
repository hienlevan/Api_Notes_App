package com.notes_api.dtos;

import com.notes_api.enntities.Note;

import java.util.List;

public class UserDto {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}