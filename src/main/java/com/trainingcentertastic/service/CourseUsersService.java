package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.CourseUsersDao;
import com.trainingcentertastic.entity.CourseUsers;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CourseUsersService {
    private static final Logger LOGGER = Logger.getLogger(CourseUsersService.class);
    private final CourseUsersDao courseUsersDao;

    public CourseUsersService(CourseUsersDao courseUsersDao) {
        this.courseUsersDao = courseUsersDao;
    }

    public Optional<CourseUsers> checkSubmit(String nameCourse, String username) throws ServiceException {
        try {
            return courseUsersDao.getByNameCourseByUsername(nameCourse, username);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateSubmit(String courseName, String username) throws ServiceException {
        try {
            courseUsersDao.createSubmit(courseName, username);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<CourseUsers> getAllTeacher() throws ServiceException {
        try {
            return courseUsersDao.getAllTeachers();
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
