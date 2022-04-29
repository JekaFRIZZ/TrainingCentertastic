package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperTest {

    private static final String ROLE = "role";
    private static final Long ID_EXPECTED = 1l;
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EXPECTED_USERNAME = "username";
    private static final String EXPECTED_PASSWORD = "password";
    private static final String EXPECTED_ROLE = "ADMIN";
    private ResultSet resultSetMock;

    @Before
    public void init() {
        resultSetMock = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testUserRowMapper() throws SQLException {

        Mockito.when(resultSetMock.getLong(ID)).thenReturn(1L);
        Mockito.when(resultSetMock.getString(USERNAME)).thenReturn(EXPECTED_USERNAME);
        Mockito.when(resultSetMock.getString(PASSWORD)).thenReturn(EXPECTED_PASSWORD);
        Mockito.when(resultSetMock.getString(ROLE)).thenReturn(EXPECTED_ROLE);

        UserMapper userMapper = new UserMapper();
        User user = userMapper.map(resultSetMock);

        Assert.assertEquals(user.getId(), ID_EXPECTED);
        Assert.assertEquals(user.getUsername(), EXPECTED_USERNAME);
        Assert.assertEquals(user.getPassword(), EXPECTED_PASSWORD);
        Assert.assertEquals(user.getRole(), Role.valueOf(EXPECTED_ROLE));
    }

}