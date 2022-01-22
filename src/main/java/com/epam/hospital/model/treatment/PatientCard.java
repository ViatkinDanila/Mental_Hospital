package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;

import java.util.Objects;

public class PatientCard implements Entity {
    private int cardId;
    private int userId;
    private String spareNumber;
    private int age;
    private String sex;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSpareNumber() {
        return spareNumber;
    }

    public void setSpareNumber(String spareNumber) {
        this.spareNumber = spareNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public PatientCard() {

    }

    public PatientCard(int cardId, int userId, String spareNumber, int age, String sex) {
        this.cardId = cardId;
        this.userId = userId;
        this.spareNumber = spareNumber;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientCard)) return false;
        PatientCard that = (PatientCard) o;
        return getCardId() == that.getCardId() && getUserId() == that.getUserId() && getAge() == that.getAge() && getSpareNumber().equals(that.getSpareNumber()) && getSex().equals(that.getSex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getUserId(), getSpareNumber(), getAge(), getSex());
    }

    @Override
    public String toString() {
        return "OutpatientCard{" +
                "cardId=" + cardId +
                ", userId=" + userId +
                ", spareNumber='" + spareNumber + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
