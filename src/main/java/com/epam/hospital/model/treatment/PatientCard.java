package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientCard implements Entity {
     Integer cardId;
     Integer userId;
     String spareNumber;
     Integer age;
     String sex;
}
