package com.epam.hospital.model.user.info;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorInfo implements Entity {
    private int doctorId;
    private int classification;
    private int workExperience;
    private String specialization;
}
