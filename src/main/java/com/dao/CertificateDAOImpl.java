package com.dao;

import java.util.List;

import com.interfaces.CertificateDAO;
import com.dao.exception.DAOException;
import com.model.Certificate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CertificateDAOImpl extends BaseDAO<Certificate, Integer> implements CertificateDAO {
    
    @Override
    public Certificate create(Certificate entity) throws DAOException {
        Integer id = idSequence.getAndIncrement();
        setEntityId(entity, id);
        entities.put(id, entity);
        log.info("Created certificate with id: {}", id);
        return entity;
    }

    @Override
    public Certificate update(Certificate entity) throws DAOException {
        Integer id = getEntityId(entity);
        if (id != null && entities.containsKey(id)) {
            entities.put(id, entity);
            log.info("Updated certificate with id: {}", id);
            return entity;
        }
        log.error("Certificate not found for update: {}", id);
        throw new DAOException("Certificate not found for update: " + id);
    }

    @Override
    public List<Certificate> findByPlayerId(Integer playerId) throws DAOException {
        log.debug("Finding certificates by player id: {}", playerId);
        return entities.values().stream()
                .filter(cert -> cert.getPlayer().getId() == playerId)
                .toList();
    }

    @Override
    public List<Certificate> findByRoomId(Integer roomId) throws DAOException {
        log.debug("Finding certificates by room id: {}", roomId);
        
        final String roomIdStr = String.valueOf(roomId);
        
        return entities.values().stream()
                .filter(cert -> cert.getRoomName() != null && cert.getRoomName().contains(roomIdStr))
                .toList();
    }

    @Override
    protected Integer getEntityId(Certificate entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Certificate entity, Integer id) {
        if (id != null) {
            entity.setId(id);
        }
    }
}
