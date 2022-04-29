package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Homework;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeworkMapperTest {

    public static final String ID = "id";
    public static final Long EXPECTED_ID = 1l;
    public static final String TASK_NAME = "task_name";
    public static final String EXPECTED_TASK_NAME = "taskName";
    public static final String EXPECTED_USERNAME = "username";
    public static final String USERNAME = "username";
    public static final String COURSE_NAME = "course_name";
    public static final String JAVA = "Java";
    public static final String EXPECTED_COURSE_NAME = JAVA;
    public static final String LINK = "link";
    public static final String EXPECTED_LINK = "github.com";
    public static final String MARK = "mark";
    public static final int EXPECTED_MARK = 10;
    public static final String REVIEW = "review";
    public static final String EXPECTED_REVIEW = "no mistakes";
    private ResultSet resultSetMock;

    @Before
    public void init() {
        resultSetMock = Mockito.mock(ResultSet.class);
    }

    @Test
    public void homeworkMapperTest() throws SQLException {
        Mockito.when(resultSetMock.getLong(ID)).thenReturn(EXPECTED_ID);
        Mockito.when(resultSetMock.getString(TASK_NAME)).thenReturn(EXPECTED_TASK_NAME);
        Mockito.when(resultSetMock.getString(USERNAME)).thenReturn(EXPECTED_USERNAME);
        Mockito.when(resultSetMock.getString(COURSE_NAME)).thenReturn(EXPECTED_COURSE_NAME);
        Mockito.when(resultSetMock.getString(LINK)).thenReturn(EXPECTED_LINK);
        Mockito.when(resultSetMock.getInt(MARK)).thenReturn(EXPECTED_MARK);
        Mockito.when(resultSetMock.getString(REVIEW)).thenReturn(EXPECTED_REVIEW);

        HomeworkMapper homeworkMapper = new HomeworkMapper();
        Homework homework = homeworkMapper.map(resultSetMock);

        Assert.assertEquals(homework.getId(), EXPECTED_ID);
        Assert.assertEquals(homework.getTaskName(), EXPECTED_TASK_NAME);
        Assert.assertEquals(homework.getUsername(), EXPECTED_USERNAME);
        Assert.assertEquals(homework.getCourseName(), EXPECTED_COURSE_NAME);
        Assert.assertEquals(homework.getLink(), EXPECTED_LINK);
        Assert.assertEquals(homework.getMark(), EXPECTED_MARK);
        Assert.assertEquals(homework.getReview(), EXPECTED_REVIEW);
    }

}