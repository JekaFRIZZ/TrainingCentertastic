package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.UserDao;
import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public Optional<User> login(String username, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(username, password);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> getUserByUsername(String username) throws ServiceException {
        try {
            return userDao.getByUsername(username);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getLimitByRole(Role role, int offset, int noOfRecords) throws ServiceException {
        try {
            return userDao.getLimitByRole(role, offset, noOfRecords);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getAllByRole(Role role) throws ServiceException {
        try {
            return userDao.getUsersByRole(role);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getStudentsByCourseName(String courseName) throws ServiceException {
        try {
            return userDao.getStudentsByCourseName(courseName);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteUserByUsername(String username) throws ServiceException {
        try {
            userDao.deleteUserByUsername(username);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void addStudent(String username, String password) throws ServiceException {
        try {
            userDao.addStudent(username, password);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
