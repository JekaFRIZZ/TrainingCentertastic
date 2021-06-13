package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Homework;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.HomeworkMapper;

import java.util.List;
import java.util.Optional;

public class HomeworkDao extends AbstractDao<Homework> implements Dao<Homework> {

    private static final String FIND_HOMEWORK_BY_USERNAME = "SELECT * FROM homework WHERE username = ? AND course_name = ?";
    private static final String UPDATE_MARK = "UPDATE homework SET mark = ? WHERE id = ? AND username = ?";
    private static final String UPDATE_REVIEW = "UPDATE homework SET review = ? WHERE id = ? AND username = ?";
    private static final String UPDATE_LINK = "UPDATE homework SET link = ? WHERE task_name = ? AND username = ?";

    protected HomeworkDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Homework item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void update(Homework item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Homework> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Homework> getByUsername(String username, String nameCourse) throws DaoException {
        return executeQuery(FIND_HOMEWORK_BY_USERNAME, new HomeworkMapper(), username, nameCourse);
    }

    public void updateMark(Long id, String username, int mark) throws DaoException {
        executeUpdate(UPDATE_MARK, mark, id, username);
    }

    public void updateReview(Long id, String username, String review) throws DaoException {
        executeUpdate(UPDATE_REVIEW, review, id, username);
    }

    public void updateLink(String taskName, String username, String link) throws DaoException {
        executeUpdate(UPDATE_LINK, link, taskName, username);
    }
}
