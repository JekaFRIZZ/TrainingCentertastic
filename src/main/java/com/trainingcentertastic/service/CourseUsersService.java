package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.CourseUsersDao;
import com.trainingcentertastic.entity.CourseUsers;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class CourseUsersService {

    private final CourseUsersDao courseUsersDao;

    public CourseUsersService(CourseUsersDao courseUsersDao) {
        this.courseUsersDao = courseUsersDao;
    }

    public Optional<CourseUsers> checkSubmit(String nameCourse, String username) throws ServiceException {
        try {
            return courseUsersDao.getByNameCourseByUsername(nameCourse, username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateSubmit(String courseName, String username) throws ServiceException {
        try {
            courseUsersDao.createSubmit(courseName, username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<CourseUsers> getAllTeacher() throws ServiceException {
        try {
            return courseUsersDao.getAllTeachers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<CourseUsers> getLimitByTeacher(int offset, int noOfRecords) throws ServiceException {
        try {
            return courseUsersDao.getLimitByTeacher(offset, noOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
