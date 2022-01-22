package com.epam.mentalhospital.model.hospital.type;

import com.epam.mentalhospital.model.Entity;

import java.util.Objects;

public class ChamberType implements Entity {
    private int chamberId;
    private String name;
    private int numberOfBeds;
    private double price;
    private int numberOfFreeRooms;

    public int getChamberId() {
        return chamberId;
    }

    public void setChamberId(int chamberId) {
        this.chamberId = chamberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfFreeRooms() {
        return numberOfFreeRooms;
    }

    public void setNumberOfFreeRooms(int numberOfFreeRooms) {
        this.numberOfFreeRooms = numberOfFreeRooms;
    }

    public ChamberType() {

    }

    public ChamberType(int chamberId, String name, int numberOfBeds, double price, int numberOfFreeRooms) {
        this.chamberId = chamberId;
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.numberOfFreeRooms = numberOfFreeRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChamberType)) return false;
        ChamberType that = (ChamberType) o;
        return getNumberOfFreeRooms() == that.numberOfFreeRooms && getChamberId() == that.getChamberId() && Double.compare(that.getPrice(), getPrice()) == 0 && getName().equals(that.getName()) && getNumberOfBeds() == that.getNumberOfBeds();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfFreeRooms(), getChamberId(), getName(), getNumberOfBeds(), getPrice());
    }

    @Override
    public String toString() {
        return "ChamberType{" +
                "chamberId=" + chamberId +
                ", name='" + name + '\'' +
                ", numberOfBeds='" + numberOfBeds + '\'' +
                ", price=" + price +
                ", numberOfFreeChambers=" + numberOfFreeRooms +
                '}';
    }
}
