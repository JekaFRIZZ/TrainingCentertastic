package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.dao.UserDao;
import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String username, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findUserByLoginAndPassword(username, password);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getAll();
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> getUserByUsername(String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getByUsername(username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getLimitByRole(Role role, int offset, int noOfRecords) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getLimitByRole(role, offset, noOfRecords);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getAllByRole(Role role) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getUsersByRole(role);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getStudentsByCourseName(String courseName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getStudentsByCourseName(courseName);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteUserByUsername(String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            userDao.deleteUserByUsername(username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void addStudent(String username, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            userDao.addStudent(username, password);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> getTeacherByUsername(String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.getTeacherByUsername(username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
