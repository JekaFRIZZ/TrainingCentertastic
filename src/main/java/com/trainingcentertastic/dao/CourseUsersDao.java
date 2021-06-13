package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.CourseUsers;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.CourseUsersMapper;

import java.util.List;
import java.util.Optional;

public class CourseUsersDao extends AbstractDao<CourseUsers> implements Dao<CourseUsers> {

    private static final String FIND_COURSE_BY_COURSE_NAME_AND_USERNAME = "SELECT * FROM course_users WHERE course_name = ? AND username = ?";
    private static final String CREATE_SUBMIT = "INSERT INTO course_users (course_name, username) VALUES (?,?)";
    private static final String GET_ALL_TEACHERS = "SELECT * FROM course_users WHERE username IN (SELECT username FROM user WHERE role = 'TEACHER') GROUP BY username";

    protected CourseUsersDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(CourseUsers item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(CourseUsers item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CourseUsers> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public Optional<CourseUsers> getByNameCourseByUsername(String nameCourse, String username) throws DaoException {
        return executeForSingleResult(FIND_COURSE_BY_COURSE_NAME_AND_USERNAME,
                new CourseUsersMapper(), nameCourse, username);
    }

    public void createSubmit(String courseName, String username) throws DaoException {
        executeUpdate(CREATE_SUBMIT, courseName, username);
    }

    public List<CourseUsers> getAllTeachers() throws DaoException {
        return executeQuery(GET_ALL_TEACHERS, new CourseUsersMapper());
    }
}
