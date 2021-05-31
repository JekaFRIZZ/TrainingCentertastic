package com.trainingcentertastic.service;

import com.trainingcentertastic.dao.StudentDao;
import com.trainingcentertastic.entity.Student;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Optional<Student> getStudentById(Long id) throws ServiceException {
        try {
            return studentDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Student> getAll() throws ServiceException {
        try {
            return studentDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Student> getLearningStudentsById(Long id) throws ServiceException {
        try {
            return studentDao.getLearningStudentsById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Optional<Student> getStudentByUsername(String username) throws ServiceException {
        try {
            return studentDao.getByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Student> getStudentsByCourseName(String courseName) throws ServiceException {
        try {
            return studentDao.getByCourseName(courseName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Student> getLimit(int offset, int noOfRecords) throws ServiceException {
        try {
            return studentDao.getLimit(offset, noOfRecords);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
