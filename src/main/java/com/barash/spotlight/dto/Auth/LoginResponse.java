package com.barash.spotlight.dto.Auth;

public class LoginResponse {
    private String username;
    private String token;

    public LoginResponse() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public static class LoginResponseBuilder {
        private LoginResponse resp = new LoginResponse();
        public LoginResponseBuilder username(String u) { resp.setUsername(u); return this; }
        public LoginResponseBuilder token(String t) { resp.setToken(t); return this; }
        public LoginResponse build() { return resp; }
    }
}
