package com.epam.hospital.service.logic.impl;

import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.logic.UserService;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.service.validator.impl.UserValidatorImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static UserService instance = new UserServiceImpl();
    private static final UserValidatorImpl userValidator = new UserValidatorImpl();


    public static UserService getInstance(){
        return instance;
    }

    @Override
    public User login(String email, String password) throws ServiceException {
        if(!userValidator.isValidOfInjectionAttack(email) ||
                !userValidator.isValidEmail(email) ||
                !userValidator.isValidOfInjectionAttack(password) ||
                !userValidator.isValidPassword(password)){
            throw new ServiceException("Invalid email, password data.");
        }
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
        if(!userValidator.isValidOfInjectionAttack(email)){
            throw new ServiceException("Invalid email data.");
        }
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
    public boolean isUserExistByLogin(String email) throws ServiceException {
        if(!userValidator.isValidOfInjectionAttack(email)){
            throw new ServiceException("Invalid email data.");
        }
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.isUserExist(email);
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
        if(!userValidator.isValidOfInjectionAttack(firstName) || !userValidator.isValidOfInjectionAttack(lastName)){
            throw new ServiceException("Invalid first, last name data.");
        }
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
    public Integer getUserRoleId() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.getUserRoleId();
        } catch (DaoException e){
            throw new ServiceException("Can't get doctor info by doctor id.", e);
        }
    }

    @Override
    public void saveUser(User user) throws ServiceException {
        if(!userValidator.isValid(user)){
            throw new ServiceException("Invalid user data.");
        }
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            userDao.save(user);
        } catch (DaoException e){
            throw new ServiceException("Can't save user.", e);
        }
    }
}
