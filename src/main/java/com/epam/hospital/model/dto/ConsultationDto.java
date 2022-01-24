package com.epam.hospital.model.dto;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationDto implements Entity {
    private int consultationId;
    private String communicationType;
    private Date date;
    private int duration;
    private String doctorFirstName;
    private String doctorLastName;
    private String patientFirstName;
    private String patientLastName;
    private List<String> diseases;
    private List<String> drugs;
    private String instruction;
}
