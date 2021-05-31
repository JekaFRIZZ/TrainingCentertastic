package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Student;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.StudentRowMapper;

import java.util.List;
import java.util.Optional;

public class StudentDao extends AbstractDao<Student> implements Dao<Student> {

    public static final String GET_STUDENT_BY_USERNAME = "SELECT s.user_id, u.username, s.review FROM student s INNER JOIN user u ON s.user_id=u.id WHERE u.username = ?";
    public static final String FIND_COURSE_BY_COURSE_NAME = "SELECT * FROM student WHERE username IN (SELECT username FROM course_users WHERE course_name = ?)";
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
    protected void update(Student item) throws DaoException {
    }

    @Override
    protected String getTableName() {
        return "student";
    }

    @Override
    public Optional<Student> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_STUDENT_BY_ID, new StudentRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

     public List<Student> getLearningStudentsById(Long id) throws DaoException {
        return executeQuery(GET_STUDENTS_BY_ID, new StudentRowMapper(), id);
    }

    public Optional<Student> getByUsername(String username) throws DaoException {
        return  executeForSingleResult(GET_STUDENT_BY_USERNAME, new StudentRowMapper(), username);
    }

    public List<Student> getByCourseName(String courseName) throws DaoException {
        return executeQuery(FIND_COURSE_BY_COURSE_NAME, new StudentRowMapper(), courseName);
    }

    public List<Student> getLimit(int offset, int noOfRecords) throws DaoException {
        return executeQuery("SELECT * FROM student LIMIT ?, ?", new StudentRowMapper(), offset, noOfRecords);
    }
}
