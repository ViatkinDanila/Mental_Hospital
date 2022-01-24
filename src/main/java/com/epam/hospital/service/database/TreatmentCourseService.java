package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.exception.ServiceException;

public interface TreatmentCourseService {
    TreatmentCourse getTreatmentCourseById(int treatmentCourseId) throws ServiceException;
}
