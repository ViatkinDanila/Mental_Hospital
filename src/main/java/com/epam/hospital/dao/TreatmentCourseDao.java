package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.treatment.TreatmentCourse;

import java.util.List;

public interface TreatmentCourseDao extends AbstractDao<TreatmentCourse> {
    TreatmentCourse findTreatmentCourseByInstruction(String instruction) throws DaoException;
}
