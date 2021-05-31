package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.UserRowMapper;

import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> implements Dao<User> {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String CREATE = "INSERT INTO user (username, password, role) VALUE (?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET username = ?, password = ?, role = ? where id = ?";
    private static final String GET_ALL = "SELECT * FROM user";
    private static final String TABLE_NAME = "user";
    private static final String REMOVE_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_BY_ROLE = "SELECT * FROM user WHERE role = ?";
    private static final String FIND_REVIEW = "SELECT * FROM student INNER JOIN user ON student.user_id=user.id  WHERE user_id = ?";

    UserDao(ProxyConnection connection) {
        super(connection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    protected void create(User item) throws DaoException {
        executeUpdate(CREATE, item.getUsername(), item.getPassword(), item.getRole().toString());
    }

    @Override
    protected void update(User item) throws DaoException {
        Optional<User> userUpdate = getById(item.getId());
        if(!userUpdate.isPresent()) {
            throw new DaoException("User " + item.getId() + " not found");
        }
        executeUpdate(UPDATE, item.getUsername(), item.getPassword(), item.getRole().toString(), item.getId());
    }


    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }


    @Override
    public Optional<User> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(GET_ALL, new UserRowMapper());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

    public List<User> getUsersByRole(Role role) throws DaoException {
        return executeQuery(FIND_BY_ROLE, new UserRowMapper(), role.toString());
    }
}
