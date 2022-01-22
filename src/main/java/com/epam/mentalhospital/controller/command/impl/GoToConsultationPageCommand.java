package com.epam.mentalhospital.controller.command.impl;

import com.epam.mentalhospital.controller.command.Command;
import com.epam.mentalhospital.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToConsultationPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
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
