package com.trainingcentertastic.dao;

import com.trainingcentertastic.entity.Identifiable;
import com.trainingcentertastic.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    Optional<T> getById(Long id) throws DaoException;
    void save(T items) throws DaoException;
    List<T> getAll() throws DaoException;
    void removeById(Long id) throws DaoException;
}
