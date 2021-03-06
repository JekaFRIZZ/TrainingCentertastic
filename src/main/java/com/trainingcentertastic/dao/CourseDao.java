package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.mapper.CourseMapper;

import java.util.List;
import java.util.Optional;

public class CourseDao extends AbstractDao<Course> implements Dao<Course> {

    private static final String FIND_COURSE_BY_USERNAME = "SELECT * FROM course WHERE name IN (SELECT course_name FROM course_users WHERE username = ?)";
    private static final String UPDATE_REQUIREMENT = "UPDATE course SET requirement = ? WHERE name = ?";
    private static final String GET_COURSE_BY_USERNAME_LIMIT = "SELECT * FROM course WHERE name IN (SELECT course_name FROM course_users WHERE username = ?) LIMIT ?, ?";
    private static final String TABLE_NAME = "course";
    private static final String CREATE = "INSERT INTO course (name, requirement, username) VALUES (?, ?, ?)";
    private static final String FIND_TAUGHT_COURSES = "SELECT * FROM course WHERE username = ?";
    private static final String ADD_TEACHER = "INSERT INTO course_users (course_name, username) VALUES (?, ?)";
    private final String GET_LIMIT = "SELECT * FROM course LIMIT ?, ?";
    private final String FIND_COURSE_BY_NAME = "SELECT * FROM course WHERE name = ?";

    protected CourseDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Course item) throws DaoException {
        executeUpdate(CREATE, item.getName(), item.getRequirement(), item.getUsername());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    public List<Course> getAll() throws DaoException {
        return super.getAll();
    }

    public List<Course> getLimit(int offset, int noOfRecords) throws DaoException {
        return executeQuery(GET_LIMIT, new CourseMapper(), offset, noOfRecords);
    }

    public Optional<Course> getByName(String name) throws DaoException {
        return executeForSingleResult(FIND_COURSE_BY_NAME, new CourseMapper(), name);
    }

    public List<Course> getStudentCoursesByUsername(String username) throws DaoException {
        return executeQuery(FIND_COURSE_BY_USERNAME, new CourseMapper(), username);
    }

    public void updateRequirement(String requirement, String name) throws DaoException {
        executeUpdate(UPDATE_REQUIREMENT, requirement, name);
    }

    public List<Course> getLimitByUsername(int offset, int recordsPerPage, String username) throws DaoException {
        return executeQuery(GET_COURSE_BY_USERNAME_LIMIT, new CourseMapper(), username, offset, recordsPerPage);
    }

    public void createCourse(String courseName, String courseRequirement, String username) throws DaoException {
        create(new Course(courseName, courseRequirement, username));
    }

    public List<Course> getTaughtTeacherCoursesByUsername(String username) throws DaoException {
        return executeQuery(FIND_TAUGHT_COURSES, new CourseMapper(), username);
    }

    public void addTeacher(String courseName, String username) throws DaoException {
        executeUpdate(ADD_TEACHER, courseName, username);
    }
}
