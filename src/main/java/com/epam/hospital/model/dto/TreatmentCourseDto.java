package com.epam.hospital.model.dto;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreatmentCourseDto implements Entity {
    private List<String> diseases;
    private List<String> drugs;
    private List<String> description;
    private List<Integer> dose;
    private String instruction;
}
