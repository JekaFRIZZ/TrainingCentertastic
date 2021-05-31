package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.CourseUsers;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.CourseUsersMapper;

import java.util.List;
import java.util.Optional;

public class CourseUsersDao extends AbstractDao<CourseUsers> implements Dao<CourseUsers> {

    public static final String FIND_COURSE_BY_COURSE_NAME_AND_USERNAME = "SELECT * FROM course_users WHERE course_name = ? AND username = ?";
    public static final String CREATE_SUBMIT = "INSERT INTO course_users (course_name, username) VALUES (?,?)";
    public static final String GET_ALL_TEACHERS = "SELECT * FROM course_users WHERE username IN (SELECT username FROM user WHERE role = 'TEACHER') GROUP BY username";
    public static final String GET_LIMIT_TEACHERS = "SELECT u.id, c.username, c.course_name FROM user u INNER JOIN course_users c on u.username=c.username WHERE u.role='TEACHER' GROUP BY username LIMIT ?, ?";

    protected CourseUsersDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(CourseUsers item) throws DaoException {

    }

    @Override
    public void update(CourseUsers item) throws DaoException {
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<CourseUsers> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {

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

    public List<CourseUsers> getLimitByTeacher(int offset, int noOfRecords) throws DaoException {
        return executeQuery(GET_LIMIT_TEACHERS, new CourseUsersMapper(), offset, noOfRecords);
    }
}
