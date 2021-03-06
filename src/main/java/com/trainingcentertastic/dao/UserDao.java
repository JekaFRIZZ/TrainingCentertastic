package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> implements Dao<User> {
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String CREATE = "INSERT INTO user (username, password, role) VALUE (?, ?, ?)";
    private static final String TABLE_NAME = "user";
    private static final String FIND_BY_ROLE = "SELECT * FROM user WHERE role = ?";
    private static final String GET_LIMIT_TEACHERS = "SELECT * FROM user WHERE role = ? LIMIT ?, ?";
    private static final String FIND_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    private static final String FIND_COURSE_BY_COURSE_NAME = "SELECT * FROM user WHERE username IN (SELECT username FROM course_users WHERE course_name = ?) AND role = 'STUDENT'";
    private static final String DELETE_FROM_USER_WHERE_USERNAME = "DELETE FROM user WHERE username = ?";
    private static final String GET_TEACHER = "SELECT * FROM user WHERE username = ? AND role = 'Teacher'";

    UserDao(ProxyConnection connection) {
        super(connection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserMapper(), login, password);
    }

    @Override
    protected void create(User item) throws DaoException {
        executeUpdate(CREATE, item.getUsername(), item.getPassword(), item.getRole().toString());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<User> getAll() throws DaoException {
        return super.getAll();
    }

    public List<User> getUsersByRole(Role role) throws DaoException {
        return executeQuery(FIND_BY_ROLE, new UserMapper(), role.toString());
    }

    public Optional<User> getByUsername(String username) throws DaoException {
        return executeForSingleResult(FIND_USER_BY_USERNAME, new UserMapper(), username);
    }


    public List<User> getLimitByRole(Role role, int offset, int noOfRecords) throws DaoException {
        return executeQuery(GET_LIMIT_TEACHERS, new UserMapper(), role.toString(), offset, noOfRecords);
    }

    public List<User> getStudentsByCourseName(String courseName) throws DaoException {
        return executeQuery(FIND_COURSE_BY_COURSE_NAME, new UserMapper(), courseName);
    }

    public void addStudent(String username, String password) throws DaoException {
        create(new User(username, password, Role.STUDENT));
    }

    public void deleteUserByUsername(String username) throws DaoException {
        executeUpdate(DELETE_FROM_USER_WHERE_USERNAME, username);
    }

    public Optional<User> getTeacherByUsername(String username) throws DaoException {
        return executeForSingleResult(GET_TEACHER, new UserMapper(), username);
    }

    public void addTeacher(String username, String password) throws DaoException {
        create(new User(username, password, Role.TEACHER));
    }
}
