package com.epam.hospital.model.dto;

import com.epam.hospital.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {
    private String userRole;
    private String email;
    private String firstname;
    private String lastname;
    private String number;
    String specialization;
    int workExperience;
    int classification;
}