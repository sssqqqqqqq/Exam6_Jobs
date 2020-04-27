package com.example.lpz_6.bean;

public class User {
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(){}

    @Override
    public String toString() {
        return  "name=" + name + '&' +
                "password=" + password + '&' +
                "email=" + email ;
    }
    public String getLogin(){
        return  "name=" + name + '&' +
                "password=" + password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
