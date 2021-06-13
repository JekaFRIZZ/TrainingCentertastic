package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.CourseUsers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseMapperTest {
    public static final Long EXPECTED_ID = 1L;
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String COURSE_NAME = "course_name";
    public static final String EXPECTED_USERNAME = "username";
    public static final String EXPECTED_COURSE_NAME = "Java";
    private ResultSet resultSetMock;

    @Before
    public void init() {
        resultSetMock = Mockito.mock(ResultSet.class);
    }

    @Test
    public void courseUsersMapperTest() throws SQLException {
        Mockito.when(resultSetMock.getLong(ID)).thenReturn(1L);
        Mockito.when(resultSetMock.getString(USERNAME)).thenReturn(EXPECTED_USERNAME);
        Mockito.when(resultSetMock.getString(COURSE_NAME)).thenReturn(EXPECTED_COURSE_NAME);

        CourseUsersMapper courseUsersMapper = new CourseUsersMapper();
        CourseUsers courseUsers = courseUsersMapper.map(resultSetMock);

        Assert.assertEquals(courseUsers.getId(), EXPECTED_ID);
        Assert.assertEquals(courseUsers.getUsername(), EXPECTED_USERNAME);
        Assert.assertEquals(courseUsers.getCourseName(), EXPECTED_COURSE_NAME);
    }

}