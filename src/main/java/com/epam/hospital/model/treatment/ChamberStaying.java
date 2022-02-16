package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChamberStaying implements Entity, Serializable {
     Integer hospitalizationId;
     Integer chamberId;
     Double price;
     Date dateIn;
     Date dateOut;
}
