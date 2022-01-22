package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;

import java.util.Date;
import java.util.Objects;

public class Hospitalization implements Entity {
    private int hospitalizationId;
    private int patientId;
    private int chamberId;
    private Date dateIn;
    private Date dateOut;

    public Hospitalization() {

    }

    public int getHospitalizationId() {
        return hospitalizationId;
    }

    public void setHospitalizationId(int hospitalizationId) {
        this.hospitalizationId = hospitalizationId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getChamberId() {
        return chamberId;
    }

    public void setChamberId(int chamberId) {
        this.chamberId = chamberId;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Hospitalization(int hospitalizationId, int patientId, int chamberId, Date dateIn, Date dateOut) {
        this.hospitalizationId = hospitalizationId;
        this.patientId = patientId;
        this.chamberId = chamberId;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospitalization)) return false;
        Hospitalization that = (Hospitalization) o;
        return getHospitalizationId() == that.getHospitalizationId() && getPatientId() == that.getPatientId() && getChamberId() == that.getChamberId() && getDateIn().equals(that.getDateIn()) && getDateOut().equals(that.getDateOut());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHospitalizationId(), getPatientId(), getChamberId(), getDateIn(), getDateOut());
    }

    @Override
    public String toString() {
        return "Hospitalization{" +
                "hospitalizationId=" + hospitalizationId +
                ", patientId=" + patientId +
                ", chamberId=" + chamberId +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                '}';
    }
}
