package com.epam.mentalhospital.dao;

import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.treatment.TreatmentCourse;

import java.util.List;

public interface TreatmentCourseDao extends AbstractDao<TreatmentCourse> {
    List<TreatmentCourse> findByConsultationId(int consultationId) throws DaoException;
}
