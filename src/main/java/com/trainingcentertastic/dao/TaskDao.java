package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.TaskMapper;

import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDao<Task> implements Dao<Task> {

    private static final String CREATE = "INSERT INTO task (task_name, course_name, assignment) VALUES (?, ?, ?)";
    private static final String FIND_TASKS_BY_COURSE_NAME = "SELECT * FROM task WHERE course_name = ?";
    private static final String FIND_TASK_BY_TACK_NAME_AND_COURSE_NAME = "SELECT * FROM task WHERE task_name = ? AND  course_name = ?";
    private static final String TABLE_NAME = "task";

    protected TaskDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Optional<Task> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(Task items) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void create(Task item) throws DaoException {
        executeUpdate(CREATE, item.getTaskName(), item.getCourseName(), item.getAssignment());
    }

    @Override
    protected void update(Task item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Task> getAll() throws DaoException {
        return super.getAll();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Task> getTasksByCourseName(String courseName) throws DaoException {
        return executeQuery(FIND_TASKS_BY_COURSE_NAME, new TaskMapper(), courseName);
    }

    public Optional<Task> getByName(String taskName, String nameCourse) throws DaoException {
        return executeForSingleResult(FIND_TASK_BY_TACK_NAME_AND_COURSE_NAME, new TaskMapper(), taskName, nameCourse);
    }

    public void createTask(String taskName, String nameCourse, String assignment) throws DaoException {
        create(new Task(taskName, nameCourse, assignment));
    }
}
