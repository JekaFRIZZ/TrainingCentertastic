package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.UserDao;
import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public Optional<User> login(String username, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
