package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.AbstractDao;
import com.epam.hospital.dao.DiseaseSymptomDao;
import com.epam.hospital.dao.TreatmentCourseDao;
import com.epam.hospital.dao.connectionpool.ConnectionPool;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.DiseaseSymptomDaoImpl;
import com.epam.hospital.dao.impl.DrugRecipeDaoImpl;
import com.epam.hospital.dao.impl.TreatmentCourseDaoImpl;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.model.treatment.DiseaseSymptom;
import com.epam.hospital.model.treatment.DrugRecipe;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.database.TreatmentCourseService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class TreatmentCourseServiceImpl implements TreatmentCourseService {
    private static TreatmentCourseService treatmentCourseService;

    private TreatmentCourseServiceImpl(){

    }

    public static TreatmentCourseService getInstance(){
        if (treatmentCourseService == null){
            treatmentCourseService = new TreatmentCourseServiceImpl();
        }
        return treatmentCourseService;
    }

    @Override
    public TreatmentCourse getTreatmentCourseById(int treatmentCourseId) throws ServiceException {
        TreatmentCourseDao treatmentCourseDao = new TreatmentCourseDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(treatmentCourseDao);
            return treatmentCourseDao.findById(treatmentCourseId);
        } catch (DaoException e) {
            throw new ServiceException("Can't get treatment course.", e);
        }
    }

    @Override
    public List<DiseaseSymptom> getDiseaseSymptoms(int treatmentCourseId) throws ServiceException {
        AbstractDao<DiseaseSymptom> diseaseSymptomDao = new DiseaseSymptomDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(diseaseSymptomDao);
            return diseaseSymptomDao.findByField(Column.DISEASE_SYMPTOMS_COURSE_ID, treatmentCourseId);
        } catch (DaoException e) {
            throw new ServiceException("Can't get disease symptoms.", e);
        }
    }

    @Override
    public List<DrugRecipe> getDrugRecipes(int treatmentCourseId) throws ServiceException {
        AbstractDao<DrugRecipe> diseaseSymptomDao = new DrugRecipeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(diseaseSymptomDao);
            return diseaseSymptomDao.findByField(Column.DRUGS_RECIPES_TREATMENT_COURSE_ID, treatmentCourseId);
        } catch (DaoException e) {
            throw new ServiceException("Can't get drug recipes.", e);
        }
    }
}
