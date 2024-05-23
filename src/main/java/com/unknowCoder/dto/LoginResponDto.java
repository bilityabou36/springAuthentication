package com.unknowCoder.dto;

import com.unknowCoder.model.UserApplication;

public class LoginResponDto {
    private UserApplication user;
    private String jwt;

    public LoginResponDto(){

    }

    public UserApplication getUser() {
        return user;
    }

    public void setUser(UserApplication user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponDto(UserApplication user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }
}
