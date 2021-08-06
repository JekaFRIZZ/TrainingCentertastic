package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.dao.HomeworkDao;
import com.trainingcentertastic.entity.Homework;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class HomeworkService {
    private static final Logger LOGGER = Logger.getLogger(HomeworkService.class);
    private DaoHelperFactory daoHelperFactory;

    public HomeworkService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Homework> getAllHomeworksStudentByUsername(String username, String nameCourse) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            HomeworkDao homeworkDao = daoHelper.createHomeworkDao();
            return homeworkDao.getByUsername(username, nameCourse);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateMark(Long id, String username, int mark) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            HomeworkDao homeworkDao = daoHelper.createHomeworkDao();
            homeworkDao.updateMark(id, username, mark);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateReview(Long id, String username, String review) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            HomeworkDao homeworkDao = daoHelper.createHomeworkDao();
            homeworkDao.updateReview(id, username, review);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateLink(String taskName, String username, String link) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            HomeworkDao homeworkDao = daoHelper.createHomeworkDao();
            homeworkDao.updateLink(taskName, username, link);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Homework> getHomeworkStudent(String taskName, String username) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            HomeworkDao homeworkDao = daoHelper.createHomeworkDao();
            return homeworkDao.getByTaskName(taskName, username);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void createHomework(String taskName, String username, String nameCourse) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            HomeworkDao homeworkDao = daoHelper.createHomeworkDao();
            homeworkDao.createHomework(taskName, username, nameCourse);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
