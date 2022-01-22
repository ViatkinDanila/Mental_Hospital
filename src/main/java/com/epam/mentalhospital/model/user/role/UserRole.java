package com.epam.mentalhospital.model.user.role;

import com.epam.mentalhospital.model.Entity;

import java.util.Objects;

public class UserRole implements Entity {
    private int userId;
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole() {

    }

    public UserRole(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return getUserId() == userRole.getUserId() && getName().equals(userRole.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName());
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}

