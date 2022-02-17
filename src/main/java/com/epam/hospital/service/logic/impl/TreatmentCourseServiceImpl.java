package com.epam.hospital.service.logic.impl;

import com.epam.hospital.dao.*;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.*;
import com.epam.hospital.constant.database.Column;
import com.epam.hospital.model.treatment.DiseaseSymptom;
import com.epam.hospital.model.treatment.DrugRecipe;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.logic.TreatmentCourseService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class TreatmentCourseServiceImpl implements TreatmentCourseService {
    private static TreatmentCourseService instance;
    //TODO вынести в конст дао
    private TreatmentCourseServiceImpl() {

    }

    public static TreatmentCourseService getInstance() {
        if (instance == null) {
            instance = new TreatmentCourseServiceImpl();
        }
        return instance;
    }

    @Override
    public TreatmentCourse getTreatmentCourseById(int treatmentCourseId) throws ServiceException {
        TreatmentCourseDao treatmentCourseDao = new TreatmentCourseDaoImpl();
        try (DaoTransactionProvider transaction = new DaoTransactionProvider()) {
            transaction.initTransaction(treatmentCourseDao);
            return treatmentCourseDao.findById(treatmentCourseId);
        } catch (DaoException e) {
            throw new ServiceException("Can't get treatment course.", e);
        }
    }

    @Override
    public List<DiseaseSymptom> getDiseaseSymptoms(int treatmentCourseId) throws ServiceException {
        AbstractDao<DiseaseSymptom> diseaseSymptomDao = new DiseaseSymptomDaoImpl();
        try (DaoTransactionProvider transaction = new DaoTransactionProvider()) {
            transaction.initTransaction(diseaseSymptomDao);
            return diseaseSymptomDao.findByField(Column.DISEASE_SYMPTOMS_COURSE_ID, treatmentCourseId);
        } catch (DaoException e) {
            throw new ServiceException("Can't get disease symptoms.", e);
        }
    }

    @Override
    public List<DrugRecipe> getDrugRecipes(int treatmentCourseId) throws ServiceException {
        AbstractDao<DrugRecipe> diseaseSymptomDao = new DrugRecipeDaoImpl();
        try (DaoTransactionProvider transaction = new DaoTransactionProvider()) {
            transaction.initTransaction(diseaseSymptomDao);
            return diseaseSymptomDao.findByField(Column.DRUG_RECIPES_TREATMENT_COURSE_ID, treatmentCourseId);
        } catch (DaoException e) {
            throw new ServiceException("Can't get drug recipes.", e);
        }
    }

    @Override
    public int saveTreatmentCourse(TreatmentCourse treatmentCourse, List<DiseaseSymptom> diseasesSymptoms, List<DrugRecipe> drugsRecipes) throws ServiceException {
        TreatmentCourseDao treatmentCourseDao = new TreatmentCourseDaoImpl();
        DrugRecipeDao drugRecipeDao = new DrugRecipeDaoImpl();
        DiseaseSymptomDao diseaseSymptomDao = new DiseaseSymptomDaoImpl();
        try (DaoTransactionProvider transaction = new DaoTransactionProvider()) {
            transaction.initTransaction(false, treatmentCourseDao, diseaseSymptomDao, drugRecipeDao);

            treatmentCourseDao.save(treatmentCourse);
            treatmentCourse = treatmentCourseDao.findTreatmentCourseByInstruction(treatmentCourse.getInstruction());
            int treatmentCourseId = treatmentCourse.getTreatmentCourseId();

            for (DiseaseSymptom diseaseSymptom : diseasesSymptoms){
                diseaseSymptom.setTreatmentCourseId(treatmentCourseId);
                diseaseSymptomDao.save(diseaseSymptom);
            }

            if (drugsRecipes.size() != 0) {
                for (DrugRecipe drugRecipe : drugsRecipes) {
                    drugRecipe.setTreatmentCourseId(treatmentCourseId);
                    drugRecipeDao.save(drugRecipe);
                }
            }
            transaction.commit();
            return treatmentCourseId;
        } catch (DaoException e) {
            throw new ServiceException("Can't get drug recipes.", e);
        }
    }
}