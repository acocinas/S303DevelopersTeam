package com.interfaces;

import java.util.List;
import java.util.Optional;
import com.dao.exception.DAOException;

public interface GenericDAO<T, ID> {
    T create(T entity) throws DAOException;
    Optional<T> findById(ID id) throws DAOException;
    List<T> findAll() throws DAOException;
    T update(T entity) throws DAOException;
    void delete(T entity) throws DAOException;
    void deleteById(ID id) throws DAOException;
}
