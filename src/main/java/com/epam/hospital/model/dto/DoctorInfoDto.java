package com.epam.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorInfoDto {
    String specialization;
    int classification;
    int workExperience;
}