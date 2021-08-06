package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.CourseDao;
import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private static final Logger LOGGER = Logger.getLogger(CourseService.class);
    private DaoHelperFactory daoHelperFactory;

    public CourseService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Course> getAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            return courseDao.getAll();
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Course> getLimit(int offset, int noOfRecords) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            return courseDao.getLimit(offset, noOfRecords);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Course> getCourseByName(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            return courseDao.getByName(name);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Course> getCoursesByUsername(String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            return courseDao.getStudentCoursesByUsername(username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateRequirement(String requirement, String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            courseDao.updateRequirement(requirement, name);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Course> getLimitByUsername(int offset, int recordsPerPage, String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            return courseDao.getLimitByUsername(offset, recordsPerPage, username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void createCourse(String courseName, String courseRequirement, String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CourseDao courseDao = daoHelper.createCourseDao();
            courseDao.createCourse(courseName, courseRequirement, username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
