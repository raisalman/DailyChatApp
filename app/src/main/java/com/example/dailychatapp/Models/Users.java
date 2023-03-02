package com.example.dailychatapp.Models;

public class Users {
        String profilePic,userName,Password,Email,lastMessage,userId,status;

    public Users(String profilePic, String userName, String password, String email, String lastMessage, String userId, String status) {
        this.profilePic = profilePic;
        this.userName = userName;
        Password = password;
        Email = email;
        this.lastMessage = lastMessage;
        this.userId = userId;
        this.status = status;
    }

    public Users(){}

    //Sign Up Constructor
    public Users(String userName, String email, String password) {
        this.userName = userName;
        Password = password;
        Email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
