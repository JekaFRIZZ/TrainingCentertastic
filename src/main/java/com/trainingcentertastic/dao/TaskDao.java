package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.TaskMapper;

import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDao<Task> implements Dao<Task> {

    public static final String FIND_TASKS_BY_COURSE_NAME = "SELECT * FROM task WHERE course_name = ?";
    public static final String FIND_TASK_BY_TACK_NAME_AND_COURSE_NAME = "SELECT * FROM task WHERE task_name = ? AND  course_name = ?";

    protected TaskDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Optional<Task> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(Task items) throws DaoException {

    }

    @Override
    protected void create(Task item) throws DaoException {

    }

    @Override
    protected void update(Task item) throws DaoException {

    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public List<Task> getAll() throws DaoException {
        return null;
    }

    @Override
    public void removeById(Long id) throws DaoException {

    }

    public List<Task> getTasksByCourseName(String courseName) throws DaoException {
        return executeQuery(FIND_TASKS_BY_COURSE_NAME, new TaskMapper(), courseName);
    }

    public Optional<Task> getByName(String taskName, String nameCourse) throws DaoException {
        return executeForSingleResult(FIND_TASK_BY_TACK_NAME_AND_COURSE_NAME, new TaskMapper(), taskName, nameCourse);
    }
}
