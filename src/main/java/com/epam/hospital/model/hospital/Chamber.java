package com.epam.hospital.model.hospital;

import com.epam.hospital.model.Entity;

import java.util.Objects;

public class Chamber implements Entity {
    private int chamberId;
    private int hospitalId;
    private int chamberTypeId;
    private int numberOfFreeBeds;


    public int getChamberId() {
        return chamberId;
    }

    public void setChamberId(int chamberId) {
        this.chamberId = chamberId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getChamberTypeId() {
        return chamberTypeId;
    }

    public void setChamberTypeId(int chamberTypeId) {
        this.chamberTypeId = chamberTypeId;
    }

    public int getNumberOfFreeBeds() {
        return numberOfFreeBeds;
    }

    public void setNumberOfFreeBeds(int numberOfFreeBeds) {
        this.numberOfFreeBeds = numberOfFreeBeds;
    }

    public Chamber() {

    }

    public Chamber(int chamberId, int hospitalId, int chamberTypeId, int numberOfFreeBeds) {
        this.chamberId = chamberId;
        this.hospitalId = hospitalId;
        this.chamberTypeId = chamberTypeId;
        this.numberOfFreeBeds = numberOfFreeBeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chamber)) return false;
        Chamber chamber = (Chamber) o;
        return getChamberId() == chamber.getChamberId() && getHospitalId() == chamber.getHospitalId()
                && getChamberTypeId() == chamber.getChamberTypeId() && getNumberOfFreeBeds() == chamber.getNumberOfFreeBeds();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChamberId(), getHospitalId(), getChamberTypeId(), getNumberOfFreeBeds());
    }

    @Override
    public String toString() {
        return "Chamber{" +
                "chamberId=" + chamberId +
                ", hospitalId=" + hospitalId +
                ", chamberTypeId=" + chamberTypeId +
                ", numberOfFreeBeds=" + numberOfFreeBeds +
                '}';
    }
}
