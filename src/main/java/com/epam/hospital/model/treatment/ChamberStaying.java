package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChamberStaying implements Entity {
     Integer hospitalizationId;
     Integer chamberId;
     Double price;
     Date dateIn;
     Date dateOut;
}
