package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;

import java.util.Objects;

public class Disease implements Entity {
    private int diseaseId;
    private String name;
    private String description;

    public Disease() {

    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Disease(int diseaseId, String description) {
        this.diseaseId = diseaseId;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disease)) return false;
        Disease disease = (Disease) o;
        return getDiseaseId() == disease.getDiseaseId() && getName().equals(disease.getName()) && getDescription().equals(disease.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiseaseId(), getName(), getDescription());
    }

    @Override
    public String toString() {
        return "Disease{" +
                "diseaseId=" + diseaseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
