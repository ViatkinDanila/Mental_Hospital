package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.TreatmentCourseDao;
import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.TreatmentCourseDaoImpl;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static UserService instance = new UserServiceImpl();

    public static UserService getInstance(){
        return instance;
    }
    @Override
    public User login(String email, String password) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findByEmailPassword(email,password);
        } catch (DaoException e){
            throw new ServiceException("Can't find out is user exit.", e);
        }
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't get user.", e);
        }
    }

    @Override
    public User getUserByLogin(String email) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findByEmail(email);
        } catch (DaoException e){
            throw new ServiceException("Can't get user by login.", e);
        }
    }

    @Override
    public String getUserRoleById(int id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findUserRole(id);
        } catch (DaoException e){
            throw new ServiceException("Can't get user by login.", e);
        }
    }

    @Override
    public boolean isUserExistByLogin(String login) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.isUserExist(login);
        } catch (DaoException e){
            throw new ServiceException("Can't get user by login.", e);
        }
    }

    @Override
    public DoctorInfo getDoctorInfoById(int id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findDoctorInfoById(id);
        } catch (DaoException e){
            throw new ServiceException("Can't get doctor info by doctor id.", e);
        }
    }

    @Override
    public User getUserByFullName(String firstName, String lastName) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findByFullName(firstName,lastName);
        } catch (DaoException e){
            throw new ServiceException("Can't get doctor info by doctor id.", e);
        }
    }

    @Override
    public List<User> getAllDoctors(int doctorRoleId) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findAllDoctors(doctorRoleId);
        } catch (DaoException e){
            throw new ServiceException("Can't get doctor info by doctor id.", e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findAll();
        } catch (DaoException e){
            throw new ServiceException("Can't get doctor info by doctor id.", e);
        }
    }

    @Override
    public void saveUser(User user) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            userDao.save(user);
        } catch (DaoException e){
            throw new ServiceException("Can't save user.", e);
        }
    }

    @Override
    public void saveUser(User user, DoctorInfo doctorInfo) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(false, userDao);
            userDao.save(user);
            userDao.saveDoctorInfo(doctorInfo);
            transaction.commit();
        } catch (DaoException e){
            throw new ServiceException("Can't save user/doctor info.", e);
        }
    }
}
