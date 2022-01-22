package com.epam.mentalhospital.model.user;

import com.epam.mentalhospital.model.Entity;

import java.util.Objects;

public class User implements Entity {
    private int userId;
    private int userRoleId;
    private String email;
    private String firstname;
    private String lastname;
    private String number;
    private String hashedPassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public User() {

    }

    public User(int userId, int userRoleId, String email, String firstname, String lastname, String number, String hashedPassword) {
        this.userId = userId;
        this.userRoleId = userRoleId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.number = number;
        this.hashedPassword = hashedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && getUserRoleId() == user.getUserRoleId() && getFirstname().equals(user.getFirstname()) && getLastname().equals(user.getLastname()) && getNumber().equals(user.getNumber()) && getEmail().equals(user.getEmail()) && getHashedPassword().equals(user.getHashedPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserRoleId(), getFirstname(), getLastname(), getNumber(), getEmail(), getHashedPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userRole=" + userRoleId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
