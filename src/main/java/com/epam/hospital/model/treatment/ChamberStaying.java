package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChamberStaying implements Entity {
    private int hospitalization_id;
    private int chamber_id;
    private Date dateIn;
    private Date dateOut;
}
