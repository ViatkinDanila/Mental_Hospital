package com.epam.mentalhospital.model.dto;

import com.epam.mentalhospital.model.Entity;
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
    private int consultationId;
    private String communicationType;
    private Date date;
    private int duration;
    private String doctorFirstName;
    private String doctorLastName;
    private List<String> diseases;
    private List<String> drugs;
    private String instruction;

    public static void main(String[] args) {
        System.out.println(ConsultationDto.builder().
                duration(141).
                build());
    }
}
