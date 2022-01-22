package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.TreatmentCourseDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.treatment.TreatmentCourse;

import java.util.List;

public class TreatmentCourseDaoImpl extends AbstractDaoImpl<TreatmentCourse> implements TreatmentCourseDao {
    public static final String FIND_BY_CONSULTATION_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.TREATMENT_COURS_TABLE,
            Column.TREATMENT_COURSE_CONSULTATION_ID
    );
    public static final String SAVE_TREATMENT_COURSE_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.TREATMENT_COURS_TABLE,
            Column.TREATMENT_COURSE_CONSULTATION_ID,
            Column.TREATMENT_COURSE_INSTRUCTION
    );
    public static final String SAVE_TREATMENT_COURSE_DISEASE_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            Table.TREATMENT_COURS_HAS_DISEASES_TABLE,
            Column.TREATMENT_COURSE_ID,
            Column.TREATMENT_COURSE_DISEASE_ID,
            Column.TREATMENT_COURSE_SYMPTOMS
    );
    public static final String SAVE_TREATMENT_COURSE_DRUGS_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            Table.DRUGS_TABLE,
            Column.TREATMENT_COURSE_ID,
            Column.TREATMENT_COURSE_DRUG_ID,
            Column.TREATMENT_COURSE_DRUG_DOSE

    );

    public static final String FIND_DRUGS_BY_TREATMENT_COURSE_ID_QUERY = String.format("%s", 1);


    public TreatmentCourseDaoImpl() {
        super(BuilderFactory.getTreatmentCourseBuilder(), Table.TREATMENT_COURS_TABLE, Column.TREATMENT_COURSE_ID);
    }

    @Override
    public List<TreatmentCourse> findByConsultationId(int consultationId) throws DaoException {
        return null;
    }

    @Override
    public void save(TreatmentCourse entity) throws DaoException {
//        return queryExecutor.executeUpdate(SAVE_TREATMENT_COURSE_QUERY,
//                entity.getConsultationId(),
//                entity.getInstruction()) +
//   /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                НЕ ЗАБУДЬ ПРОВЕРИТЬ*/
//                queryExecutor.executeUpdate(SAVE_TREATMENT_COURSE_DISEASE_QUERY,
//                        entity.getTreatmentCourseId(),
//                        entity.getDiseaseId(),
//                        entity.getSymptoms()) +
//                queryExecutor.executeUpdate(SAVE_TREATMENT_COURSE_DRUGS_QUERY,
//                        entity.getTreatmentCourseId(),
//                        entity.getDrugId(),
//                        entity.getDose());
    }

    @Override
    public void update(TreatmentCourse entity) throws DaoException {
//        throw new DaoException("Treatment cannot be updated, only created.");
    }

    @Override
    public void deleteById(int id) throws DaoException {
//        return super.deleteById(id);
    }

    @Override
    public TreatmentCourse findById(int id) throws DaoException {
        return super.findById(id);
    }

    @Override
    public List<TreatmentCourse> findAll() throws DaoException {
        return super.findAll();
    }


}
