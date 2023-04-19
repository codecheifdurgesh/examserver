package com.exam.examserver.Entity;

public class JwtResponse {
    private String token;

    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
