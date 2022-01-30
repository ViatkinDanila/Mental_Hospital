package com.epam.hospital.model.dto;

import com.epam.hospital.model.treatment.type.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortConsultationDto {
    private int id;
    private Date date;
    private String communicationType;
    private String doctorFullName;
    private String patientFullName;
    private ConsultationStatus consultationStatus;
}
