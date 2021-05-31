package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.CourseDao;
import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class CourseService {

    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> getAll() throws ServiceException {
        try {
            return  courseDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Course> getLimit(int offset, int noOfRecords) throws ServiceException {
        try {
            return courseDao.getLimit(offset, noOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Course> getCourseById(Long id) throws ServiceException {
        try {
            return courseDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Course> getCourseByName(String name) throws ServiceException {
        try {
            return courseDao.getByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Course> getCoursesByUsername(String username) throws ServiceException {
        try {
            return courseDao.getStudentCoursesByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateRequirement(String requirement,String name) throws ServiceException {
        try {
            courseDao.updateRequirement(requirement, name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
