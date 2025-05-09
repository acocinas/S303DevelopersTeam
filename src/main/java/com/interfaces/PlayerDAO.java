package com.interfaces;

import java.util.List;
import com.dao.exception.DAOException;
import com.model.Player;

public interface PlayerDAO extends GenericDAO<Player, Integer> {
    List<Player> findByName(String name) throws DAOException;
    List<Player> findTopPlayers(int limit) throws DAOException;
}
