package com.epam.mentalhospital.model.treatment;

import com.epam.mentalhospital.model.Entity;

import java.util.Objects;

public class Drug implements Entity {
    private int drugId;
    private String name;

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drug() {

    }

    public Drug(int drugId, String name) {
        this.drugId = drugId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drug)) return false;
        Drug drug = (Drug) o;
        return getDrugId() == drug.getDrugId() && getName().equals(drug.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDrugId(), getName());
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId=" + drugId +
                ", name='" + name + '\'' +
                '}';
    }
}
