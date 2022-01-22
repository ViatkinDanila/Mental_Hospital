package com.epam.mentalhospital.model.hospital.type;

import com.epam.mentalhospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamberType implements Entity {
    private int chamberTypeId;
    private String name;
    private int numberOfBeds;
    private double price;
    private int numberOfFreeRooms;
}
