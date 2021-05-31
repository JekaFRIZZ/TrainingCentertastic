package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.CourseMapper;

import java.util.List;
import java.util.Optional;

public class CourseDao extends AbstractDao<Course> implements Dao<Course> {

    public static final String FIND_COURSE_BY_NAME_STUDENT = "SELECT * FROM course WHERE name IN (SELECT course_name FROM course_users WHERE username = ?)";
    public static final String UPDATE_REQUIREMENT = "UPDATE course SET requirement = ? WHERE name = ?";
    private final String GET_ALL = "SELECT * FROM course";
    private final String GET_LIMIT = "SELECT * FROM course LIMIT ?, ?";
    private final String FIND_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
    private final String FIND_COURSE_BY_NAME = "SELECT * FROM course WHERE name = ?";


    protected CourseDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Course item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void update(Course item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<Course> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_COURSE_BY_ID, new CourseMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Course> getAll() throws DaoException {
        return executeQuery(GET_ALL, new CourseMapper());
    }

    public List<Course> getLimit(int offset, int noOfRecords) throws DaoException {
        return executeQuery(GET_LIMIT, new CourseMapper(), offset, noOfRecords);
    }

    public Optional<Course> getByName(String name) throws DaoException {
        return executeForSingleResult(FIND_COURSE_BY_NAME, new CourseMapper(), name);
    }

    public List<Course> getStudentCoursesByUsername(String username) throws DaoException {
            return executeQuery(FIND_COURSE_BY_NAME_STUDENT, new CourseMapper(), username);
    }

    public void updateRequirement(String requirement, String name) throws DaoException {
        executeUpdate(UPDATE_REQUIREMENT, requirement, name);
    }
}
