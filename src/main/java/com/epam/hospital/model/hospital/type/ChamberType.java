package com.epam.hospital.model.hospital.type;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
