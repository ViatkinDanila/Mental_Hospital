package com.epam.hospital;

import com.epam.hospital.model.treatment.DrugRecipe;

public class Main {
    public static void main(String[] args) {
        DrugRecipe drugRecipe = new DrugRecipe();
        drugRecipe.setDrugId(1);
        drugRecipe.setDose(15);
        System.out.println(drugRecipe.toString());
//        UserDao patientCardDao = new UserDaoImpl();
//        try {
//            ConnectionPool.getInstance().init("jdbc:mysql://localhost:3306/mental_hospital?useSSL=false","root","admin");
//            patientCardDao.setConnection((PooledConnection) ConnectionPool.getInstance().takeConnection());
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
