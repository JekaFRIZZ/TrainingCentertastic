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
     * Searches for an entity class by id
     * @param id entity class id
     * @return Optional object if the class entity with a specific id exists or returns Optional.empty()
     * @throws DaoException if query is invalid or database connection error
     */
    Optional<T> getById(Long id) throws DaoException;

    /**
     * Saves entity class to database
     *
     * @param items which need to be saved
     * @throws DaoException if query is invalid or database connection error
     */
    void save(T items) throws DaoException;

    /**
     *
     * @return List of entity objects
     * @throws DaoException if query is invalid or database connection error
     */
    List<T> getAll() throws DaoException;

    /**
     * Delete an entity class by id
     *
     * @param id entity class id
     * @throws DaoException if query is invalid or database connection error
     */
    void removeById(Long id) throws DaoException;
}
