package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.HomeworkDao;
import com.trainingcentertastic.entity.Homework;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import java.util.List;

public class HomeworkService {

    private final HomeworkDao homeworkDao;

    public HomeworkService(HomeworkDao homeworkDao) {
        this.homeworkDao = homeworkDao;
    }

    public List<Homework> getAllHomeworksStudentByUsername(String username, String nameCourse) throws ServiceException {
        try {
            return homeworkDao.getByUsername(username, nameCourse);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateMark(Long id, String username, int mark) throws ServiceException {
        try {
            homeworkDao.updateMark(id, username, mark);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateReview(Long id, String username, String review) throws ServiceException {
        try {
            homeworkDao.updateReview(id, username, review);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateLink(String taskName, String username, String link) throws ServiceException {
        try {
            homeworkDao.updateLink(taskName, username, link);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
