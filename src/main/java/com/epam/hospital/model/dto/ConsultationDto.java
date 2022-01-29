package com.epam.hospital.model.dto;

import com.epam.hospital.model.Entity;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationDto implements Entity {
    private String communicationType;
    private Date date;
    private int duration;
    private ConsultationStatus consultationStatus;
    private String doctorFirstName;
    private String doctorLastName;
    private int doctorId;
    private int userId;
    private String patientFirstName;
    private String patientLastName;
    private List<DiseaseWithSymptomsDto> diseases;
    private List<DrugRecipeDto> drugs;
    private String instruction;
    private double price;
}
