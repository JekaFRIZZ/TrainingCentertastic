package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.TaskDao;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskDao taskDao;


    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getTasksByCourseName(String courseName) throws ServiceException {
        try {
            return taskDao.getTasksByCourseName(courseName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Optional<Task> getTaskByName(String taskName, String courseName) throws ServiceException {
        try {
            return taskDao.getByName(taskName, courseName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
