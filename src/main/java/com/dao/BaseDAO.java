package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.interfaces.GenericDAO;
import com.dao.exception.DAOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseDAO<T, ID> implements GenericDAO<T, ID> {
    protected final Map<ID, T> entities = new HashMap<>();
    protected AtomicInteger idSequence = new AtomicInteger(1);
    
    @Override
    public List<T> findAll() throws DAOException {
        log.debug("Finding all entities");
        return new ArrayList<>(entities.values());
    }
    
    @Override
    public Optional<T> findById(ID id) throws DAOException {
        log.debug("Finding entity with id: {}", id);
        return Optional.ofNullable(entities.get(id));
    }
    
    @Override
    public void delete(T entity) throws DAOException {
        ID id = getEntityId(entity);
        if (id != null) {
            log.debug("Deleting entity with id: {}", id);
            entities.remove(id);
        }
    }
    
    @Override
    public void deleteById(ID id) throws DAOException {
        log.debug("Deleting entity with id: {}", id);
        entities.remove(id);
    }
    
    protected abstract ID getEntityId(T entity);
    protected abstract void setEntityId(T entity, ID id);
}
