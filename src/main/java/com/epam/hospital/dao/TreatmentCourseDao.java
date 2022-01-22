package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.treatment.TreatmentCourse;

import java.util.List;

public interface TreatmentCourseDao extends AbstractDao<TreatmentCourse> {
    List<TreatmentCourse> findByConsultationId(int consultationId) throws DaoException;
}
