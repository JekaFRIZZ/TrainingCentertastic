package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Student;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.CourseMapper;
import com.trainingcentertastic.mapper.StudentRowMapper;

import java.util.List;
import java.util.Optional;

public class StudentDao extends AbstractDao<Student> implements Dao<Student> {

    private final String GET_ALL = "SELECT * FROM student";
    private final String FIND_STUDENT_BY_ID = "SELECT * FROM student WHERE user_id = ?";
    private final String GET_STUDENTS_BY_ID = "SELECT * FROM student s INNER JOIN course_users c ON s.user_id=c.user_id WHERE c.course_id = ?";

    protected StudentDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Student item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<Student> update(Student item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<Student> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_STUDENT_BY_ID, new StudentRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Student> getAll() throws DaoException {
        return executeQuery(GET_ALL, new StudentRowMapper());
    }
     public List<Student> getLearningStudentsById(Long id) throws DaoException {
        return executeQuery(GET_STUDENTS_BY_ID, new StudentRowMapper(), id);
    }

}
