package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.TaskDao;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private static final Logger LOGGER = Logger.getLogger(TaskService.class);
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getTasksByCourseName(String courseName) throws ServiceException {
        try {
            return taskDao.getTasksByCourseName(courseName);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Optional<Task> getTaskByName(String taskName, String courseName) throws ServiceException {
        try {
            return taskDao.getByName(taskName, courseName);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void createTask(String taskName, String nameCourse, String assignment) throws ServiceException {
        try {
            taskDao.createTask(taskName, nameCourse, assignment);
        } catch (DaoException e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
