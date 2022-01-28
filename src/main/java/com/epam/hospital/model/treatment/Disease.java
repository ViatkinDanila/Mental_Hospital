package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Disease implements Entity {
    private int diseaseId;
    private String name;
    private String description;
}
