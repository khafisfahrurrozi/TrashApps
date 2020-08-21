package com.example.trashapplication;

public class getset {
    private String mImageUrl,username,maps,password,usernameup,passwordup,email,weight,typetrash;
    private Long phone;


    public getset(String mImageUrl, String username, String maps, String password, String usernameup, String passwordup, String email, String weight, String typetrash, Long phone) {

        this.mImageUrl = mImageUrl;
        this.username = username;
        this.maps = maps;
        this.password = password;
        this.usernameup = usernameup;
        this.passwordup = passwordup;
        this.email = email;
        this.weight = weight;
        this.typetrash = typetrash;
        this.phone = phone;
    }

    public getset() {
    }

    public getset(String weight, String ImageUrl,String maps) {

        this.weight=weight;
        this.mImageUrl=ImageUrl;
        this.maps=maps;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String ImageUrl) {
        this.mImageUrl = ImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameup() {
        return usernameup;
    }

    public void setUsernameup(String usernameup) {
        this.usernameup = usernameup;
    }

    public String getPasswordup() {
        return passwordup;
    }

    public void setPasswordup(String passwordup) {
        this.passwordup = passwordup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTypetrash() {
        return typetrash;
    }

    public void setTypetrash(String typetrash) {
        this.typetrash = typetrash;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
