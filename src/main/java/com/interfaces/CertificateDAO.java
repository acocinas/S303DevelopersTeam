package com.interfaces;

import java.util.List;
import com.dao.exception.DAOException;
import com.model.Certificate;

public interface CertificateDAO extends GenericDAO<Certificate, Integer> {
    List<Certificate> findByPlayerId(Integer playerId) throws DAOException;
    List<Certificate> findByRoomId(Integer roomId) throws DAOException;
}
