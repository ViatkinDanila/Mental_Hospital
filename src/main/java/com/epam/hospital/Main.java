package com.epam.hospital;

import com.epam.hospital.dao.HospitalizationDao;
import com.epam.hospital.dao.TreatmentCourseDao;
import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.connectionpool.ConnectionPool;
import com.epam.hospital.dao.connectionpool.PooledConnection;
import com.epam.hospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.impl.HospitalizationDaoImpl;
import com.epam.hospital.dao.impl.TreatmentCourseDaoImpl;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.treatment.DrugRecipe;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.model.user.User;
import com.epam.hospital.util.Hasher;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    }
}
