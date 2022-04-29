package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.DaoHelper;
import com.trainingcentertastic.dao.DaoHelperFactory;
import com.trainingcentertastic.dao.TaskDao;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private static final Logger LOGGER = Logger.getLogger(TaskService.class);
    private DaoHelperFactory daoHelperFactory;

    public TaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Task> getTasksByCourseName(String courseName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TaskDao taskDao = daoHelper.createTaskDao();
            return taskDao.getTasksByCourseName(courseName);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Task> getTaskByName(String taskName, String courseName) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TaskDao taskDao = daoHelper.createTaskDao();
            return taskDao.getByName(taskName, courseName);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void createTask(String taskName, String nameCourse, String assignment) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            TaskDao taskDao = daoHelper.createTaskDao();
            taskDao.createTask(taskName, nameCourse, assignment);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
