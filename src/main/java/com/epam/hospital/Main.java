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

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HospitalizationDao hospitalizationDao = new HospitalizationDaoImpl();
        UserDao userDao = new UserDaoImpl();
        TreatmentCourseDao dao = new TreatmentCourseDaoImpl();
        try{
            ConnectionPool.getInstance().init("jdbc:mysql://localhost:3306/mental_hospital?useSSL=false&allowPublicKeyRetrieval=true","root","admin");
            dao.setConnection((PooledConnection) ConnectionPool.getInstance().takeConnection());
            List<TreatmentCourse> tc = dao.findByField("id",1);
            System.out.println(tc.toString());
        } catch (DaoException e){
            System.out.println("Dao here.");
        } catch (ConnectionPoolException e){

        }
//
//        try {
//
//            User user = new User(3,1,"Flucker","s","s","s","s");
//            patientCardDao.save(user);;
//            System.out.println("Benn here");
//            System.out.println(user.toString());
//        } catch (DaoException e) {
//            System.out.println("Daoexcp");
//        } catch (ConnectionPoolException e){
//            System.out.println("conpol");
//
//        }
    }
}
