package com.epam.mentalhospital.model.treatment;

import com.epam.mentalhospital.model.Entity;
import com.epam.mentalhospital.model.treatment.type.CommunicationType;

import java.util.Objects;

public class Consultation implements Entity {
    private int consultationId;
    private int doctorId;
    private int patientId;
    private CommunicationType communicationType;
    private String date;
    private int duration;

    public Consultation() {

    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public CommunicationType getCommunicationTypeId() {
        return communicationType;
    }

    public void setCommunicationTypeId(CommunicationType communicationType) {
        this.communicationType = communicationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Consultation(int consultationId, int doctorId, int patientId, CommunicationType communicationType, String date, int duration) {
        this.consultationId = consultationId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.communicationType = communicationType;
        this.date = date;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consultation)) return false;
        Consultation that = (Consultation) o;
        return getConsultationId() == that.getConsultationId() && getDoctorId() == that.getDoctorId() && getPatientId() == that.getPatientId() && getDuration() == that.getDuration() && getCommunicationTypeId() == that.getCommunicationTypeId() && getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsultationId(), getDoctorId(), getPatientId(), getCommunicationTypeId(), getDate(), getDuration());
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "consultationId=" + consultationId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", communicationTypeId=" + communicationType +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
