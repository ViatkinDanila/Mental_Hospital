package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.TreatmentCourseDao;
import com.epam.hospital.dao.connectionpool.ConnectionPool;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.TreatmentCourseDaoImpl;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.database.TreatmentCourseService;
import com.epam.hospital.service.exception.ServiceException;

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
}
