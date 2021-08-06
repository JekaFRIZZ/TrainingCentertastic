package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.CourseUsersDao;
import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.entity.CourseUsers;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CourseUsersService {
    private static final Logger LOGGER = Logger.getLogger(CourseUsersService.class);
    private DaoHelperFactory daoHelperFactory;

    public CourseUsersService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<CourseUsers> checkSubmit(String nameCourse, String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseUsersDao courseUsersDao = daoHelper.createCourseUsersDao();
            return courseUsersDao.getByNameCourseByUsername(nameCourse, username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateSubmit(String courseName, String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseUsersDao courseUsersDao = daoHelper.createCourseUsersDao();
            courseUsersDao.createSubmit(courseName, username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<CourseUsers> getAllTeacher() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseUsersDao courseUsersDao = daoHelper.createCourseUsersDao();
            return courseUsersDao.getAllTeachers();
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
