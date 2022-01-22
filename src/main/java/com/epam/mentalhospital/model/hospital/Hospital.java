package com.epam.mentalhospital.model.hospital;

import com.epam.mentalhospital.model.Entity;

import java.util.Objects;

public class Hospital implements Entity {
    private int hospital_id;
    private String address;
    private String phone_number;

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public Hospital() {

    }

    public Hospital(int hospital_id, String address, String phone_number) {
        this.hospital_id = hospital_id;
        this.address = address;
        this.phone_number = phone_number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber(), getAddress(), getHospital_id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chamber)) return false;
        Hospital hospital = (Hospital) o;
        return getHospital_id() == hospital.getHospital_id() && getPhoneNumber().equals(hospital.getPhoneNumber()) && getAddress().equals(hospital.getAddress());
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospital_id=" + hospital_id +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
