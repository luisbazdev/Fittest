package com.example.demo.jwt;

public class JwtToken {
    private String token;

    public JwtToken(){

    }

    public JwtToken(String token){
        this.token = token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
