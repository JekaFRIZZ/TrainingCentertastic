package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.dao.UserDao;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    public static final String CORRECT_USERNAME = "student1";
    private DaoHelper daoHelperMock;
    private DaoHelperFactory daoHelperFactoryMock;
    private UserService userService;
    private UserDao userDaoMock;
    private User userMock;

    @Before
    public void init() {
        daoHelperMock = Mockito.mock(DaoHelper.class);
        daoHelperFactoryMock = Mockito.mock(DaoHelperFactory.class);
        userDaoMock = Mockito.mock(UserDao.class);
        userMock = Mockito.mock(User.class);
        when(daoHelperFactoryMock.create()).thenReturn(daoHelperMock);
        when(daoHelperMock.createUserDao()).thenReturn(userDaoMock);
        userService = new UserService(userDaoMock);
    }

    @Test
    public void testGetUserByUsernameShouldReturnUser() throws DaoException, ServiceException {
        when(userDaoMock.getByUsername(anyString())).thenReturn(Optional.of(userMock));
        User user = userService.getUserByUsername(CORRECT_USERNAME).get();
        Assert.assertNotNull(user);
    }
}