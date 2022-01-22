package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.request.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConsultationPageCommand implements Command {

    @Override
    public CommandResult execute(RequestContext requestContext) {
//        String consultationIdString = req.getParameter("id");
//        int consultationId = Integer.parseInt(consultationIdString);
//        Consultation consultation = ConsultationService.getConsultaionById(consultationId);
//        TreatmentCourse tc = TreatmentCourseSerivece.getTCByConsId(consultation.getTCId);
//        User doctor = UserService.getUserById(consultation.getDoctorId());
//        PatientCard patien = PatientCardService.getPatientById(consultation.getPatientId());
//        List<String> drugs = tc.getDrugId().stream().map(Drug::getName).collect(Collectors.toList());
        return null;
    }
}
