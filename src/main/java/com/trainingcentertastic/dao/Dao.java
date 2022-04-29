package com.trainingcentertastic.dao;

import com.trainingcentertastic.entity.Identifiable;
import com.trainingcentertastic.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Common methods interface {@code AbstractDao}
 *
 * @author Eugene Lapko
 */
public interface Dao<T extends Identifiable> {
    /**
     * @return List of entity objects
     * @throws DaoException if query is invalid or database connection error
     */
    List<T> getAll() throws DaoException;
}