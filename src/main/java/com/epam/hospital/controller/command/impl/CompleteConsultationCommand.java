package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.TreatmentCourseDto;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.TreatmentCourseService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.TreatmentCourseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;

public class CompleteConsultationCommand implements Command {
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final TreatmentCourseService treatmentCourse = TreatmentCourseServiceImpl.getInstance();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int consultationId = ParameterExtractor.extractInt(Attribute.DOCTOR_ID, requestContext);

        Consultation consultation = consultationService.getConsultationById(consultationId);


        consultation.setTreatmentCourseId(1);
        return null;
    }
}
